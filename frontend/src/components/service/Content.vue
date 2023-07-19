<template>
    <div class="content">
        
        <v-layout class="hidden-sm-and-down">
            <div class="categories d-flex align-center justify-space-around" style="width: 100%; border-bottom: 0.1mm solid #CFD0D3;">
                <li v-for="i in 5" :key="i" style="list-style-type: none; margin: 0px 10px 20px ;">Hotel</li>
            </div>
        </v-layout>
        
        <v-container style="margin-top: 50px;">
            <p style="font-size: 40px;">Hello!Ghassen</p>
            <div class="introduction" style="border: 0.1mm solid #CFD0D3; height: auto;border-radius: 10px;padding: 20px 25px 20px 25px;">
                <v-layout  row wrap>
                    <v-flex>
                        <v-layout row wrap>
                            <v-flex class="d-flex justify-center align-center"> 
                                    <img src="../../assets/catalogue.png" alt="" width="100px" >
                            </v-flex>
                            <v-flex class="pa-5 mt-xs-0">
                                <p style="font-weight: bold;margin-bottom: 5px;" class="">Lorem ipsum dolor sit amet consectetur, adipisicing elit.</p>
                                <p class="">Lorem ipsum dolor sit amet consectetur adipisicing.</p>
                            </v-flex>
                        </v-layout>  
                    </v-flex>
                    <v-flex></v-flex>
                </v-layout>
            </div>

            <div class="mostRaited" style="height: 400px;border: 0.1mm solid #CFD0D3;border-radius: 10px; margin-top: 50px;">

            </div>
        </v-container>
    </div>
</template>
<script>
export default {

}
</script>
<style scoped>
*{
    font-family: Tahoma, Geneva, sans-serif;
    scroll-behavior: smooth;
    overflow: hidden;
    box-sizing: border-box;
}
</style>