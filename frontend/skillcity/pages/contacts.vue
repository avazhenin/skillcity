<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="auto"><p class="text-h4">Контакты</p></v-col>
    </v-row>

    <v-row justify="center">
      <v-col cols="auto">
        <AddPageText :page="this.page"/>
        <AddPageImage :page="this.page"/>
        <AddPageYouTube :page="this.page"/>
      </v-col>
    </v-row>

    <ListEditPageContent :page_content="content" :page="page"/>

    <PhoneButton />

  </v-container>
</template>

<style scoped/>

<script>
import AddPageText from "../components/AddPageText";
import AddPageImage from "../components/AddPageImage";
import ListEditPageContent from "../components/ListEditPageContent";
import YouTubeVideoEmbed from "../components/YouTubeVideoEmbed";
import PhoneButton from "../components/PhoneButton";

export default {
  components: {PhoneButton, YouTubeVideoEmbed, ListEditPageContent, AddPageImage, AddPageText},
  layout(context) {
    return context.store.getters["store/getLayout"]
  },
  data() {
    return {
      page: {'id': 4, 'page_type': process.env.page_type.contacts}
    }
  },
  computed: {
    content() {
      return this.$store.state['contacts'].pageContent
    }
  },
  mounted() {
    if (!this.$store.state["contacts"].isContentLoaded) this.$content_find_all_by_page_id(this.page.id, this.page.page_type)
  }
}
</script>
