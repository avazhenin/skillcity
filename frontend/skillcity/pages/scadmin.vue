<template>
  <div>Admin</div>
</template>

<script>
export default {
  name: 'scadmin',
  layout: 'admin-layout',
  mounted() {
    console.log('sc-admin page mounted')
    if (!localStorage.getItem('token')) this.$router.push("/login")
    else this.$store.commit('store/set_admin', true);
  }
}
</script>

<style scoped>

</style>
