<template>
    <div id="app">
            <v-app-bar  flat style="background-color: #ffffff;margin-top: 20px;height: 100px;border-bottom: 0.1mm solid #CFD0D3;"> 
            <v-app-bar-nav-icon @click="drawer = true" 
                                class="d-flex d-sm-none" 
                                ></v-app-bar-nav-icon>
                <v-toolbar-title class="text-center-sm hidden-sm-and-down" style="font-size: 25px;font-weight: bolder;">Village<span style="color: #105955d1;">Connect<span style="font-weight: bolder;font-size: 35px;color: #12c2b9;">.</span></span></v-toolbar-title> 
                        <!-- <v-autocomplete
                                fill
                               elevation=0
                                label="Tapez votre village..."
                                solo
                                style="height: 60px;max-width: auto;"
                                class="ml-lg-14 ml-md-12 ml-sm-10" 
                                >
                                <template v-slot:no-data>
                                    <v-list-item>
                                    <v-list-item-title>
                                    
                                    </v-list-item-title>
                                    </v-list-item>
                                </template>
                                <template v-slot:selection="{ attr, on, item, selected }">
                                    <v-chip
                                    v-bind="attr"
                                    :input-value="selected"
                                    color="blue-grey"
                                    class="white--text"
                                    v-on="on"
                                    >
                                    <v-icon left>
                                        mdi-bitcoin
                                    </v-icon>
                                    <span v-text="item.name"></span>
                                    </v-chip>
                                </template>
                        </v-autocomplete> -->
                        <v-autocomplete
                        filled
                        rounded
                        style="height: 60px;max-width: auto;"
                        class="ml-lg-14 ml-md-12 ml-sm-10" 
                        
                        
                        ></v-autocomplete>
                        <v-spacer></v-spacer>
        
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
                
                    <v-avatar
                    color="primary"
                    size="50"
                    class="ml-10 white--text hidden-xs-only"
                    >GA</v-avatar>
            </v-app-bar>
            <v-navigation-drawer
            v-model="drawer"
            absolute
            temporary
            >
            <v-list
                nav
                dense
            >
                <v-list-item-group
                >
                <v-list-item >
                    <v-list-item-title></v-list-item-title>
                </v-list-item>

                </v-list-item-group>
            </v-list>
            </v-navigation-drawer>
            <Content style="margin-top: 10px;"></Content>
    </div>
</template>
<script>
import Content from '../../components/service/Content.vue'
export default {
    name:'ServiceView',
components:{
    Content
},
data(){
    return{
        drawer: false,
        
    }
},
methods:{
   
    
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



</style>