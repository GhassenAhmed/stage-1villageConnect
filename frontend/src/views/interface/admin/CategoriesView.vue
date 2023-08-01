<template>
    <div class="root">
       <Navbar></Navbar>
       hhhhh
    </div>
  </template>
  <script>
  import { AuthUser } from "@/store/AuthStore";
  import Navbar from '@/components/admin/Navbar.vue'
    export default {
      components:{
        Navbar
     },
      setup(){
        const store=AuthUser();
        return{
            store
        }
    },
    created(){
       
    },
    name:'CategoriesView',
data(){
    return{
        drawer: false,
        mini: true,
        
    }
},
methods:{
    
},
  computed:{
     
  }
}
  </script>