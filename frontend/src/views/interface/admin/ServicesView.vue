<template>
    <div class="root">
       <Navbar></Navbar>
       <p class="text-xl-h3 text-lg-h5 text-md-h4 text-sm-h6 mt-15 px-5 align-center" style="margin-bottom: 30px;">Les services a acceptés.</p>
       <div class="py-5">
        <v-simple-table
        fixed-header
        height="300px" 
        >
        <template v-slot:default>
        <thead >
        <tr >
          <th class="text-left" style="font-size: 15px;">
            Nom du Service
          </th>
          <th class="text-left" style="font-size: 15px;">
            Adresse
          </th>

          <th class="text-left" style="font-size: 15px;">
            Date d'inscription
          </th>

          <th class="text-left" style="font-size: 15px;">
            Telephone
          </th>

          <th class="text-left" style="font-size: 15px;">
            Village
          </th>

          <th class="text-left" style="font-size: 15px;">
            Categorie
          </th>

          <th class="text-left" style="font-size: 15px;">
            Utilisateur
          </th>
          <th class="text-center" style="font-size: 15px;">
            Action
          </th>
        </tr>
      </thead>
      <tbody v-if="services.length!=0">
        <tr
          v-for="service in services"
          :key="service.id"
          class="pa-5"
        >
          <td style="font-size: 12px;padding: 20px;">{{ service.serviceName }}</td>
          <td style="font-size: 12px;">{{ service.adresse }}</td>
          <td style="font-size: 12px;">{{ service.created_at }}</td>
          <td style="font-size: 12px;">{{ service.phone }}</td>
          <td style="font-size: 12px;">{{ service?.village['villageName'] }}</td>
          <td style="font-size: 12px;">{{ service?.categorie['categorieName'] }}</td>
          <td style="font-size: 12px;">{{ service?.user['email'] }}</td>
          <td style="font-size: 12px;">
            <v-btn text @click="updateService(service.id)"><v-icon color="green" x small >mdi-check</v-icon></v-btn>
            <v-dialog
            transition="dialog-bottom-transition"
            max-width="600"
            >
            <template v-slot:activator="{ on, attrs }">
            <v-btn
                text
                v-bind="attrs"
                v-on="on"
            ><v-icon color="red" x small>mdi-delete</v-icon></v-btn>
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
                    @click="dialog.value = false,deleteService(service.id)"
                >Supprimer</v-btn>
            </v-toolbar>
            </v-card>
            </template>
        </v-dialog>
        </td>
        </tr>
      </tbody>
      <tbody v-else>
        <tr>
          <td colspan="10" class="text-center mt-5"><v-icon color="red" large>mdi-alert-circle</v-icon>Aucun Service</td>
        </tr>
      </tbody>
      </template>
        </v-simple-table>
       </div>

       <p class="text-xl-h3 text-lg-h5 text-md-h4 text-sm-h6 px-5 align-center" style="margin-bottom: 30px;">Tous les services</p>
       <div class="py-5">
        <v-simple-table
        fixed-header
        height="auto" 
        >
        <template v-slot:default>
        <thead >
        <tr >
          <th class="text-left" style="font-size: 15px;">
            Nom du Service
          </th>
          <th class="text-left" style="font-size: 15px;">
            Adresse
          </th>

          <th class="text-left" style="font-size: 15px;">
            Date d'inscription
          </th>

          <th class="text-left" style="font-size: 15px;">
            Telephone
          </th>

          <th class="text-left" style="font-size: 15px;">
            Village
          </th>

          <th class="text-left" style="font-size: 15px;">
            Categorie
          </th>

          <th class="text-left" style="font-size: 15px;">
            Utilisateur
          </th>
          <th class="text-center" style="font-size: 15px;">
            Action
          </th>
        </tr>
      </thead>
      <tbody v-if="allServices.length!=0">
        <tr
          v-for="allService in allServices"
          :key="allService.id"
          class="pa-5"
        >
          <td style="font-size: 12px;padding: 20px;">{{ allService.serviceName }}</td>
          <td style="font-size: 12px;">{{ allService.adresse }}</td>
          <td style="font-size: 12px;">{{ allService.created_at }}</td>
          <td style="font-size: 12px;">{{ allService.phone }}</td>
          <td style="font-size: 12px;">{{ allService?.village['villageName'] }}</td>
          <td style="font-size: 12px;">{{ allService?.categorie['categorieName'] }}</td>
          <td style="font-size: 12px;">
            <span v-if="allService?.user['email']!=null">{{ allService.user['email'] }}</span>
            <span v-else>Non valable</span>
          </td>
          <td style="font-size: 12px;">
            <v-dialog
            transition="dialog-bottom-transition"
            max-width="600"
            >
            <template v-slot:activator="{ on, attrs }">
            <v-btn
                text
                v-bind="attrs"
                v-on="on"
            ><v-icon color="red" x small>mdi-delete</v-icon></v-btn>
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
                    @click="dialog.value = false,deleteService(allService.id)"
                >Supprimer</v-btn>
            </v-toolbar>
            </v-card>
            </template>
        </v-dialog>
        </td>
        </tr>
      </tbody>
      <tbody v-else>
        <tr>
          <td colspan="10" class="text-center mt-5"><v-icon color="red" large>mdi-alert-circle</v-icon>Aucun Service</td>
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
       this.getServiceNotActived();
       this.getAllServices();
    },
    name:'ServicesView',
data(){
    return{
        drawer: false,
        mini: true,
        services:[],
        allServices:[]
        
    }
},
methods:{
    getServiceNotActived(){
        StatsServices.getServiceNotActived().then((res)=>{
            this.services=res.data;
        }).catch((err)=>{
            console.log(err);
        })
    },

    updateService(id){
        StatsServices.updateService(id).then((res)=>{
          window.location.reload();
        }).catch((err)=>{
            console.log(err);
        })
    },

    deleteService(id){
        StatsServices.deleteService(id).then((res)=>{
          window.location.reload();
        }).catch((err)=>{
            console.log(err);
        })
    },

    getAllServices(){
        StatsServices.getAllService().then((res)=>{
          this.allServices=res.data;
            console.log(this.allServices);
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
*{
  font-family:cursive;
  scroll-behavior: smooth;
  overflow: hidden;
  box-sizing: border-box;
}
</style>