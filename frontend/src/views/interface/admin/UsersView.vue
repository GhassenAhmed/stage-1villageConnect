<template>
    <div class="root">
       <Navbar></Navbar>
       <p class="text-xl-h3 text-lg-h5 text-md-h4 text-sm-h6 mt-15 px-5 align-center" style="margin-bottom: 30px;">Listes des Utilisateurs</p>
       <div class="mt-5 py-5 px-5">
        <v-simple-table
        fixed-header
        height="500px" 
        >
        <template v-slot:default>
        <thead >
        <tr >
          <th class="text-left" style="font-size: 17px;">
            Nom
          </th>
          <th class="text-left" style="font-size: 17px;">
            Prénom
          </th>

          <th class="text-left" style="font-size: 17px;">
            Email
          </th>

          <th class="text-left" style="font-size: 17px;">
            Date d'inscription
          </th>

          <th class="text-left" style="font-size: 17px;">
            Role
          </th>
          <th class="text-left" style="font-size: 17px;">
            Operation
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
        <td>
          <v-dialog
              transition="dialog-bottom-transition"
              max-width="600"
              >
              <template v-slot:activator="{ on, attrs }">
              <v-icon color="red" v-bind="attrs"
                  v-on="on">mdi-delete</v-icon>
              </template>
              <template v-slot:default="dialog">
              <v-card>
                  <v-toolbar
                  color="white"
                  class="black--text"
                  dark
                  >Supperimer Ce Service ?
                  <v-btn
                  class="float-end ml-15"
                  color="green"
                      text
                      @click="dialog.value = false"
                  >Supprimer</v-btn>
              </v-toolbar>
              </v-card>
              </template>
          </v-dialog>
          <v-btn text 
          @click="snackbar = true,updateStatus(user.id)"
          >
            <v-icon color="green" >mdi-check</v-icon>
          </v-btn>
          <v-snackbar
            v-model="snackbar"
            :timeout="timeout"
          >
            {{ message_update_status }}

            <template v-slot:action="{ attrs }">
              <v-btn
                color="blue"
                text
                v-bind="attrs"
                @click="snackbar = false"
              >
                Close
              </v-btn>
            </template>
          </v-snackbar>
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
  import userInfos from "@/services/UserInfos"
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
        snackbar: false,
        timeout: 2000,
        message_update_status:""
       
        
    }
},
methods:{
    getAllUsers(){
        StatsServices.getAllUsers().then((res)=>{
            this.users=res.data;
            console.log(this.users);
        }).catch((err)=>{
            console.log(err);
        })
    },
    updateStatus(id){
      userInfos.UpdateStatusRole(id).then((res)=>{
        this.message_update_status=res.data;
        console.log(res.data);
      }).catch((err)=>{
        console.log(err);
      })
    }

},
  computed:{
     
  }
}
  </script>
  <style scoped>
  *,p{
    font-family:cursive;
    scroll-behavior: smooth;
    overflow: hidden;
    box-sizing: border-box;
}
</style>