<template>
  <form @submit.prevent>
    <span id="message-label">
        {{ messageLabel }}
    </span>
    <TextInput
        id="input-login"
        v-model="auth.login"
        v-model:input-css-class="loginCssClass"
        v-model:is-correct-validation-predicate="isNotEmpty"
        :inputType="'text'"
        placeholder-text="Логин"
    />
    <TextInput
        id="input-pass"
        v-model="auth.password"
        v-model:input-css-class="passCssClass"
        v-model:is-correct-validation-predicate="isNotEmpty"
        :inputType="'password'"
        placeholder-text="Пароль"
    />
    <SimpleSingleButton type="submit" class="auth-button" @click="tryToAuth">
      Войти
    </SimpleSingleButton>
  </form>
</template>

<script>
import TextInput from "@/components/forms/TextInput";
import SimpleSingleButton from "@/components/forms/SimpleSingleButton";
import FormValidationUtils from "@/components/forms/FormValidationUtils";
import Properties from "@/Properties";
import axios from "axios";

export default {
  name: "AuthorizationForm",
  components: {
    TextInput,
    SimpleSingleButton
  },
  props: {
    actionOnSuccess: {
      type: Function
    }
  },
  data() {
    return {
      auth: {
        login: '',
        password: ''
      },
      messageLabel: '',
      isNotEmpty: FormValidationUtils.fieldIsNotEmpty,
      loginCssClass: '',
      passCssClass: '',
    }
  },
  methods: {
    tryToAuth() {
      const wrongInputClass = 'wrong-input';
      let isSomeFieldIsNotFilled = false;
      if (FormValidationUtils.fieldIsEmpty(this.auth.login)) {
        this.loginCssClass = wrongInputClass;
        isSomeFieldIsNotFilled = true;
      }
      if (FormValidationUtils.fieldIsEmpty(this.auth.password)) {
        this.passCssClass = wrongInputClass;
        isSomeFieldIsNotFilled = true;
      }
      if (isSomeFieldIsNotFilled) {
        this.messageLabel = 'Заполните все необходимые поля!';
        return;
      }

      this.loginCssClass = '';
      this.passCssClass = '';
      const backAddress = Properties.BBACK_ADDRESS;
      axios.post(backAddress + '/login', this.auth, {
        withCredentials: true
      })
          .then(response => {
            const respModel = response.data;
            if (!respModel.exception && respModel.response.isSuccessful) {
              this.actionOnSuccess();
              return;
            }
            const exception = respModel.exception;
            if (exception === 'WRONG_CREDENTIALS') {
              this.messageLabel = 'Неправильный логин или пароль!';
              return;
            }
            this.messageLabel = 'Неизвестная ошибка';
          }).catch(error => {
        this.messageLabel = error;
      })
    }
  },
}
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
}

#message-label {
  align-self: center;
  font-size: 16pt;
  justify-self: center;
  text-align: center;
  min-height: 20pt;
  margin: 5pt 15pt 5pt 7pt;
}

.auth-button {
  align-self: end;
  margin: 5pt 10pt 5pt 15pt;
}
</style>