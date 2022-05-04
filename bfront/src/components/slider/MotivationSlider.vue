<template>
  <div class="container">
    <div class="button" id="left-button" @click="turnLeft">
      <IndexIcon iconName="left-arrow" class="icon"/>
    </div>
    <div class="button" id="right-button" @click="turnRight">
      <IndexIcon iconName="right-arrow" class="icon"/>
    </div>
    <div class="image-container">
      <div id="slide">
        <span id="slide-label">{{this.slides[0].label}}</span>
      </div>
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
    slides: {
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
      const arraySize = this.slides.length;
      if (this.currentIndex + n >= arraySize) {
        this.currentIndex = (this.currentIndex + n) % arraySize;
      } else if (this.currentIndex + n < 0) {
        this.currentIndex = arraySize - 1;
      } else {
        this.currentIndex += n;
      }
      this.animationOfTurningSlide();
    },
    turnRight() {
      this.turn(1);
    },
    turnLeft() {
      this.turn(-1);
    },
    animationOfTurningSlide() {
      const slideObj = this.slides[this.currentIndex];
      const imageName = slideObj.path;
      const imagePath = require('@/assets/' + imageName);
      const slideElem = document.getElementById('slide');
      const slideLabelElem = document.getElementById('slide-label');
      var percent = 0;
      let unFadeFun = () => {
        let unFadeTimer = setInterval(function () {
          if (percent <= 0){
            clearInterval(unFadeTimer);
          }
          slideElem.style.backgroundColor = "rgba(0,0,0,"+ percent +")";
          percent -= 0.08;
        }, 7);
      }
      let fadeTimer = setInterval(function () {
        if (percent >= 1){
          clearInterval(fadeTimer);
          slideElem.style.background = "url(" + imagePath + ") no-repeat center top";
          slideLabelElem.innerHTML = slideObj.label;
          unFadeFun();
        }
        slideElem.style.backgroundColor = "rgba(0,0,0,"+ percent +")";
        percent += 0.08;
      }, 7, unFadeFun);

    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');
* {
  --width-of-button: 14%;
}
.container {
  display: flex;
  justify-content: space-between;
  height: 400pt;
  position: relative;
}

.button {
  display: flex;
  background: rgba(0, 0, 0, 0.4);
  align-items: center;
  width: var(--width-of-button);
}

.button:hover {
  background: rgba(0, 0, 0, 0.57);
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

.image-container {
  background: #222;
  height: inherit;
  width: 100%;
  position: absolute;
  z-index: -3;
}
#slide {
  background-blend-mode: multiply;
  background: url("@/assets/slider/slider_1.jpg") no-repeat center top;
  background-color: rgba(0,0,0,0);
  height: inherit;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  width: 100%;
}
#slide-label {
  background: rgba(0,0,0,0.5);
  font-family: 'Russo One', sans-serif;
  width: calc(100% - var(--width-of-button) * 2 - 6%);
  padding: 5pt 15pt;
  border-radius: 20pt;
  font-size: 60pt;
  color: rgba(255, 153, 68, 0.6);
}

</style>