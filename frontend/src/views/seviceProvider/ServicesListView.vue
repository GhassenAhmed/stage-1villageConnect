<template>
    <div class="root">
        <v-app-bar  flat style="background-color: #ffffff;margin-top: 20px;height: 100px;border-bottom: 0.1mm solid #CFD0D3;"> 
            <v-app-bar-nav-icon @click="drawer = true" 
                                class="d-flex d-sm-none" 
                                ></v-app-bar-nav-icon>
                <v-toolbar-title class="text-center-sm" style="font-size: 20px;font-weight: bolder;">Village<span style="color: #105955d1;">Connect<span style="font-weight: bolder;font-size: 35px;color: #12c2b9;">.</span></span></v-toolbar-title> 
                        
                        <v-spacer></v-spacer>
                        
                <v-btn icon class="hidden-sm-and-down" to="/home">
                    <v-icon X Small >mdi-home</v-icon>
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
                            <v-badge
                                color="red"  
                                >
                                <v-icon >mdi-bell</v-icon>
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
                <v-icon X Small >mdi-home</v-icon>
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
            <v-list-item to="/editProfil">   
                <v-list-item-icon>
                <v-icon X Small>mdi-wrench</v-icon>
                </v-list-item-icon>

                <v-list-item-title style="font-size: 15px;margin-top: 5px;padding-bottom: 5px;">Profile</v-list-item-title>
            </v-list-item>
            <v-divider class="mt-5 mb-5"></v-divider>
            <v-list-item>
            <v-btn
            plain
            @click="logout()"
            >
                <v-icon class="">mdi-logout</v-icon>
                <span >log out</span>
            </v-btn>
        </v-list-item>
        </v-list>
        </v-navigation-drawer>
            <v-container fluid class="mt-5">
                <v-simple-table
                    fixed-header
                    height="auto"
                    class="mt-10"
                >
                    <thead>
                        <tr>
                        <th class="text-left">
                            Nom Service
                        </th>
                        <th class="text-left">
                            Photo
                        </th>
                        <th class="text-left">
                            Adresse
                        </th>
                        <th class="text-left">
                            Telephone
                        </th>
                        <th class="text-left">
                            Depuis
                        </th>
                        <th class="text-left">
                           Rate
                        </th>
                        <th class="text-left">
                           Village
                        </th>
                        <th class="text-left">
                           Categorie
                        </th>
                        <th class="text-left">
                           Status
                        </th>
                        <th class="text-left">
                           Opérations
                        </th>
                        </tr>
                    </thead>
                    <tbody v-if="services.length!=0">
                        <tr
                        v-for="service in services"
                        :key="service.id"
                        >
                        
                        <td>{{ service.serviceName }}</td>
                        <td>
                            <img :src="service.photo" alt="" width="90px">
                        </td>
                        <td>{{ service.adresse }}</td>
                        <td>{{ service.phone }}</td>
                        <td>{{ service.yearsInBusiness }}</td>
                        <td>{{ service.raiting }}</td>
                        <td>{{ service.village['villageName'] }}</td>
                        <td>{{ service.categorie['categorieName'] }}</td>
                        <td>
                            <span v-if="service.status==1" style="color: green;">Actif</span>
                            <span v-else style="color: red;">Desactif</span>
                        </td>
                        <td>
                            <router-link :to="/editService/ + service.id" style="text-decoration: none;color: black;">
                                <v-icon color="green">mdi-table-edit</v-icon>
                            </router-link>
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
                </v-simple-table>
               
            </v-container>
            <Footer class="mt-15"></Footer>
    </div>
</template>
<script>
import { AuthUser } from "@/store/AuthStore";
import ServiceServices from '@/services/ServiceServices';
import Footer from '../../components/home/Footer.vue'
import NotificationServices from '@/services/NotificationServices';
export default {
    setup(){
        const store=AuthUser();
        return{
            store
        }
    },
    created(){
        this.getServicesById();
        this.getNotifs();
        this.getNotifsNotSeen();
    },
    components:{
    Footer
    },
    data(){
        return{
            vertical: true,
            drawer: false,
            loading:false,
            services:[],
            notifications:[],
        notificationNotSeen:[],
           
        }
    },
    methods:{
        logout(){
            this.store.logOut();
            this.$router.push({name:"login"});
        },
        getServicesById(){
            ServiceServices.getServicesById().then((res)=>{
                this.services=res.data;
                console.log(this.services);
            })
        },
        deleteService(id){
            ServiceServices.deleteService(id).then((res=>{
            console.log(res.data);
            window.location.reload();
            })).catch((err)=>{
                console.log(err);
            });
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
.form span{
    color:#284389;
}
.container{
    height: 100vh;
}
</style>