<template>
    <div class="root">
       <Navbar></Navbar>
       <div class="mt-5 py-5 px-5 mb-15">
        <v-layout class="mt-15 mb-15" row >
          <v-flex  xl3 md3 lg3 sm6 xs12 class="mt-5">
            <v-card
              class="mx-auto"
              max-width="344"
              outlined
            >
              <v-list-item three-line>
                <v-list-item-content>
                  <div class="text-overline mb-4">
                    Statistiques
                  </div>
                  <v-list-item-title class="text-h5 mb-1">
                    Users
                  </v-list-item-title>
                  <v-list-item-title>{{ Users }}</v-list-item-title>
                </v-list-item-content>

                <v-list-item-avatar
                  tile
                  size="50"
                >
                <img src="../../../assets/userstat.png" alt="">
              </v-list-item-avatar>
              </v-list-item>
            </v-card>
          </v-flex>

          <v-flex  xl3 md3 lg3 sm6 xs12 class="mt-5">
            <v-card
              class="mx-auto"
              max-width="344"
              outlined
            >
              <v-list-item three-line>
                <v-list-item-content>
                  <div class="text-overline mb-4">
                    Statistiques
                  </div>
                  <v-list-item-title class="text-h5 mb-1">
                    Categories
                  </v-list-item-title>
                  <v-list-item-title>{{ Categories }}</v-list-item-title>
                </v-list-item-content>

                <v-list-item-avatar
                  tile
                  size="50"
                >
                <img src="../../../assets/categoriestat.png" alt="">
              </v-list-item-avatar>
              </v-list-item>
            </v-card>
          </v-flex>


          <v-flex  xl3 md3 lg3 sm6 xs12 class="mt-5">
            <v-card
              class="mx-auto"
              max-width="344"
              outlined
            >
              <v-list-item three-line>
                <v-list-item-content>
                  <div class="text-overline mb-4">
                    Statistiques
                  </div>
                  <v-list-item-title class="text-h5 mb-1">
                    Services
                  </v-list-item-title>
                  <v-list-item-title>{{ Services }}</v-list-item-title>
                </v-list-item-content>

                <v-list-item-avatar
                  tile
                  size="50"
                >
                <img src="../../../assets/servicestat.png" alt="">
              </v-list-item-avatar>
              </v-list-item>
            </v-card>
          </v-flex>

          <v-flex xl3 md3 lg3 sm6 xs12 class="mt-5">
            <v-card
              class="mx-auto"
              max-width="344"
              outlined
            >
              <v-list-item three-line>
                <v-list-item-content>
                  <div class="text-overline mb-4">
                    Statistiques
                  </div>
                  <v-list-item-title class="text-h5 mb-1">
                    Villages
                  </v-list-item-title>
                  <v-list-item-title>{{ Villages }}</v-list-item-title>
                </v-list-item-content>

                <v-list-item-avatar
                  tile
                  size="50"
                >
                <img src="../../../assets/villagestat.png" alt="">
              </v-list-item-avatar>
              </v-list-item>
            </v-card>
          </v-flex>

        </v-layout>
        <div class="card row mt-5 d-flex align-center justify-center">
                <v-chart  class="chart col-lg-6 col-sm-12 col-xs-12"  :option="option"  autoresize/>
                <v-chart  class="chart col-lg-6 col-sm-12 col-xs-12"  :option="option1"  autoresize/>
        </div> 

        
              
    </div>
    </div>
  </template>
  <script>
  import StatsServices from "@/services/StatsServices.js";
  import { AuthUser } from "@/store/AuthStore";
  import Navbar from '@/components/admin/Navbar.vue'
  import { use } from "echarts/core";
  import { CanvasRenderer } from "echarts/renderers";
  import { PieChart } from "echarts/charts";
  import {
  TitleComponent,
  TooltipComponent,
  LegendComponent
  } from "echarts/components";
  import VChart, { THEME_KEY } from "vue-echarts";

  use([
  CanvasRenderer,
  PieChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent
  ]);
    export default {
    provide: {
        [THEME_KEY]: "dark"
    },
      components:{
        Navbar,VChart
     },
      setup(){
        const store=AuthUser();
        return{
            store
        }
    },
    created(){
       this.getClient();
       this.getServiceVerified();
       this.getServiceProvider();
       this.getServiceNonVerified();
       this.countUsers();
       this.countCategories();
       this.countVillages();
       this.countServices();
    },
    name:'StatistiquesView',
data(){
    return{
        drawer: false,
        mini: true,
        clients:0,
        serviceProviders:0,
        Users:0,
        Villages:0,
        Categories:0,
        Services:0,
 
     option : {
         title: {
         text: ` Client `,
         left: 'center',
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)',
      },
      legend: {
        orient: 'vertical',
        left: 'left',
        data: ['Client', 'ServiceProvider'],
      },
      series: [
        {
          name: 'Static',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: [
            { value:0, name: 'Client' },
            { value:0, name: 'ServiceProvider' },
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)',
            },
          },
        },
         ],
      },

      option1 : {
         title: {
         text: ` Service `,
         left: 'center',
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)',
      },
      legend: {
        orient: 'vertical',
        left: 'left',
        data: ['Verifier', 'Non verifier'],
      },
      series: [
        {
          name: 'Static',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: [
            { value:0, name: 'Verifier' },
            { value:0, name: 'Non verifier' },
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)',
            },
          },
        },
         ],
      },
        }
    },
methods:{
    getClient(){
        StatsServices.getClient().then((res)=>{
            this.option.series[0].data[0].value=res.data;
            this.clients=res.data;
        }).catch((err)=>{
            console.log(err);
        })
    },
    getServiceProvider(){
        StatsServices.getServiceProvider().then((res)=>{
            this.option.series[0].data[1].value=res.data;
            this.serviceProviders=res.data;
        }).catch((err)=>{
            console.log(err);
        })
    },

    getServiceVerified(){
        StatsServices.getServiceVerified().then((res)=>{
            this.option1.series[0].data[0].value=res.data;
            this.serviceProviders=res.data;
        }).catch((err)=>{
            console.log(err);
        })
    },
    getServiceNonVerified(){
        StatsServices.getServiceNonVerified().then((res)=>{
            this.option1.series[0].data[1].value=res.data;
            this.serviceProviders=res.data;
        }).catch((err)=>{
            console.log(err);
        })
    },
    countUsers(){
      StatsServices.countUsers().then((res)=>{
              this.Users=res.data;
              
            }).catch((err)=>{
              console.log(err);
            })
    },
    countCategories(){
      StatsServices.countCategories().then((res)=>{
              this.Categories=res.data;
            }).catch((err)=>{
              console.log(err);
            })
    },
    countVillages(){
      StatsServices.countVillages().then((res)=>{
              this.Villages=res.data;
            }).catch((err)=>{
              console.log(err);
            })
    },
    countServices(){
      StatsServices.countServices().then((res)=>{
              this.Services=res.data;
            }).catch((err)=>{
              console.log(err);
            })
    }
},
  computed:{
     
  }
}
</script>
<style scoped>
*{
    font-family:cursive;
    scroll-behavior: smooth;
    overflow: hidden;
    box-sizing: border-box;
}
.content{
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr ;
  grid-gap: 25px;
}

@media screen and (max-width:950px){
  .content{
    display: grid;
    grid-template-columns: 1fr 1fr ;
    grid-gap: 25px;
  }
  
}

@media screen and (max-width:530px){
  .content{
    display: grid;
    grid-template-columns: 1fr;
    grid-gap: 25px;
    text-align: center;
  }
}
.chart {
    height: 50vh;
    width: 30vw;
  }
  
  @media screen and (max-width:750px) {
    .chart {
      height: 30vh;
      width: 50vw;
    }
  }
  
  @media screen and (max-width:500px) {
    .chart {
      height: 30vh;
      width: 70vw;
    }
  }


</style>