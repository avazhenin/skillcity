export const state = () => ({
  error: {
    code: '',
    message: '',
    errorCause: '',
    data: '',
    stackTrace: '',
    status: '',
    timestamp: ''
  },
  showErrorDialog: false,
  snackbarText: '',
  showSnack: false,
  snackbarDuration: 0,
  notification_status: ['Новый', 'Просмотренный']
})

export const mutations = {
  SET_ERROR_INFO(state, payload) {
    state.error.code = payload.code
    state.error.message = payload.message
    state.error.cause = payload.cause
    state.error.data = payload.data
    state.error.stackTrace = payload.stackTrace
    state.error.status = payload.status
    state.error.timestamp = payload.timestamp
  },
  SET_SNACKBAR(state, payload) {
    state.showSnack = payload
  },
  SET_ERR_DIALOG(state, payload) {
    state.showErrorDialog = payload
  },
  SET_SNACKBAR_DUR(state, payload) {
    state.snackbarDuration = payload
  },
  SET_SNACKBAR_TEXT(state, payload) {
    if (payload)
      state.snackbarText = payload
    else
      state.snackbarText = ''
  },
  CLEAR_ERROR(state, payload) {
    state.error = {}
  }
}

export const actions = {
  setErrorInfo(context, payload) {
    context.commit('SET_ERROR_INFO', payload)
  },
  setShowSnackbar(context, payload) {
    context.commit('SET_SNACKBAR', payload)
  },
  setShowErrorDialog(context, payload) {
    context.commit('SET_ERR_DIALOG', payload)
  },
  setSnackbarDur(context, payload) {
    context.commit('SET_SNACKBAR_DUR', payload)
  },
  setClearError(context, payload) {
    context.commit('CLEAR_ERROR', payload)
  },
  setSnackBarText(context, payload) {
    context.commit('SET_SNACKBAR_TEXT', payload)
  }
}

export const getters = {
  getNotificationStatus(state) {
    return state.notification_status
  },
  getSnackBarText(state) {
    return state.snackbarText
  },
  getCurrentError(state) {
    return state.error
  }
}
