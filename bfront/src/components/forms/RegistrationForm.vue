<template>
  <form @submit.prevent>
    <span id="message-label">
      {{messageLabel}}
    </span>
    <TextInput
        id="input-email"
        v-model="reg.eMail"
        type="text"
        placeholder-text="Электронная почта"
    />
    <TextInput
        id="input-login"
        v-model="reg.login"
        type="text"
        placeholder-text="Логин"
    />
    <TextInput
        id="input-pass"
        v-model="reg.pass"
        type="text"
        placeholder-text="Пароль"
    />
    <TextInput
        id="input-check-pass"
        v-model="reg.checkPass"
        type="text"
        placeholder-text="Повторите пароль"
    />
    <SimpleSingleButton type="submit" class="reg-button" @click="tryToReg">
      Зарегистрироваться
    </SimpleSingleButton>
  </form>
</template>

<script>
import TextInput from "@/components/forms/TextInput";
import SimpleSingleButton from "@/components/forms/SimpleSingleButton";

export default {
  name: "RegistrationForm",
  components: {
    TextInput,
    SimpleSingleButton
  },
  data() {
    return {
      reg: {
        eMail: '',
        login: '',
        pass: '',
        checkPass: '',
      },
      messageLabel: ''
    }
  },
  methods: {
    tryToReg() {
      const isSomeFieldIsNotFilled = this.fieldIsEmpty(this.eMail)
          || this.fieldIsEmpty(this.login)
          || this.fieldIsEmpty(this.pass)
          || this.fieldIsEmpty(this.checkPass)
      if (isSomeFieldIsNotFilled) {
        this.messageLabel = 'Заполните все необходимые поля!'
      }
    },
    fieldIsEmpty(field, fieldId) {
      if (field !== null && field !== '') {
        return false;
      }
      const fieldElem = document.getElementById(fieldId);
      if (fieldElem == null) {
        console.warn('Unable to find element with such id: ' + fieldId);
        return false;
      }

      return true;
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
  height: 20pt;
  margin: 5pt 15pt 5pt 7pt;
}

.reg-button {
  align-self: end;
  margin: 5pt 10pt 5pt 10pt;
}
</style>