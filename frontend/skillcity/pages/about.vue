<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="auto"><p class="text-h4">О нас</p></v-col>
      <PhoneButton/>
    </v-row>

    <v-row class="d-block d-md-flex justify-center" justify="center" no-gutters>
      <v-col cols="auto" class="text-center ma-1">
        <AddPageText :page="this.page"/>
      </v-col>
      <v-col cols="auto" class="text-center ma-1">
        <AddPageImage :page="this.page"/>
      </v-col>
      <v-col cols="auto" class="text-center ma-1">
        <AddPageYouTube :page="this.page"/>
      </v-col>
    </v-row>

    <ListEditPageContent :page_content="content" :page="page"/>

  </v-container>

</template>

<style scoped/>

<script>
import AddPageText from "../components/AddPageText";
import AddPageImage from "../components/AddPageImage";
import ListEditPageContent from "../components/ListEditPageContent";
import AddPageYouTube from "../components/AddPageYouTube";
import PhoneButton from "../components/PhoneButton";

export default {
  components: {PhoneButton, AddPageYouTube, ListEditPageContent, AddPageImage, AddPageText},
  layout(context) {
    return context.store.getters["store/getLayout"]
  },
  data() {
    return {
      page: {'id': 1, 'page_type': process.env.page_type.about}
    }
  },
  computed: {
    content() {
      return this.$store.state['about'].pageContent
    }
  },
  mounted() {
    if (!this.$store.state["about"].isContentLoaded) this.$content_find_all_by_page_id(this.page.id, this.page.page_type)
  }

}
</script>
