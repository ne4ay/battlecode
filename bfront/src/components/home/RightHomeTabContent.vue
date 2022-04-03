<template>
  <div class="tab-container" v-scrollanimation>
    <img :src="require('@/assets/' + image)" alt="image-content" class="content-block" id="tab-image">
    <span id="tab-text" class="content-block">
      {{ text }}
    </span>
  </div>
</template>

<script>
export default {
  name: "RightHomeTabContent",
  props: {
    text: String,
    image: String,
  },
}
</script>

<style scoped>
@import "HomeTabStyle.css";
.before-enter {
  opacity: 0.1;
  transform: translateX(-400pt);
  transition: all 1s cubic-bezier(.41, .01, .57, 1.61);
}

.enter {
  opacity: 1;
  transform: translateX(0pt);
}
</style>