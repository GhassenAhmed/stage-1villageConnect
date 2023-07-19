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

            <!-- ----------------------------------------Most raited div------------------------------  -->
            <div class="mostRaited" style="height: auto;border: 0.1mm solid #CFD0D3;border-radius: 10px;margin-top: 50px;">
                <p style="font-size: 30px;padding-left:  70px;padding-top: 20px;">Most raited !</p>
                <div class="card d-flex wrap">
                    <v-card
                    :loading="loading"
                    class="mx-auto my-5"
                    max-width="250"
                    min-width="100"
                    v-for="i in 3"
                    :key="i"
                >
                    <template slot="progress">
                    <v-progress-linear
                        color="deep-purple"
                        height="5"
                        indeterminate
                    ></v-progress-linear>
                    </template>

                    <v-img
                    height="150"
                    src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
                    ></v-img>

                    <v-card-title>Cafe Badilico</v-card-title>
                    <v-divider class="mx-4"></v-divider>
                    <v-card-text>
                    <v-chip-group
                        v-model="selection"
                        active-class="deep-purple accent-4 white--text"
                        column
                    >
                        <v-chip>5:30PM</v-chip>
                    </v-chip-group>
                    </v-card-text>

                    <v-card-actions>
                    <v-btn
                        color="deep-purple lighten-2"
                        text
                        @click="reserve"
                    >
                        Reserve
                    </v-btn>
                    </v-card-actions>
                    </v-card>
                </div>
            </div>
            <!-- ---------------------------------------- /Most raited div------------------------------  -->


            <!-- ----------------------------------------verified div------------------------------  -->
            <div class="verified" style="height: auto;border-radius: 10px;margin-top: 50px;background-color: #FAFAFA;">
                <p style="font-size: 30px;padding-left:  70px;padding-top: 20px;">Verified!</p>
                <div class="card d-flex">
                        <v-card
                        v-for="i in 3"
                        :key="i"
                        :loading="loading"
                        class="mx-auto my-5"
                        max-width="250"
                        min-width="100"
                        style="background-color: #FAFAFA;"
                        >
                        <template slot="progress">
                        <v-progress-linear
                            color="deep-purple"
                            height="5"
                            indeterminate
                        ></v-progress-linear>
                        </template>

                        <v-img
                        height="150"
                        src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
                        ></v-img>
                        <v-card-title>business</v-card-title>
                        <v-card-text>Lorem ipsum dolor sit.</v-card-text>
                        </v-card>
                        
                    </div>  
                </div>
            <!-- ---------------------------------------- /verified div------------------------------  -->
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