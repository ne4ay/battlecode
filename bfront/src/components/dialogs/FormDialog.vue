<template>
  <transition name="swim" id="trans">
        <div class="dialog-content" v-if="show">
          <slot></slot>
        </div>
  </transition>
</template>

<script>
export default {
  name: "FormDialog",
  props: {
    show: {
      type: Boolean,
      default: false
    }
  }
}
</script>

<style scoped>

.dialog-content {
  z-index: 2;
  margin: auto;
  margin-top: 150pt;
  background: white;
  border-radius: 12pt;
  min-height: 50pt;
  min-width: 300pt;
  padding: 20pt;
}

.swim-enter-active ,
.swim-leave-active {
  transition: all 0.6s cubic-bezier(.42,0,.77,1.53);
}

.swim-enter-from ,
.swim-leave-to {
  transform: translateY(-400pt);
}
</style>