<template>
    <div class="content">
        <section class="dots-container align-center mt-5 pa-5" v-if="loader" style="margin-bottom: 70vh;">
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
            <div class="dot"></div>
        </section>
        
        <div class="section" v-else>
            <v-layout class="hidden-sm-and-down">
            
            <div class="categories d-flex align-center justify-space-around" style="width: 100%; border-bottom: 0.1mm solid #CFD0D3;">
                <v-btn small plain :disabled="testPrev==true"  @click="changerPage(pageCurrent-1 )" style="margin: 0px 0px 20px ;">
                    <v-icon>
                        mdi-chevron-left
                    </v-icon>
                </v-btn> 
                <div class="li" v-for="categorie in categories" :key="categorie.id">
                    <router-link :to="/Categorie/ + categorie.id" style="text-decoration: none;color: black;">
                        <li  style="list-style-type: none; margin: 0px 0px 20px ;font-size: 15px;">{{categorie.categorieName}}</li>
                    </router-link>
                </div>
                
                <v-btn small plain :disabled="testNext==true"  @click="changerPage(pageCurrent+1)" style="margin: 0px 0px 20px ;">
                    <v-icon>
                        mdi-chevron-right
                    </v-icon>
                </v-btn>
            </div>
        </v-layout> 
        <v-container style="margin-top: 50px;">
            <p style="font-size: 30px;padding-left:  50px;font-weight: bold;">Salut,ghassen</p>
            <div class="introduction" style="border: 0.1mm solid #CFD0D3; height: auto;border-radius: 10px;padding: 30px 25px 20px 30px;">
                <v-layout  row wrap>
                    <v-flex>
                        <v-layout row wrap>
                            <v-flex class="d-flex justify-center align-center"> 
                                    <img src="../../assets/smile.png" alt="" width="70px" class="hidden-sm-and-down">
                            </v-flex>
                            <v-flex class="pa-5 mt-xs-0">
                                <p style="font-weight: bold;margin-bottom: 5px;font-size: 20px;"> Découvrez les services de votre village.</p>
                                <p class="mt-sm-3" style="font-weight: normal;font-size: 18px;">Ajoutez vos services et créez votre propre portfolio.</p>
                            </v-flex>
                            <v-flex class="pa-5 mt-xs-0 float-xl-end d-flex justify-center align-center">
                                <v-btn large text class="mt-3 justify-center align-center" style="width: auto;color: #ffffff; background-color: #12c2b9;" to="/createService">Creer service <v-icon class="ml-3">mdi-plus-circle-outline</v-icon></v-btn>
                            </v-flex>
                        </v-layout>  
                    </v-flex>
                </v-layout>
            </div>
        </v-container>
        <v-container style="margin-top: 50px;" fluid>
            <v-layout row wrap style="margin-left: 15%; margin-right: 10%;">
                
                <v-flex xl10 md10 lg10 sm10 xs9>
                    <v-autocomplete
                    :items="villagesNames"
                    v-model="search"
                    rounded
                    placeholder="Choisir une village ..."
                    style="height: 60px;background-color: #e7eaf1;"
                    ></v-autocomplete>
                </v-flex>
                <v-flex xl2 md2 lg2 sm2 xs3>
                    <v-btn plain class="ml-3 mt-4" @click="getServiceRaiting(),getServiceVerified()">
                        <v-icon X large size="50">mdi-magnify</v-icon>
                    </v-btn>
                </v-flex>
            </v-layout>
            
            <!-- ----------------------------------------Most raited div------------------------------  -->
            <div class="mostRaited" style="height: auto;border: 0.1mm solid #CFD0D3;border-radius: 10px;margin-top: 100px; padding: 20px 30px 50px 30px;">
                <div class="div d-flex" style="padding-left:  50px;padding-top: 20px;margin-bottom: 50px;">
                    <img src="../../assets/star.png" alt="" width="70px" style="margin-right:20px;" class="hidden-sm-and-down">
                    <p style="font-size: 30px;padding-top: 20px;">Le plus évalué ! </p>
                </div>
                <div class="card">
                    <v-layout row class="mt-5 align-center justify-center" v-if="servicesRaiting.length>0">
                        <v-btn small plain :disabled="testPrev1==true"  @click="changerPage1(pageCurrent1-1 )" style="margin: 0px 0px 20px ;">
                            <v-icon X large color="#12c2b9">
                                mdi-chevron-left
                            </v-icon>
                        </v-btn>
                        <v-btn small plain :disabled="testNext1==true"  @click="changerPage1(pageCurrent1+1)" style="margin: 0px 0px 20px ;">
                            <v-icon X large color="#12c2b9">
                                mdi-chevron-right
                            </v-icon>
                        </v-btn>
                    </v-layout>
                    <v-layout v-else class="d-flex align-center justify-center mb-15">
                            <p style="font-size: 20px;">Pas de service  disponible dans cette village</p>
                        </v-layout>
                    <v-layout row wrap>
                        <v-flex v-for="service in servicesRaiting"
                            :key="service.id">
                                <router-link :to="/service/ + service.id" style="text-decoration: none;color: black;">
                                <v-card
                                :loading="loading"
                                class="cards mx-auto my-12"
                                max-width="250"
                            
                                style="width: 300px;cursor: pointer;"
                                >
                                <template slot="progress">
                                <v-progress-linear
                                    color="deep-purple"
                                    height="10"
                                    indeterminate
                                ></v-progress-linear>
                                </template>

                                <v-img
                                height="180"
                                :src="service.photo"
                                ></v-img>

                                <v-card-title>{{service.serviceName}} - <span style="font-size: 13px;margin-left: 3px;color:rgb(73, 70, 70)"> {{ service.categorie['categorieName'] }}</span></v-card-title>
                                <v-card-text>
                                <v-row
                                    class="my-1 ml-1"
                                    align="center"
                                >
                                    <v-rating
                                    :value="4.5"
                                    color="amber"
                                    dense
                                    half-increments
                                    readonly
                                    size="14"
                                    ></v-rating>

                                    <div class="grey--text ms-4">
                                    4.5 (413)
                                    </div>
                                </v-row>
                                <div><img src="../../assets/emplacement.png" alt="" width="20px" class="mr-2 mt-1">{{service.village['villageName']}} <img src="../../assets//verifie.png" alt="" v-if="service.backgroundVerified" class="float-end" width="30px"></div>
                                </v-card-text>

                                <v-divider class="mx-4"></v-divider>

                                <v-card-title><img src="../../assets/money-management.png" alt="" width="30px" class="mr-2 mt-1">Prix range</v-card-title>
                                <v-card-text>{{ service.minPrice }} DT - {{ service.maxPrice }} DT</v-card-text>
                                <v-card-actions class="justify-center">
                                <v-btn
                                    color="deep-purple lighten-1"
                                    text
                                    
                                >
                                    Plus
                                </v-btn>
                                </v-card-actions>
                                </v-card>
                            </router-link>
                        </v-flex>   
                    </v-layout>
                    <v-layout row class="mt-5 align-center justify-center" v-if="servicesRaiting.length>0">
                        <v-btn small plain :disabled="testPrev1==true"  @click="changerPage1(pageCurrent1-1 )" style="margin: 0px 0px 20px ;">
                            <v-icon X large color="#12c2b9">
                                mdi-chevron-left
                            </v-icon>
                        </v-btn>
                        <v-btn small plain :disabled="testNext1==true"  @click="changerPage1(pageCurrent1+1)" style="margin: 0px 0px 20px ;">
                            <v-icon X large color="#12c2b9">
                                mdi-chevron-right
                            </v-icon>
                        </v-btn>
                    </v-layout>
                </div>
            </div>
            <!-- ---------------------------------------- /Most raited div------------------------------  -->


            <!-- ----------------------------------------verified div------------------------------  -->
            <div class="verified" style="height: auto;border-radius: 10px;margin-top: 50px;background-color: #FAFAFA;">
                <div class="div d-flex" style="padding-left:  50px;padding-top: 20px;margin-bottom: 50px;">
                    <img src="../../assets/verifie.png" alt="" width="60px" height="60px" style="margin-right:20px;margin-left: 10px;margin-top: 15px;" class="hidden-sm-and-down">
                    <p style="font-size: 30px;padding-top: 20px;">Vérifié ! </p>
                </div>
                    <div class="card">
                        <v-layout row class="mt-5 align-center justify-center" v-if="servicesVerified.length>0">
                            <v-btn small plain :disabled="testPrev2==true"  @click="changerPage2(pageCurrent2-1 )" style="margin: 0px 0px 20px ;">
                                <v-icon X large color="#12c2b9">
                                    mdi-chevron-left
                                </v-icon>
                            </v-btn>
                            <v-btn small plain :disabled="testNext2==true"  @click="changerPage2(pageCurrent2+1)" style="margin: 0px 0px 20px ;">
                                <v-icon X large color="#12c2b9">
                                    mdi-chevron-right
                                </v-icon>
                            </v-btn>
                        </v-layout>
                        <v-layout v-else class="d-flex align-center justify-center mb-15">
                            <p style="font-size: 20px;">Pas de service verifié disponible dans cette village</p>
                        </v-layout>
                        <v-layout row wrap>
                            <v-flex v-for="service in servicesVerified"
                                :key="service.id">
                                <router-link :to="/service/ + service.id" style="text-decoration: none;color: black;">
                                    <v-card
                                :loading="loading"
                                class="cards mx-auto my-12"
                                max-width="250"
                            
                                style="width: 300px;cursor: pointer;"
                                >
                                <template slot="progress">
                                <v-progress-linear
                                    color="deep-purple"
                                    height="10"
                                    indeterminate
                                ></v-progress-linear>
                                </template>

                                <v-img
                                height="180"
                                :src="service.photo"
                                ></v-img>

                                <v-card-title>{{service.serviceName}} - <span style="font-size: 13px;margin-left: 3px;color:rgb(73, 70, 70)"> {{ service.categorie['categorieName'] }}</span></v-card-title>
                                <v-card-text>
                                <v-row
                                    class="my-1 ml-1"
                                    align="center"
                                >
                                    <v-rating
                                    :value="4.5"
                                    color="amber"
                                    dense
                                    half-increments
                                    readonly
                                    size="14"
                                    ></v-rating>

                                    <div class="grey--text ms-4">
                                    4.5 (413)
                                    </div>
                                </v-row>
                                <div><img src="../../assets/emplacement.png" alt="" width="20px" class="mr-2 mt-1">{{service.village['villageName']}} <img src="../../assets//verifie.png" alt="" v-if="service.backgroundVerified" class="float-end" width="30px"></div>
                                </v-card-text>

                                <v-divider class="mx-4"></v-divider>

                                <v-card-title><img src="../../assets/money-management.png" alt="" width="30px" class="mr-2 mt-1">Prix range</v-card-title>
                                <v-card-text>{{ service.minPrice }} DT - {{ service.maxPrice }} DT</v-card-text>
                                <v-card-actions class="justify-center">
                                <v-btn
                                    color="deep-purple lighten-1"
                                    text
                                    
                                >
                                    Plus
                                </v-btn>
                                </v-card-actions>
                                    </v-card>
                                </router-link>
                            </v-flex>
                        </v-layout>
                        <v-layout row class="mt-5 align-center justify-center" v-if="servicesVerified.length>0">
                            <v-btn small plain :disabled="testPrev2==true"  @click="changerPage(pageCurrent2-1 )" style="margin: 0px 0px 20px ;">
                                <v-icon X large color="#12c2b9">
                                    mdi-chevron-left
                                </v-icon>
                            </v-btn>
                            <v-btn small plain :disabled="testNext2==true"  @click="changerPage(pageCurrent2+1)" style="margin: 0px 0px 20px ;">
                                <v-icon X large color="#12c2b9">
                                    mdi-chevron-right
                                </v-icon>
                            </v-btn>
                        </v-layout>
                    </div>
                </div>
                <!-- ---------------------------------------- /verified div------------------------------  -->
        </v-container>
        </div>
        
    </div>
</template>
<script>
import VillageServices from "@/services/VillageServices";
import CategorieServices from '@/services/CategorieServices';
import ServiceServices from '@/services/ServiceServices';
export default {
    created(){
    this.getVillages();
    this.getCategories();
    this.getServiceRaiting();
    this.getServiceVerified();
},
    data(){
        return{
            categories:[],
            page:1,
            pageCurrent:0,
            per_page:7,
            countPage:[],
            testNext:true,
            testPrev:true,
            loading:false,
            servicesRaiting:[], 
            servicesVerified:[], 
            village_id:0,
            search:0,
            villages:[],
            villagesNames:[],
            loader: true,
            page1:1,
            pageCurrent1:0,
            per_page1:4,
            countPage1:[],
            testNext1:true,
            testPrev1:true,
            page2:1,
            pageCurrent2:0,
            per_page2:4,
            countPage2:[],
            testNext2:true,
            testPrev2:true,
        }
    },
    methods:{
        getCategories(){
            CategorieServices.getCategories(this.pageCurrent,this.per_page).then((res)=>{
                this.countPage=res.data.count_page;
                this.categories=res.data.categorie.content;
                this.pageCurrent=res.data.page;
                this.testNext=res.data.categorie.last;
                this.testPrev=res.data.categorie.first;
            }).catch((err)=>{
                console.log(err);
            })
        },
        changerPage(num){
            if(num<0){
            num=0;
            }
            this.pageCurrent=num;
            this.getCategories();
        },
        changerPage1(num){
            if(num<0){
            num=0;
            }
            this.pageCurrent1=num;
            this.getServiceRaiting();
        },
        changerPage2(num){
            if(num<0){
            num=0;
            }
            this.pageCurrent2=num;
            this.getServiceVerified();
        },
        getServiceRaiting(){
            
            if(this.search==0){
                this.village_id=0;
            }else{
                this.village_id=this.villagesNames.indexOf(this.search)+1;
            }
            ServiceServices.getServiceRaiting(this.pageCurrent1,this.village_id,this.per_page1).then((res)=>{
                this.countPage1=res.data.count_page;
                this.servicesRaiting=res.data.service.content;
                this.pageCurrent1=res.data.page;
                this.testNext1=res.data.service.last;
                this.testPrev1=res.data.service.first;
                setTimeout(()=>this.loader=false,2000);
            }).catch((err)=>{
                console.log(err);
            })
        },
        getServiceVerified(){
            if(this.search==0){
                this.village_id=0;
            }else{
                this.village_id=this.villagesNames.indexOf(this.search)+1;
            }
            ServiceServices.getServiceVerified(this.pageCurrent,this.village_id,this.per_page).then((res)=>{
                this.countPage2=res.data.count_page;
                this.servicesVerified=res.data.service.content;
                console.log(this.servicesVerified);
                this.pageCurrent2=res.data.page;
                this.testNext2=res.data.service.last;
                this.testPrev2=res.data.service.first;
                setTimeout(()=>this.loader=false,2000);
            }).catch((err)=>{
                console.log(err);
            })
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
.cards{
    transition: transform 0.3s ease;
}
.cards:hover{
    transform: translateY(-20px);
}
li{
    display: block;
    padding-bottom: 10px;
    padding-top: 10px;
    border-bottom: 3.5px solid #ffffff;
}
li:hover{
    border-bottom: 3.5px solid #12c2b9;
    overflow: hidden;
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