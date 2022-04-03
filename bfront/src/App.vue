<template>
  <GlobalHeader/>
  <MotivationSlider class="slider" :slides="slides"/>
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
      slides: [{path: 'slider/slider_1.jpg', label: "Достигни мастерства"},
        {path: 'slider/slider_2.jpg', label: "Брось вызов"},
        {path: 'slider/slider_3.jpg', label: "Отточи свои навыки"}]
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
