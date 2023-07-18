<template>
    <div class="home">
       
                <!-- <v-layout row wrap>
                    <v-flex xl4 lg4 md4 sm6 xs6>
                        <div class="logo  mt-5 ml-15" style="width: auto;">
                            <img src="../../assets/logo.png" id="logo-nav"  style="max-width: 170px;" class="text-center-sm">
                        </div>
                    </v-flex>
                    <v-flex xl4 lg4 md4 sm12 xs12>
                        <v-autocomplete
                        filled
                        rounded
                        item-text="name"
                        item-value="id"
                        label="Search for a coin..."
                        solo
                        style="height: 60px;max-width: auto;"
                        
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
                        </v-autocomplete>
                    </v-flex>

                    <v-flex xl4 lg4 md4 class="hidden-sm-and-down">
                        <div class="div float-end">
                            <v-icon class="ml-10 hidden-sm-and-down"  X Small >
                                mdi-bell-outline
                            </v-icon>

                            <v-icon class="ml-10 hidden-sm-and-down"  X Small>
                                mdi-email-outline
                            </v-icon>

                            <v-icon class="ml-10 hidden-sm-and-down" X Small>
                                mdi-heart-outline
                            </v-icon>
                            
                            <v-avatar
                            color="primary"
                            size="50"
                            class="ml-10"
                            ></v-avatar>
                        </div>
                    </v-flex>
                </v-layout> -->
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
a{
    text-decoration: none;
}

.navbar{
    height: 400px;
}

</style>