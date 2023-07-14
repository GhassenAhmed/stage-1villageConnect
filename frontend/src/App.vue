<template>
  <v-app>
    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script >
export default {
  name: 'App',
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