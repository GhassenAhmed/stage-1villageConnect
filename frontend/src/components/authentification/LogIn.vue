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
            <div  v-if="message!=''">
                <v-alert type="success" >
                    {{ message }}
            </v-alert>
            </div>
        <v-container>
            <v-layout class="mt-15">
                <v-flex>
                   <h1 style="font-size: 25px;margin-bottom: 10px;">Connectez-vous</h1>
                   <p>Bienvenue sur VillageConnect, veuillez saisir vos informations de connexion ci-dessous.
                   <br> pour commencer à utiliser l'application.</p>
                   <form @submit.prevent="Login()" data-aos="fade-right">
                        <v-layout  wrap row class="pa-4">
                                <v-flex class="mt-5 mr-5">
                                    <v-text-field
                                            name="Email"
                                            append-icon="mdi-account"
                                            type="text"
                                            outlined
                                            v-model="form.email"
                                            :error-messages="email_error"
                                            dense
                                            placeholder="Enter E-mail"
                                    ></v-text-field>
                                </v-flex>

                                <v-flex class="mt-5 mr-5">
                                    <v-text-field
                                        name="Password"
                                        v-model="form.password"
                                        :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                                        :type="show ? 'text' : 'password'"
                                        @click:append="show = !show"
                                        dense
                                        :error-messages="password_error"
                                        outlined
                                        placeholder="Enter Password"
                                    ></v-text-field>
                                </v-flex>
                                
                        </v-layout>
                        <v-layout class="d-flex justify-center" row>
                            <div class="mb-5">
                                <p  color="#4F5BD8" style="color:#2B3277;font-weight:bold;">
                                <router-link to="/forgotpassword">Forgot Password ?</router-link>  </p>   
                            </div>
                        </v-layout>
                        <hr color="#2B3277" size="1px" class="mb-3">
                        <div class="flex text-center mt-10">
                            <v-btn :loading="loading" type="submit" color="#4F5BD8" style="color:#fff;font-weight:bold;">
                                Connectez-vous
                            </v-btn>
                        </div>
                        <br>
                        <div class="text-center mt-5">
                            <p>
                                Vous n'avez pas de compte ?  <span style="color:#4F5BD8;font-weight:bold;">
                                    <router-link to="/Signup"> Inscrivez-vous.</router-link></span> 
                            </p>
                        </div>
                    </form>
                </v-flex>
            </v-layout>
            <v-snackbar
         v-model="snackbar"
       >
         {{ message_error }}
   
         <template v-slot:action="{ attrs }">
           <v-btn
             color="pink"
             text
             v-bind="attrs"
             @click="snackbar = false"
           >
             Close
           </v-btn>
         </template>
       </v-snackbar>
        </v-container>
    </div>
</template>
<script>
import {required,email} from "vuelidate/lib/validators"
import authService from '@/services/AuthServices';
import serviceEdit from '@/services/EditProfilServices';
import { AuthUser } from "../../store/AuthStore.js";
export default {
    name:"login",
    data(){
        return{
            show:false,
            loading:false,
            message_error:"",
            snackbar:false,
            form:{
                email:"",
                password:""
            },
            message:""
        }
    },
    validations:{
        form:{
            email:{
                required,
                email,
            },
            password:{
                required
            }
        }   
        
      },
    setup(){
        const store=AuthUser();
        return{
            store
        }
    },
    created(){
        if(this.$route.query.content){
            this.message=this.$route.query.content;
            setTimeout(()=>this.message="",3000);
        }
        if(this.$route.query.email){
            this.VerifyEmail(this.$route.query.email);
        }
        if(this.$route.query.email && this.$route.query.email_new ){
            this.store.logOut();
            this.updatedEmail(this.$route.query.email,this.$route.query.email_new);
          }
    },
   
 
      methods:{
        Login(){
                 this.$v.form.$touch();
                 if(this.$v.form.$invalid){
                    this.loading=false;
                    return;
                }
                this.loading=true;
                authService.login(this.form.email,this.form.password).then((res)=>{
                this.loading=false;
                if(this.store.getIsClient==true){
                    this.$router.push("/home");
                }

                if(this.store.getIsadmin==true){
                    this.$router.push("/statistiques")
                }

                
              }).catch((error)=>{
                this.loading=false;
                this.snackbar=true;
                this.message_error=error.response.data;
                
              })
          },
          VerifyEmail(email){
            authService.VerifyEmail(email).then((res)=>{
                if(res.status==200){
                    this.message=res.data;
                }else{
                    this.message_error=res.data;
                    this.snackbar=true;
                }
                 this.$router.replace({'query':null});
            })
          },
          updatedEmail(old_email,new_email){
            serviceEdit.updateEmail(
              {
                 "email_old":old_email,
                 "email_new":new_email
               }).then((res)=>{
                 this.message=res.data;
                 this.$router.replace({'query':null});
               }).catch((error)=>{
                 console.log(error);
                 this.$router.replace({'query':null});
               })
          }
      },
      computed:{
        email_error(){
            const error=[];
            if(!this.$v.form.email.$dirty) return error;
            !this.$v.form.email.required && error.push('Email Required');
            !this.$v.form.email.email && error.push('Email Invalid');
            if(!this.$v.form.email.required){
                error.push("Email required");
                return error;
            }else if(!this.$v.form.email.email){
                error.push("Email invalid");
                return error;
            }
            return error;
        },
        password_error(){
            const error=[];
             if (!this.$v.form.password.$dirty) return error;
             !this.$v.form.password.required && error.push('Password Required');
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