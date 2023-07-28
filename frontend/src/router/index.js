import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from "@/views/HomeView.vue"
import SignUpView from "@/views/authentification/SignUpView.vue"
import LogInView from "@/views/authentification/LogInView.vue"
import ServiceView from "@/views/interface/ServiceView.vue";
import ServiceCategorieView from "@/views/interface/ServiceCategorieView.vue";
import CreateServiceView from "@/views/seviceProvider/CreateServiceView.vue";
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/Signup',
    name: 'signup',
    component: SignUpView
  },

  {
    path: '/Login',
    name: 'login',
    component: LogInView
  },

  {
    path: '/home',
    name: 'service',
    component: ServiceView
  },
  {
    path: '/Categorie/:id',
    name: 'categorie',
    component: ServiceCategorieView
  },
  {
    path: '/service',
    name: 'service',
    component: ServiceView
  },

  {
    path: '/createService',
    name: 'createService',
    component: CreateServiceView
  },
]

const router = new VueRouter({
  routes
})

export default router
