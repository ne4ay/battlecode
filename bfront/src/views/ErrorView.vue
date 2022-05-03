<template>
  <GlobalHeader :is-needed-to-display-profile-info="true"/>
  <div class="content">
    <MainBackground>
      <div id="window">
      <span class="title">
        Ошибка!
      </span>
        <span class="text">
          {{ errorMessage }}
        </span>
        <a href="/" class="link">
          На главную
        </a>
      </div>
    </MainBackground>
  </div>
</template>

<script>
import GlobalHeader from "@/components/header/GlobalHeader";

export default {
  name: "ErrorView",
  components: {
    GlobalHeader,
  },
  data() {
    return {
      errorMessage: ''
    }
  },
  created() {
    console.log(this.$route.path);
    this.errorMessage = this.$route.query.error;
  },
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');

* {
  margin: 0;
  padding: 0;
}

template {
  background: #202020;
}

.content {
  position: absolute;
  z-index: -1;
  height: calc(100% - 65pt);
  width: 100%;
  background-color: #202020;
}

#window {
  z-index: 2;
  margin: 18pt auto auto auto;
  background: #181818;
  border-radius: 12pt;
  min-height: 50pt;
  width: 350pt;
  font-family: 'Ubuntu', sans-serif;
  font-size: 16pt;
  box-shadow: 0 5pt 5pt black;
  color: #e5e5e5;
  padding: 5pt 10pt 10pt 15pt;
  display: flex;
  flex-direction: column;
}

.title {
  font-family: 'Russo One', sans-serif;
  font-size: 22pt;
  margin: 10pt 10pt;
}
.text {
  margin: 5pt 10pt 10pt 10pt;
}

.link {
  font-family: 'Russo One', sans-serif;
  margin: 5pt 10pt 5pt 10pt;
  font-size: 22pt;
  align-self: end;
  padding: 5pt 10pt;
  border-radius: 15pt;
  text-decoration: none;
}

.link:hover {
  color: #f7a36a;
  background: rgba(0, 0, 0, .6);
}

.link:active {
  color: #e36815;
  background: rgba(0, 0, 0, .4);
}

.link:visited {
  color: #e5e5e5;
}
</style>