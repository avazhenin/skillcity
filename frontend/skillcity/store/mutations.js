export const GlobalMutations = {
  CONTENT_FILL_DATA(state, payload) {
    state.pageContent = payload
    state.isContentLoaded = true
  },
  CONTENT_ADD_DATA(state, payload) {
    state.pageContent.push(payload)
  },
  CONTENT_SET_DATA(state, payload) {
    state.pageContent.filter(
      function (value, index, array) {
        if (value.id === payload.id) {
          Object.keys(value).forEach((key) => value[key] = payload[key])
        }
      }
    )
  },
  CONTENT_DELETE_DATA(state, payload) {
    state.pageContent.filter(
      function (value, index, arr) {
        if (value.id === payload.id) {
          arr.splice(index, 1)
        }
      }
    )
  },
  CONTENT_CLEAR_DATA(state, data) {
    state.pageContent = null
  },
  FILL_PAGES(state, payload) {
    state.pages = payload
  },
  ADD_PAGE(state, payload) {
    state.pages.push(payload)
  },
  EDIT_PAGE(state, payload) {
    state.pages.filter(
      function (value, index, array) {
        if (value.id === payload.id) {
          Object.keys(value).forEach((key) => value[key] = payload[key])
        }
      }
    )
  },
  DELETE_PAGE(state, payload){
    state.pages.filter(
      function (value, index, arr) {
        if (value.id === payload.id) {
          arr.splice(index, 1)
        }
      }
    )
  }
}

export default GlobalMutations
