<template>
  <v-container>
    <v-form>
      <v-text-field label="Имя пользователя" v-model="username"/>
      <v-text-field label="Пароль" v-model="password" type="password"/>
      <v-btn @click.prevent="submitForm">Войти</v-btn>
    </v-form>
  </v-container>
</template>

<script>
export default {
  name: "login",
  layout: "login-layout",
  data() {
    return {
      username: '',
      password: '',
    }
  },
  methods: {
    async submitForm() {
      this.$setAdmin(this.username, this.password, true)
    }
  },
  mounted() {
    localStorage.removeItem('token');
  }
}
</script>

<style scoped>

</style>
