<template>
  <div class="text-center">
    <v-dialog
      v-model="dialog"
      width="500"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-badge
          :content="notificationsCount"
          :value="notificationsCount"
          color="green"
          overlap
          offset-x="20"
          offset-y="20"
        >
          <v-btn icon
                 v-bind="attrs"
                 v-on="on"
                 @click="viewNotifications()">
            <v-icon :color="bell_color">
              mdi-bell
            </v-icon>
          </v-btn>
        </v-badge>
      </template>

      <v-card>
        <v-card-title class="text-h5 grey lighten-2">
          Уведомления
        </v-card-title>

        <v-data-table
          :headers="notificationsHeaders"
          :items="notifications"
          :single-expand="singleExpand"
          :expanded.sync="expanded"
          item-key="id"
          :item-class="rowColor"
          show-expand
          class="elevation-1"
        >
          <template v-slot:item.insert_date="{ item }">
            <span>{{ new Date(item.insert_date).toLocaleString() }}</span>
          </template>
          <template v-slot:item.status="{ item }">
            <span>{{ $store.getters["constants/getNotificationStatus"][item.status] }}</span>
          </template>
          <template v-slot:top>
            <v-toolbar flat>
              <v-toolbar-title>Expandable Table</v-toolbar-title>
              <v-spacer></v-spacer>
              <v-switch
                v-model="singleExpand"
                label="Single expand"
                class="mt-2"
              ></v-switch>
            </v-toolbar>
          </template>
          <template v-slot:expanded-item="{ headers, item }">
            <td :colspan="headers.length" class="ma-0 pa-0">
              <v-card class="ma-2">
                <v-card-text>
                  <v-row no-gutters>
                    <v-col>Тип уведомления: {{ item.type }}</v-col>
                  </v-row>
                  <v-row no-gutters>
                    <v-col>Детали: {{ item.cell_phone }}</v-col>
                  </v-row>
                </v-card-text>
                <v-card-actions>
                  <v-btn small outlined @click="viewNotifications(item)">Пометить прочитанным</v-btn>
                </v-card-actions>
              </v-card>
            </td>
          </template>
        </v-data-table>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="primary"
            text
            @click="dialog = false"
          >
            Закрыть
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
export default {
  name: "Notifications",
  props: ['bell_color'],
  data() {
    return {
      dialog: false,
      expanded: [],
      singleExpand: true,
      notificationsCount:0,
      notificationsHeaders: [
        {text: 'Дата уведомления', value: 'insert_date'},
        {text: 'Статус', value: 'status'},
        {text: '', value: 'data-table-expand'}],
      notifications: {}
    }
  },
  methods: {
    viewNotifications(item) {
      if(item) {
        let oldStatus = item.status
        try{
          item.status = process.env.notification_status.viewed
          this.$add_set_notification(item);
          this.notificationsCount = this.notificationsCount - 1
        }
        catch (error){
          item.status = oldStatus
        }
      }
    },
    rowColor(item) {
      if (item.status == 0) return 'green lighten-3'
    }
  },
  mounted() {
    this.$axios.get('/notification/findAll')
      .then(response => {
        this.notifications = response.data
        this.notificationsCount = this.notifications.length
      })
      .catch(error => {
        this.$showError(error)
      });
  }

}
</script>

<style scoped>

</style>
