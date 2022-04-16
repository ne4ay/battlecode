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
  </div>
  <div id="dialog-wrapper" @click.self="hideDialog">
    <FormDialog class="reg-form"
                v-model:is-showing="dialogVisible"
                v-model:title="typeOfTheDialog.title">
      <component :is="DialogContentAuthComponent"/>
    </FormDialog>
  </div>
</template>

<script>
import MainLogo from "@/components/MainLogo";
import UnAuthLinks from "@/components/header/UnAuthLinks";
import RegistrationForm from "@/components/forms/RegistrationForm";
import AuthorizationForm from "@/components/forms/AuthorizationForm";
import FormDialog from "@/components/dialogs/FormDialog";

export default {
  name: "GlobalHeader",
  components: {
    FormDialog,
    MainLogo,
    UnAuthLinks,
    AuthorizationForm,
    RegistrationForm
  },
  data() {
    return {
      dialogVisible: false,
      typeOfTheDialog: {
        name: String,
        title: String
      },
    }
  },
  methods: {
    showAuthDialog(dialogType) {
      this.dialogVisible = true;
      this.typeOfTheDialog = dialogType;
      document.getElementById('dialog-wrapper').style.display = 'flex';
    },
    hideDialog() {
      if (this.dialogVisible) {
        this.dialogVisible = false;
        setTimeout(() => {
          document.getElementById('dialog-wrapper').style.display = 'none';
        }, 380);
      }
    },
  },
  computed: {
    DialogContentAuthComponent() {
      const componentName = this.typeOfTheDialog.name + 'Form';
      console.log(componentName);
      return componentName;
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');

* {
  --height-of-header: 65pt;
}

.container {
  margin: 0;
  padding: 0;
  background: linear-gradient(#000000, #202020);
  box-shadow: 0 5pt 5pt black;
  height: var(--height-of-header);
  width: 100%;
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

#dialog-wrapper {
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  z-index: 1;
  position: fixed;
  display: none;
  transition: 0.7s;
}
</style>