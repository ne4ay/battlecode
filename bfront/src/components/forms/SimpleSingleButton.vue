<template>
  <button class="btn">
    <slot></slot>
  </button>
</template>

<script>
export default {
  name: "SimpleSingleButton"
}
</script>

<style scoped>
.btn {
  font-family: 'Ubuntu', sans-serif;
  font-size: 16pt;
  background: none;
  color: #f3ece4;
  padding: 5pt 10pt;
  margin: 5pt 15pt 5pt 15pt;
  border-radius: 50pt;
  border: none;
  background: rgba(0, 0, 0, .2);
}

.btn:hover {
  color: #f7a36a;
  background: rgba(0, 0, 0, .6);
}

.btn:active {
  color: #e36815;
  background: rgba(0, 0, 0, .4);
}
</style>