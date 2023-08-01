<template >
    <div class="root">
        <v-list-item
          v-for="link in links"
          :key="link.title"
          class="mb-3"
        >
        <v-btn text class="white--text">
            <v-list-item-icon>
            <v-icon color="white">{{ link.icon }}</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>{{ link.title }}</v-list-item-title>
          </v-list-item-content>
        </v-btn>
        </v-list-item>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                links:[
                    {icon:"mdi-account",to:"",title:"Utilisateurs"},
                    {icon:"mdi-room-service",to:"",title:"Services"},
                    {icon:"mdi-sort-variant",to:"",title:"Categories"},
                    {icon:"mdi-calculator",to:"",title:"Statistiques"},
                ]
            }
        },
        methods:{
        }
    }
</script>
<style scoped>
.v-sheet.v-list {
    width: 289px;
}
.v-menu__content{
    min-width: 289px !important;
    top: 284px !important;
    left: 650px !important;
    transform-origin: left top !important;
    z-index: 204 !important;
}
</style>