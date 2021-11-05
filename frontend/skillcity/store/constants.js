export const state = () => ({
  errorCode: '',
  errorMessage: '',
  showSnack: false,
  snackbarDuration: 0,
  notification_status: ['Новый', 'Просмотренный']
})

export const mutations = {
  SET_ERROR_CODE(state, payload) {
    state.errorCode = payload
  },
  SET_ERROR_MSG(state, payload) {
    state.errorMessage = payload
  },
  SET_SNACKBAR(state, payload) {
    state.showSnack = payload
  },
  SET_SNACKBAR_DUR(state, payload) {
    state.snackbarDuration = payload
  },
  CLEAR_ERROR(state, payload) {
    state.showSnack = false;
    state.errorMessage = '';
    state.errorCode = '';
    state.snackbarDuration = 0
  }
}

export const actions = {
  setErrorCode(context, payload) {
    context.commit('SET_ERROR_CODE', payload)
  },
  setErrorMessage(context, payload) {
    context.commit('SET_ERROR_MSG', payload)
  },
  setShowSnackbar(context, payload) {
    context.commit('SET_SNACKBAR', payload)
  },
  setSnackbarDur(context, payload) {
    context.commit('SET_SNACKBAR_DUR', payload)
  },
  setClearError(context, payload) {
    context.commit('CLEAR_ERROR', payload)
  }
}

export const getters = {
  getNotificationStatus(state) {
    return state.notification_status
  }
}
