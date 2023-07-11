<template>
  <v-app>
    <Navbar></Navbar>
    <main>
      <Introduction></Introduction>
    </main>
    <footer>
      <Footer></Footer>
    </footer>
    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script >
import Navbar from './components/home/Navbar.vue';
import Introduction from './components/home/Introduction.vue';
import Footer from './components/home/Footer.vue';
export default {
  name: 'App',
  components:{
    Navbar,Introduction,Footer
  },
  data: () => ({
    //
  }),
};
</script>
<style>
*{
  box-sizing: border-box;
  overflow: hidden;
  scroll-behavior: smooth;
}
body{
  cursor: url('../src/assets/cursor.png'),auto;
  font-family: Tahoma, Geneva, sans-serif;
  scroll-behavior: smooth;
}

</style>