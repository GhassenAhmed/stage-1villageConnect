<template>
    <div class="root">
        <v-app-bar  flat style="background-color: #ffffff;margin-top: 20px;height: 100px;border-bottom: 0.1mm solid #CFD0D3;"> 
            <v-app-bar-nav-icon @click="drawer = true" 
                                class="d-flex d-sm-none" 
                                ></v-app-bar-nav-icon>
    <v-toolbar-title class="text-center-sm" style="margin-left: -50px;"><img src="../assets/village2.png" width="300px"></v-toolbar-title> 
                        
                        <v-spacer></v-spacer>
                
                        <v-btn icon class="hidden-xs-only" to="/home">
                            <v-icon class=""  X Small>
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
                <v-btn icon class="hidden-xs-only">
                    <v-icon class=""  X Small>
                        mdi-email-outline
                    </v-icon>
                </v-btn>

                <v-btn icon class="hidden-xs-only">
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
                               to="/editProfil"
                            >
                                <v-icon class="pa-2">mdi-wrench</v-icon>
                                <span class="">Profile</span>
                            </v-btn>
                        </v-list-item-action>
                        
                    </v-list-item>
                    <v-list-item v-if="store.IsServiceProvider==true">
                        
                        <v-list-item-action>
                            <v-btn
                                plain
                               to="/services"
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
                    <v-list-item-icon >
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
                <v-list-item v-if="store.IsServiceProvider==true" to="/services">   
                    <v-list-item-icon>
                    <v-icon X Small>mdi-cards-variant</v-icon>
                    </v-list-item-icon>

                    <v-list-item-title style="font-size: 15px;margin-top: 5px;padding-bottom: 5px;">Services</v-list-item-title>
                </v-list-item>
                <v-list-group
                no-action
                sub-group
                
                
                >
                <template v-slot:activator>
                    <v-list-item-content>
                    <v-list-item-title>Categories</v-list-item-title>
                    </v-list-item-content>
                </template>
                <div class="categories" style="overflow-y: scroll;height: 400px;">
                    <v-list-item
                    v-for="categorie in categories"
                    :key="categorie.id"
                    link
                    
                >
                <router-link :to="/Categorie/ + categorie.id" style="text-decoration: none;color: black;">
                    <v-list-item-title >{{ categorie.categorieName }}</v-list-item-title>
                </router-link>
                </v-list-item>
                </div>
               
                </v-list-group>
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
            <main>
                <ModifierProfil></ModifierProfil>
            </main>

            <footer style="margin-top: 200px;">
                <Footer></Footer>
            </footer>

    </div>
</template>
<script>
import { AuthUser } from "@/store/AuthStore";
import NotificationServices from '@/services/NotificationServices';
import CategorieServices from '@/services/CategorieServices';
import ModifierProfil from "@/components/EditProfil/ModifierProfil.vue";
import Footer from "@/components/home/Footer.vue";
export default {
    setup(){
        const store=AuthUser();
        return{
            store
        }
    },
    created(){
        this.getCategories();
        this.getNotifs();
        this.getNotifsNotSeen();
    },
    name:'EditProfilView',
components:{
    ModifierProfil,Footer
},
data(){
    return{
        drawer: false,
        categories:[],
        notifications:[],
        notificationNotSeen:[],
        
    }
},
methods:{
    logout(){
            this.store.logOut();
            this.$router.push({name:"login"});
        },
   getCategories(){
            CategorieServices.getAll().then((res)=>{
                this.categories=res.data;
            }).catch((err)=>{
                console.log(err);
            })
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
</style>