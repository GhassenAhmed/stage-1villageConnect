<template>
    <div class="signup">
        <v-toolbar class="mb-4" >
            <v-toolbar-title>
                <img src="../../assets/village..png" width="300px" alt="logo_app">
            </v-toolbar-title>
            <v-spacer></v-spacer>
            <router-link to="/">
            <v-btn dense >
                <v-icon color="#4F5BD8">mdi-home</v-icon>  
            </v-btn>
            </router-link>
        </v-toolbar>
        <v-container>
            <v-layout class="mt-15">
                <v-flex>
                   <h1 style="font-size: 25px;margin-bottom: 10px;">Creer un compte</h1>
                   <p>Accédez à des fonctionnalités exclusives en créant un compte.</p>
                   
                   <form @submit.prevent="Register()" enctype="multipart/form-data">
                        <v-layout  wrap row class="pa-4">
                                <v-flex class="mt-5 mr-5">
                                    <v-text-field
                                            name="Name"
                                            label="Prénom"
                                            v-model="form.firstName"
                                            append-icon="mdi-rename-outline"
                                            :error-messages="firstNameError"
                                            type="text"
                                            dense
                                            outlined
                                            placeholder="Entrez votre Prénom"
                                    ></v-text-field>
                                    
                                    <v-text-field
                                            name="Name"
                                            label="Nom"
                                            v-model="form.lastName"
                                            :error-messages="LastNameError"
                                            append-icon="mdi-rename-outline"
                                            type="text"
                                            dense
                                            outlined
                                            placeholder="Entrez votre Nom"
                                    ></v-text-field>
                                    
                                </v-flex>

                                <v-flex class="mt-5 mr-5">
                                    <v-text-field
                                            name="Email"
                                            label="E-mail"
                                            v-model="form.email"
                                            :error-messages="email_error"
                                            append-icon="mdi-account"
                                            type="text"
                                            outlined
                                            dense
                                            placeholder="Enter E-mail"
                                    ></v-text-field>
                                    <v-text-field
                                        name="Password"
                                        label="Password"
                                        v-model="form.password"
                                        :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                                        :type="show ? 'text' : 'password'"
                                        @click:append="show = !show"
                                        :error-messages="password_error"
                                        dense
                                        outlined
                                        placeholder="Enter Password"
                                    ></v-text-field>
                                    <input
                                        name="file"
                                        id="file"
                                        label="Your Photo"
                                        :error-messages="photo_error"
                                        @change="base64()"
                                        type="file"
                                        ref="photo"
                                    >
                                    <div class="err" style="color: red;" v-if="photo_err">
                                        L'image doit être du type (jpg, jpeg, png, svg, gif)
                                    </div>
                                </v-flex>
                        </v-layout>
                        <hr color="#2B3277" size="1px" class="mb-3">
                        <div class="flex text-center mt-10">
                            <v-btn :loading="loading" type="submit" color="#4F5BD8" style="color:#fff;font-weight:bold;">
                                Create my account
                            </v-btn>
                        </div>
                        <br>
                        <div class="text-center mt-5">
                            <p>
                                Vous avez déjà un compte  ? <span style="color:#4F5BD8;font-weight:bold;">
                                    <router-link to="/Login"> Connectez-vous.</router-link></span> 
                            </p>
                        </div>
                    </form>
                </v-flex>
            </v-layout>
        </v-container>
    </div>
</template>
<script>
import userInfosService from "@/services/UserInfos.js";
import authService from "@/services/AuthServices.js"
import {required,email,minLength,maxLength} from "vuelidate/lib/validators";
export default {
    data(){
        return{
            form:{
                firstName:"",
                lastName:"",
                email:"",
                password:"",
                photo:""

            },
            show:false,
            loading:false,
            photo_err:false
        }
    },
    validations:{
        form:{
                firstName:{
                    required,
                    minLength:minLength(3),
                    maxLength:maxLength(8),
                },
                lastName:{
                    required,
                    minLength:minLength(3),
                    maxLength:maxLength(8),
                },
                email:{
                    required,
                    email,
                    async exist(val){
                      if(val==""){
                          return true;
                      }
                      const response=await userInfosService.TestEmail(val);
                      return response.data;
                  }
                },
                password:{
                    required,
                    minLength:minLength(6),
                    maxLength:maxLength(10),
                    containsUppercase: function(value) {
                        return /[A-Z]/.test(value);
                    },
                    containsLowercase: function(value) {
                        return /[a-z]/.test(value);
                    },
                    containsNumber: function(value) {
                        return /[0-9]/.test(value);
                    },
                    containsSpecial: function(value) {
                        return /[#?!@$%^&*-]/.test(value);
                    }
                },
                photo:{
                    typeFile(val){
                     const tab_ext_dispo=['jpg','gif','png','svg','jpeg'];
                     const extention=val.split(';')[0].split('/')[1];
                     return tab_ext_dispo.find((v)=>v==extention) ? true : false ;
                  }
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
        Register(){
                this.$v.form.$touch();
                if(this.$v.form.$invalid){
                    this.loading=false;
                    return;
                }
               this.loading=true;
               console.log(this.form.photo);
               authService.StoreUser({
                  "firstName":this.form.firstName,
                  "lastName":this.form.lastName,
                  "email":this.form.email,
                  "password":this.form.password,
                  "photo":this.form.photo,
                }).then((res)=>{
                      this.form.firstName="";
                      this.form.lastName="";
                      this.form.email="";
                      this.form.password="";
                      this.form.photo="";
                      this.loading=false;
                      this.$router.push({name:"login",query:{content:"Register successfully"}});
                  }).catch((error)=>{
                       this.loading=false;
                       console.log(error);
                  })
          },
    },
    computed:{
        firstNameError(){
              const error=[];
              if(!this.$v.form.firstName.$dirty) return error;
              !this.$v.form.firstName.required && error.push("Prénom requis");
              !this.$v.form.firstName.maxLength && error.push("Veuillez entrer le prénom  avec un maximum de 10 caractères");
              this.$v.form.firstName.minlength && error.push("Veuillez entrer le prénom  avec un minimum de 3 caractères ");
              return error;
          },

          LastNameError(){
              const error=[];
              if(!this.$v.form.lastName.$dirty) return error;
              !this.$v.form.lastName.required && error.push("Prénom requis");
              !this.$v.form.lastName.maxLength && error.push("Veuillez entrer le nom  avec un maximum de 10 caractères");
              this.$v.form.lastName.minlength && error.push("Veuillez entrer le nom  avec un minimum de 3 caractères ");
              return error;
          },
          password_error(){
              const error=[];
               if (!this.$v.form.password.$dirty) return error;
               !this.$v.form.password.required && error.push('Mot de passe requis');
               !this.$v.form.password.maxLength && error.push('Veuillez entrer un mot de passe avec un maximum de 10 caractères');
               !this.$v.form.password.minLength && error.push('Veuillez entrer un mot de passe comportant au moins 6 caractères ');
               !this.$v.form.password.containsUppercase && error.push('Le mot de passe doit contenir des lettres majuscules');
               !this.$v.form.password.containsLowercase && error.push('Le mot de passe doit contenir des lettres minuscules');
               !this.$v.form.password.containsNumber && error.push('Le mot de passe doit contenir des chiffres');
               !this.$v.form.password.containsSpecial && error.push('Le mot de passe doit contenir un caractère spécial');
               return error;
          },
          photo_error(){
              const error=[];
              if(!this.$v.form.photo.$dirty) return error;
              !this.$v.form.photo.typeFile && error.push("L'image doit être du type (jpg, jpeg, png, svg, gif)")&&this.photo_err==true;
              return error; 
          },
          email_error(){
              const error=[];
              if(!this.$v.form.email.$dirty) return error;
              !this.$v.form.email.required && error.push("Email requis");
              !this.$v.form.email.email && error.push("Email invalide");
              !this.$v.form.email.exist && error.push("Email déjà utilisé");
              return error;
          },
    }
}
</script>
<style scoped>
.container{
    background-color: #FBFDFE;
    background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 1600 900'%3E%3Cdefs%3E%3ClinearGradient id='a' x1='0' x2='0' y1='1' y2='0' gradientTransform='rotate(0,0.5,0.5)'%3E%3Cstop offset='0' stop-color='%23F2FFDA'/%3E%3Cstop offset='1' stop-color='%23CA92C7'/%3E%3C/linearGradient%3E%3ClinearGradient id='b' x1='0' x2='0' y1='0' y2='1' gradientTransform='rotate(0,0.5,0.5)'%3E%3Cstop offset='0' stop-color='%237B88D1'/%3E%3Cstop offset='1' stop-color='%23997BD1'/%3E%3C/linearGradient%3E%3C/defs%3E%3Cg fill='%23FFF' fill-opacity='0' stroke-miterlimit='10'%3E%3Cg stroke='url(%23a)' stroke-width='3.3'%3E%3Cpath d='M1409 581 1450.35 511 1490 581z'/%3E%3Ccircle stroke-width='1.1' transform='' cx='500' cy='100' r='40'/%3E%3Cpath transform='' d='M400.86 735.5h-83.73c0-23.12 18.74-41.87 41.87-41.87S400.86 712.38 400.86 735.5z'/%3E%3C/g%3E%3Cg stroke='url(%23b)' stroke-width='1'%3E%3Cpath transform='' d='M149.8 345.2 118.4 389.8 149.8 434.4 181.2 389.8z'/%3E%3Crect stroke-width='2.2' transform='' x='1039' y='709' width='100' height='100'/%3E%3Cpath transform='' d='M1426.8 132.4 1405.7 168.8 1363.7 168.8 1342.7 132.4 1363.7 96 1405.7 96z'/%3E%3C/g%3E%3C/g%3E%3C/svg%3E");
    background-attachment: fixed;
    background-size: cover;
}
</style>