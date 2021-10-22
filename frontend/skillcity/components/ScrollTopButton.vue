<template>
  <v-btn
    v-scroll="onScroll"
    v-show="showScrollTopBtn"
    fab
    dark
    fixed
    bottom
    right
    class="blue lighten-5"
    @click="toTop"
  >
    <v-icon class="ma-2" color="blue">mdi-arrow-up-drop-circle</v-icon>
  </v-btn>
</template>

<script>
export default {
  name: "ScrollTopButton",
  data() {
    return {
      showScrollTopBtn: false
    }
  },
  methods: {
    onScroll(e) {
      if (typeof window === 'undefined') return
      const top = window.pageYOffset || e.target.scrollTop || 0
      this.showScrollTopBtn = top > 20
    },
    toTop() {
      this.$vuetify.goTo(0)
    }
  }
}
</script>

<style scoped>

</style>
