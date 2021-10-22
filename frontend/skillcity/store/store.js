export const state = () => ({
  menulist: [
    {label: 'Новости', to: 'news', userCanView: true},
    {label: 'О нас', to: 'about', userCanView: true},
    {label: 'Афиша', to: 'afisha', userCanView: true},
    {label: 'Курсы', to: 'courses', userCanView: true},
    {label: 'Контакты', to: 'contacts', userCanView: true},
    {label: 'Учителя', to: 'teachers', userCanView: false}
  ],
  isAdmin: false,
  token: localStorage.getItem('token')
})

export const mutations = {
  set_admin(state, text) {
    state.isAdmin = text;
  },
  set_token(state, token) {
    if (!token) {
      localStorage.removeItem('token')
      state.isAdmin = false
    } else {
      localStorage.setItem('token', token);
      state.token = token
      state.isAdmin = true
    }
  }
}

export const getters = {
  getLayout(state) {
    return 'default-layout';
  },
  getToken(state) {
    return state.token
  },
  isAdmin(state) {
    return state.isAdmin
    // return false
  }
}

