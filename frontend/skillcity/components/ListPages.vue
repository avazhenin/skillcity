<template>
  <v-row justify="center" no-gutters class="pa-0 ma-0">

    <v-btn v-if="links_only && index < links_limit"
           class="d-block"
           :dark="darkText" text x-small
           v-for="(page, index) in pages" :key="page.id"
           @click.stop="viewPage(page)">
      {{ page.name }}
    </v-btn>

    <v-col v-if="!links_only" class="col-12 pa-0 ma-0">
      <v-row justify="center">
        <v-col cols="auto"
               v-for="page in pages"
               :key="page.id"
               class="d-flex justify-center ma-1"
               style="min-width: 200px"
               @click.stop="viewPage(page)">

          <v-card id="object_page"
                  style="cursor: pointer"
                  class="ma-1">
            <v-img id="page_img" :src="apiURL+'/uploads/'+page.image"/>
            <v-card-subtitle>{{ page.name }}</v-card-subtitle>
            <v-card-text>{{ page.dscr }}</v-card-text>
            <v-card-actions v-if="isAdmin()">
              <EditPage :page="page"/>
              <v-spacer/>
              <v-btn icon @click.stop="deletePage(page)">
                <v-icon>mdi-delete</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-col>

    <v-dialog v-model="dialog"
              scrollable
              fullscreen
              hide-overlay
    >
      <v-card style="overflow-y: auto">
        <v-app-bar fixed dark class="blue lighten-3">
          <v-btn
            icon
            dark
            @click="dialog = false"
          >
            <v-icon>mdi-arrow-left-circle</v-icon>
          </v-btn>
          <v-toolbar-title>Назад</v-toolbar-title>
          <v-spacer></v-spacer>
        </v-app-bar>
        <v-main>
          <ViewPage :show_page="this.temp_page" :show_page_content="this.temp_page_content"/>
        </v-main>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>

import EditPage from "./EditPage";
import ViewPage from "./ViewPage";

export default {
  name: "ListPages",
  components: {ViewPage, EditPage},
  props: {
    page_type: null,
    pages_custom: {},
    links_only: {type: Boolean, default: false},
    links_limit: {type: Number, default: 4},
    darkText: false
  },
  computed: {
    pages() {
      if (this.pages_custom) return this.pages_custom
      else return this.$store.state[this.page_type].pages;
    },
    temp_page_content() {
      return this.$store.state[this.page_type].pageContent
    }
  },
  data() {
    return {
      apiURL:process.env.api_base_url,
      dialog: false,
      temp_page: {},
    }
  }
  ,
  methods: {
    deletePage(page) {
      this.$delete_page(page, this.page_type);
    }
    ,
    isAdmin() {
      return this.$store.getters["store/isAdmin"]
    }
    ,
    viewPage(page) {
      this.temp_page = page
      this.$content_find_all_by_page_id(page.id, page.page_type)
      this.dialog = true
    }
  }
  ,
  mounted() {
    this.$get_all(this.page_type);
  }
}
</script>

<style scoped>
#object_page #page_img {
  width: 200px;
  height: 250px;
  display: flex;
}
</style>
