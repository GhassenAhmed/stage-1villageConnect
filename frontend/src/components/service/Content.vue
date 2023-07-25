<template>
    <div class="content">
        
        <v-layout class="hidden-sm-and-down">
            
            <div class="categories d-flex align-center justify-space-around" style="width: 100%; border-bottom: 0.1mm solid #CFD0D3;">
                <v-btn small plain :disabled="testPrev==true"  @click="changerPage(pageCurrent-1 )" style="margin: 0px 0px 20px ;">
                    <v-icon>
                        mdi-chevron-left
                    </v-icon>
                </v-btn> 
                <div class="li" v-for="categorie in categories" :key="categorie.id">
                    <router-link :to="/Categorie/ + categorie.id" style="text-decoration: none;color: black;">
                        <li  style="list-style-type: none; margin: 0px 0px 20px ;font-size: 20px;">{{categorie.categorieName}}</li>
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
                <v-layout  row wrap class="">
                    <v-flex>
                        <v-layout row wrap>
                            <v-flex class="d-flex justify-center align-center"> 
                                    <img src="../../assets/smile.png" alt="" width="70px" class="hidden-sm-and-down">
                            </v-flex>
                            <v-flex class="pa-5 mt-xs-0">
                                <p style="font-weight: bold;margin-bottom: 5px;font-size: 20px;"> Découvrez les services de votre village.</p>
                                <p style="font-weight: normal;font-size: 18px;">Ajoutez vos services et créez votre propre portfolio.</p>
                            </v-flex>
                        </v-layout>  
                    </v-flex>
                    <v-flex></v-flex>
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
                    <v-btn plain class="ml-3 mt-4" @click="getServiceRaiting()">
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
                <div class="card d-flex wrap">
                    <v-layout row wrap>
                        <v-flex v-for="service in servicesRaiting"
                            :key="service.id" xl4 md4 lg4 sm6 xs12>
                            <v-card
                            :loading="loading"
                            class="mx-auto my-12"
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
                            height="200"
                            src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
                            ></v-img>

                            <v-card-title>Service Name</v-card-title>

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
                            <div>Small plates, salads & sandwiches - an intimate setting with 12 indoor seats plus patio seating.</div>
                            </v-card-text>

                            <v-divider class="mx-4"></v-divider>

                            <v-card-title>Schedule and prices</v-card-title>

                            <v-layout row>
                                <v-flex class="ml-4">
                                    <v-card-text>
                                        schedule
                                    </v-card-text>
                                </v-flex>
                                <v-flex>
                                    <v-card-text>
                                        prices
                                    </v-card-text>
                                </v-flex>
                            </v-layout>

                            <v-card-actions>
                            <v-btn
                                color="deep-purple lighten-2"
                                text
                                
                            >
                                Reserve
                            </v-btn>
                            </v-card-actions>
                            </v-card>
                        </v-flex>
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
                    <div class="card d-flex wrap">
                        <v-layout row wrap>
                            <v-flex v-for="i in 3"
                                :key="i" xl4 md4 lg4 sm6 xs12>
                                <v-card
                                :loading="loading"
                                class="mx-auto my-12"
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
                                height="200"
                                src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
                                ></v-img>

                                <v-card-title>Service Name</v-card-title>

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
                                <div>Small plates, salads & sandwiches - an intimate setting with 12 indoor seats plus patio seating.</div>
                                </v-card-text>

                                <v-divider class="mx-4"></v-divider>

                                <v-card-title>Schedule and prices</v-card-title>

                                <v-layout row>
                                    <v-flex class="ml-4">
                                        <v-card-text>
                                            schedule
                                        </v-card-text>
                                    </v-flex>
                                    <v-flex>
                                        <v-card-text>
                                            prices
                                        </v-card-text>
                                    </v-flex>
                                </v-layout>

                                <v-card-actions>
                                <v-btn
                                    color="deep-purple lighten-2"
                                    text
                                    
                                >
                                    Reserve
                                </v-btn>
                                </v-card-actions>
                                </v-card>
                            </v-flex>
                        </v-layout>
                        
                    </div>
                </div>
                <!-- ---------------------------------------- /verified div------------------------------  -->
        </v-container>
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
},
    data(){
        return{
            categories:[],
            page:1,
            pageCurrent:0,
            per_page:5,
            countPage:[],
            testNext:true,
            testPrev:true,
            loading:false,
            servicesRaiting:[], 
            village_id:0,
            search:0,
            pageCurrent:0,
            per_page:7,
            villages:[],
            villagesNames:[],
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
        getServiceRaiting(){
            if(this.search==0){
                this.village_id=0;
            }else{
                this.village_id=this.villagesNames.indexOf(this.search)+1;
                console.log(this.villagesNames.indexOf(this.search)+"ghassen");
            }
            ServiceServices.getServiceRaiting(this.pageCurrent,this.village_id,this.per_page).then((res)=>{
                this.countPage=res.data.count_page;
                this.servicesRaiting=res.data.service.content;
                console.log(this.servicesRaiting.length);
                this.pageCurrent=res.data.page;
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
</style>