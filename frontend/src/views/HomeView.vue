<template>
    <div class="home">
        <Navbar></Navbar>
        <main >
        <Introduction data-aos="fade-left"></Introduction>
        </main>
        <footer>
        <Footer></Footer>
        </footer>
    </div>
</template>

<script>
import Navbar from '../components/home/Navbar.vue';
import Introduction from '../components/home/Introduction.vue';
import Footer from '../components/home/Footer.vue';
export default {
    name: 'HomeView',
    components:{
    Navbar,Introduction,Footer
    },
}

</script>
<style scoped>
*{
  box-sizing: border-box;
  overflow: hidden;
  scroll-behavior: smooth;
}
</style>