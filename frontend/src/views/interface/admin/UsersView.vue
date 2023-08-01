<template>
    <div class="root">
       <Navbar></Navbar>
       <div class="mt-15 py-5 px-5">
        <v-simple-table
        fixed-header
        height="300px" 
        >
        <template v-slot:default>
        <thead >
        <tr >
          <th class="text-left" style="font-size: 25px;">
            Nom
          </th>
          <th class="text-left" style="font-size: 25px;">
            Prénom
          </th>

          <th class="text-left" style="font-size: 25px;">
            Email
          </th>

          <th class="text-left" style="font-size: 25px;">
            Date d'inscription
          </th>

          <th class="text-left" style="font-size: 25px;">
            Role
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="user in users"
          :key="user.id"
          class="pa-5"
        >
          <td style="font-size: 20px;padding: 20px;">{{ user.firstName }}</td>
          <td style="font-size: 20px;">{{ user.lastName }}</td>
          <td style="font-size: 20px;">{{ user.email }}</td>
          <td style="font-size: 20px;">{{ user.created_at }}</td>
          <td style="font-size: 20px;">
            <span>
                {{ user.roles[0]['roleName'] }}
            </span><br>
            <span v-if="user.roles.length>1">
                {{ user.roles[1]['roleName'] }}
            </span>
        </td>
        </tr>
      </tbody>
      </template>
      </v-simple-table>
       </div>
    </div>
  </template>
  <script>
  import { AuthUser } from "@/store/AuthStore";
  import Navbar from '@/components/admin/Navbar.vue'
  import StatsServices from "@/services/StatsServices.js";
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
       this.getAllUsers();
    },
    name:'UsersView',
data(){
    return{
        drawer: false,
        mini: true,
        users:[],
       
        
    }
},
methods:{
    getAllUsers(){
        StatsServices.getAllUsers().then((res)=>{
            this.users=res.data;
            console.log(this.users[0].roles[1].length);
        }).catch((err)=>{
            console.log(err);
        })
    }
},
  computed:{
     
  }
}
  </script>