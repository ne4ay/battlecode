<template>
<a href="/admin" class="back-link">
  ← Вернуться к админ-панели
</a>
</template>

<script>
export default {
  name: "AdminBackLink"
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');
.back-link {
  margin: 8pt;
  background: #282828;
  color: #e5e5e5;
  font-family: 'Ubuntu', sans-serif;
  padding: 10pt;
  font-size: 17pt;
  box-shadow: 0 3pt 3pt black;
  border-radius: 15pt;
  text-decoration: none;
  display: flex;
  width: 250pt;
  align-self: center;
  transform: translateX(-75pt);
}
.back-link:hover {
  color: #f7a36a;
  background: #303030;
}

.back-link:active {
  color: #e36815;
  background-color: #080808;
}
</style>