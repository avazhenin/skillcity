import GlobalMutations from '../mutations'
import GlobalActions from '../actions'

export const state = () => ({
  pages: [],
  pageContent: [],
  isContentLoaded: false
})

export const mutations = {
  ...GlobalMutations
}

export const actions = {
  ...GlobalActions
}
