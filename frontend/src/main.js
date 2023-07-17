import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import Vuelidate from "./plugins/vuelidate";
import { createPinia, PiniaVuePlugin } from 'pinia'
import { interceptors } from "./interceptor/interceptor.js";

interceptors();
Vue.use(PiniaVuePlugin)
const pinia = createPinia()


Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  pinia,
  Vuelidate,
  render: h => h(App)
}).$mount('#app')
