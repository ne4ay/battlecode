<template>
  <div class="container">
    <div id="logo-wrapper">
      <MainLogo/>
    </div>
    <div>

    </div>
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
  margin-left: 30pt;
}


</style>