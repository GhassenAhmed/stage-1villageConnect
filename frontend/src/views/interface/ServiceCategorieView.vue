<template>
    <div id="app">
        <v-app-bar  flat style="background-color: #ffffff;margin-top: 20px;height: 100px;border-bottom: 0.1mm solid #CFD0D3;"> 
        <v-app-bar-nav-icon @click="drawer = true" 
                            class="d-flex d-sm-none" 
                            ></v-app-bar-nav-icon>
            <v-toolbar-title class="text-center-sm hidden-sm-and-down" style="font-size: 25px;font-weight: bolder;">Village<span style="color: #105955d1;">Connect<span style="font-weight: bolder;font-size: 35px;color: #12c2b9;">.</span></span></v-toolbar-title> 
                    <v-autocomplete
                    :items="villagesNames"
                    v-model="villageName"
                    filled
                    rounded
                    style="height: 60px;max-width: auto;"
                    class="ml-lg-14 ml-md-12 ml-sm-10" 
                    >
                </v-autocomplete>
                    <v-spacer></v-spacer>
            
            <v-btn icon class="hidden-sm-and-down" to="/home">
                <v-icon   X Small >
                    mdi-home
                </v-icon>
            </v-btn>
            <v-btn icon class="hidden-sm-and-down">
                <v-icon   X Small >
                    mdi-bell-outline
                </v-icon>
            </v-btn>
    
            <v-btn icon class="hidden-sm-and-down">
                <v-icon class=""  X Small>
                    mdi-email-outline
                </v-icon>
            </v-btn>

            <v-btn icon class="hidden-sm-and-down">
                <v-icon  X Small>
                        mdi-heart-outline
                </v-icon>
            </v-btn>
            
            <v-menu offset-y 
            transition="slide-x-transition" 
            left
            
            >
                <template v-slot:activator="{ on, attrs }">
                <v-btn   plain v-bind="attrs"
                    v-on="on"
                    class="hidden-xs-only"
                    >
                        <v-avatar   size="43px">
                            <img :src="store.user['photo']">
                        </v-avatar>
                    </v-btn>
                </template>
                <v-list>
                <v-list-item>
                    <v-list-item-title>
                        <v-btn
                            plain
                            block
                            class="py-3"
                        >
                        {{store.user['firstName']}}
                        </v-btn>
                    </v-list-item-title>
                    </v-list-item>
                <v-list-item>
                    
                    <v-list-item-action>
                        <v-btn
                            plain
                            
                        >
                            <v-icon class="pa-2">mdi-wrench</v-icon>
                            <span class="">Profile</span>
                        </v-btn>
                    </v-list-item-action>
                    
                </v-list-item>
                <v-list-item >
                    <v-list-item-action>
                        <v-btn
                            plain
                            @click="logout()"
                        >
                            <v-icon class="pa-2">mdi-logout</v-icon>
                            <span >log out</span>
                        </v-btn>
                    </v-list-item-action>
                </v-list-item>

                </v-list>
            </v-menu>
        </v-app-bar>
        <v-navigation-drawer
        v-model="drawer"
        absolute
        temporary
        >
        <v-app-bar  flat style="background-color: #ffffff;">
            <v-layout row>
                <v-flex sm4 xs4>
                        <v-avatar   size="40px" class="mt-5 ml-4">
                            <img :src="store.user['photo']">
                        </v-avatar>
                </v-flex>
                <v-flex sm8 xs8 class="mt-8">
                    <span style="font-size: 12px;">{{ store.user['email'] }}</span>
                </v-flex>
            </v-layout>
        </v-app-bar>
        <v-list
            nav
            dense
            class="mt-5 py-5"
        >
            <v-list-item to="/home">
                <v-list-item-icon>
                <v-icon X Small>mdi-home</v-icon>
                </v-list-item-icon>

                <v-list-item-title style="font-size: 15px;">Home</v-list-item-title>
            </v-list-item>

            <v-list-item>
                <v-list-item-icon>
                <v-icon X Small>mdi-email-outline</v-icon>
                </v-list-item-icon>

                <v-list-item-title style="font-size: 15px;margin-top: 5px;padding-bottom: 5px;">Messages</v-list-item-title>
            </v-list-item>

            <v-list-item>
                <v-list-item-icon>
                <v-icon X Small>mdi-heart-outline</v-icon>
                </v-list-item-icon>

                <v-list-item-title style="font-size: 15px;margin-top: 5px;padding-bottom: 5px;">Listes</v-list-item-title>
            </v-list-item>
            <v-list-item>
                <v-btn
                plain
                @click="logout()"
                >
                    <v-icon class="pa-2">mdi-logout</v-icon>
                    <span >log out</span>
                </v-btn>
            </v-list-item>
            
        </v-list>
        </v-navigation-drawer>
        <section class="dots-container align-center mt-5 pa-5" v-if="loader">
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
        </section>
        <v-container class="" v-else>
                
                <div class="titre mt-15 ml-15 mb-5" style="font-size: 25px;" v-if="this.services!=null">
                    Découvrez nos <span style="color: #12c2b9;margin-left: 10px;font-weight: bold;font-size: 27px;">{{ categorieName }}</span>
                </div>

                <div class=" d-flex align-center mt-5 pa-5 justify-center" style="font-size: 25px;" v-else>
                    <span style="color: #12c2b9;font-weight: bold;font-size: 27px;">Pas de service disponible.</span>
                </div>
            <v-layout row wrap>
                <v-flex  v-for="service in services" xl4 md4 lg4 sm6 xs12
                    :key="service.id">
                    <v-card
                    :loading="loading"
                    class="mx-auto my-12"
                    max-width="250"
                
                    style="width: 300px;cursor: pointer;"
                    >
                    <template slot="progress">
                    <v-progress-linear
                        color="deep-purple"
                        height="10"
                        indeterminate
                    ></v-progress-linear>
                    </template>

                    <v-img
                    height="200"
                    src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
                    ></v-img>

                    <v-card-title>Service Name</v-card-title>

                    <v-card-text>
                    <v-row
                        class="my-1 ml-1"
                        align="center"
                    >
                        <v-rating
                        :value="4.5"
                        color="amber"
                        dense
                        half-increments
                        readonly
                        size="14"
                        ></v-rating>

                        <div class="grey--text ms-4">
                        4.5 (413)
                        </div>
                    </v-row>
                    <div>Small plates, salads & sandwiches - an intimate setting with 12 indoor seats plus patio seating.</div>
                    </v-card-text>

                    <v-divider class="mx-4"></v-divider>

                    <v-card-title>Schedule and prices</v-card-title>

                    <v-layout row>
                        <v-flex class="ml-4">
                            <v-card-text>
                                schedule
                            </v-card-text>
                        </v-flex>
                        <v-flex>
                            <v-card-text>
                                prices
                            </v-card-text>
                        </v-flex>
                    </v-layout>

                    <v-card-actions>
                    <v-btn
                        color="deep-purple lighten-2"
                        text
                        @click="reserve"
                    >
                        Reserve
                    </v-btn>
                    </v-card-actions>
                    </v-card>
                </v-flex>
            </v-layout>     
        </v-container>
        
    </div>
</template>
<script>
import { AuthUser } from "@/store/AuthStore";
import VillageServices from "@/services/VillageServices";
import CategorieServices from "@/services/CategorieServices";
export default {
    name:'ServiceCategorieView',
    setup(){
        const store=AuthUser();
        return{
            store
        }
    },
    created(){
        this.id=this.$route.params.id;
        
    },
    mounted(){
        this.getServiceCategorie();
        this.getVillages();
        this.getCategories();
        setTimeout(()=>this.loader=false,2000);
    },
    data(){
        return{
            id:"",
            services:[],
            drawer: false,
            villages:[],
            villagesNames:[],
            categories:[],
            loading: false,
            selection: 1,
            categorieName:"",
            loader:true,
            villageName:""
        }
    },
    methods:{
        logout(){
            this.store.logOut();
            this.$router.push({name:"login"});
        },
        getServiceCategorie(){
            CategorieServices.getServiceCategorie(this.id).then((res)=>{
                if(res.data==false){
                    this.services=null;
                }else{
                    this.services=res.data;
                    this.categorieName=this.services[0].categorie['categorieName'];
                }
            }).catch((err)=>{
                console.log(err);
            })
        },
        getVillages(){
    VillageServices.getAllVillages().then((res)=>{
        this.villages=res.data;
        for(let i=0 ;i<this.villages.length;i++){
            this.villagesNames.push(this.villages[i].villageName)
        }
    }).catch((err)=>{
        console.log(err);
    })
   },
   getCategories(){
            CategorieServices.getAll().then((res)=>{
                this.categories=res.data;
            }).catch((err)=>{
                console.log(err);
            })
        },
        reserve () {
        this.loading = true
        setTimeout(() => (this.loading = false), 2000)
      },
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


.dots-container {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  width: 100%;
}

.dot {
  height: 20px;
  width: 20px;
  margin-right: 10px;
  border-radius: 10px;
  background-color: #b3d4fc;
  animation: pulse 1.5s infinite ease-in-out;
}

.dot:last-child {
  margin-right: 0;
}

.dot:nth-child(1) {
  animation-delay: -0.3s;
}

.dot:nth-child(2) {
  animation-delay: -0.1s;
}

.dot:nth-child(3) {
  animation-delay: 0.1s;
}

@keyframes pulse {
  0% {
    transform: scale(0.8);
    background-color: #b3d4fc;
    box-shadow: 0 0 0 0 rgba(178, 212, 252, 0.7);
  }

  50% {
    transform: scale(1.2);
    background-color: #12c2b9;
    box-shadow: 0 0 0 10px rgba(178, 212, 252, 0);
  }

  100% {
    transform: scale(0.8);
    background-color: #b3d4fc;
    box-shadow: 0 0 0 0 rgba(178, 212, 252, 0.7);
  }
}
</style>