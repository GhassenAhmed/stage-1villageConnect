<template>
  <v-app>
    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script >
import Aos from "aos"
export default {
  name: 'App',
  mounted(){
    Aos.init();
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