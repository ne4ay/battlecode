<template>
  <input :value="modelValue" @input="updateInput" class="input" type="text">
</template>

<script>
export default {
  name: "TextInput",
  props: {
    modelValue: {
      type: String
    }
  },
  methods: {
    updateInput(event) {
      this.$emit('update:modelValue', event.target.value)
    }
  }
}
</script>

<style scoped>
.input {
  border: 1pt solid peachpuff;
  padding: 5pt 10pt;
  margin-top: 5pt;
}
</style>