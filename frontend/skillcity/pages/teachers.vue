<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="auto"><span class="text-h4">Наши учителя</span></v-col>
    </v-row>
    <v-row justify="center">
      <v-col cols="auto">
        <AddPage :page_type="page_type"/>
      </v-col>
    </v-row>
    <v-row>
      <ListPages :page_type="page_type"/>
    </v-row>
  </v-container>
</template>

<script>
import ListPages from "../components/ListPages";
import AddPage from "../components/AddPage";

export default {
  name: "teachers",
  components: {ListPages, AddPage},
  layout(context) {
    return context.store.getters["store/getLayout"]
  },
  data() {
    return {
      page_type: process.env.page_type.teachers
    }
  }
}
</script>

<style scoped>

</style>
