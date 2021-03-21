<template>
    <v-app>
        <v-app-bar/>
        <router-view/>
    </v-app>
</template>

<script>
    import vAppBar from "./components/vAppBar";

    export default {
        name: 'App',
        components: {vAppBar},
        data() {
            return {}
        },
    };
</script>
