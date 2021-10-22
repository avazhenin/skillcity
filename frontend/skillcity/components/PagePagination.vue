<template>
  <v-row class="d-block" no-gutters>

    <v-col class="text-h5 text-md-h4 text-center"> {{ title }}</v-col>

    <v-col class="pa-2" style="width: 100% !important;">
      <ListPages :pages_custom="this.content[this.page-1]" :page_type="page_type"/>
    </v-col>

    <v-col>
      <v-pagination
        v-model="page"
        :length="pages_length"
        circle
      />
    </v-col>

  </v-row>
</template>

<script>
import ListPages from "./ListPages";

export default {
  name: "PagePagination",
  components: {ListPages},
  props: {
    title: '',
    page_type: ''
  },
  computed: {
    pages_length() {
      return this.content.length
    },
    content() {
      return this.splice_array(this.$store.state[this.page_type].pages, this.pagination)
    }
  },
  data() {
    return {
      page: 1,
      pagination: 1
    }
  },
  methods: {
    splice_array(iArray, chunkSize) {
      var array = iArray;
      return [].concat.apply([],
        array.map(function (elem, i) {
          return i % chunkSize ? [] : [array.slice(i, i + chunkSize)];
        })
      );
    },
    handleResize(){
      this.pagination = this.$pagesPerPagination()
    }
  },
  created() {
    window.addEventListener('resize', this.handleResize);
  },
  destroyed() {
    window.removeEventListener('resize', this.handleResize);
  }
}
</script>

<style scoped>

</style>
