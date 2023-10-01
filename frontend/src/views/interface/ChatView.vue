<template>
    <div id="app">
            <v-app-bar  flat style="background-color: #ffffff;margin-top: 20px;height: 100px;border-bottom: 0.1mm solid #CFD0D3;"> 
            <v-app-bar-nav-icon @click="drawer = true" 
                                class="d-flex d-sm-none" 
                                ></v-app-bar-nav-icon>
                <v-toolbar-title class="text-center-sm" style="margin-left: -50px;"><img src="../../assets/village2.png" width="300px"></v-toolbar-title> 
                        
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

                <v-list-item>
                   

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
           
            <v-layout class="mt-15" row>
                <v-flex xl3 md3 lg3 sm12 xs12 class="pa-5">
                    Tous les messages <v-icon>mdi-arrow-down</v-icon>
                    <div class="boite1 d-flex justify-center align-center" style="height: auto;margin-top: 15px;flex-direction: column;">
                        <div class="noAmis pa-5" v-if="this.amis.length==0" style="border: 0.005svb solid rgb(218, 217, 217);border-radius: 10px;">
                            <img src="../../assets/message1.png" alt="" width="auto" height="200px">
                            <p style="font-size: 20px;margin-top: 20px;">Pas de conversations</p>
                        </div>
                        <div class="Amis" style="width: 100%;">
                            <div class="users ma-3 pa-1">
                                <v-layout row class="user pa-4" v-for="ami in amis" :key="ami.id" @click="getAllChatPrivate(ami.id)">
                                        <v-flex xl4 md4 lg4 class="d-flex justify-center align-center" >
                                            <v-avatar class="mr-5" size="50">
                                                <img :src="ami.photo" alt="">
                                            </v-avatar>
                                        </v-flex>
                                        <v-flex xl8 md8 lg8>
                                            <p style="font-size: 15px;" class="mt-2">{{ami.firstName}} {{ ami.lastName }}</p>
                                            <p style="font-size: 12px;">{{ami.email}}</p>
                                        </v-flex>
                                </v-layout>
                            </div>
                        </div>
                    </div>
                </v-flex>
                <v-flex xl8 md8 lg8 sm12 xs12>
                    <div class="boite2" style="height: auto;border: 0.005svb solid rgb(218, 217, 217);border-radius: 10px;margin-top: 15px;flex-direction: column;margin-right:30px;">
                        <div class="PasMessage d-flex justify-center align-center" v-if="chats.length==0">
                             <img src="../../assets/message2.png" alt="" width="auto" height="250px">
                            <p style="font-size: 20px;margin-top: 20px;">Ah, une toute nouvelle boîte de réception</p>
                        </div>
                        <v-container class="ma-15" v-else>
                            <v-layout row>
                                <v-flex>
                                    <div class="title d-flex align-center justify-center">
                                        <h3>Discussion textuelle de groupe en temps réel en ligne</h3>
                                    </div>
                                    <v-divider class="title-divider"></v-divider>
                                </v-flex>
                            </v-layout>
                            <v-layout row wrap>
                                <v-flex> 
                                    <div class="scroll">
                                        <v-container>
                                            <div class="chat pa-5"  v-for="chat in chats" :key="chat.id">
                                                <div class="other" v-if="chat.userEnvoi['id']!=store.user['id']">
                                                    <v-layout wrap>
                                                        <v-flex>
                                                            <v-avatar size="35">
                                                                <v-img :src="chat.userEnvoi['photo']"></v-img>
                                                            </v-avatar>
                                                            <span class="name ml-4">{{ chat.userEnvoi['firstName'] }}<span class="time" v-if="chat.created_at!=null"> {{chat.created_at}}</span></span>
                                                        </v-flex>
                                                    </v-layout>
                                                    <v-layout wrap>
                                                        <v-flex class="mr-5">
                                                            <div class="mt-4 float-start" style="border-radius: 50px;background-color: #a9baef;padding: 20px 40px 20px 40px; ">
                                                                <span class="message">{{ chat.message }}</span>
                                                            </div>
                                                        </v-flex>
                                                    </v-layout>
                                                </div>


                                                <div class="mine float-end mr-5 py-5" v-else >
                                                    <v-layout wrap>
                                                        <v-flex>
                                                            <v-avatar size="35">
                                                                <v-img :src="store.user['photo']"></v-img>
                                                            </v-avatar>
                                                            <span class="name ml-5">Moi<span class="time mr-4">{{chat.created_at}}</span></span>
                                                        </v-flex>
                                                    </v-layout>
                                                    <v-layout wrap>
                                                            <v-flex class="mr-5">
                                                                <div class="mt-4 float-end" style="border-radius: 50px;background-color: #CFD0D3;padding: 20px 40px 20px 40px; ">
                                                                    <span class="message">{{ chat.message }}</span>
                                                                </div>
                                                            </v-flex>
                                                    </v-layout>
                                                </div>   
                                            </div>
                                            
                                        </v-container>
                                        
                                    </div>
                                </v-flex>
                            </v-layout>
                        </v-container>
                        <v-layout row>
                            <v-flex>
                                <div class="container-input">
                                    
                                    <form @submit.prevent="addPost()" class="">
                                        <v-container class="d-flex wrap">
                                            <input type="text" placeholder="...." name="text" class="input float-start" style="width: 70%;" v-model="message">
                                            <v-btn class="button float-end mr-10 ml-5 text-body-2" type="submit" text>Envoyer</v-btn>
                                        </v-container>
                                    </form>                                              
                                </div>
                            </v-flex >
                        </v-layout>
                    </div>
                </v-flex>
            </v-layout>
    </div>
</template>
<script>
import { AuthUser } from "@/store/AuthStore";
import NotificationServices from '@/services/NotificationServices';
import ChatServices from "@/services/ChatServices"
export default {
    setup(){
        const store=AuthUser();
        return{
            store
        }
    },
    created(){
        this.getNotifs();
        this.getNotifsNotSeen();
        this.getAmis();
    },
    name:'ChatView',
data(){
    return{
        drawer: false,
        notifications:[],
        notificationNotSeen:[],
        chats:[],
        amis:[],
        id:-1,
        message:"",
        
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

         getAllChatPrivate(id){
            ChatServices.getAllChatPrivate(id).then((res)=>{
                this.chats=res.data;
                this.id=id;
                console.log(this.chats);
            }).catch((err)=>{
                console.log(err);
            });
         },
         getAmis(){
            ChatServices.getAmis().then((res)=>{
                this.amis=res.data;
                console.log(this.amis[0].id);
            }).catch((err)=>{
                console.log(err);
            })
         },

         addPost(){
            ChatServices.addMessagePrivate(this.id,this.message).then((res)=>{
                console.log(res.data);
                location.reload();
            }).catch((err)=>{
                console.log(err);
            });
         }
    
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


.user:hover{
    background-color: rgb(236, 234, 234);
    cursor: pointer;
}

.message-mine{
        border: solid #4e538d 2px;
        padding: 20px;
        margin-right: 20px;
        border-radius: 10px;
        box-sizing:border-box;
        max-width: 85%;
        background-color: #ffffff;
        text-align: justify;
        display: inline-block;
        display: table;
}
    .title{
        margin-top: 10px;
        color: #27272d;
        width: auto;
    }
    .title-divider{
        margin-top: 34px;
        width: 100%;
    }
    
    .users{
        height: 100vh;
    }
    
    .v-navigation-drawer__content{
        width: 100% !important;
    }
    
    .message-div{
        border: solid #525fe1 2px;
        padding: 20px;
        margin-right: 20px;
        border-radius: 10px;
        box-sizing:border-box;
        max-width: 85%;
        background-color: #f0f1ff;
    }
    .message{
        text-align: justify;
        font-size: 15px;
    }
    .name{
        font-size: 15px;
        font-weight: bolder;
        
    }
    .time{
        font-size: 10px;
        margin-left: 5px;
        color: rgb(135, 133, 133);
        font-weight: lighter;
    }
    *{
        overflow: hidden;
    }
    
    .scroll{
        overflow-y: scroll;
        height: auto;
        scroll-margin-block-start: 40px;
        scrollbar-width: none;
        scrollbar-color: #12c2b9;
    }
    .list-users{
        overflow-y: scroll;
        height: 70vh;
        scroll-margin-block-start: 40px;
        scrollbar-width: none;
        scrollbar-color: #12c2b9;
    }
    
    .container-input {
      margin: 20px;
    }
    
    .input {
      width: 80%;
      border-radius: 15px;
      padding: 10px 0px 10px 40px;
      border: 0.005svb solid rgb(218, 217, 217);
      transition: all .2s ease-in-out;
      outline: none;
      opacity: 0.8;
      float: inline-end;
     
    }
</style>