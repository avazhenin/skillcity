export default function ({app, $axios, store, env}, inject) {
  // Here specify https://skillcity.kz/api
  // But Nginx will forward to inner http://localhost:8888/api
  $axios.setBaseURL(env.api_base_url + '/api')

  let LocalStoragetoken = store.getters['store/getToken']

  if (LocalStoragetoken) {
    $axios.setToken(LocalStoragetoken, 'Bearer')
    store.commit('store/set_admin', true)
  }

  function showError(error) {
    if (error.response) {
      store.dispatch('constants/setErrorCode', error.response.data.error)
      if (error.response.data.cause) store.dispatch('constants/setErrorMessage', error.response.data.cause.message)
      if (!error.response.data.cause) store.dispatch('constants/setErrorMessage', error.response.data.message)
      store.dispatch('constants/setShowSnackbar', true)
      store.dispatch('constants/setSnackbarDur', 10 * 1000)
      if (error.response.status === 401) {
        if (LocalStoragetoken) store.commit('store/set_token', undefined);
        forward2LoginPage()
      }
    } else {
      store.dispatch('constants/setErrorMessage', error)
      store.dispatch('constants/setShowSnackbar', true)
    }

    throw error;
  }

  inject('showError', showError)

  const setAdmin = async (username, password, rememberMe) => {

    let token = localStorage.getItem('token');
    if (!token) {
      token = await $axios.post('auth',
        {
          'username': username,
          'password': password,
          'rememberMe': rememberMe
        })
        .then(function (response) {
            return response.data.id_token;
          }
        ).catch(function (error) {
          showError(error)
        })
      if (token != null) {
        store.commit('store/set_token', token);
      } else {
        console.log(response.data.id_token)
      }
    } else {
      store.commit('store/set_admin', true);
    }
    $axios.setToken(store.getters['store/getToken'], 'Bearer') // set axios configuration
    app.router.push('/')
  }

  inject('setAdmin', setAdmin);

  const forward2LoginPage = () => {
    store.commit('store/set_admin', false)
    app.router.push("/login")
  }

  inject('forwardToLogin', forward2LoginPage)

  inject('upload_page', (name, dscr, image, page_type) => {
    let formData = new FormData()
    formData.append("name", name);
    formData.append("dscr", dscr);
    if (image) formData.append("image", image);
    formData.append("page_type", page_type);

    const page = $axios.post('/page/upload', formData)
      .then(response => {
        store.dispatch(page_type + '/addPage', response.data);
      })
      .catch((error) => {
        showError(error)
      });
  })

  inject('edit_page', (id, name, dscr, image, old_image, page_type) => {
    let formData = new FormData()
    formData.append("id", id);
    formData.append("name", name);
    formData.append("dscr", dscr);
    if (image) formData.append("image", image);
    formData.append("page_type", page_type);

    let page = {
      'id': id,
      'name': name,
      'dscr': dscr,
      'image': null, // change it , when post request is done
      'page_type': page_type
    }

    const res = $axios.post('/page/upload', formData)
      .then(response => {
        page.image = response.data.image // set newly added picture
        store.dispatch(page_type + '/editPage', response.data);
        return 0;
      })
      .catch((error) => {
        showError(error)
      });
    return res;
  })

  inject('delete_page', (page) => {
    $axios.post('/page/delete', null, {params: {'id': page.id}})
      .then(response => {
        store.dispatch(page.page_type + '/deletePage', page);
      })
      .catch(error => {
        showError(error)
      });
  })

  inject('get_all', (page_type) => {
    $axios.get("/page/getall", {params: {'page_type': page_type}})
      .then((response) => {
        store.dispatch(page_type + '/fillPages', response.data);
      })
      .catch((error) => {
        showError(error)
      });
  })

  // добавляем новый контент
  inject('content_add', (page, content, content_type) => {
    let formData = new FormData()
    formData.append("id", page.id);

    if (content_type == env.page_content_type.picture)
      formData.append("file", content);
    else formData.append("text", content);

    formData.append("content_type", content_type);

    const result = $axios.post('/page/content/add_content', formData)
      .then(response => {
        store.dispatch(page.page_type + '/addPageContent', response.data);
        return 0
      })
      .catch(error => {
        showError(error)
      })

    return result
  })

  // изменяем текст и сохраняем
  inject('content_edit_data', (page, page_content, file) => {
    let formData = new FormData()
    // пробежимся по объекту и заполним данные формы
    for (var key in page_content) {
      formData.append(key, page_content[key]);
    }
    if (page_content.content_type == env.page_content_type.picture) formData.append('file', file);

    const result = $axios.post('/page/content/edit_data', formData)
      .then(response => {
        store.dispatch(page.page_type + '/setPageContent', response.data);
        return 0
      })
      .catch(error => {
        showError(error)
      })

    return result
  })

  inject('content_delete', (content, page_type) => {
    $axios({
      method: 'POST',
      url: '/page/content/delete',
      params: {'id': content.id}
    })
      .then(response => {
        store.dispatch(page_type + '/deletePageContent', content);
      })
      .catch(error => {
        showError(error)
      });
  })

  inject('content_find_all_by_page_id', (page_id, page_type) => {
      $axios.get('/page/content/get_page_data', {params: {'page_id': page_id}})
        .then(response => {
          store.dispatch(page_type + '/fillPageContent', response.data);
        })
        .catch(error => {
          showError(error)
        });
    }
  )

  inject('add_set_notification', async (payload) => {
    await $axios.post('/notification/add', payload)
      .then(response => {
        console.log(response)
      })
      .catch(error => {
        showError(error)
      });
  })
}
