<template>
  <form @submit.prevent>
    <h4>Регистрация</h4>
    <TextInput
        v-model="auth.title"
        type="text"
        placeholder="Название"
    />
    <TextInput
        v-model="auth.body"
        type="text"
        placeholder="Описание"
    />

  </form>
</template>

<script>
import TextInput from "@/components/forms/TextInput";

export default {
  name: "RegistrationForm",
  components: {
    TextInput
  },
  data() {
    return {
      auth: {
        login: '',
        eMail: '',
        name: '',
        age: '',
        pass: '',
      }
    }
  },
  methods: {
  },
}
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
}
</style>