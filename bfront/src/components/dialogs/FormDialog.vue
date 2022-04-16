<template>
  <transition name="swim" id="tr  ans">
    <div class="dialog-container" v-if="isShowing">
      <div class="dialog-title">
        {{title}}
      </div>
      <div class="dialog-content">
        <slot></slot>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  name: "FormDialog",
  props: {
    title: {
      type: String
    },
    isShowing: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    hideDialog() {
      if (this.isShowing) {
        this.$emit('update:isShowing', false)
      }
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');
.dialog-container {
  z-index: 2;
  margin: 150pt   auto auto;
  background: #222222;
  border-radius: 12pt;
  min-height: 50pt;
  min-width: 300pt;
  font-family: 'Ubuntu', sans-serif;
  font-size: 17pt;
}

.dialog-title {
  color: #f7a36a;
  padding: 15pt 15pt 5pt 15pt;
}

.dialog-content {
  color: #ead1c6;
  padding: 5pt 15pt 15pt 15pt;
}

.swim-enter-active,
.swim-leave-active {
  transition: all 0.6s cubic-bezier(.42, 0, .77, 1.53);
}

.swim-enter-from,
.swim-leave-to {
  transform: translateY(-400pt);
}
</style>