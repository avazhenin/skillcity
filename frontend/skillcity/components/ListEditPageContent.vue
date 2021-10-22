<template>
  <v-row justify="center" no-gutters>
    <v-col class="col-12 col-md-8 col-lg-8 col-xl-6 text-center">
      <v-list style="overflow-y: auto; height: 100%;">

        <v-list-item v-for="content in page_content"
                     :key="content.id" class="pa-0 ma-0 justify-center"
        >
          <v-sheet width="100%" elevation="3" rounded :class="($store.getters['store/isAdmin']?' white ':' ')+' ma-1 pa-1'">
            <v-row no-gutters class="d-block">
              <v-col class="text-center justify-center align-center">
                <v-list-item-content v-if="content.content_type==page_content_type.text && content.edit_fl==0">
                  <span v-html="content.text"/>

                </v-list-item-content>

                <Editor v-if="content.content_type==page_content_type.text && content.edit_fl==1" class="ma-1"
                        v-model="page_object.text" :set_value="content.text"/>

                <v-list-item-content v-if="content.content_type==page_content_type.picture">
                  <v-img :src="(file && page_object.id == content.id)?url:`http://localhost:8888/uploads/`+content.text"
                         :contain="true"
                         style="max-height: 400px; max-width: 400px"/>
                </v-list-item-content>

                <v-file-input
                  v-if="content.content_type==page_content_type.picture && (page_object.id && page_object.id == content.id)"
                  label="Выбрать новое изображение"
                  v-model="file"
                />

                <v-list-item-content v-if="content.content_type==page_content_type.youtube" class="ma-0 pa-0">
                  <YouTubeVideoEmbed :url="content.text"/>
                </v-list-item-content>
              </v-col>
              <v-col cols="auto" class="text-center">
                <v-list-item-action v-if="$store.getters['store/isAdmin']">

                  <v-row class="d-flex">
                    <v-btn v-if="!page_object.id || (page_object.id && page_object.id != content.id)"
                           :disabled="page_object.id && page_object.id != content.id"
                           icon outlined class="blue ma-1"
                           @click="content_edit(content)">
                      <v-icon color="white">mdi-pencil</v-icon>
                    </v-btn>

                    <v-btn v-if="page_object.id && page_object.id == content.id"
                           :disabled="page_object.id && page_object.id != content.id"
                           icon outlined class="green ma-1"
                           @click="content_save(content)">
                      <v-icon color="white">mdi-content-save</v-icon>
                    </v-btn>

                    <v-btn icon icon outlined class="red ma-1" @click="content_delete(content)">
                      <v-icon color="white">mdi-delete</v-icon>
                    </v-btn>
                  </v-row>
                </v-list-item-action>
              </v-col>
            </v-row>
          </v-sheet>
        </v-list-item>
      </v-list>
    </v-col>
  </v-row>
</template>

<script>
import Editor from "./Editor";
import YouTubeVideoEmbed from "./YouTubeVideoEmbed";

export default {
  name: "ListEditPageContent",
  components: {YouTubeVideoEmbed, Editor},
  props: {
    page: {},
    page_content: []
  },
  data() {
    return {
      page_object: {},
      file: null
    }
  },
  computed: {
    url() {
      if (!this.file) return;
      return URL.createObjectURL(this.file);
    },
    page_content_type() {
      return process.env.page_content_type
    }
  },
  methods: {
    content_edit(page) {
      this.page_object = JSON.parse(JSON.stringify(page)); // клонируем объект
      this.page_object.edit_fl = 1 // меняем значение
      this.$store.commit('about/CONTENT_SET_DATA', this.page_object) // меняем значение в vuex
    },
    content_save() {
      this.page_object.edit_fl = 0 // меняем значение
      this.$content_edit_data(this.page, this.page_object, this.file) // сохраняем в базе измененные данные
      this.page_object = {}
      this.file = null
    },
    content_delete(content) {
      this.$content_delete(content, this.page.page_type);
    }
  },
}
</script>

<style scoped>

</style>
