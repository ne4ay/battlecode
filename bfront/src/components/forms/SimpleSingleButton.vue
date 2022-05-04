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
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');
.btn {
  font-family: 'Ubuntu', sans-serif;
  font-size: 16pt;
  background: none;
  color: #f3ece4;
  padding: 5pt 10pt;
  border-radius: 50pt;
  border: none;
  background: rgba(0, 0, 0, .2);
  -webkit-user-select: none;   /* Chrome/Safari/Opera */
  -moz-user-select: none;      /* Firefox */
  -ms-user-select: none;       /* Internet Explorer/Edge */
  user-select: none;           /* Non-prefixed version, currently */
}



.btn:hover {
  color: #f7a36a;
  background: rgba(0, 0, 0, .6);
}

.btn:active {
  color: #e36815;
  background: rgba(0, 0, 0, .4);
}

.btn:focus {
  outline: 1pt solid #f7a36a !important;
}
</style>