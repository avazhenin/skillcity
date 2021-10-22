<template>
  <v-container class="pa-0 ma-0" fluid>
    <v-main class="test_gradient pa-0" style="min-height: 300px;max-height: 300px;">
      <v-row class="fill-height justify-center ma-0" style="height: 100%;">
        <v-col class="ma-0 pa-0 col-sm-12">
          <v-row class="pa-1 ma-0" justify="center">
            <v-col class="justify-center align-start" cols="auto">
              <v-img src="skillcity-logo.png" max-width="160px"/>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
      <PhoneButton/>
    </v-main>

    <v-row justify="center">
      <v-col class="col-12 col-md-8">
        <PagePagination page_type="news" title="Новости"/>
        <PagePagination page_type="courses" title="Наши направления"/>
        <PagePagination page_type="teachers" title="Наши учителя"/>
      </v-col>
    </v-row>


  </v-container>
</template>

<script>
import ListPages from "../components/ListPages";
import PhoneButton from "../components/PhoneButton";
import LeaveRequest from "../components/LeaveRequest";
import PagePagination from "../components/PagePagination";

export default {
  components: {PagePagination, LeaveRequest, PhoneButton, ListPages},
  layout(context) {
    return context.store.getters["store/getLayout"]
  }
}
</script>

<style scoped>
.sticky {
  position: sticky;
  top: 76px;
  z-index: 1;
}

.test_gradient {
  background: #9dd07e;
  background: -webkit-linear-gradient(#f2ec30, #2cc4f3);
  background: -moz-linear-gradient(#f2ec30, #2cc4f3);
  background: linear-gradient(#f2ec30, #2cc4f3);
}
</style>
