import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from "@/views/HomeView.vue"
import SignUpView from "@/views/authentification/SignUpView.vue"
import LogInView from "@/views/authentification/LogInView.vue"
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
]

const router = new VueRouter({
  routes
})

export default router
