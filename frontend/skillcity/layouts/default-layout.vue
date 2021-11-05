<template>
  <v-app app>
    <v-navigation-drawer
      app
      v-model="drawer"
      temporary
    >
      <v-row class="d-block ma-0 pa-0" justify="center">
        <v-col class="d-flex align-center ma-0 pa-1" style="background-color: #48c5dd">
          <div class="d-flex align-center" style="color: white; cursor: pointer" @click="$router.push('/')">
            <v-img class="mr-2" src="skillcity-logo.png" max-width="40px"/>
            <span class="text-body-2 font-weight-bold" style="color: white;">SKILLCITY</span>
          </div>
          <v-spacer/>
          <v-btn outlined icon @click="drawer = !drawer" color="white">
            <v-icon>mdi-arrow-left</v-icon>
          </v-btn>
        </v-col>
        <v-col v-for="link in menu" :key="link.label" class="d-flex pa-1 ma-0 justify-center">
          <v-btn plain :to="link.to" width="150px"
                 v-if="(!$store.getters['store/isAdmin'] && link.userCanView) || $store.getters['store/isAdmin']">
            {{ link.label }}
          </v-btn>
        </v-col>
      </v-row>
    </v-navigation-drawer>

    <v-app-bar
      app
      elevate-on-scroll
      elevation="5"
      fixed
      :color="pickColor('#f2ec30','#48c5dd')"
    >
      <v-row id="app-bar-row" justify="center" align="center" class="transparent">
        <v-app-bar-nav-icon v-if="!drawer" @click="drawer = !drawer" :color="pickColor('#000000','#faf9f9')"
                            class="d-flex d-md-none"></v-app-bar-nav-icon>

        <v-col cols="auto">
          <v-toolbar-title
            style="cursor: pointer"
            @click="$router.push('/')"
          >
            <v-btn text :color="pickColor('#000000','#faf9f9')">{{ title }}</v-btn>
          </v-toolbar-title>
        </v-col>
        <v-spacer/>
        <v-col cols="auto" v-for="link in menu" :key="link.label" class="d-none d-md-flex pa-1">
          <v-btn outlined class="ma-0 " :dark="($route.name == 'index' && windowScrolled) || $route.name != 'index'"
                 :to="link.to"
                 v-if="(!$store.getters['store/isAdmin'] && link.userCanView) || $store.getters['store/isAdmin']">
            {{ link.label }}
          </v-btn>
        </v-col>
        <v-spacer/>
        <v-col class="d-none d-md-flex">
          <v-btn class="ma-0 pa-0" v-if="s.active" icon v-for="s in social" :key="s.link" @click="openInNewTab(s.link)">
            <v-icon :color="pickColor('#000000','#faf9f9')">{{ s.name }}</v-icon>
          </v-btn>
        </v-col>
        <v-col class="d-none d-md-flex" v-if="$store.getters['store/isAdmin']">
          <Notifications :bell_color="pickColor('#000000','#faf9f9')"/>
        </v-col>
      </v-row>
    </v-app-bar>

    <v-main>
      <Nuxt/>
      <ScrollTopButton/>
    </v-main>

    <v-footer color="#48c5dd">
      <v-row justify="center" no-gutters>
        <v-col cols="8">
          <v-row no-gutters class="d-none d-md-flex">
            <v-col class="d-flex align-start">
              <v-col class="d-block align-start pa-0 col-md-9">
                <v-col class="d-flex align-center">
                  <v-img src="skillcity-logo.png" max-width="40px"/>
                  <v-btn to="/" color="white" text rounded class="my-2"> SKILLCITY</v-btn>
                </v-col>
                <v-col>
                  <v-col class="d-block pa-0" v-if="s.active" v-for="s in social" :key="s.link">
                    <v-btn icon @click="openInNewTab(s.link)">
                      <v-icon color="white">{{ s.name }}</v-icon>
                    </v-btn>
                    <span style="color: white">{{ s.info }}</span>
                  </v-col>
                </v-col>
              </v-col>
              <v-col class="col-md-3">
                <v-btn to="/courses" color="white" text rounded class="my-2"> КУРСЫ</v-btn>
                <ListPages class="d-block" page_type="courses" :dark-text="true" :links_only=true :links_limit="4"/>
              </v-col>
            </v-col>
          </v-row>
          <v-expansion-panels class="d-md-none d-sm-block">
            <v-expansion-panel>
              <v-expansion-panel-header @click="scrollBottom()">
                О нас
              </v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-row no-gutters class="d-block">
                  <v-row class="d-flex" justify="center" no-gutters>
                    <v-col cols="auto" class="d-flex align-center">
                      <v-img src="skillcity-logo.png" max-width="40px"/>
                      <v-btn to="/" color="blue" text rounded plain class="my-2">SKILLCITY</v-btn>
                    </v-col>
                  </v-row>
                  <v-row class="d-flex" justify="center" no-gutters>
                    <v-col cols="auto" class="ma-1 justify-end" v-if="s.active" v-for="s in social" :key="s.link">
                      <v-btn outlined icon text @click="openInNewTab(s.link)">
                        <v-icon color="blue">{{ s.name }}</v-icon>
                        <span class="d-none d-xs-flex" style="color: white">{{ s.info }}</span>
                      </v-btn>
                    </v-col>
                  </v-row>
                </v-row>
              </v-expansion-panel-content>
            </v-expansion-panel>
            <v-expansion-panel>
              <v-expansion-panel-header @click="scrollBottom()">
                Курсы
              </v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-row no-gutters>
                  <ListPages class="d-block" :dark-text="false" page_type="courses" :links_only=true :links_limit="4"/>
                </v-row>
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>
          <v-row no-gutters>
            <v-col class="py-4 text-center white--text">
              {{ new Date().getFullYear() }} <strong>©</strong> <span style="color:white; cursor: pointer"
                                                                      @click="openInNewTab('http://www.linkedin.com/in/anatoliy-vazhenin-10bb9695')">avazhenin</span>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-footer>

    <v-snackbar
      v-model="snackbar"
      :multi-line="true"
      :timeout="snackbarDur"
      :vertical="true"
      light
    >
      {{ snackbarText }}

      <template v-slot:action="{ attrs }">
        <v-btn
          color="red"
          text
          v-bind="attrs"
          @click="deactivateSnackBar"
        >
          Закрыть
        </v-btn>
      </template>
    </v-snackbar>
  </v-app>
</template>

<script>
import PhoneButton from "../components/PhoneButton";
import ScrollTopButton from "../components/ScrollTopButton";
import ListPages from "../components/ListPages";
import Notifications from "../components/Notifications";

export default {

  components: {Notifications, ListPages, ScrollTopButton, PhoneButton},
  computed: {
    snackbar: {
      get: function () {
        return this.$store.state.constants.showSnack;
      },
      set: function () {
      }
    },
    snackbarText() {
      return this.$store.state.constants.errorMessage
    },
    snackbarDur() {
      return this.$store.state.constants.snackbarDuration
    },
    menu() {
      return this.$store.state.store.menulist;
    },
    currentRouteName() {
      return this.$route.name;
    }
  },
  data() {
    return {
      drawer: false,
      title: 'SkillCity',
      windowScrolled: false,
      social: [
        {name: 'mdi-whatsapp', link: 'https://wa.me/+77772336421', info: '+77772336421', active: true},
        {name: 'mdi-facebook', link: '', info: '', active: false},
        {
          name: 'mdi-instagram',
          link: 'https://www.instagram.com/skillcity.almaty/',
          info: 'Мы в инстаграм',
          active: true
        },
        {name: 'mdi-twitter', link: '', info: '', active: false},
        {
          name: 'mdi-map-marker',
          link: 'https://go.2gis.com/8iyoba',
          info: '9-й микрорайон, Ауэзовский район, Алматы',
          active: true
        },]
    }
  },
  watch: {
    $route(to, from) {
      console.log('windowScrolled=' + this.windowScrolled)
    }
  },
  methods: {
    handleScroll() {
      this.windowScrolled = window.pageYOffset > 0
    },
    openInNewTab(url) {
      window.open(url, '_blank');
    },
    pickColor(color1, color2) {
      return (this.$route.name == 'index' && !this.windowScrolled) ? color1 : color2
    },
    scrollBottom() {
      setTimeout(() => {
        this.$vuetify.goTo(document.body.scrollHeight)
      }, 200)
    },
    deactivateSnackBar() {
      this.$store.dispatch('constants/setClearError');
    }
  },
  created() {
    window.addEventListener('scroll', this.handleScroll);

  },
  destroyed() {
    window.removeEventListener('scroll', this.handleScroll);
  }
}
</script>

<style>
::-webkit-scrollbar { /* chrome based */
  width: 0px; /* ширина scrollbar'a */
  background: transparent; /* опционально */
}

.vlink {
  color: black !important;
  text-decoration: transparent !important;
  transition: 0.3s;
}

.vlink-hover:hover {
  color: black !important;
  /*color: #2e2e2e !important;*/
  text-decoration: underline !important;
  transition: 0.3s;
}

/*Icon transitions & animations*/
.fade-enter-active, .fade-leave-active {
  transition: opacity .5s
}

.fade-enter, .fade-leave-to {
  transition: opacity .5s;
  opacity: 0
}

</style>
