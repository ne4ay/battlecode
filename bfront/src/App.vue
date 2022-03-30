<template>
  <GlobalHeader />
  <img alt="Vue logo" src="./assets/logo.png">
  <HelloWorld msg="Welcome to Your Vue.js App"/>
</template>

<script>
import GlobalHeader from '@/components/GlobalHeader.vue'
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    GlobalHeader,
    HelloWorld,
  }
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
}

#app {

  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
