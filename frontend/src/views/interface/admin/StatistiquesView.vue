<template>
    <div class="root">
       <Navbar></Navbar>
       <div class="mt-5 py-5 ">
            
        <div class="card row mt-5 d-flex align-center justify-center">
                <v-chart  class="chart col-lg-6 col-sm-12 col-xs-12"  :option="option"  autoresize/>
                <v-chart  class="chart col-lg-6 col-sm-12 col-xs-12"  :option="option1"  autoresize/>
        </div> 
        <!-- <div class="content mt-15 d-flex justify-center">
        <v-card  
            max-width="180"
            max-height="180"
            variant="outlined"
            class="ml-15"
            >
        <v-list-item   style="padding: 10px;" class="text-center">
            <div>
            <div class="text-overline-h4 mb-3">
                <v-icon size="35px" color="blue" >mdi-sort-variant</v-icon>
            </div>
            <div  class="text-h6 mb-2">
                aaaa
            </div>
            <div class="text-h6">Categories</div>
            </div>
        </v-list-item>
        </v-card>

        <v-card  
            max-width="180"
            max-height="180"
            variant="outlined"
            class="ml-15"
            >
        <v-list-item   style="padding: 10px;" class="text-center">
            <div>
            <div class="text-overline-h4 mb-3">
                <v-icon size="35px" color="blue" >mdi-store-outline</v-icon>
            </div>
            <div  class="text-h6 mb-2">
                aaaa
            </div>
            <div class="text-h6">Villages</div>
            </div>
        </v-list-item>
        </v-card>

        <v-card  
            max-width="180"
            max-height="180"
            variant="outlined"
            class="ml-15"
            >
        <v-list-item   style="padding: 10px;" class="text-center">
            <div>
            <div class="text-overline-h4 mb-3">
                <v-icon size="35px" color="blue" >mdi-account-tie</v-icon>
            </div>
            <div  class="text-h6 mb-2">
                aaaa
            </div>
            <div class="text-h6">Utilisateurs</div>
            </div>
        </v-list-item>
        </v-card>
        </div> -->
              
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
    },
    name:'StatistiquesView',
data(){
    return{
        drawer: false,
        mini: true,
        clients:0,
        serviceProviders:0,
        All_data:[
                {icon:"mdi-account-tie",nbr:0,titre:"Nombre du Clients"},
                {icon:"mdi-account-tie",nbr:0,titre:"Nombre du ServiceProvider"},
            ],
 
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
            console.log(this.option.series[0].data[0].value);
            this.clients=res.data;
        }).catch((err)=>{
            console.log(err);
        })
    },
    getServiceProvider(){
        StatsServices.getServiceProvider().then((res)=>{
            this.option.series[0].data[1].value=res.data;
            this.serviceProviders=res.data;
            console.log(this.option.series[0].data[1].value);
        }).catch((err)=>{
            console.log(err);
        })
    },

    getServiceVerified(){
        StatsServices.getServiceVerified().then((res)=>{
            this.option1.series[0].data[0].value=res.data;
            this.serviceProviders=res.data;
            console.log(this.option1.series[0].data[0].value);
        }).catch((err)=>{
            console.log(err);
        })
    },
    getServiceNonVerified(){
        StatsServices.getServiceNonVerified().then((res)=>{
            this.option1.series[0].data[1].value=res.data;
            this.serviceProviders=res.data;
            console.log(this.option1.series[0].data[1].value);
        }).catch((err)=>{
            console.log(err);
        })
    },
},
  computed:{
     
  }
}
</script>
<style scoped>
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