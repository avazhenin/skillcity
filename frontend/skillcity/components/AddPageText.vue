<template>
  <v-dialog v-if="this.isAdmin()"
            v-model="dialog"
            max-width="450px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn v-bind="attrs"
             outlined color="blue"
             min-width="280px"
             v-on="on">
        Добавить текст
        <v-spacer/>
        <v-icon color="green">mdi-plus-circle</v-icon>
      </v-btn>
    </template>
    <v-card flat>
      <Editor class="red" v-model="textarea"/>
      <v-card-actions>
        <v-btn outlined @click="addText">Добавить</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import Editor from "./Editor";

export default {
  name: "AddPageText",
  components: {Editor},
  props: {
    page: {}
  },
  data() {
    return {
      dialog: null,
      textarea: ''
    }
  },
  methods: {
    addText() {
      const result = this.$content_add(this.page, this.textarea, process.env.page_content_type.text);
      result.then(r => {
        if (r == 0) {
          this.textarea = null;
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
