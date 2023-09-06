<template>
    <div id="app">
        <v-app-bar  flat style="background-color: #ffffff;margin-top: 20px;height: 100px;border-bottom: 0.1mm solid #CFD0D3;"> 
        <v-app-bar-nav-icon @click="drawer = true" 
                            class="d-flex d-sm-none" 
                            ></v-app-bar-nav-icon>
                <v-toolbar-title class="text-center-sm hidden-sm-and-down" style="margin-left: -50px;"><img src="../../assets/village2.png" width="300px"></v-toolbar-title> 
                    <v-autocomplete
                    :items="villagesNames"
                    v-model="search"
                    filled
                    rounded
                    style="height: 60px;max-width: auto;"
                    class="ml-lg-14 ml-md-12 ml-sm-10" 
                    >
                    </v-autocomplete>
                    <v-btn plain class="ml-3 mt-1" @click="getServiceCategorie()">
                        <v-icon X large size="50">mdi-magnify</v-icon>
                    </v-btn>
                    <v-spacer></v-spacer>
            
            <v-btn icon class="hidden-sm-and-down" to="/home">
                <v-icon   X Small >
                    mdi-home
                </v-icon>
            </v-btn>
            <v-menu offset-y

                        transition="slide-x-transition" 
                        left
                        class="mt-7"
                        max-width="auto"
                        min-width="300"
                        >
                
                            <template v-slot:activator="{ on, attrs }">
                            <v-btn
                            plain
                            v-bind="attrs"
                            v-on="on"
                            @click="changerEtatNotif()"
                            class="hidden-xs-only"
                            >
                                <v-icon X Small>mdi-bell</v-icon>
                                <v-badge
                                color="green"
                                content="6"
                                >
                                </v-badge>
                            </v-btn>
                            
                            </template>
                    
                            <v-list>
                                    <v-list-item class="text-h5">
                                        Notifications
                                    </v-list-item>
                            </v-list>
                                <v-divider></v-divider>
                            <v-list v-if="notifications.length==0">
                            <v-list-item  class="mt-5 red--text">
                                <v-list-item-title class="text-h7 ml-8 mb-8">Pas de notification </v-list-item-title>
                            </v-list-item>
                            </v-list>
                        
                            <v-list v-else>
                            <v-list-item
                                v-for="notif in notifications" :key="notif.id"
                            >
                        <v-list-item-avatar>
                            <v-avatar size="43px">
                                <v-img  :src="notif.photo"></v-img>
                            </v-avatar>
                        </v-list-item-avatar>
                      
                        <v-list-item-content>
                            <v-list-item-title class="px-5 justify-center">
                                {{ notif.msg }}<br><span class="date" v-if="notif.date!=null"> Since : {{ notif.date }}</span>
                            </v-list-item-title>
                        </v-list-item-content> 
                        <v-list-item-action>
                            <v-col cols="auto">
                            <v-dialog
                                transition="dialog-bottom-transition"
                                max-width="400"
                            >
                                <template v-slot:activator="{ on, attrs }">
                                    <v-btn
                                    plain
                                    @click="dialog==true"
                                    v-bind="attrs"
                                    v-on="on"
                                    >
                                        <v-icon>mdi-close</v-icon>
                                    </v-btn>
                                </template>
                                 <template v-slot:default="dialog">
                                <v-card>
                                    <v-card-text>
                                    <div class="text-h5 pa-5">Supprimer notification</div>
                                    </v-card-text>
                                    <v-card-actions class="justify-end">
                                    <v-btn
                                        text
                                        @click="dialog.value = false"
                                    >Non</v-btn>
                                    <v-btn
                                        text
                                        @click="deleteNotificationById(notif.idNotif),dialog.value = false"
                                    >Supprimer</v-btn>
                                    </v-card-actions>
                                </v-card>
                                </template> 
                            </v-dialog>
                            </v-col>
                        </v-list-item-action>
                        </v-list-item>
                            <v-divider></v-divider>
                            <v-list-item class="mt-5">
                                <v-btn 
                                plain
                                v-if="notifications.length!=0"
                                @click="deleteAllNotif()"
                                >
                                    <v-list-item-title class="red--text ml-15"> 
                                        Supprimer tous<v-icon class="pa-1 red--text">mdi-delete</v-icon>
                                    </v-list-item-title>
                            </v-btn>
                    </v-list-item>
                    </v-list>
                    </v-menu>
    
            <v-btn icon class="hidden-sm-and-down" to="/chat">
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
                <v-list-item v-if="store.IsServiceProvider">
                        
                        <v-list-item-action>
                            <v-btn
                                plain
                               to="/servicesList"
                            >
                                <v-icon class="pa-2">mdi-cards-variant</v-icon>
                                <span class="">Services</span>
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

            <v-list-item to="/chat">
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
            <v-list-item to="/editProfil">   
                    <v-list-item-icon>
                    <v-icon X Small>mdi-wrench</v-icon>
                    </v-list-item-icon>

                    <v-list-item-title style="font-size: 15px;margin-top: 5px;padding-bottom: 5px;">Profile</v-list-item-title>
                </v-list-item>
                <v-list-item v-if="store.IsServiceProvider" to="/servicesList">   
                    <v-list-item-icon>
                    <v-icon X Small>mdi-cards-variant</v-icon>
                    </v-list-item-icon>

                    <v-list-item-title style="font-size: 15px;margin-top: 5px;padding-bottom: 5px;">Services</v-list-item-title>
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
        <section class="dots-container align-center mt-5 pa-5" v-if="loader" style="margin-bottom: 100vh;">
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
        </section>
        <v-container class="" v-else style="height: 100vh;">
                
                <div class="titre mt-15 ml-15 mb-5" style="font-size: 25px;" v-if="this.services!=null">
                    Découvrez nos <span style="color: #12c2b9;margin-left: 10px;font-weight: bold;font-size: 27px;">{{ categorieName }}</span>
                </div>

                <div class=" d-flex align-center mt-5 pa-5 justify-center" style="font-size: 25px;margin-top: 300px;" v-else>
                    <span style="color: #12c2b9;font-weight: bold;font-size: 27px;">Pas de service disponible.</span>
                </div>
            <v-layout row wrap>
                <v-flex  v-for="service in services" xl4 md4 lg4 sm6 xs12
                    :key="service.id">
                    <router-link :to="/service/ + service.id" style="text-decoration: none;color: black;">
                        <v-card
                                :loading="loading"
                                class="cards mx-auto my-12"
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
                                height="180"
                                src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
                                ></v-img>

                                <v-card-title>{{service.serviceName}} - <span style="font-size: 13px;margin-left: 3px;color:rgb(73, 70, 70)"> {{ service.categorie['categorieName'] }}</span></v-card-title>
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
                                <div><img src="../../assets/emplacement.png" alt="" width="20px" class="mr-2 mt-1">{{service.village['villageName']}} <img src="../../assets//verifie.png" alt="" v-if="service.backgroundVerified" class="float-end" width="30px"></div>
                                </v-card-text>

                                <v-divider class="mx-4"></v-divider>

                                <v-card-title><img src="../../assets/money-management.png" alt="" width="30px" class="mr-2 mt-1">Prix range</v-card-title>
                                <v-card-text>{{ service.minPrice }} DT - {{ service.maxPrice }} DT</v-card-text>
                                <v-card-actions class="justify-center">
                                <v-btn
                                    color="deep-purple lighten-1"
                                    text
                                    
                                >
                                    Plus
                                </v-btn>
                                </v-card-actions>
                        </v-card>
                    </router-link>
                </v-flex>
            </v-layout>     
        </v-container>

        <Footer></Footer>
        
    </div>
</template>
<script>
import { AuthUser } from "@/store/AuthStore";
import VillageServices from "@/services/VillageServices";
import Footer from "@/components/home/Footer.vue";
import CategorieServices from "@/services/CategorieServices";
import NotificationServices from '@/services/NotificationServices';
export default {
    name:'ServiceCategorieView',
    setup(){
        const store=AuthUser();
        return{
            store
        }
    },
    components:{
    Footer
    },
    created(){
        this.id=this.$route.params.id;
        this.getNotifsNotSeen();
        
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
            search:0,
            categories:[],
            loading: false,
            selection: 1,
            categorieName:"",
            loader:true,
            villageName:"",
            notifications:[],
            notificationNotSeen:[],
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
                    if(this.search!=0){
                        this.village_id=this.villagesNames.indexOf(this.search)+1;
                         this.services = this.services.filter((item) => item.village.id === this.village_id);
                    }
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
      getNotifs(){
            NotificationServices.getNotifs().then((res)=>{
                for(let i=0;i<res.data.length;i++){
                    this.notifications.push({idNotif:res.data[i].id,msg:res.data[i].message,etat:res.data[i].etat,date:(res.data[i].created_at)?.substring(0,10),photo:res.data[i].userEnvoi['photo']})
                } 
                console.log(this.notifications);
            
            }
            )
        },
        deleteNotificationById(id){
            NotificationServices.deleteNotificationById(id).then((res)=>{
                this.notifications=[];
                this.notificationNotSeen=[];
                this.getNotifs();
                this.getNotifsNotSeen();
            }).catch((err)=>{
                console.log(err);
            })
        },
        deleteAllNotif(){
            NotificationServices.deleteAllNotif().then((res)=>{
                this.notifications=[];
                this.notificationNotSeen=[];
            }).catch((err)=>{
                console.log(err);
            })
        },
        changerEtatNotif(){
            NotificationServices.updateNotif()
            .then((res)=>{
                this.notifications=[];
                this.notificationNotSeen=[];
                this.getNotifsNotSeen();
                this.getNotifs();
            }).catch((err)=>{
                console.log(err);
            })
        },
        getNotifsNotSeen(){
            NotificationServices.getNotifNotSeen().then((res)=>{
                for(let i=0;i<(res.data).length;i++){
                    this.notificationNotSeen.push({idNotif:res.data[i].id,msg:res.data[i].message,etat:res.data[i].etat})
                }
            }).catch((err)=>{
                console.log(err);
            })
         },
    },
    computed:{
    getNbrNotif(){
        const nbrNotif=this.notifications.length
        return nbrNotif;
    },
            
    getNbrNotifNotSeen(){
            const nbrNotifNotSeen=this.notificationNotSeen.length
            return nbrNotifNotSeen;
    }
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
.cards{
    transition: transform 0.3s ease;
}
.cards:hover{
    transform: translateY(-20px);
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