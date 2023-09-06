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
                    <v-list-item v-if="store.IsServiceProvider==true">  
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
            <v-container fluid class="mt-5 d-flex justify-center">
                <v-card style="padding: 15px;" class="edit-profile">
                        <div class="mb-5 d-flex justify-center">
                           <v-avatar size="120" >
                                <img :src="service.photo" alt="">
                            </v-avatar>
                        </div>
                        <input
                        name="file"
                        id="file"
                        label="Votre image"
                        class="align-center"
                        :error-messages="photo_error"
                        @change="saveImage()"
                        type="file"
                        >
                        <br>
                          <v-btn type="submit" style="color:#fff" class="mt-5 text-center" color="#12c2b9"   :loading="loadimage" @click="SavePhoto()">Upload</v-btn>
                </v-card>
            </v-container>
            <v-container class="mt-10 py-10">
                <form  @submit.prevent="EditService()" enctype="multipart/form-data">

                <v-card style="padding: 15px;" class="">
                <v-layout flex wrap class="pa-5">
                    
                        <v-flex xl6 md6 lg6 sm6 xs12 class="pr-10">
                            <span style="font-size: 20px;">Nom du service</span>
                            <v-text-field
                            v-model="form.serviceName"
                                color="purple darken-2"
                                required
                            ></v-text-field>

                            <span>Adresse</span>
                            <v-text-field
                            v-model="form.adresse"
                                color="purple darken-2"
                                required
                            ></v-text-field>

                            <span style="font-size: 20px;">Telephone</span>
                            <v-text-field
                            v-model="form.phone"
                                color="purple darken-2"
                                required
                            ></v-text-field>

                            <span style="font-size: 20px; ">Description</span>
                            <v-textarea
                            v-model="form.description"
                                color="purple darken-2"
                                required
                            ></v-textarea>

                        </v-flex>
                        
                        <v-flex xl6 md6 lg6 sm6 xs12>

                            <span style="font-size: 20px; ">Lien</span>
                            <v-text-field
                                v-model="form.thumbnailUrl"
                                color="purple darken-2"
                                required
                            ></v-text-field>

                            <span style="font-size: 20px; ">Depuis</span>
                            <v-text-field
                            v-model="form.yearsInBusiness"
                                color="purple darken-2"
                                required
                            ></v-text-field>

                            <span style="font-size: 20px; ">Min Prix</span>
                            <v-text-field
                            v-model="form.minPrice"
                                color="purple darken-2"
                                required
                            ></v-text-field>

                            <span style="font-size: 20px; ">Max Prix</span>
                            <v-text-field
                            v-model="form.maxPrice"
                                color="purple darken-2"
                                required
                            ></v-text-field>

                        </v-flex>
                        <v-flex>
                            <v-btn type="submit">Edit</v-btn>
                        </v-flex>
                    
                </v-layout>
                </v-card>
                </form>
            </v-container>
            <Footer class="mt-15"></Footer>
    </div>
</template>
<script>
import { required} from "vuelidate/lib/validators"
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
        this.id=this.$route.params.id;
        this.getServicebyId();
    },
    components:{
    Footer
    },
    data(){
        return{
            vertical: true,
            drawer: false,
            loading:false,
            id:"",
            service:[],
            photo:"",
            photo:"",
            loadimage:false,
            snackbar:false,
            message:"",
            form:{
                serviceName:"",
                adresse:"",
                description:"",
                phone:"",
                thumbnailUrl:"",
                yearsInBusiness:"",
                maxPrice:"",
                minPrice:"",
            },
            loadingEdit:false,
        }
    },
    methods:{
        logout(){
            this.store.logOut();
            this.$router.push({name:"login"});
        },
        getServicebyId(){
            ServiceServices.getServiceDetailsById(this.id).then((res)=>{
                this.service=res.data;
                this.form.serviceName=this.service.serviceName;
                this.form.adresse=this.service.adresse;
                this.form.phone=this.service.phone;
                this.form.description=this.service.description;
                this.form.yearsInBusiness=this.service.yearsInBusiness;
                this.form.maxPrice=this.service.maxPrice;
                this.form.minPrice=this.service.minPrice;
                this.form.thumbnailUrl=this.service.thumbnailUrl;
                console.log(this.service);
            }).catch((err)=>{
                console.log(err);
            })
        },
        saveImage(){
            const file = document.querySelector("#file").files[0];
            const reader = new FileReader();
            reader.onloadend = () => {
                this.photo= reader.result;
           };
           reader.readAsDataURL(file);
        },
        SavePhoto(){
            this.loadimage=true;
            
            ServiceServices.updatePhoto(this.id,{
                 'photo':this.photo
                }).then((res)=>{
                    console.log(res.data);
                    setTimeout(() => {
                        this.loadimage=false;
                    }, 3000);
                    
                    
            }).catch((error)=>{
                this.loadimage=false;
                console.log(error);
            })
        },
        EditService(){
            this.loadingEdit=true;;
            ServiceServices.EditService(this.id,{
                "serviceName":this.form.serviceName,
                "adresse":this.form.adresse,
                "phone":this.form.phone,
                "thumbnailUrl":this.form.thumbnailUrl,
                "description":this.form.description,
                "minPrice":this.form.minPrice,
                "maxPrice":this.form.maxPrice,
                "yearsInBusiness":this.form.yearsInBusiness,
            }).then((res)=>{
                this.loadingEdit=false;
                console.log(res.data);
            }).catch((err)=>{
                console.log(err);
            })
        }

    },
    computed:{
        photo_error(){
            const error=[];
            if(!this.$v.photo.$dirty) return error;
            !this.$v.photo.required && error.push("image Required");
            !this.$v.photo.typeFile && error.push("image must be of type ( jpg, jpeg, png, svg, gif)");
            return error;
        },
    },
    validations:{
         photo:{
               required,
               typeFile(val){
                     const tab_ext_dispo=['jpg','gif','png','svg','jpeg'];
                     const extention=val.split(';')[0].split('/')[1];
                     return tab_ext_dispo.find((v)=>v==extention) ? true : false ;
                }
            },
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
.container{
    height: auto;
}
.edit-profile{
display: flex;
flex-direction: column;
}
</style>