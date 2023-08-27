import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from "@/views/HomeView.vue"
import EditProfilView from "@/views/EditProfilView.vue"
import SignUpView from "@/views/authentification/SignUpView.vue"
import LogInView from "@/views/authentification/LogInView.vue"
import ServiceView from "@/views/interface/ServiceView.vue";
import UsersView from "@/views/interface/admin/UsersView.vue";
import ServicesView from "@/views/interface/admin/ServicesView.vue";
import ChatView from "@/views/interface/ChatView.vue";
import CategoriesView from "@/views/interface/admin/CategoriesView.vue";
import StatistiquesView from "@/views/interface/admin/StatistiquesView.vue";
import ServiceCategorieView from "@/views/interface/ServiceCategorieView.vue";
import CreateServiceView from "@/views/seviceProvider/CreateServiceView.vue";
import ForgotPasswordView from "@/views/authentification/ForgotPassword/ForgotPasswordView.vue"
import ChangerPasswordView from "@/views/authentification/ForgotPassword/ChangerPasswordView.vue"
import serviceDetailsView from "@/views/interface/serviceDetailsView.vue"
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
    name: 'home',
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
  {
    path: '/services',
    name: 'services',
    component: ServicesView
  },
  {
    path: '/categories',
    name: 'categories',
    component: CategoriesView
  },
  {
    path: '/users',
    name: 'users',
    component: UsersView
  },
  {
    path: '/statistiques',
    name: 'statistiques',
    component: StatistiquesView
  },
  {
    path: '/editProfil',
    name: 'editProfil',
    component: EditProfilView
  },
  
  {
    path: '/forgotpassword',
    name: 'forgotpassword',
    component: ForgotPasswordView
  },
  {
    path: '/changerpassword',
    name: 'changerpassword',
    component: ChangerPasswordView
  },
  {
    path: '/service/:id',
    name: 'serviceDetails',
    component: serviceDetailsView
  },
  {
    path: '/chat',
    name: 'chat',
    component: ChatView
  },
  
]

const router = new VueRouter({
  routes
})

export default router
