<template>
    <div>
        <form  @submit.prevent="ChangerEmail()" >
            <div class="mx-5 px-5">
               <div class="row col-lg-6">
                   <div class="col-lg-12">
                       <v-text-field
                         readonly
                         v-model="email"
                         
                     ></v-text-field>
                   </div>
                   <div class="col-lg-12">
                    <v-text-field
                      v-model="email_new"
                      :error-messages="email_new_error"
                      label="Nouvelle Email"
                  ></v-text-field>
                </div>
               </div>
            </div>
          <v-divider></v-divider>
         <v-card-actions class="justify-center">
           <v-btn class="mx-2" type="submit" color="#12c2b9" :loading="load">
               Update
           </v-btn>
         </v-card-actions>
         </form> 
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
import {required,email} from "vuelidate/lib/validators"
import UserInfos from "@/services/UserInfos";
import EditProfilServices from "@/services/EditProfilServices";
import {AuthUser} from "@/store/AuthStore.js";
export default{
    validations:{
        email_new:{
            required,email
        }
    },
    setup(){
        const store = AuthUser();
        return {store};
    },
    created(){
        UserInfos.getUserAuthentifie().then((res)=>{
            this.email=res.data['email'];
        })
    },
    data(){
        return{
            load:false,
            email:"",
            email_new:"",
            snackbar:false,
            message:""
        }
    },
    methods:{
        ChangerEmail(){
            this.load=true;
            this.$v.email_new.$touch();
            if( this.$v.email_new.$invalid){
                this.load=false;
                return;
            }
            EditProfilServices.SendChangedEmail(this.email_new).then((res)=>{
                 this.load=false;
                 this.message="Email Sent For Confirm";
                 this.snackbar=true;
            }).catch((error)=>{
                this.load=false;
                console.log(error);
            })
        }
    },
    computed:{
        email_new_error(){
            const error=[];
            if(!this.$v.email_new.$dirty) return error;
            !this.$v.email_new.required && error.push("Email Required");
            !this.$v.email_new.email && error.push("Invalid Email");
            return error;
        }
    }
}
</script>