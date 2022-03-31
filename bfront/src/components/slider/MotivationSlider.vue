<template>
  <div class="container">
  <div class="button" id="left-button">
    <IndexIcon iconName="left-arrow" class="icon" />
  </div>
  <div class="button" id="right-button">
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
  data() {
    return {

    }
  },
  props: {
    images: {
      type: Array,
      default: () => []
    }
  },
  methods: {

  }
}
</script>

<style scoped>
.container {
  display: flex;
  border: 5px dotted black;
  justify-content: space-between;
  height: 250pt;
}
.button {
  display: flex;
  background: rgba(0, 0, 0, 0.3);
  align-items: center;
  width: 10%;
}
.icon {
  padding: 0;
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