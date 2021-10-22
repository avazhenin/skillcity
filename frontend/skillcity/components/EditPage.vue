<template>
  <v-dialog v-if="this.isAdmin()"
            v-model="dialog"
            style="background-color: #2cc4f3"
            fullscreen>
    <template v-slot:activator="{ on, attrs }">
      <v-btn icon v-bind="attrs" v-on="on" @click.stop="openDialogActions()">
        <v-icon>mdi-pencil</v-icon>
      </v-btn>
    </template>
    <v-app app>
      <v-container app fluid class="full_height">
        <v-stepper elevation="0" v-model="e1" class="stepper">
          <v-stepper-header class="white sticky_header">
            <v-stepper-step
              :complete="e1 > 1"
              step="1"
            >
              Заполнение карточки
            </v-stepper-step>

            <v-divider/>

            <v-stepper-step
              :complete="e1>2"
              step="2"
            >
              Заполнение страницы
            </v-stepper-step>
            <v-spacer/>
          </v-stepper-header>
          <v-stepper-items id="object-stepper-content" class="pa-0 ma-0">

            <v-stepper-content step="1" class="full_height">
              <v-row justify="center" no-gutters>
                <v-col cols="9" class="pa-4">
                  <v-text-field label="Наименование" v-model="name" :value="page.name"/>
                  <v-textarea label="Детальное описание" v-model="dscr" :value="page.dscr"/>
                  <v-file-input label="Изображение" show-size accept="image/*" v-model="files"/>
                </v-col>

                <v-col cols="4">
                  <v-img id="img0"
                         class="align-baseline"
                         :contain="true"
                         v-if="!files"
                         :src="`http://localhost:8888/uploads/`+page.image"
                  />
                  <v-img id="img1"
                         class="align-baseline"
                         :contain="true"
                         v-if="files"
                         :src="this.url"
                  />
                </v-col>
              </v-row>
            </v-stepper-content>
            <v-stepper-content step="2">
              <v-row no-gutters>
                <v-col>
                  <ListEditPageContent :page="page" :page_content="pageData" style="width: 100%;"/>
                </v-col>
              </v-row>
            </v-stepper-content>
          </v-stepper-items>
        </v-stepper>
      </v-container>
      <v-footer class="sticky_footer ma-0 pa-0">
        <v-row no-gutters>
          <v-col class="ma-0 pa-0">
            <v-card elevation="0" class="d-flex">
              <v-btn class="d-none d-md-flex" v-if="e1>1" @click="e1--">Назад</v-btn>
              <v-btn class="d-flex d-md-none" icon x-large v-if="e1>1" @click="e1--">
                <v-icon>mdi-chevron-left</v-icon>
              </v-btn>
              <v-btn class="d-none d-md-flex" v-if="e1<2" @click="e1++">Далее</v-btn>
              <v-btn class="d-flex d-md-none" icon x-large v-if="e1<2" @click="e1++">
                <v-icon>mdi-chevron-right</v-icon>
              </v-btn>
              <v-btn class="d-none d-md-flex" text @click="cancel">Отмена</v-btn>
              <v-btn class="d-flex d-md-none" icon x-large @click="cancel">
                <v-icon>mdi-undo</v-icon>
              </v-btn>
              <v-spacer class="d-none d-md-block"/>
              <v-row dense v-if="e1>1" class="d-none d-sm-none d-md-flex">
                <v-col cols="auto" class="d-flex justify-end align-center">
                  <AddPageImage :page="page"/>
                </v-col>
                <v-col>
                  <AddPageText :page="page"/>
                </v-col>
              </v-row>
              <v-spacer class="d-none d-md-block"/>
              <v-btn color="light-green" class="d-none d-md-flex" @click="save()">сохранить
                <v-icon right large color="white">mdi-content-save</v-icon>
              </v-btn>

              <v-btn class="d-flex d-md-none" icon x-large @click="save()">
                <v-icon>mdi-content-save</v-icon>
              </v-btn>
              <v-spacer class="d-block d-md-none"/>
              <v-menu v-if="e1>1" offset-y top left content-class="menu-max-width">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="primary"
                    dark outlined
                    class="d-none d-sm-block d-md-none"
                    v-bind="attrs"
                    v-on="on"
                  >
                    Меню
                  </v-btn>
                  <v-btn
                    icon x-large
                    class="d-block d-sm-none align-center"
                    v-bind="attrs"
                    v-on="on"
                  >
                    <v-icon>mdi-menu</v-icon>
                  </v-btn>
                </template>
                <v-card class="pa-1">
                  <v-row no-gutters class="d-block">
                    <v-col class="text-center pa-1">
                      <AddPageImage :page="page"/>
                    </v-col>
                    <v-col class="text-center pa-1">
                      <AddPageText :page="page"/>
                    </v-col>
                  </v-row>
                </v-card>
              </v-menu>
            </v-card>
          </v-col>
        </v-row>
      </v-footer>
    </v-app>
  </v-dialog>
</template>

<style scoped>
@media screen and (max-width: 400px) {
  .menu-max-width {
    max-width: 100% !important;
  }
}

#img0, #img1 {
  max-height: 350px;
}

.stepper {
  overflow: visible;
}

.sticky_header {
  position: sticky;
  top: 0;
  z-index: 1;
}

.sticky_footer {
  position: sticky;
  bottom: 0;
  z-index: 1;
}

.full_height{
  height: 100% !important;
}
</style>

<script>
import ListEditPageContent from "./ListEditPageContent";
import Editor from "./Editor";
import AddPageImage from "./AddPageImage";
import AddPageText from "./AddPageText";

export default {
  name: "EditPage",
  components: {AddPageText, AddPageImage, Editor, ListEditPageContent},
  props: {
    page: {}
  },
  computed: {
    url() {
      if (!this.files) return;
      return URL.createObjectURL(this.files);
    },
    pageData() {
      return this.$store.state[this.page.page_type].pageContent;
    },
  },
  data() {
    return {
      scollingAreaHeight: 0,
      dialog: false,
      name: '',
      dscr: '',
      files: null,
      e1: 1,
      items: [
        {title: 'Добавить текст', ispicture: false},
        {title: 'Добавить изображение', ispicture: true}],
      tab: null,
      textarea: ''
    }
  },
  methods: {
    isAdmin() {
      return this.$store.getters["store/isAdmin"]
    },
    save() {
      const result = this.$edit_page(this.page.id, this.name, this.dscr, (this.files) ? this.files : null, this.page.image, this.page.page_type)
      result.then(r => {
        if (r == 0) this.cancel()
      });
    },
    cancel() {
      this.files = null
      this.dialog = false
      this.e1 = 1
      this.$store.dispatch('courses/clearPageContent')
    },
    addPicture() {
      const result = this.$content_add_image(this.page, this.files);
      result.then(r => {
        if (r == 0) this.files = null
      })
    },
    deleteText(pageData) {
      this.$content_delete(pageData, this.page.page_type);
    },
    openDialogActions() {
      this.$content_find_all_by_page_id(this.page.id, this.page.page_type);
    },
    setScrollContentHeight() {
      let scollingObject = document.getElementById("object-stepper-content")
      if (typeof (scollingObject) != 'undefined' && scollingObject != null) {
        let newHeight = window.innerHeight * 0.84 + "px"
        console.log("меняем высоту на " + newHeight)
        scollingObject.style.maxHeight = newHeight;
      }
    },
    onWindowResize() {
      this.setScrollContentHeight()
    }
  },
  mounted() {
    this.name = this.page.name
    this.dscr = this.page.dscr

    let el = document.getElementById("object-stepper-content")
    if (el) {
      this.$nextTick(function () {
        console.log("mounted next tick object-stepper-content " + el.offsetHeight)
      });
    }
  },
  created() {
    // window.addEventListener('resize', this.onWindowResize);
    // let el = document.getElementById("object-stepper-content")
    // if (el) {
    //   this.$nextTick(function () {
    //     console.log("created next tick object-stepper-content " + el.offsetHeight)
    //   });
    // }
  }
}
</script>
