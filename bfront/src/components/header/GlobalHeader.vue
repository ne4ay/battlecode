<template>
  <div class="container">
    <div class="content-wrapper">
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

    <UnAuthLinks class="content-wrapper" id="auth" :auth-listener="showAuthDialog"/>
    <FormDialog class="reg-form" v-model:show="dialogVisible">
     <RegistrationForm />
    </FormDialog>
  </div>
</template>

<script>
import MainLogo from "@/components/MainLogo";
import UnAuthLinks from "@/components/header/UnAuthLinks";
import RegistrationForm from "@/components/forms/RegistrationForm";
import FormDialog from "@/components/dialogs/FormDialog";

export default {
  name: "GlobalHeader",
  components: {
    FormDialog,
    MainLogo,
    UnAuthLinks,
    RegistrationForm
  },
  data() {
    return {
      dialogVisible: false,
    }
  },
  methods: {
    showAuthDialog() {
      this.dialogVisible = true;
      console.log("kz");
    }
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
  justify-content: space-between;
}

.content-wrapper {
  display: flex;
  margin: 0;
  padding: 0;
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

#auth {
  margin-right: 14pt;
}
.reg-form {
  position: absolute;
  width: 100%;
  height: 100%;
  left: 50%;
  top: 50%;
  z-index: 5  ;
  transform: translate(-50%, -50%);
}
</style>