<template>
    <div class="root">
       <Navbar></Navbar>
       <v-dialog
            v-model="dialog"
            persistent
            max-width="600px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                color="primary"
                dark
                v-bind="attrs"
                v-on="on"
              >
                Open Dialog
              </v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="text-h5">Ajouter Categorie</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row> 
                    <v-col
                      cols="12"
                      sm="6"
                      md="4"
                    >
                    
                      <v-text-field
                        label="Nom du Categorie"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="blue darken-1"
                  text
                  @click="dialog = false"
                >
                  Close
                </v-btn>
                <v-btn
                  color="blue darken-1"
                  text
                  @click="dialog = false"
                >
                  Save
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
       <div class="mt-15 py-5 px-5">
            <v-simple-table
            fixed-header
            height="auto" 
            >
          <template v-slot:default>
            <thead>
            <tr >
              <th class="text-left" style="font-size: 15px;">
                Nom du Categorie
              </th>
              <th class="text-left" style="font-size: 15px;">
                Date d'inscription
              </th>
              <th class="text-center" style="font-size: 15px;">
                Action
              </th>
            </tr>
            </thead>
            <tbody v-if="categories.length!=0">
              <tr
                v-for="categorie in categories"
                :key="categorie.id"
                class="pa-5"
              >
                <td style="font-size: 12px;padding: 20px;">{{ categorie.categorieName }}</td>
                <td style="font-size: 12px;"><span v-if="categorie.created_at!=null">{{ categorie.created_at }}</span> <span v-else>Non valable !</span></td>
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
                      
                      >La suppression du categorie cause la suppression du tous services relier a ce categorie
                      <v-btn
                      class="float-end ml-15"
                      color="green"
                          text
                          @click="dialog.value = false,deleteCategories(categorie.id)"
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
       this.getAllCategories();
    },
    name:'CategoriesView',
data(){
    return{
        drawer: false,
        mini: true,
        categories:[],
        dialog: false,
        
    }
},
methods:{
    getAllCategories(){
      StatsServices.getAllCategorie().then((response)=>{
              this.categories=response.data;
            }).catch((err)=>{
              console.log(err);
            })
    },
    deleteCategories(id){
      StatsServices.deleteCategorie(id).then((res)=>{
        window.location.reload();
    }).catch((err)=>{
      console.log(err);
    });
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