<template>
  <component :is="IconComponent" class="icon" preserveAspectRatio="none" viewBox="0 0 21 21"/>
</template>

<script>
import LeftArrow from '@/components/icon/src/LeftArrow';
import RightArrow from "@/components/icon/src/RightArrow";

export default {
  props: {
    iconName: {
      type: String,
      required: true
    },
  },
  computed: {
    IconComponent() {
      switch (this.iconName) {
        case 'left-arrow':
          return LeftArrow;
        case 'right-arrow':
          return RightArrow;
        default:
          return null;
      }
    }
  }
}


</script>

<style>
svg {
  height: 70%;
  width: 74%;
}
polyline {
  fill: none;
  stroke: rgba(255, 255, 255, 0.6);
  stroke-width: 3;
}
</style>