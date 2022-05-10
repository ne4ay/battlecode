<template>
  <div class="bar-wrapper">
    <svg class="bar-container">
      <defs>
        <linearGradient id="lgrad" x1="0%" y1="50%" x2="100%" y2="50%" >

          <stop offset="0%" style="stop-color:rgb(62,196,228);stop-opacity:1.00" />
          <stop :offset="((100/valueInPercent) * 28) + '%'" style="stop-color:rgb(255,190,147);stop-opacity:1.00" />
          <stop :offset="((100/valueInPercent) * 100) + '%'" style="stop-color:rgb(255,118,26);stop-opacity:1.00" />

        </linearGradient>
      </defs>
      <rect :width="rectWidth + 'pt'" :height="height"
            class="rect-class"
            style=""/>
    </svg>
  </div>
</template>

<script>
export default {
  name: "ExperienceBar",
  props: {
    width: {
      type: Number,
      default: 300,
    },
    height: {
      type: Number,
      default: 50
    },
    valueInPercent: {
      type: Number,
      default: 100
    }
  },
  data() {
    return {
      rectWidth: (this.valueInPercent * this.width) / 100,
    }
  }
}
</script>

<style scoped>
.bar-wrapper {
  width: inherit;
}

.bar-container {
  border-radius: 20pt;
  width: inherit;
  border: 2pt solid #ead1c6;
}

.rect-class {
  fill: url(#lgrad);
  stroke-width: 1;
  stroke: rgb(0, 0, 0)
}
</style>