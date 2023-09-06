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
                           Edit
                        </th>
                        </tr>
                    </thead>
                    <tbody>
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
                        </td>
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
export default {
    setup(){
        const store=AuthUser();
        return{
            store
        }
    },
    created(){
        this.getServicesById();
    },
    components:{
    Footer
    },
    data(){
        return{
            vertical: true,
            drawer: false,
            loading:false,
            services:[]
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
.form span{
    color:#284389;
}
.container{
    height: 100vh;
}
</style>