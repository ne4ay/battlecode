<template>
  <button class="btn">
    <slot></slot>
  </button>
</template>

<script>
export default {
  name: "SimpleButton"
}
</script>

<style scoped>
.btn {
  padding: 5pt 10pt;
  background: #202020;
  color: #f7a36a;
  border: 1pt solid #f7a36a;
}
</style>