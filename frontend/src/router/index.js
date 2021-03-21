import Vue from 'vue'
import Router from 'vue-router'

import vHome from '../components/vHome'
import vAdmin from '../components/vAdmin'

import VueRouter from 'vue-router'

Vue.use(VueRouter)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {path: '/', name: 'home', component: vHome},
        {path: '/admin', name: 'catalog', component: vAdmin},
    ],
})