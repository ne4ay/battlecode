<template>
  <div class="container">
    <div id="logo-wrapper">
      <MainLogo/>
    </div>
    <a class="header-link">
      Главная
    </a>
    <a class="header-link">
      Задачи
    </a>
    <a class="header-link">
      Рейтинг
    </a>
  </div>
</template>

<script>
import MainLogo from "@/components/MainLogo";

export default {
  name: "GlobalHeader",
  components: {
    MainLogo
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');
.container {
  margin: 0;
  padding: 0;
  background: linear-gradient(#000000, #202020);
  box-shadow: 0 5pt 5pt black;
  height: 65pt;
  display: flex;
  align-items: center;
}
#logo-wrapper {
  margin-left: 20pt;
}
.header-link {
  margin: 20pt;
  font-family: 'Russo One', sans-serif;
  font-stretch: condensed;
  font-size: 22pt;
  color: #e5e5e5;
}


</style>