<template>
    <div class="text-center py-5 mt-5">
        <v-container>
            <div class="row">
                <div class="col-lg-4">
                        <v-card style="padding: 15px;" class="edit-profile ">
                            <div class="mb-5">
                            <span class="text">
                                {{store.user['firstName'].charAt(0).toUpperCase() +""+store.user['firstName'].substring(1,(store.user['firstName']).length)}}
                            </span>
                            <span class="mb-3 text">
                                {{" "+store.user['lastName'].charAt(0).toUpperCase() +""+store.user['lastName'].substring(1,(store.user['lastName']).length)}}
                            </span>
                        </div>
                            <div class="mb-5">
                           <v-avatar size="120" >
                                <img :src="store.user['photo']" alt="">
                            </v-avatar>
                        </div>
                        <input
                        name="file"
                        id="file"
                        label="Votre image"
                        :error-messages="photo_error"
                        @change="saveImage()"
                        type="file"
                        >
                        <br>
                          <v-btn type="submit" style="color:#fff" class="mt-5" color="#12c2b9"  :loading="loadimage" @click="SavePhoto()">Upload</v-btn>
                             <p class="py-3" >
                                Member Since: {{ store.user['created_at'].substring(0,10) }}
                             </p>
                         </v-card>
                </div>
                <div class="col-lg-8">
                    <v-card >
                        <template class="mt-5 py-5">
                            <v-card >
                                <v-tabs
                                fixed-tabs
                                background-color="#12c2b9"
                                dark
                            >
                                <v-tab @click="page=0">
                                    Update Info Personnel
                                </v-tab>
                                <v-tab @click="page=1">
                                    Update Password
                                </v-tab>
                                <v-tab @click="page=2">
                                    Update Email
                                </v-tab>
                            </v-tabs>
                            <v-card-text v-if="page==0" >
                            <form  @submit.prevent="EditUser()" enctype="multipart/form-data">
                                <div class="">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <v-text-field
                                            v-model="form.firstName"
                                            :error-messages="firstname_error"
                                            
                                        ></v-text-field>
                                        </div>
                                        <div class="col-lg-6">
                                            <v-text-field
                                            v-model="form.lastName"
                                            :error-messages="lastname_error"
                                            
                                        ></v-text-field>
                                        </div>
                                        <div class="col-lg-12">
                                            <v-text-field
                                                readonly
                                                v-model="form.email"
                                                type="email"
                                                
                                            ></v-text-field>
                                        </div>
                                    </div>
                                </div>
                            <v-divider></v-divider>
                            <v-card-actions class="justify-center py-3">
                                <v-btn :loading="loadingEdit" style="color:#fff" class="mx-2" type="submit" color="#12c2b9">
                                    Updated
                                </v-btn>
                            </v-card-actions>
                            </form>
                            </v-card-text>
                             <v-card-text v-else-if="page==1">
                            <changerPassword></changerPassword>
                            </v-card-text>
                            <v-card-text v-else>
                                <ChangerEmail></ChangerEmail>
                            </v-card-text>
                            </v-card>
                            <v-snackbar
                            color="#12c2b9"
                            v-model="snackbar"
                        >
                            {{ message }}
                            <template v-slot:action="{ attrs }">
                            <v-btn
                                color="#000"
                                text
                                v-bind="attrs"
                                @click="snackbar = false"
                            >
                                Close
                            </v-btn>
                            
                            </template>
                           </v-snackbar>
                        </template>
                    </v-card>
                 </div>
            </div>
         </v-container>
         <v-snackbar
         color="#12c2b9"
         v-model="snackbar"
       >
         {{ message }}
         <template v-slot:action="{ attrs }">
           <v-btn
             color="#000"
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
<script>
import { required,minLength,maxLength} from "vuelidate/lib/validators"
import { AuthUser } from "@/store/AuthStore.js";
import userInfos from "@/services/UserInfos.js"
import EditProfilServices from "@/services/EditProfilServices.js";
import ChangerPassword from "./ChangerPassword.vue";
import ChangerEmail from "./ChangerEmail.vue";
export default { 
    setup(){
        const store = AuthUser();
        return {store};
    },
    created(){
        userInfos.getUserAuthentifie().then((res)=>{
            this.form.firstName=res.data['firstName'];
            this.form.lastName=res.data['lastName'];
            this.form.email=res.data['email'];
        })
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
            form:{
                firstName:{
                    required,
                    minlength:minLength(3),
                    maxLength:maxLength(10)
                },
                lastName:{
                    required,
                    minlength:minLength(3),
                    maxLength:maxLength(10)
                },
             }
    },
    data(){
        return{
            photo:"",
            loadimage:false,
            snackbar:false,
            message:"",
            page:0,
            loadingEdit:false,
            message:"",
            snackbar:false,
            form:{
                firstName:"",
                lastName:"",
                email:""
            }
        }
    },
    methods:{
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
            this.$v.photo.$touch();
            if(this.$v.photo.$invalid){
                this.loadimage=false;
                return;
            }
            EditProfilServices.uploadPhoto({
                 'photo':this.photo
                }).then((res)=>{
                    userInfos.getUserAuthentifie().then((res)=>{
                    this.store.SetUser(res.data);
                })
                this.loadimage=false;
                this.message=res.data;
                this.snackbar=true;
            }).catch((error)=>{
                this.loadimage=false;
                console.log(error);
            })
        },
        EditUser(){
            this.loadingEdit=true;
            this.$v.form.$touch();
            if(this.$v.form.$invalid){
                this.loadingEdit=false;
                return;
            }
            EditProfilServices.EditInfoPersonnel({
                "firstName":this.form.firstName,
                "lastName":this.form.lastName,
            }).then((res)=>{
                console.log(this.form.firstName,this.form.lastName);
                userInfos.getUserAuthentifie().then((res)=>{
                    this.store.SetUser(res.data);
                });
                this.loadingEdit=false;
                this.message=res.data;
                this.snackbar=true;
            }).catch((error)=>{
                this.loadingEdit=false;
                console.log(error);
            })
        }
    },
    computed:{
        photo_error(){
            // const error=[];
            // if(!this.$v.photo.$dirty) return error;
            // !this.$v.photo.required && error.push("image Required");
            // !this.$v.photo.typeFile && error.push("image must be of type ( jpg, jpeg, png, svg, gif)");
            // return error;
        },
        lastname_error(){
            const error=[];
            // if(!this.$v.form.lastName.$dirty) return error;
            // !this.$v.form.lastName.required && error.push("LastName required");
            // !this.$v.form.lastName.maxLength && error.push("Please enter LastName with a maximum of 10 characters");
            // !this.$v.form.lastName.minlength && error.push("Please enter LastName with a minimum of 3 characters");
             //return error;
        },
        firstname_error(){
            const error=[];
            // if(!this.$v.form.firstName.$dirty) return error;
            // !this.$v.form.firstName.required && error.push("FirstName required");
            // !this.$v.form.firstName.maxLength && error.push("Please enter FirstName with a maximum of 10 characters");
            // !this.$v.form.firstName.minlength && error.push("Please enter FirstName with a minimum of 3 characters");
             //return error;
        },
    },
    components:{
        ChangerPassword,ChangerEmail
    }
    
}
</script>