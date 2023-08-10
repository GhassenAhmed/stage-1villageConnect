<template>
    <div data-aos="fade-right" class="changer_interface" >
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
                      <p class=" font-weight-bold header">Change Password </p>
                        <form @submit.prevent="ChangerPassword()" >
                              <div class="col-lg-12">
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
                              </div>
                              <div class="col-lg-12">
                                <v-text-field
                                    name="Password"
                                    label="Password"
                                    v-model="form.password"
                                    :error-messages="password_error"
                                    :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                                    :type="show ? 'text' : 'password'"
                                    @click:append="show = !show"
                                    dense
                                    outlined
                                    placeholder="Enter Password"
                            ></v-text-field>
                             </div>
                             <div class="col-lg-12">
                                <v-text-field
                                    name="ConfirmPassword"
                                    label="Confirm Password"
                                    v-model="form.confirmPassword"
                                    :error-messages="confirm_error"
                                    :append-icon="showconfirm ? 'mdi-eye' : 'mdi-eye-off'"
                                    :type="showconfirm ? 'text' : 'password'"
                                    @click:append="showconfirm = !showconfirm"
                                    dense
                                    outlined
                                    placeholder="Enter Password"
                            ></v-text-field>
                             </div>
                             <div style="display:flex;justify-content: center;" >
                                <div class="col-lg-6">
                                    <v-otp-input
                                        length="8"
                                        v-model="form.code"
                                    ></v-otp-input>
                                    <small style="color:red" >
                                        {{ tokenerror[0] }}
                                   </small>
                                </div>
                             </div>
                            <hr color="#2B3277" size="1px" class="mb-3">
                        <div class="float-end">
                            <v-btn :loading="loading" type="submit" color="#4F5BD8" style="color:#fff;font-weight:bold;">
                                 Change Password
                             </v-btn>
                         </div>
                        <br>
                        <div class="text-center mt-5 py-5">
                         <p>
                            <router-Link to="/login"> Back to login  </router-Link> 
                         </p>
                         </div>
                </form>
             </div>
         </v-col>
            </v-row>
         </v-card>
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
    import AuthServices from "@/services/AuthServices.js";
    import {required,email,sameAs,minLength,maxLength,numeric} from "vuelidate/lib/validators"
    import UserInfos from "@/services/UserInfos.js"
    export default{
      name:"forgotpassword",
      validations:{
        form:{
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
             confirmPassword:{
                required,
                same:sameAs('password')
            },
            code:{
                required,
                async existCode(val){
                    if(val==""){
                        return true;
                    }
                    const res=await UserInfos.TestExistcode(val);
                    return !res.data;
                }
            }
        }
      },
      data(){
          return{
            form:{
                email:"",
                password:"",
                confirmPassword:"",
                code:""
            },
              loading:false,
              show:false,
              snackbar:false,
              message_error:"",
              showconfirm:false,
             
          }
      },
      methods:{
        ChangerPassword(){
            this.$v.form.$touch();
            if(this.$v.form.$invalid){
                return;
            }
            this.loading=true;
            AuthServices.changerPassword({
                email:this.form.email,
                password:this.form.password,
                token:this.form.code})
            .then((res)=>{
                setTimeout(() => {
                    this.loading=false;
                    this.$router.push({name:"login",query:{content:res.data}});
                }, 2000);
                
            }).catch((error)=>{
                this.loading=false;
                this.snackbar=true;
                this.message_error=error.response.data;
            })
        }
      },
      computed:{
        password_error(){
            const error=[];
             if (!this.$v.form.password.$dirty) return error;
             !this.$v.form.password.required && error.push('Password required');
             !this.$v.form.password.maxLength && error.push('Please enter a password with a maximum of 10 characters');
             !this.$v.form.password.minLength && error.push('Please enter a password with a minimum of 6 characters');
             !this.$v.form.password.containsUppercase && error.push('Password must contain capital letters');
             !this.$v.form.password.containsLowercase && error.push('Password must contain lower case letters');
             !this.$v.form.password.containsNumber && error.push('Password must contain numbers');
             !this.$v.form.password.containsSpecial && error.push('password must contain a special character');
             return error;
        },
          email_error(){
            const error=[];
            if(!this.$v.form.email.$dirty) return error;
            !this.$v.form.email.required && error.push("Email Required");
            !this.$v.form.email.email && error.push("Email invalid");
            !this.$v.form.email.exist && error.push("Email Already used");
            return error;
        },
        confirm_error(){
            const error=[];
            if(!this.$v.form.confirmPassword.$dirty) return error;
            !this.$v.form.confirmPassword.same && error.push("Confirm password should be like Password");
            !this.$v.form.confirmPassword.required && error.push("Confirm Password Required");
            return error;
        },
        tokenerror(){
            const error=[];
            if(!this.$v.form.code.$dirty) return error;
            !this.$v.form.code.required && error.push("Code Required");
            !this.$v.form.code.existCode && error.push("Token not found");
            return error;
        }
      },
      components:{
          
      }
  }
  
 </script>