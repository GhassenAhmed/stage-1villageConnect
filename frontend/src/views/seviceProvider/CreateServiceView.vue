<template>
    <div class="root">
        <v-app-bar  flat style="background-color: #ffffff;margin-top: 20px;height: 100px;border-bottom: 0.1mm solid #CFD0D3;"> 
            <v-app-bar-nav-icon @click="drawer = true" 
                                class="d-flex d-sm-none" 
                                ></v-app-bar-nav-icon>
                <v-toolbar-title class="text-center-sm" style="font-size: 25px;font-weight: bolder;">Village<span style="color: #105955d1;">Connect<span style="font-weight: bolder;font-size: 35px;color: #12c2b9;">.</span></span></v-toolbar-title> 
                        
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
                <v-list-item>
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
                <v-layout row wrap class="ma-5">
                    <v-flex xl4 md4 lg4 sm12 xs12>
                        <p style="font-size: 25px;font-weight: bold;color: rgb(57, 56, 56);">Veuillez remplir tous les champs</p>
                        <p style="margin-top: 20px;font-size: 25px;font-weight: bold;font-weight: bold;color: rgb(57, 56, 56);">obligatoires...</p>
                        <img src="../../assets/form.png" alt="" width="350px">
                    </v-flex>
                    <v-flex xl8 md8 lg8 sm12 xs12>
                        <div class="form" style="margin-left: 15%;margin-right: 10%;overflow-y: scroll;height: 70vh;">
                            <div class="header d-flex pa-1" style="background-color: #F0F4FF;color: #284389;">
                                <v-icon color="#284389">mdi-arrow-right-thick</v-icon><p style="font-size: 20px;margin-top: 14px;margin-left: 15px;">Soyez concis et direct.</p>
                            </div>
                            <form action="" class="mt-5" enctype="multipart/form-data">
                                <div class="nom mb-4 mt-4">
                                    <span>Donnez un titre bref à votre service. *</span>
                                    <v-text-field
                                    v-model="serviceName"
                                    label="Nom du service"
                                    required
                                    ></v-text-field>
                                </div>

                                <div class="description mb-4 mt-4">
                                    <span>Donnez un titre bref à votre service. *</span>
                                    <v-textarea
                                    v-model="description"
                                    label="Description"
                                    required
                                    
                                    ></v-textarea>
                                </div>
                                
                                <div class="adresse mb-4 mt-4">
                                    <span>Donnez votre precise adresse. *</span>
                                    <v-text-field
                                    v-model="adresse"
                                    label="Adresse"
                                    required
                                    
                                    ></v-text-field>
                                </div>

                                <div class="Categorie mb-4 mt-4">
                                    <span>Choisir categorie. *</span>
                                    <v-select
                                    :items="villagesNames"
                                    label="Choisir categorie"
                                    ></v-select>
                                </div>

                                <div class="village mb-4 mt-4">
                                    <span>Choisir village. *</span>
                                    <v-select
                                    :items="categories"
                                    label="Choisir village"
                                    ></v-select>
                                </div>

                                <div class="Maximum-prix mb-4 mt-4">
                                    <span>Maximum prix.</span>
                                    <v-text-field
                                    v-model="maxPrice"
                                    label="Maximum prix"
                                    value="0"
                                    prefix="$"
                                    ></v-text-field>
                                </div>

                                <div class="Minimum-prix mb-4 mt-4">
                                    <span>Minimum prix.</span>
                                    <v-text-field
                                    v-model="minPrice"
                                    label="Minimum prix"
                                    value="0"
                                    prefix="$"
                                    ></v-text-field>
                                </div>
                                
                                <div class="lien mb-4 mt-4">
                                    <span>Lien. </span>
                                    <v-text-field
                                    v-model="thumbnailUrl"
                                    label="Http//...."
                                    required
                                    
                                    ></v-text-field>
                                </div>

                                <div class="years mb-4 mt-4">
                                    <span>Années d'expérience. </span>
                                    <v-text-field
                                    v-model="yearsInBusiness"
                                    required
                                    prefix="Ans"
                                    ></v-text-field>
                                </div>  

                                <div class="photo mb-4 mt-4">
                                    <span>Photo du votre service. </span>
                                    <input type="file" name="photo" @change="base64()">
                                </div>  
                            </form>
                        </div>
                    </v-flex>
                </v-layout>
            </v-container>
    </div>
</template>
<script>
import { AuthUser } from "@/store/AuthStore";
import VillageServices from "@/services/VillageServices";
import CategorieServices from '@/services/CategorieServices';
export default {
    setup(){
        const store=AuthUser();
        return{
            store
        }
    },
    created(){
        this.getVillages();
        this.getCategories();
    },
    data(){
        return{
            drawer: false,
            villages:[],
            villagesNames:[],
            categories:[],
            service:{
                serviceName:"",
                description:"",
                adresse:"",
                maxPrice:0,
                minPrice:0,
                thumbnailUrl:"",
                isBackgroundVerified:0,
                photo:"",
                yearsInBusiness:null,


            }

        }
    },
    methods:{
        base64(){
            const file = document.querySelector("#file").files[0];
            const reader = new FileReader();
            reader.onloadend = () => {
               this.form.photo = reader.result;
           };
           reader.readAsDataURL(file);
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
.form,span{
    color:#284389;
}
</style>