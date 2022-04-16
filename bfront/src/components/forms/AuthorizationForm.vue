<template>
  <form @submit.prevent>
    <span id="message-label">
        {{messageLabel}}
    </span>
    <TextInput
        v-model="auth.login"
        type="text"
        placeholder-text="Логин"
    />
    <TextInput
        v-model="auth.pass"
        type="text"
        placeholder-text="Пароль"
    />
    <SimpleSingleButton type="submit" class="auth-button">
      Войти
    </SimpleSingleButton>
  </form>
</template>

<script>
import TextInput from "@/components/forms/TextInput";
import SimpleSingleButton from "@/components/forms/SimpleSingleButton";

export default {
  name: "AuthorizationForm",
  components: {
    TextInput,
    SimpleSingleButton
  },
  data() {
    return {
      auth: {
        login: '',
        pass: ''
      },
      messageLabel: ''
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
  margin: 5pt 15pt 5pt 15pt;
}

.auth-button {
  align-self: end;
  margin: 5pt 10pt 5pt 15pt;
}
</style>