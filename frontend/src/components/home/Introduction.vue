<template>
<div class="section1">
    <v-container class="py-15 container--fluid">
        <div class="introduction px-15">
            <v-layout flex  wrap class="pa-5 ma-5">
                <v-flex xl4 lg4 md12 sm12 xs12>
                    <div class="img1 hidden-md-and-down">
                        <img src="../../assets/intro1.png" height="400px" alt="">
                    </div>
                </v-flex>
                <v-flex xl4 lg4 md12 sm12 xs12>
                    <div class="p">
                        <span id="p1">{{ p1 }}</span> <br>
                        <div class="title">
                            <span class="title-word title-word-1">Vill</span>
                            <span class="title-word title-word-2">age</span>
                            <span class="title-word title-word-3">Connect</span>
                        </div>
                        <button class="sign-btn" style="font-family: font-family: 'Courier New', Courier, monospace;">Commencer</button>  
                    </div>     
                </v-flex>
                <v-flex xl4 lg4 md4 sm12 xs12>
                    <div class="img2 hidden-md-and-down">
                        <img src="../../assets/intro2.png" height="400px" alt="">
                    </div>
                </v-flex>
            </v-layout>
        </div>
        <div class="line1">
        </div>
        <div class="subject">
            <h3 class="">Nos services populaires</h3>
        </div>
        <div class="service" id="service">
            <v-container fluid>
                <div class="card2 pa-4 text-center rounded-0">
                    <v-layout flex wrap class="py-5 justify-space-around">
                                <v-flex xl3 lg3 md6 sm12 xs12 class="px-5 py-4 text-center" v-for="service in services" :key="service.name" data-aos="fade-down">
                                    <div class="card-body">
                                        <div class="img">
                                            <v-img :src="service.image" width="500px" height="300px"></v-img>
                                        </div>   
                                        <p class="py-5 m-5" style="font-family:Tahoma, Geneva, sans-serif ;">{{service.desc}}</p>
                                    </div>
                                </v-flex>     
                    </v-layout>
                </div>
            </v-container>
        </div>
        
        <div class="service2">
            <v-layout flex wrap>
                <v-flex xl6 lg6 md6 sm12 xs12>
                    <div class="content pa-15">
                        <h3 style="margin-bottom: 70px; margin-top: -50px;">La meilleure partie ? Tout.</h3>
                        <div class="body" v-for="service in services2"  :key="service.titre">
                            <h4> <v-icon color="grey">mdi-check-circle-outline</v-icon>{{ service.titre }}</h4>
                            <p>{{ service.desc }}</p>
                        </div>
                    </div>
                </v-flex>
                <v-flex xl6 lg6 md6 sm12 xs12 class="mt-7 hidden-sm-and-down">
                    <img src="../../assets/service.png" width="600px" alt="">
                </v-flex>
            </v-layout>
        </div>

        <div class="subject" id="categorie">
            <h3 class="">Vous en avez besoin, nous l'avons.</h3>
        </div>

        <div class="categories">
            <v-layout flex class="ma-15" wrap>
                <v-flex v-for="categorie in categories" :key="categorie.desc">
                    <div class="text text-center ma-10">
                        <img :src="categorie.image" alt="" width="auto" height="100px">
                        <h3 style="font-size: 15px;">{{categorie.desc}}</h3> 
                    </div>
                </v-flex>
            </v-layout>
        </div>
        <div class="about" id="about">
            <v-layout flex wrap>
                <v-flex xl6 lg6 md6 sm12 xs12>
                    <div class="content pa-15">
                        <h3 style="margin-bottom: 70px; margin-top: -50px;">A propos nous !</h3>


                        <div class="body">
                            <h4> <v-icon color="grey">mdi-lightbulb-on-outline</v-icon>Notre mission</h4>
                            <p>Notre mission est de faciliter les services entre les prestataires de services et les clients dans le même village. Nous croyons fermement en l'importance de soutenir les entreprises locales et de favoriser le développement économique au niveau communautaire.</p>
                        </div>

                        <div class="body">
                            <h4> <v-icon color="grey">mdi-lightbulb-on-outline</v-icon>Notre Vision</h4>
                            <p>Notre vision est de créer une plateforme dynamique qui connecte les habitants d'un village aux différents services dont ils ont besoin, tout en encourageant les échanges et en renforçant le tissu social local. Nous nous efforçons de fournir une expérience conviviale, transparente et pratique.</p>
                        </div>
                    </div>
                </v-flex>
                <v-flex xl6 lg6 md6 sm12 xs12 class="mt-7 hidden-sm-and-down">
                    <img src="../../assets/service.png" width="600px" alt="">
                </v-flex>
            </v-layout>
        </div>
    </v-container>
</div>
</template>
<script>
export default {
    data(){
        return{
            p1:"Découvrez l'âme de chaque village grâce à notre plateforme innovante.",
            services:[
            {desc:'Découvrez les perspectives d emploi locales dans votre village. Explorez des opportunités passionnantes qui correspondent à vos compétences et aspirations.',image:require('../../assets/contact.png')},
            {desc:'Découvrez facilement les informations du fournisseur : coordonnées de contact, emplacement précis et description convaincante, tout à portée de main.',image:require('../../assets/job.png')},
            {desc:'Créez votre portfolio époustouflant en toute simplicité : mettez en valeur vos travaux, compétences et réalisations dans une présentation captivante.',image:require('../../assets/portfolio.png')},
            {desc:'Valorisez votre entreprise avec un catalogue captivant : présentez vos produits et services dans une présentation visuellement attrayante.',image:require('../../assets/catalogue.png')},
            ],
             services2:[
            {titre:" Découvrez les services de votre village",desc:"Explorez les services disponibles dans votre village depuis le confort de votre foyer"},
            {titre:" Localisation instantanée du service",desc:"Découvrez les informations de contact et la localisation précise d'un service spécifique, pour une expérience personnalisée et sans tracas."},
            {titre:" Évaluez et partagez votre expérience",desc:"Partagez votre expérience et exprimez votre opinion en évaluant les services proposés."},
            {titre:" Portfolio sur mesure pour votre service",desc:"Façonnez votre portfolio sur mesure et mettez en valeur votre service comme jamais auparavant."},
            ],
            categories:[
                {image:require("../../assets/restaurant.png"),desc:"Restaurant"},
                {image:require("../../assets/coffe.png"),desc:"Café"},
                {image:require("../../assets/hotel.png"),desc:"Hôtel"},
                {image:require("../../assets/vetements.png"),desc:"Boutique de vêtements"},
                {image:require("../../assets/decor.png"),desc:"Équipement de maison"},
                {image:require("../../assets/electronic.png"),desc:"Vendre des machines électriques"},
            ]
        }
    }
}
</script>
<style scoped>
h4{
    font-size: 25px;
}
*{
    scroll-behavior: smooth;
}
.content h3{
    font-size: 33px;
    font-family: Arial, Helvetica, sans-serif;
    color: #404145;
    margin-bottom: 20px;
}
.content{
    margin:100px 0 0;
}

.content .body h4{
    font-size: 19px;
    font-family: Arial, Helvetica, sans-serif;
    color: #404145;
}


.content .body p{
    font-size: 16px;
    font-family: Arial, Helvetica, sans-serif;
    color: #62646A;
    font-weight: lighter;
}

.content .body{
    margin-bottom: 20px;
}

.p{
    padding: 50px;
    font-family: Arial, Helvetica, sans-serif;
    font-size: 25px;
    letter-spacing: 2px;
    word-spacing: 2px;
    text-align: center;
    font-weight: bolder;
}
#p1{
    font-weight: bold;
    text-align: center;
    color: #0c1a22;
    font-family: 'Courier New', Courier, monospace;
}
.title{
    margin-top: 65px;
}
.title-word {
  animation: color-animation 4s linear infinite;
  font-size: 35px;
 
}

.title-word-1 {
  --color-1: #997bd1;
  --color-2: #525fe1;
  --color-3: #ff60aa;
}

.title-word-2 {
  --color-1: #7b88d1;
  --color-2: #5094df;
  --color-3: #a6cd49;
}

.title-word-3 {
  --color-1: #ACCFCB;
  --color-2: #E4A9A8;
  --color-3: #F1FDF7;
}

@keyframes color-animation {
  0%    {color: var(--color-1)}
  32%   {color: var(--color-1)}
  33%   {color: var(--color-2)}
  65%   {color: var(--color-2)}
  66%   {color: var(--color-3)}
  99%   {color: var(--color-3)}
  100%  {color: var(--color-1)}
}

.sign-btn {
  color: #0c1a22;
  border: 1px solid #94bfef;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
  transition: box-shadow 0.3s ease;
  margin-left: 70px;
  font-size: 20px;
  border-radius: 5px;
  width: 155px;
  margin-top: 40px;
  margin-right: 70px;
  background-color: #94bfef;
  font-family:  Tahoma, Geneva, sans-serif;
  font-weight: lighter;
}
.sign-btn:hover {
    color: white;
    box-shadow: 0 0 15px #94bfef;
}
.service{
    height: auto; 
    margin-top: -21px;
}
.card{
    border:2px solid#1976D2;
    padding: 20px;
    height: 33vh;
    background-color: #81D4FA;
    color: #444;
    display:flex;
    flex-direction: column;
    align-items: center;   
}

.card:hover{
    background-color: #1976D2;
    color: white;
    
    
}
.subject{
    margin-top: 100px;
    left:50%;
    margin-left: 55px;
    
}
.subject>h3{
    font-family: 'DM Sans', sans-serif;
    color: #0c1a22;
    font-size: 33px;
    display: inline-block;
}
.card-body{
    border-radius: 5px;
    -webkit-box-shadow: -6px 5px 20px -10px #b6bef1; 
    box-shadow: -6px 5px 20px -10px #7b88d1;
    cursor: pointer;
    transition: transform 0.3s ease;
    width: auto;
    padding: 40px;
    height: 550px;
}
.card-body p{
    font-size: 17px;
    text-align: center;
}
.card-body:hover{
    transform: translateY(-20px);
}
.line1{
    background-color: #59adcf;
    height: 40px;
    clip-path: polygon(0 41%, 98% 69%, 100% 75%, 0 71%);
    width: 100%;
}
.line2{
    background-color: #2f48a2;
    height: 40px;
    clip-path: polygon(0 41%, 98% 69%, 100% 75%, 0 71%);
    width: 100%;
}
.img{
    margin-left: 40px;
}
.service2{
    height: auto;
    background-color: #F1FDF7;
    margin-top: 100px;
}
.about{
    height: auto;
    background-color: #f7f0ed;
    margin-top: 100px;
}
</style>