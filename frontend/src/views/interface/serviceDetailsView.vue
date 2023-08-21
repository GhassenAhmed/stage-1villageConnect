<template>
    <div id="app">
        <v-app-bar  flat style="background-color: #ffffff;margin-top: 20px;height: 100px;border-bottom: 0.1mm solid #CFD0D3;"> 
        <v-app-bar-nav-icon @click="drawer = true" 
                            class="d-flex d-sm-none" 
                            ></v-app-bar-nav-icon>
                <v-toolbar-title class="text-center-sm" style="margin-left: -50px;"><img src="../../assets/village2.png" width="300px"></v-toolbar-title> 
                    <v-spacer></v-spacer>
            
            <v-btn icon class="hidden-sm-and-down" to="/home">
                <v-icon   X Small >
                    mdi-home
                </v-icon>
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
            <v-list-item>
                <v-btn
                plain
                @click="logout()"
                >
                    <v-icon class="pa-2">mdi-logout</v-icon>
                    <span >log out</span>
                </v-btn>
            </v-list-item>
            
        </v-list>
        </v-navigation-drawer>
        <section class="dots-container align-center mt-5 pa-5" v-if="loader" style="margin-bottom: 100vh;">
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
        </section>
        <v-container class="" v-else style="height: auto;margin-top: 100px; margin-bottom: 200px;">
                <v-layout row wrap>
                    <v-flex xl8 md8 lg8 sm12 xs12 class="pl-4">
                        <p style="font-size: 30px;">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Eos, dolor!</p>
                        <v-layout style="width: 100%; margin-top: 40px;" row wrap>
                            <v-flex xl2 md2 lg2 sm2 xs4 class="pa-5">
                                <v-avatar size="70">
                                    <img :src="service.user['photo']" width="100%" height="auto">
                                </v-avatar>
                            </v-flex>
                            <v-flex xl6 md6 lg6 sm6 xs8 class="pa-5">
                                <span style="font-size: 20px;padding-bottom: 10px;">{{ service.user['firstName'] }}  {{ service.user['lastName'] }} </span><br>
                                <span style="font-size: 20px;margin-top: 20px;">{{ service.serviceName }} | {{ service.categorie['categorieName'] }}</span>
                            </v-flex>
                        </v-layout>
                        <img  class="img" :src="service.photo" v-if="service.photo!=null" width="70%"  height="auto" style="margin-top: 50px;margin-bottom: 50px;">
                        <p v-else style="font-size: 100%;margin-top: 50px;margin-bottom: 50px;">Pas de photo disponible</p>
                        
                        

                    </v-flex>

                    <v-flex xl4 md4 lg4 sm12 xs12>
                        <span style="font-size: 35px;margin-top: 20px;margin-bottom: 50px;">{{ service.serviceName }} | {{ service.categorie['categorieName'] }}</span>
                        <div class="details" style="margin-top: 50px;padding-left: 10px;">
                            <h2 style="margin-bottom: 10px;color: #757575;"> Adresse : <a href=""><span font-size="30px" style="color:#5b5a5a;">{{ service.adresse }}</span> </a></h2>
                            <h2 style="margin-bottom: 10px;color: #757575;"> Phone : <span font-size="30px" style="color:#5b5a5a;">{{ service.phone }}</span></h2>

                        <div class="div" v-if="service.thumbnailUrl!=null">
                            <h2 style="margin-bottom: 10px;color: #757575;"> Lien : <span font-size="30px" style="color:#5b5a5a;">{{ service.thumbnailUrl }}</span></h2>
                        </div>

                        <div class="div" v-if="service.yearsInBusiness!=null">
                            <h2 style="margin-bottom: 10px;color: #757575;"> Depuis : <span font-size="30px" style="color:#5b5a5a;">{{ service.yearsInBusiness }} ans</span></h2>
                        </div>

                        <div class="div" v-if="service.village['villageName']!=null">
                            <h2 style="margin-bottom: 10px;color: #757575;"> Village : <span font-size="30px" style="color:#5b5a5a;">{{ service.village['villageName'] }}</span></h2>
                             
                        </div>

                        <div class="div" v-if="service.maxPrice!=null || service.minPrice!=null">
                            <h2 style="margin-bottom: 10px;color: #757575;"> Prix : <span font-size="30px" style="color:#5b5a5a;">{{ service.minPrice }} DT - {{ service.maxPrice }} DT</span></h2>
                             
                        </div>
                        </div>
                        
                    </v-flex>
                </v-layout>
            
        </v-container>

        <Footer></Footer>
        
    </div>
</template>
<script>
import { AuthUser } from "@/store/AuthStore";
import Footer from "@/components/home/Footer.vue";
import ServiceServices from "@/services/ServiceServices.js"
export default {
    name:'serviceDetailsView',
    setup(){
        const store=AuthUser();
        return{
            store
        }
    },
    components:{
    Footer
    },
    created(){
        this.id=this.$route.params.id;
        console.log(this.id);
        
    },
    mounted(){
        this.getSeviceDtails();
        setTimeout(()=>this.loader=false,2000);
    },
    data(){
        return{
            id:"",
            service:"",
            drawer: false,
            loading: false,
            loader:true,
        }
    },
    methods:{
        logout(){
            this.store.logOut();
            this.$router.push({name:"login"});
        },

        getSeviceDtails(){
            ServiceServices.getServiceDetailsById(this.id).then((res)=>{
                this.service=res.data;
                console.log(this.service);
            }).catch((err)=>{
                console.log(err);
            })
        }
    }

}
</script>
<style scoped>
*{
    font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
    font-weight: lighter;
    scroll-behavior: smooth;
    overflow: hidden;
    box-sizing: border-box;
}
.cards{
    transition: transform 0.3s ease;
}
.cards:hover{
    transform: translateY(-20px);
}

.img{
    border-radius: 15px;
}

.dots-container {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  width: 100%;
}

.dot {
  height: 20px;
  width: 20px;
  margin-right: 10px;
  border-radius: 10px;
  background-color: #b3d4fc;
  animation: pulse 1.5s infinite ease-in-out;
}

.dot:last-child {
  margin-right: 0;
}

.dot:nth-child(1) {
  animation-delay: -0.3s;
}

.dot:nth-child(2) {
  animation-delay: -0.1s;
}

.dot:nth-child(3) {
  animation-delay: 0.1s;
}

@keyframes pulse {
  0% {
    transform: scale(0.8);
    background-color: #b3d4fc;
    box-shadow: 0 0 0 0 rgba(178, 212, 252, 0.7);
  }

  50% {
    transform: scale(1.2);
    background-color: #12c2b9;
    box-shadow: 0 0 0 10px rgba(178, 212, 252, 0);
  }

  100% {
    transform: scale(0.8);
    background-color: #b3d4fc;
    box-shadow: 0 0 0 0 rgba(178, 212, 252, 0.7);
  }
  
}
</style>