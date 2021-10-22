<template>
  <v-dialog v-if="this.isAdmin()"
            v-model="dialog"
            max-width="450px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn v-bind="attrs"
             outlined color="blue"
             min-width="280px"
             v-on="on">
        Добавить изображение
        <v-spacer/>
        <v-icon color="green">mdi-plus-circle</v-icon>
      </v-btn>
    </template>
    <v-card>
      <v-card-text>
        <v-file-input label="Изображение" show-size accept="image/*" v-model="files"/>
        <v-img class="align-baseline"
               style="max-height: 250px"
               :contain="true"
               v-if="files"
               :src="this.url"
        />
      </v-card-text>
      <v-card-actions>
        <v-btn @click="addPicture">Добавить</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: "AddPageImage",
  props: {
    page: {}
  },
  data() {
    return {
      dialog: null,
      files: null,
    }
  },
  computed: {
    url() {
      if (!this.files) return;
      return URL.createObjectURL(this.files);
    }
  },
  methods: {
    addPicture() {
      const result = this.$content_add(this.page, this.files, process.env.page_content_type.picture);
      result.then(r => {
        if (r == 0) {
          this.files = null
          this.dialog = false
        }
      })
    },
    isAdmin() {
      return this.$store.getters["store/isAdmin"]
    }
  }
}
</script>

<style scoped>

</style>
