<template>
  <v-dialog v-if="this.isAdmin()" v-model="dialog" max-width="450px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn v-bind="attrs" v-on="on"
             outlined color="blue"
             min-width="280px"      >
        Добавить
        <v-spacer/>
        <v-icon color="green">mdi-plus-circle</v-icon>
      </v-btn>
    </template>
    <v-card>
      <v-form class="pa-2">
        <v-text-field :label="this.labels.name" v-model="name"/>
        <v-textarea :label="this.labels.dscr" v-model="dscr"/>
        <v-file-input :label="this.labels.image" show-size accept="image/*" v-model="files"/>
        <v-btn @click.prevent="upload()">Сохранить</v-btn>
      </v-form>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: "AddPage",
  props: {
    page_type: null
  },
  data() {
    return {
      name: '',
      dscr: '',
      files: null,
      dialog: null,
      labels: {
        'name': null,
        'dscr': null,
        'image': 'Изображение',
      }
    }
  },
  methods: {
    upload() {
      this.$upload_page(this.name, this.dscr, this.files, this.page_type);
      this.dialog = false
    },
    isAdmin() {
      return this.$store.getters["store/isAdmin"]
    }
  },
  mounted() {
    switch (this.page_type) {
      case process.env.page_type.news:
        this.labels.name = 'Название новости'
        this.labels.dscr = 'Описание новости'
        break;
      case process.env.page_type.about:
        break;
      case process.env.page_type.afisha:
        this.labels.name = 'Название афиши'
        this.labels.dscr = 'Описание афиши'
        break;
      case process.env.page_type.courses:
        this.labels.name = 'Название курса'
        this.labels.dscr = 'Описание курса'
        break;
      case process.env.page_type.contacts:
        break;
      case process.env.page_type.teachers:
        this.labels.name = 'Имя учителя'
        this.labels.dscr = 'Описание'
        break;
    }
  }
}
</script>

<style scoped>

</style>
