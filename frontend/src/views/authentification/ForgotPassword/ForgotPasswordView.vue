<template>
    <div data-aos="fade-right" class="forgot_interface" >
      <v-container class="mt-1 py-5" fluid>
         <v-card  style="height:100%" class="mt-2 card" elavation="3">
            <v-row >
               <v-col>
                <v-toolbar class="mb-4">
                    <v-toolbar-title class="text-center-sm" style="margin-left: -50px;"><img src="../../../assets/village2.png" width="300px"></v-toolbar-title> 
                      <v-spacer></v-spacer>
                        <v-btn dense >
                             <v-icon color="#4F5BD8">mdi-home</v-icon>  
                        </v-btn>
                   </v-toolbar>
                   <div class="mx-5 px-5 mt-5 py-5" style="margin-top:15% !important">
                      <p class=" font-weight-bold header">Password recovery </p>
           <form @submit.prevent="ForgotPassword()" >
                              <div class="col-lg-12">
                                 <v-text-field
                                     name="Email"
                                     label="E-mail"
                                     v-model="email"
                                     :error-messages="email_error"
                                     append-icon="mdi-account"
                                     type="text"
                                     outlined
                                     dense
                                     placeholder="Enter E-mail"
                                 ></v-text-field>
                              </div>
                              
                            <hr color="#2B3277" size="1px" class="mb-3">
                        <div class="float-end">
                            <v-btn :loading="loading" type="submit" color="#4F5BD8" style="color:#fff;font-weight:bold;">
                                 Reset My Password
                             </v-btn>
                         </div>
                        <br>
                        <div class="text-center mt-5 py-5">
                         <p>
                            <router-Link to="/Login"> Back to login  </router-Link> 
                         </p>
                         </div>
                </form>
             </div>
         </v-col>
            </v-row>
         </v-card>
      </v-container>
    </div>
  </template>
  <script>
    import authService from "@/services/AuthServices.js"
    import {required,email} from "vuelidate/lib/validators"
    import UserInfos from "@/services/UserInfos.js"
    export default {
      name:"forgotpassword",
      validations:{
               email:{
                  required,
                  email,
                  async exist(val){
                      if(val==""){
                          return true;
                      }
                      const response=await UserInfos.TestEmail(val);
                      return !response.data;
                  }
               },
      },
      data(){
          return{
              email:"",
              loading:false,
          }
      },
      methods:{
        ForgotPassword(){
            this.$v.email.$touch();
            if(this.$v.email.$invalid){
                return;
            }
            this.loading=true;
            authService.forgotPassword(this.email).then((res)=>{
                this.loading=false;
                this.$router.push({name:"login",query:{content:res.data}});
            })
        }
      },
      computed:{
          email_error(){
              const error=[];
              if(!this.$v.email.$dirty) return error;
              !this.$v.email.required && error.push("Email Required");
              !this.$v.email.email && error.push("Email invalid");
              !this.$v.email.exist && error.push("Email Not exist");
              return error;
          },
      },
      components:{
          
      }
  }
  
  </script>
  
  <style scoped>
  .forgot_interface{
      background-color: #BAC1CA;
      height: 100%;
      width: 100vw;
  }
  .card{
      margin: 25px;
  }
  .header{
      font-size: 25px;
  }
  .second_part{
     background-image: linear-gradient(to bottom,#4F5BD8,#2B3277);
     color:#fff;
  }
  </style>
