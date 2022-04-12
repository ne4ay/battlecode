<template>
  <GlobalHeader/>
  <MotivationSlider class="slider" :slides="slides"/>
  <HomeContent/>
</template>

<script>
import HomeContent from "@/components/home/HomeContent";
import GlobalHeader from "@/components/header/GlobalHeader";
import MotivationSlider from "@/components/slider/MotivationSlider";

export default {
  name: "HomeView",
  components: {
    HomeContent,
    GlobalHeader,
    MotivationSlider,
  },
  data() {
    return {
      slides: [
        {path: 'slider/slider_1.jpg', label: "Достигни мастерства"},
        {path: 'slider/slider_2.jpg', label: "Брось вызов"},
        {path: 'slider/slider_3.jpg', label: "Отточи свои навыки"}]
    }
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}

.slider {
  height: 400pt;
}

</style>