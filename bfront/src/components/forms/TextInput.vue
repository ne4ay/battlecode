<template>
  <div class="input-container">
<!--    <span class="input-member">-->
<!--      {{placeholderText}}-->
<!--    </span>-->
    <input :value="modelValue" @input="updateInput" class="input-member" type="text" :placeholder="placeholderText">
  </div>
</template>

<script>
export default {
  name: "TextInput",
  props: {
    modelValue: {
      type: String
    },
    placeholderText: {
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
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');
.input-container {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.input-member{
  margin: 5pt;
  /*padding: 5pt 10pt;*/
  /*margin-top: 5pt;*/
}
input {
  font-family: 'Ubuntu', sans-serif;
  font-size: 15pt;
  background: #222222;
  padding: 5pt 10pt;
  color: #f3ece4;
  border: none;
  border-radius: 50pt;
}
input:focus {
  outline: none !important;
  border: 1pt solid #f7a36a;
}

</style>