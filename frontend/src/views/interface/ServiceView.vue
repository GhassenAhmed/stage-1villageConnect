<template>
    <div id="app">
            <v-app-bar  flat style="background-color: #ffffff;margin-top: 20px;height: 100px;border-bottom: 0.1mm solid #CFD0D3;"> 
            <v-app-bar-nav-icon @click="drawer = true" 
                                class="d-flex d-sm-none" 
                                ></v-app-bar-nav-icon>
                <v-toolbar-title class="text-center-sm hidden-sm-and-down" style="font-size: 25px;font-weight: bolder;">Village<span style="color: #105955d1;">Connect<span style="font-weight: bolder;font-size: 35px;color: #12c2b9;">.</span></span></v-toolbar-title> 
                        <v-autocomplete
                        :items="villagesNames"
                        filled
                        rounded
                        style="height: 60px;max-width: auto;"
                        class="ml-lg-14 ml-md-12 ml-sm-10" 
                        ></v-autocomplete>
                        <v-spacer></v-spacer>
        
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
            <v-list
                nav
                dense
            >
                <v-list-item-group
                >
                <v-list-item >
                    <v-list-item-title></v-list-item-title>
                </v-list-item>

                </v-list-item-group>
            </v-list>
            </v-navigation-drawer>
            <Content style="margin-top: 10px;"></Content>
    </div>
</template>
<script>
import Content from '../../components/service/Content.vue'
import { AuthUser } from "@/store/AuthStore";
import VillageServices from "@/services/VillageServices";
export default {
    setup(){
        const store=AuthUser();
        return{
            store
        }
    },
    created(){
        this.getVillages();
    },
    name:'ServiceView',
components:{
    Content
},
data(){
    return{
        drawer: false,
        villages:[],
        villagesNames:[]
        
    }
},
methods:{
   getVillages(){
    VillageServices.getAllVillages().then((res)=>{
        this.villages=res.data;
        for(let i=0 ;i<this.villages.length;i++){
            this.villagesNames.push(this.villages[i].villageName)
        }
    }).catch((err)=>{
        console.log(err);
    })
   }
    
},
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