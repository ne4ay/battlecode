<template>
  <form @submit.prevent>
    <span id="message-label">
      {{messageLabel}}
    </span>
    <TextInput
        id="input-email"
        v-model="reg.eMail"
        v-model:input-css-class="emailCssClass"
        v-model:is-correct-validation-predicate="isNotEmpty"
        type="text"
        placeholder-text="Электронная почта"
    />
    <TextInput
        id="input-login"
        v-model="reg.login"
        v-model:input-css-class="loginCssClass"
        v-model:is-correct-validation-predicate="isNotEmpty"
        type="text"
        placeholder-text="Логин"
    />
    <TextInput
        id="input-pass"
        v-model="reg.pass"
        v-model:input-css-class="passCssClass"
        v-model:is-correct-validation-predicate="isNotEmpty"
        type="text"
        placeholder-text="Пароль"
    />
    <TextInput
        id="input-check-pass"
        v-model="reg.checkPass"
        v-model:input-css-class="checkPassCssClass"
        v-model:is-correct-validation-predicate="isNotEmpty"
        type="text"
        placeholder-text="Повторите пароль"

    />
    <SimpleSingleButton type="submit" class="reg-button" @click="tryToReg">
      Зарегистрироваться
    </SimpleSingleButton>
  </form>
</template>

<script>
import axios from 'axios';
import TextInput from "@/components/forms/TextInput";
import SimpleSingleButton from "@/components/forms/SimpleSingleButton";
import FormValidationUtils from "@/components/forms/FormValidationUtils";
import Properties from "@/Properties";

export default {
  name: "RegistrationForm",
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
      reg: {
        eMail: '',
        login: '',
        pass: '',
        checkPass: '',
      },
      messageLabel: '',
      isNotEmpty: FormValidationUtils.fieldIsNotEmpty,
      emailCssClass: '',
      loginCssClass: '',
      passCssClass: '',
      checkPassCssClass: '',
    }
  },
  methods: {
    tryToReg() {
      const wrongInputClass = 'wrong-input';
      let isSomeFieldIsNotFilled = false;
      if (FormValidationUtils.fieldIsEmpty(this.reg.eMail)) {
        this.emailCssClass = wrongInputClass;
        isSomeFieldIsNotFilled = true;
      }
      if (FormValidationUtils.fieldIsEmpty(this.reg.login)) {
        this.loginCssClass = wrongInputClass;
        isSomeFieldIsNotFilled = true;
      }
      if (FormValidationUtils.fieldIsEmpty(this.reg.pass)) {
        this.passCssClass = wrongInputClass;
        isSomeFieldIsNotFilled = true;
      }
      if (FormValidationUtils.fieldIsEmpty(this.reg.checkPass)) {
        this.checkPassCssClass = wrongInputClass;
        isSomeFieldIsNotFilled = true;
      }
      if (isSomeFieldIsNotFilled) {
        this.messageLabel = 'Заполните все необходимые поля!';
        return;
      }
      const backAddress = Properties.BBACK_ADDRESS;
      axios.post(backAddress + '/register', this.reg)
          .then(() => {
            this.actionOnSuccess();
          }).catch(error => {
            this.messageLabel = error;
          })
    },
  },
}
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
}
#message-label {
  height: 20pt;
  margin: 5pt 15pt 5pt 7pt;
}

.reg-button {
  align-self: end;
  margin: 5pt 10pt 5pt 10pt;
}
</style>