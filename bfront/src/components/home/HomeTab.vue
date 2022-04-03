<template>
  <div class="container" :style="backgroundStyle">
    <component :is="HomeTabContentComponent" :text="this.text" :image="this.image" class="content"/>
  </div>
</template>

<script>
import LeftHomeTabContent from "@/components/home/LeftHomeTabContent";
import RightHomeTabContent from "@/components/home/RightHomeTabContent";

export default {
  name: "HomeContent",
  props: {
    text: String,
    image: String,
    textPosition: String,
  },
  computed: {
    backgroundStyle() {
      return 'background: ' + (this.textPosition === 'right' ? '#161616' : '#111111') + ';'
    },
    HomeTabContentComponent() {
      switch (this.textPosition) {
        case 'left':
          return LeftHomeTabContent;
        case 'right':
          return RightHomeTabContent;
        default:
          return null;
      }
    }
  }
}
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: row;
  height: 230pt;
  justify-content: center;
}
.content {
  height: 210pt;
}

</style>