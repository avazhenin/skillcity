<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="auto"><span class="text-h4">Афиша</span></v-col>
      <PhoneButton/>
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

import AddPage from "../components/AddPage";
import ListPages from "../components/ListPages";

export default {
  components: {ListPages, AddPage},
  layout(context) {
    return context.store.getters["store/getLayout"]
  },
  data() {
    return {
      page_type: process.env.page_type.afisha
    }
  }
}
</script>
