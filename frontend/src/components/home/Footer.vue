<template>
    <div class="footer mt-5">
        <v-layout class="mr-5 ml-10" row wrap>
            <v-flex xl6 md6 lg6 sm12 xs12 class="pt-5" style="color: #757575;"><p style="font-size: 20px;">Restez connecté avec nous sur les réseaux sociaux:</p></v-flex>
            <v-flex xl6 md6 lg6 sm12 xs12>
                <div class="icons pt-5 mb-5">
                    <v-icon class="icon px-5 py-2" large>mdi-facebook</v-icon>
                    <v-icon class="icon px-5 py-2" large>mdi-twitter</v-icon>
                    <v-icon class="icon px-5 py-2" large>mdi-instagram</v-icon>
                    <v-icon class="icon px-5 py-2" large>mdi-linkedin</v-icon>
                    <v-icon class="icon px-5 py-2" large>mdi-github</v-icon>
                </div>
            </v-flex>
        </v-layout>
        <div class="divider"></div>
        <v-layout class="mt-2" style="gap: 20px;" row wrap>
            <v-flex col xl4 md4 lg4 sm5 xs12 class="ml-5  pr-5">
                <v-layout class="mb-5">
                    <v-icon class="px-3" color="#9FA6B2">mdi-diamond</v-icon>
                    <h3 class="">VillageConnect</h3>
                </v-layout>
                <p style="text-align: justify;max-width: 400px;" class="text-sm-body-2 text-md-body-1 ">Lorem ipsum dolor sit amet consectetur adipisicing elit. Quis eligendi debitis amet consectetur error doloremque sapiente blanditiis, repellendus vel voluptate?</p>
            </v-flex>
            <v-flex class="mt-3 ml-5" xl3 md3 lg3 sm5 xs12>
                <h3 class="mb-4">LIENS UTILES</h3>
                <p class="py-2">
                    <a href="#!" class="text-reset">Pricing</a>
                </p>
                <p class="py-2">
                    <a href="#!" class="text-reset">Settings</a>
                </p>
                <p>
                    <a href="#!" class="text-reset">Orders</a>
                </p>
                <p class="py-2">
                    <a href="#!" class="text-reset">Help</a>
                </p>
            </v-flex>
            
            <v-flex class="mt-3 ml-5" xl3 md3 lg3 sm5 xs12>
                <h3 class="mb-4 px-3">CONTACT</h3>

                <p class="py-2">
                    <v-icon class="icon pr-2" large>mdi-home</v-icon>
                    Bizerte 7000,TN
                </p>

                <p class="py-2">
                    <v-icon class="icon pr-2" large>mdi-phone</v-icon>
                    +216-58253557
                </p>

                <p class="py-2">
                    <v-icon class="icon pr-2" large>mdi-email</v-icon>
                    ghassenahmed74@gmail.com
                </p>
            </v-flex>
        </v-layout>

        <v-layout class="copyright d-flex justify-center">
            
                <v-icon class="icon px-4" large>mdi-copyright</v-icon> <p class="grey--text mt-4">2023 VillageConnect.All rights reserved.</p>

        </v-layout>
    </div>
</template>
<script>
export default {

}
</script>
<style scoped>
*{

    scroll-behavior: smooth;
    overflow: hidden;
    box-sizing: border-box;
}
.footer{
    height: auto;
}
p>a{
    text-decoration: none;
    color: #757575;
    
}
.copyright{
    padding-top: 100px;
    padding-bottom: 100px;
    background-color: #F9F9F9;
}
.icon{
    cursor: pointer;
    transition: transform 0.4s ease;
    color: #9FA6B2;
}
.icon:hover{
    transform: translateY(-5px);
}
.divider{
    margin-top: 20px;
    border: solid 0.1px #E0E0E0;
}
h3{
    font-size: 24px;
    color: #757575;
}
p{
    font-size: 18px;
}
</style>