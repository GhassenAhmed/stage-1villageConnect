<template>
    <nav>
     <v-app-bar app text >  
         <v-app-bar-nav-icon >
             <v-btn  text @click="drawer=!drawer">
                 <v-icon color="grey">mdi-view-list</v-icon>
             </v-btn>
         </v-app-bar-nav-icon>
         <v-toolbar-title class="text-uppercase grey--text">
             <div class="hidden-md-and-down">
                 <span class="font-weight-light">Dashboard</span>
                 <span class="ml-3" ><v-icon>mdi-border-color</v-icon></span>
             </div>
         </v-toolbar-title>
         <v-spacer></v-spacer>
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
        <v-badge color="red" :content="getNbrNotifNotSeen ? getNbrNotifNotSeen : '0'"
        ></v-badge> 
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
     <v-navigation-drawer v-model="drawer" app class="deep-purple lighten-2">
         <v-layout column align-center >
             <v-flex class="mt-5">
                 <v-avatar size="100">
                     <img :src="store.user['photo']" alt="" >
                 </v-avatar>
                 <p class="white--text subheading mt-1">{{ store.user['firstName'] +" " +store.user['lastName']}}</p>
             </v-flex>
             <v-flex class="mt-5">
                 <Dashboard/>
             </v-flex>
         </v-layout>
     </v-navigation-drawer>
     
    </nav>
    
 </template>
 <script>
 import Dashboard from '@/components/admin/Dashboard.vue'
 import NotificationServices from '@/services/NotificationServices';
 import { AuthUser } from "@/store/AuthStore";
 export default {
     components:{
        Dashboard
     },
     setup(){
        const store=AuthUser();
        return{
            store
        }
    },
    created(){
        this.getNotifs();
        this.getNotifsNotSeen();
    },
 data(){
     return{
         drawer:false,
         notifications:[],
        notificationNotSeen:[],
     }
 },
 methods:{
    logout(){
            this.store.logOut();
            this.$router.push({name:"login"});
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