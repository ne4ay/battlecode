<template>
  <GlobalHeader />
  <MotivationSlider class="slider" :images="images"/>
  <HelloWorld msg="Welcome to Your Vue.js App"/>

</template>

<script>
import MotivationSlider from "@/components/slider/MotivationSlider";
import GlobalHeader from '@/components/header/GlobalHeader.vue'
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    GlobalHeader,
    HelloWorld,
    MotivationSlider
  },
  data() {
    return {
      images: ['slider/slider_1.jpg', 'slider/slider_2.jpg', 'slider/slider_3.jpg']
    }
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
.slider {
  height: 100pt;
}
</style>
