<template>
  <form @submit.prevent>
    <span id="message-label">
      {{messageLabel}}
    </span>
    <TextInput
        id="top-input"
        v-model="reg.eMail"
        type="text"
        placeholder-text="Электронная почта"
    />
    <TextInput
        v-model="reg.login"
        type="text"
        placeholder-text="Логин"
    />
    <TextInput
        v-model="reg.pass"
        type="text"
        placeholder-text="Пароль"
    />
    <TextInput
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