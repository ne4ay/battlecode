<template>
  <div class="auth-wrapper">
    <a class="auth"
      @click="showDialog(DialogType.AUTHORIZATION)">
      Войти
    </a>
    <span class="delimiter">
      |
    </span>
    <a class="auth"
      @click="showDialog(DialogType.REGISTRATION)">
      Регистрация
    </a>
  </div>
</template>

<script>
import AuthDialogType from "@/components/header/AuthFormType";

export default {
  name: "UnAuthLinks",
  props: {
    authListener: {
      type: Function,
      required: true,
    }
  },
  data() {
    return {
      DialogType: AuthDialogType
    }
  },
  components: {
  },
  methods: {
    showDialog(dialogType) {
      this.authListener(dialogType);
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
  font-size: 21pt;
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

.auth:hover {
  color: #f7a36a;
}

.auth:active {
  color: #e36815;
}
</style>