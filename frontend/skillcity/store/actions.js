export const GlobalActions = {
  fillPageContent(context, payload) {
    context.commit('CONTENT_FILL_DATA', payload)
  },
  addPageContent(context, payload) {
    context.commit('CONTENT_ADD_DATA', payload)
  },
  setPageContent(context, payload) {
    context.commit('CONTENT_SET_DATA', payload)
  },
  deletePageContent(context, payload) {
    context.commit('CONTENT_DELETE_DATA', payload)
  },
  clearPageContent(context) {
    context.commit('CONTENT_CLEAR_DATA')
  },
  fillPages(context, payload) {
    context.commit('FILL_PAGES', payload)
  },
  addPage(context, payload){
    context.commit('ADD_PAGE', payload)
  },
  editPage(context, payload){
    context.commit('EDIT_PAGE', payload)
  },
  deletePage(context, payload){
    context.commit('DELETE_PAGE', payload)
  }
}

export default GlobalActions
