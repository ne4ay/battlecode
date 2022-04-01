<template>
  <div class="container" id="slide">
  <div class="button" id="left-button" @click="turnLeft">
    <IndexIcon iconName="left-arrow" class="icon" />
  </div>
  <div class="button" id="right-button" @click="turnRight">
    <IndexIcon iconName="right-arrow" class="icon"/>
  </div>
  </div>
</template>

<script>
import IndexIcon from '@/components/icon/IndexIcon.vue';

export default {
  name: "MotivationSlider",
  components: {
    IndexIcon
  },
  props: {
    images: {
      type: Array,
      default: () => []
    },
  },
  data() {
    return {
      currentIndex: 0
    }
  },
  methods: {
    turn(n) {
      const arraySize = this.images.length;
      if (this.currentIndex + n >= arraySize) {
        this.currentIndex = (this.currentIndex + n) % arraySize;
      } else if (this.currentIndex + n < 0) {
        this.currentIndex = arraySize - 1;
      } else {
        this.currentIndex += n;
      }
      const imageName = this.images[this.currentIndex];
      const imagePath = require('@/assets/' +imageName);
      const elem = document.getElementById('slide');
      elem.setAttribute('style', 'background: url("' + imagePath + '") no-repeat center top #222');
    },
    turnRight(event) {
      console.log(event)
      this.turn(1);
    },
    turnLeft() {
      this.turn(-1);
    }
  }
}
</script>

<style scoped>
.container {
  background: url("@/assets/slider/slider_1.jpg") no-repeat center top #222;
  display: flex;
  justify-content: space-between;
  height: 400pt;
}
.button {
  display: flex;
  background: rgba(0, 0, 0, 0.3);
  align-items: center;
  width: 13%;
}
.button:hover {
  background: rgba(0, 0, 0, 0.52);
}

.icon {
  padding: 0;
  margin-bottom: 40pt;
}
.icon polyline {
  height: 30pt;
}

#left-button {
  justify-content: left;
}
#right-button {
  justify-content: right;
}


</style>