<template>
  <v-app id="scadmin">
    <v-main>
      <v-container class="ma-0 pa-0">
        <v-row>
          <v-col cols="3">
            <v-row v-for="(item, i) in menu" :key="i">
              <v-col class="text-center pa-1">
                <v-btn id="menu_buttons" :to="item.to">
                  {{ item.label }}
                </v-btn>
              </v-col>
            </v-row>
            <v-divider class="ma-4"/>
            <div class="text-center">
              <v-btn link dark @click="exit">Выход</v-btn>
            </div>
          </v-col>
          <v-col cols="9">
            <Nuxt/>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  computed: {
    menu() {
      return this.$store.state.store.menulist;
    }
  },
  data() {
    return {
      drawer: true
    }
  },
  methods: {
    exit() {
      this.$store.commit('store/set_admin', false);
      this.$store.commit('store/set_token')
      this.$router.push("/");
    }
  },
  mounted() {
    console.log('admin-layout mounted')
  }
}
</script>

<style scoped>
#menu_buttons {
  width: 150px;
}
</style>
