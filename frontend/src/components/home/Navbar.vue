<template>
    <div class="navbar">
        <div class="nav">
            <v-toolbar flat class="toolbar">
                     
                    <div class="logo">
                        <img src="../../assets/logo.png" id="logo-nav" width="220px">
                    </div>
                        
                        <v-spacer></v-spacer>
                        <v-tooltip bottom>
                            <template v-slot:activator="{ on, attrs }">
                                <a href="#service"><li class="mr-7 ml-7 hidden-md-and-down" v-bind="attrs" v-on="on">Services <v-icon large color="#0c1a22">mdi-room-service-outline</v-icon></li></a>
                            </template>
                            <span>Assistance, aide</span>
                        </v-tooltip>
                        <v-tooltip bottom>
                            <template v-slot:activator="{ on, attrs }">
                                <a href="#categorie"><li class="mr-7 ml-7 hidden-md-and-down" v-bind="attrs" v-on="on">Categories <v-icon large color="#0c1a22">mdi-account-group-outline</v-icon></li></a>
                            </template>
                            <span>Regroupement, organisation</span>
                        </v-tooltip>
                        <v-tooltip bottom>
                            <template v-slot:activator="{ on, attrs }">
                                <a href="#about"><li class="mr-7 ml-7 hidden-md-and-down" v-bind="attrs" v-on="on">A Propos <v-icon large color="#0c1a22">mdi-shape-circle-plus</v-icon></li></a>
                            </template>
                            <span>En savoir plus sur nous</span>
                        </v-tooltip>
                        <!-- --------------------menu-------------------------- -->
                            <v-menu offset-y transition="slide-x-transition" 
                            right
                            >
                                <template v-slot:activator="{ on, attrs }">
                                    <v-btn
                                    
                                    v-bind="attrs"
                                    v-on="on"
                                    plain
                                    class="hidden-lg-and-up grey--text mr-3"
                                    >
                                    <v-icon size="43px" color="#7b88d1">
                                        mdi-menu
                                    </v-icon>
                                    </v-btn>
                                </template>
                                <v-list>
                                
                                    <v-list-item
                                    v-for="link in links"
                                    :key="link.title"
                                    >
                                        <v-list-item-title>
                                            <v-btn plain> <a :href="link.to" style="color:#0c1a22">{{link.title}}</a> </v-btn>
                                        </v-list-item-title>
                                    </v-list-item>
                                    <v-list-item>
                                        <v-list-item-title>
                                            <v-btn color="#7b88d1" class="hidden-md-and-up"><span><router-link to="/signup" style="color:#0c1a22;">Inscrire</router-link></span></v-btn>
                                        </v-list-item-title>
                                    </v-list-item>
                                </v-list>
                            </v-menu>
                            <button class="sign-btn hidden-sm-and-down" > <span><router-link to="/signup" style="color:#0c1a22;">Inscrire</router-link></span></button>
                
            </v-toolbar>
        </div>
      
    </div>
    
</template>

<script>



export default {
    data(){
        return{
            links:[
            {title:'Services',icon:'mdi-room-service-outline',tooltip:'Assistance, aide',to:"#service1"},
            {title:'Categories',icon:'mdi-account-group-outline',tooltip:'Regroupement, organisation',to:"#categorie"},
            {title:'A Propos',icon:'mdi-shape-circle-plus',tooltip:'En savoir plus sur nous',to:"#about"}
        ],
        route:"/signup"
        }
    },  
    methods:{
    },
    components: {
    },
}
</script>
<style scoped>
*{
    font-family: Tahoma, Geneva, sans-serif;
    scroll-behavior: smooth;
}
a{
    text-decoration: none;
}
.toolbar{
    position: fixed;
    z-index: 999;
    width: 100%;
    height: 100px;
}
li{
    list-style: none;
    transition: transform 0.3s ease;
    cursor: pointer;
    font-size: 22px;
    padding-right: 10px;
    color: #0c1a22;
}

li:hover {
  transform: translateY(-6px);

}


.sign-btn {
  color: #0c1a22 !important;
  border: 3px solid #7b88d1;
  padding: 5px 20px;
  font-size: 5px;
  cursor: pointer;
  transition: box-shadow 0.3s ease;
  margin-left: 70px;
  font-size: 20px;
  border-radius: 5px;
}

.sign-btn:hover {
  box-shadow: 0 0 15px #7b88d1;
  color: #ffffff;
  background-color: #7b88d1;

}
.logo{
    margin-left: 100px;
}
</style>