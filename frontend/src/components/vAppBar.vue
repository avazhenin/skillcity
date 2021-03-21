<template>
    <div>
        <v-toolbar>
            <v-app-bar-nav-icon></v-app-bar-nav-icon>

            <v-toolbar-title>
                <router-link to="/" custom v-slot="{ navigate  }">
                            <span style="cursor: pointer" @click="navigate" @keypress.enter="navigate "
                                  role="link">SkillCity</span>
                </router-link>
            </v-toolbar-title>
            <v-spacer/>
            <v-btn text rounded link to="/admin">Admin panel</v-btn>
            <v-spacer/>
        </v-toolbar>
    </div>
</template>

<script>
    export default {
        name: "vAppBar"
    }
</script>

<style scoped>

</style>