<template>
  <div class="text-center">
    <v-dialog max-width="500px" v-model="dialog" :v-scroll="true">
      <template v-slot:activator="{ on, attrs }">
        <v-btn v-bind="attrs"
               v-on="on"
               icon x-large
               class="float blue lighten-5"
               @click="resetPhone()"
               elevation="4">
          <v-icon class="ma-2" color="blue">mdi-phone</v-icon>
        </v-btn>
      </template>
      <v-card flat>
        <v-card-title>Заказать обратный звонок</v-card-title>
        <v-card-text>
          <v-text-field label="Укажите номер телефона" v-model="phone_number" v-mask="'+7(###)-###-##-##'"/>
        </v-card-text>
        <v-card-actions>
          <v-btn @click.stop="call_back()">Заказать звонок</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import {mask} from "vue-the-mask";

export default {
  name: "PhoneButton",
  directives: {mask},
  data() {
    return {
      dialog: false,
      phone_number: ''
    }
  },
  methods: {
    resetPhone() {
      this.$store.dispatch('constants/setShowSnackbar', false)
      this.phone_number = '+7'
      this.$getScreenType()
    },
    call_back() {
      let payload = {
        'type': process.env.notification_type.call_request,
        'insert_date': new Date(),
        'viewed_date': null,
        'status': process.env.notification_status.new,
        'cell_phone': this.phone_number,
        'email': null
      }
      try {
        this.$add_set_notification(payload)
        this.$store.dispatch('constants/setErrorMessage', 'Мы обязательно свяжемся с вами!')
        this.$store.dispatch('constants/setSnackbarDur', 5 * 1000)
        this.$store.dispatch('constants/setShowSnackbar', true)
      } catch (error) {
      }

      this.dialog = false;
    }
  }
}
</script>

<style scoped>

.float {
  position: fixed;
  top: 65px;
  width: 72px !important;
  height: 72px !important;
}

@media screen and (max-width: 400px) {
  .float {
    right: 20px !important;
    width: 45px !important;
    height: 45px !important;
  }
}

@media screen and (max-width: 960px) {
  .float {
    right: 40px;
    width: 62px;
    height: 62px;
  }
}

@media screen and (min-width: 960px) {
  .float {
    right: 150px;
  }
}

@media screen and (min-width: 1440px) and (max-width: 1680px) {
  .float {
    right: 250px;
  }
}

@media screen and (min-width: 1680px) {
  .float {
    right: 300px;
  }
}

@media screen and (min-width: 1920px) {
  .float {
    right: 350px;
  }
}
</style>
