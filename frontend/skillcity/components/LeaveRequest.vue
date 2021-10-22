<template>
  <v-col class="cols_custom_style">
    <v-form ref="form">
      <v-col v-if="title1" class="text-h3">{{title1}}</v-col>
      <v-col v-if="title2" class="text-body-1">{{title2}}</v-col>
      <v-col class="d-flex align-center">
        <v-text-field class="mr-1" hide-details outlined placeholder="Email родителя"></v-text-field>
        <v-btn color="#f2ec30" x-large @click.stop="send()">Отправить заявку</v-btn>
      </v-col>
      <v-col class="d-flex pt-0" style="max-width: 500px">
        <v-checkbox
          v-model="checkbox"
          :rules="[v => !!v || 'Требуется соглашение для отправки заявки!']"
          class="pa-0 ma-0"
          color="white"
          required
        >
          <template v-slot:label>
            <div :class="csscheckbox()">
              Я подтверждаю согласие на обработку персональных данных
            </div>
          </template>
        </v-checkbox>
      </v-col>
    </v-form>
  </v-col>
</template>

<script>
export default {
  name: "LeaveRequest",
  props: {
    title1: '',
    title2: '',
  },
  data() {
    return {
      checkbox: false
    }
  },
  methods: {
    send() {
      console.log(this.checkbox)
      if (!this.checkbox || this.checkbox === null || this.checkbox === undefined) this.$refs.form.validate()
      else alert('123')
    },
    csscheckbox() {
      if (this.checkbox === null || this.checkbox === undefined) return 'black--text'
      if (!this.checkbox) return 'red--text'
      if (this.checkbox) return 'yellow--text'
    }
  }
}
</script>

<style scoped>
.cols_custom_style {
  padding: 0;
  margin: 0;
}
</style>
