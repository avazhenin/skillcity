<template>
  <v-row class="d-block d-md-flex ma-2" no-gutters>
    <v-col class="d-block col-12 col-md-4">
      <v-col class="text-h4 text-center ma-2">{{ show_page.name }}</v-col>
      <v-col class="text-center ma-2">{{ show_page.dscr }}</v-col>
      <v-col class="d-flex justify-center">
        <v-img :src="'https://skillcity.kz/uploads/'+show_page.image" max-height="500px" width="200px" contain/>
      </v-col>
    </v-col>
    <v-col class="d-block justify-center col-12 col-md-8">
      <v-row v-for="page_content in show_page_content" :key="page_content.id" class="mt-1" no-gutters>
        <v-col>
          <span v-html="page_content.text" v-if="isText(page_content.content_type)"
                style="white-space:pre-wrap; word-wrap:break-word;">
          </span>
          <v-img v-if="isPicture(page_content.content_type)"
                 :src="'https://skillcity.kz/uploads/'+page_content.text"
                 style="max-height: 400px; max-width: 400px"
          />
          <YouTubeVideoEmbed :url="page_content.text"/>
        </v-col>
      </v-row>
    </v-col>
  </v-row>
</template>

<script>
import YouTubeVideoEmbed from "./YouTubeVideoEmbed";

export default {
  name: "ViewPage",
  components: {YouTubeVideoEmbed},
  props: {
    show_page: {},
    show_page_content: []
  },
  methods: {
    closeDialog() {
      this.$emit('closeDialog')
    },
    isPicture(content_type) {
      if (content_type == process.env.page_content_type.picture) return true;
      return false;
    },
    isText(content_type) {
      if (content_type == process.env.page_content_type.text) return true;
      return false;
    }
  },

}
</script>

<style scoped>

</style>
