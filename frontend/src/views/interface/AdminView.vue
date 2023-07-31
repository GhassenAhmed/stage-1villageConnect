<template>
    <div class="root">
        <v-navigation-drawer
        permanent
        absolute
        expand-on-hover
        style="height: 100%;"
      >
        <v-list>
          <v-list-item class="px-2">
            <v-list-item-avatar>
              <v-img :src="store.user['photo']"></v-img>
            </v-list-item-avatar>
          </v-list-item>

          <v-list-item link>
            <v-list-item-content>
              <v-list-item-title class="text-h6">
                {{store.user['firstName'] + " " +store.user['lastName']}} 
              </v-list-item-title>
              <v-list-item-subtitle>{{ store.user['email'] }}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-list>

        <v-divider></v-divider>

        <v-list
          nav
          dense
        >
          <v-list-item link>
            <v-list-item-icon>
              <v-icon>mdi-folder</v-icon>
            </v-list-item-icon>
            <v-list-item-title>My Files</v-list-item-title>
          </v-list-item>
          <v-list-item link>
            <v-list-item-icon>
              <v-icon>mdi-account-multiple</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Shared with me</v-list-item-title>
          </v-list-item>
          <v-list-item link>
            <v-list-item-icon>
              <v-icon>mdi-star</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Starred</v-list-item-title>
          </v-list-item>
        </v-list>
        </v-navigation-drawer>
    </div>
   
  </template>
  
  <script>
  import { AuthUser } from "@/store/AuthStore";
    export default {
      setup(){
        const store=AuthUser();
        return{
            store
        }
    },
  
      data () {
        return {
            items: [
          { title: 'Home', icon: 'mdi-home-city' },
          { title: 'My Account', icon: 'mdi-account' },
          { title: 'Users', icon: 'mdi-account-group-outline' },
        ],
        }
      },
  
      methods: {
        
      },
    }
  </script>