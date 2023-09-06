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

            <v-container fluid class="mt-5">
                <form @submit.prevent="addService()" class="mt-5" enctype="multipart/form-data">
                <v-layout row wrap class="ma-5">
                    <v-flex xl4 md4 lg4 sm12 xs12>
                        <p style="font-size: 25px;font-weight: bold;color: rgb(57, 56, 56);">Veuillez remplir tous les champs</p>
                        <p style="margin-top: 20px;font-size: 25px;font-weight: bold;font-weight: bold;color: rgb(57, 56, 56);">obligatoires...</p>
                        <img src="../../assets/form.png" alt="" width="350px" class="hidden-sm-and-down">
                    </v-flex>
                    <v-flex xl8 md8 lg8 sm12 xs12>
                        <div class="form" style="margin-left: 5%;margin-right: 5%;overflow-y: scroll;height: 70vh;">
                            <div class="header d-flex pa-1" style="background-color: #F0F4FF;color: #284389;">
                                <v-icon color="#284389">mdi-arrow-right-thick</v-icon><p style="font-size: 20px;margin-top: 14px;margin-left: 15px;">Soyez concis et direct.</p>
                            </div>
                            
                                <div class="nom mb-4 mt-4">
                                    <span>Donnez un titre bref à votre service. *</span>
                                    <v-text-field
                                    v-model="formData.form.serviceName"
                                    :error-messages="serviceNameError"
                                    placeholder="Nom du service"
                                    outlined
                                    class="py-5"
                                    style="width: 95%;"
                                    ></v-text-field>
                                   
                                </div>

                                <div class="description mb-4 mt-4">
                                    <span>Donnez un titre bref à votre service. *</span>
                                    <v-textarea
                                    v-model="formData.form.description"
                                    placeholder="Description sur votre service"
                                    :error-messages="descriptionError"
                                    outlined
                                    class="py-5"
                                    style="width: 95%;"
                                    ></v-textarea>
                                </div>
                                
                                <div class="adresse mb-4 mt-4">
                                    <span>Donnez votre precise adresse. *</span>
                                    <v-text-field
                                    v-model="formData.form.adresse"
                                    placeholder="Adresse du votre service"
                                   :error-messages="adresseError"
                                    outlined
                                    class="py-5"
                                    style="width: 95%;"
                                    ></v-text-field>
                                </div>

                                <div class="phone mb-4 mt-4">
                                    <span>Donnez votre numero telephone. *</span>
                                    <v-text-field
                                    v-model="formData.form.phone"
                                    placeholder="numero du telephone"
                                    :error-messages="phoneError"
                                    outlined
                                    class="py-5"
                                    style="width: 95%;"
                                    ></v-text-field>
                                </div>

                                <div class="Categorie mb-4 mt-4">
                                    <span>Choisir categorie. *</span>
                                    <v-select
                                    
                                    outlined
                                    class="py-5"
                                    style="width: 95%;"
                                    v-model="formData.categorie_id"
                                    :items="categorieNames"
                                    placeholder="Categorie"
                                    ></v-select>
                                </div>

                                <div class="village mb-4 mt-4">
                                    <span>Choisir village. *</span>
                                    <v-select
                                    outlined
                                    class="py-5"
                                    style="width: 95%;"
                                    
                                    v-model="formData.village_id"
                                    :items="villagesNames"
                                    placeholder="Village"
                                    ></v-select>
                                </div>

                                <div class="Maximum-prix mb-4 mt-4">
                                    <span>Maximum prix.</span>
                                    <v-text-field
                                    
                                    outlined
                                    class="py-5"
                                    style="width: 95%;"
                                    v-model="formData.form.maxPrice"
                                    placeholder="Maximum price"
                                    :error-messages="maxError"
                                    value="0"
                                    prefix="$"
                                    ></v-text-field>
                                </div>

                                <div class="Minimum-prix mb-4 mt-4">
                                    <span>Minimum prix.</span>
                                    <v-text-field
                                    outlined
                                    
                                    class="py-5"
                                    style="width: 95%;"
                                    v-model="formData.form.minPrice"
                                    placeholder="Minimum price"
                                    :error-messages="minError"
                                    value="0"
                                    prefix="$"
                                    ></v-text-field>
                                </div>
                                
                                <div class="lien mb-4 mt-4">
                                    <span>Mettez un lien utile pour plus d'informations.</span>
                                    <v-text-field
                                    outlined
                                    :error-messages="lienError"
                                    class="py-5"
                                    style="width: 95%;"
                                    v-model="formData.form.thumbnailUrl"
                                    placeholder="Http...."
                                    
                                    
                                    ></v-text-field>
                                </div>

                                <div class="years mb-4 mt-4">
                                    <span>Années d'expérience. </span>
                                    <v-text-field
                                    outlined
                                    
                                    class="py-5"
                                    style="width: 95%;"
                                    v-model="formData.form.yearsInBusiness"
                                    :error-messages="yearsError"
                                    placeholder="Annees d'experience"
                                    
                                    ></v-text-field>
                                </div>  

                                <div class="photo mb-4 mt-4">
                                    <span>Mettez une image sur votre service </span><br>
                                    <input name="file"
                                        id="file"
                                        class="py-5"
                                        label="Your Photo"
                                        style="width: 90%;font-size: 90%;"
                                        @change="base64()"
                                        type="file"
                                        ref="photo">
                                        <div class="err" style="color: red;" v-if="photo_err">
                                            L'image doit être du type (jpg, jpeg, png, svg, gif)
                                        </div>
                                </div> 
                        </div>
                        
                    </v-flex>
                   
                </v-layout>
                <v-btn type="submit"
                    class="float-end mr-15"
                    
                    :loading="loading"
                    fab
                    dark
                    color="indigo">
                    <v-icon>mdi-send</v-icon>
                </v-btn> 
            </form>
            </v-container>
                
            <template>
                <div class="text-center">

                    <v-snackbar
                    v-model="snackbar"
                    color="success"
                    outlined
                    :vertical="vertical"
                    >
                    {{ text }}

                    <template v-slot:action="{ attrs }">
                        <v-btn
                        color="red"
                        text
                        v-bind="attrs"
                        @click="snackbar = false"
                        >
                        Close
                        </v-btn>
                    </template>
                    </v-snackbar>
                    
                </div>
            </template>
    </div>
</template>
<script>
import { AuthUser } from "@/store/AuthStore";
import VillageServices from "@/services/VillageServices";
import CategorieServices from '@/services/CategorieServices';
import ServiceServices from '@/services/ServiceServices';
import {required,minLength,maxLength} from "vuelidate/lib/validators";
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
            snackbar: false,
            text: 'Votre service a ete creer ,SVP attend l administrateur pour l accepter ',
            vertical: true,
            drawer: false,
            villages:[],
            villagesNames:[],
            categories:[],
            categorieNames:[],
            photo_err:false,
            loading:false,
            formData: {
                form: {
                    serviceName:"",
                    description:"",
                    adresse:"",
                    maxPrice:0,
                    minPrice:0,
                    phone:"",
                    thumbnailUrl:"",
                    photo:"",
                    yearsInBusiness:null,
                },
                categorie_id:null,
                village_id: null
            }

        }
    },
    validations:{
        formData: {
                form: {
                    serviceName:{
                        required,
                        maxLength:maxLength(15),
                    },
                    description:{
                        required,
                        maxLength:maxLength(100),
                        minLength:minLength(20)
                    },
                    adresse:{
                        required,
                        maxLength:maxLength(20),
                        minLength:minLength(5),
                    },
                    phone:{
                        required,
                        maxLength:maxLength(8),
                        minLength:minLength(8),
                    notContainsLetter: function(value) {
                        return /^[0-9]+$/.test(value);
                    },
                    },
                    maxPrice:{
                    notContainsLetter: function(value) {
                        return /^[0-9]+$/.test(value);
                    },
                    },
                    minPrice:{
                    notContainsLetter: function(value) {
                        return /^[0-9]+$/.test(value);
                    },
                    },
                    thumbnailUrl:{
                        maxLength:maxLength(20),
                        minLength:minLength(5),
                    },
                    yearsInBusiness:{
                        notContainsLetter: function(value) {
                        return /^[0-9]+$/.test(value);
                        },
                    },
                    photo:{
                    typeFile(val){
                     const tab_ext_dispo=['jpg','gif','png','svg','jpeg'];
                     const extention=val.split(';')[0].split('/')[1];
                     return tab_ext_dispo.find((v)=>v==extention) ? true : false ;
                    }
                    }
                }
            }
    },
    methods:{
        logout(){
            this.store.logOut();
            this.$router.push({name:"login"});
        },
        base64(){
            const file = document.querySelector("#file").files[0];
            const reader = new FileReader();
            reader.onloadend = () => {
               this.formData.form.photo = reader.result;
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
                for(let i=0 ;i<this.categories.length;i++){
                this.categorieNames.push(this.categories[i].categorieName)
            }
                
            }).catch((err)=>{
                console.log(err);
            })
        },
        addService(){
            this.$v.formData.form.$touch();
            if(this.$v.formData.form.$invalid){
                    this.loading=false;
                    return;
                }
            this.loading=true;
            setTimeout(() => {
                this.loading=false;
                this.snackbar=true;
            }, 3000);
            ServiceServices.createService({
                "service":{
                "serviceName":this.formData.form.serviceName,
                "description":this.formData.form.description,
                "adresse":this.formData.form.adresse,
                "phone":this.formData.form.phone,
                "maxPrice":this.formData.form.maxPrice,
                "minPrice":this.formData.form.minPrice,
                "thumbnailUrl":this.formData.form.thumbnailUrl,
                "photo":this.formData.form.photo,
                "yearsInBusiness":this.formData.form.yearsInBusiness, 
                },
                "categorie_id":this.categorieNames.indexOf(this.formData.categorie_id)+1,
                "village_id":this.villagesNames.indexOf(this.formData.village_id)+1
            }).then((res)=>{
                    console.log(res.data);
                    this.formData.form.serviceName="",
                    this.formData.form.description="",
                    this.formData.form.adresse="",
                    this.formData.form.phone="",
                    this.formData.form.maxPrice="",
                    this.formData.form.minPrice="",this.formData.
                    this.formData.form.thumbnailUrl="",
                    this.formData.form.isBackgroundVerified="",
                    this.formData.form.photo="",
                    this.formData.form.yearsInBusiness="",
                    this.formData.categorie_id="",
                    this.formData.village_id=""
                }).catch((err)=>{
                console.log(err);
            })
        }
    },
    computed:{
        serviceNameError(){
              const error=[];
              if(!this.$v.formData.form.serviceName.$dirty) return error;
              !this.$v.formData.form.serviceName.required && error.push("Nom du service requis");
              !this.$v.formData.form.serviceName.maxLength && error.push("Veuillez entrer le nom  avec un maximum de 15 caractères");
              return error;
          },
        },
        adresseError(){
              const error=[];
              if(!this.$v.formData.form.adresse.$dirty) return error;
              !this.$v.formData.form.adresse.required && error.push("Adresse du service requis");
              !this.$v.formData.form.adresse.maxLength && error.push("Veuillez entrer l adresse  avec un maximum de 20 caractères");
              !this.$v.formData.form.adresse.minlength && error.push("Veuillez entrer l adresse  avec un minimum de 5 caractères ");
              return error;
          },
          descriptionError(){
              const error=[];
              if(!this.$v.formData.form.description.$dirty) return error;
              !this.$v.formData.form.description.required && error.push("description du service requis");
              !this.$v.formData.form.description.maxLength && error.push("Veuillez entrer la description  avec un maximum de 100 caractères");
              !this.$v.formData.form.description.minlength && error.push("Veuillez entrer la description  avec un minimum de 20 caractères ");
              return error;
          },
          phoneError(){
              const error=[];
              if(!this.$v.formData.form.phone.$dirty) return error;
              !this.$v.formData.form.phone.required && error.push("description du phone requis");
              !this.$v.formData.form.phone.maxLength && error.push("Veuillez entrer l adresse  avec un maximum de 8 caractères");
              !this.$v.formData.form.phone.minlength && error.push("Veuillez entrer l adresse  avec un minimum de 8 caractères ");
              !this.$v.formData.form.phone.notContainsLetter && error.push("Phone seulement du numeros");
              return error;
              
          },

          maxError(){
              const error=[];
              if(!this.$v.formData.form.maxPrice.$dirty) return error;
              !this.$v.formData.form.maxPrice.notContainsLetter && error.push("Seulement du numeros");
              return error;
              
          },
          minError(){
              const error=[];
              if(!this.$v.formData.form.minPrice.$dirty) return error;
              !this.$v.formData.form.minPrice.notContainsLetter && error.push("Seulement du numeros");
              return error;
              
          },
          yearsError(){
              const error=[];
              if(!this.$v.formData.form.yearsInBusiness.$dirty) return error;
              !this.$v.formData.form.yearsInBusiness.notContainsLetter && error.push("Seulement du numeros");
              return error;
              
          },
          lienError(){
              const error=[];
              if(!this.$v.formData.form.thumbnailUrl.$dirty) return error;
              !this.$v.formData.form.thumbnailUrl.maxLength && error.push("Veuillez entrer    un maximum de 20 caractères");
              !this.$v.formData.form.thumbnailUrl.minlength && error.push("Veuillez entrer un minimum de 5 caractères ");
              return error;
          },
          photo_error(){
              const error=[];
              if(!this.$v.form.photo.$dirty) return error;
              !this.$v.form.photo.typeFile && error.push("L'image doit être du type (jpg, jpeg, png, svg, gif)")&&this.photo_err==true;
              return error; 
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
.form span{
    color:#284389;
}
</style>