<template>
  <div class="auth-wrapper">
    <a class="auth">
      Войти
    </a>
    <span class="delimiter">
      |
    </span>
    <a class="auth"
      @click="showDialog">
      Регистрация
    </a>
  </div>
</template>

<script>
export default {
  name: "UnAuthLinks",
  props: {
    authListener: {
      type: Function,
      required: true
    }
  },
  components: {
  },
  methods: {
    showDialog() {
      this.authListener();
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');
.auth-wrapper {
  width: available;
}

.auth {
  font-family: 'Russo One', sans-serif;
  font-stretch: condensed;
  font-size: 22pt;
  color: #e5e5e5;
  margin: 0 5pt;
}
.delimiter {
  font-family: 'Ubuntu', sans-serif;
  font-stretch: condensed;
  font-size: 26pt;
  color: #e5e5e5;
  margin: 0 5pt;
}

</style>