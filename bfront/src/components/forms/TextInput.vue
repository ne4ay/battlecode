<template>
  <div class="input-container">
<!--    <span class="input-member">-->
<!--      {{placeholderText}}-->
<!--    </span>-->
    <input :value="modelValue"
           @input="updateInput"
           :class="'input-member ' + this.inputCssClass"
           :placeholder="placeholderText"
           :type="inputType">
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
    },
    inputCssClass: {
      type: String
    },
    isCorrectValidationPredicate: {
      type: Function
    },
    inputType: {
      type: String
    }
  },
  methods: {
    updateInput(event) {
      const newValue = event.target.value;
      this.$emit('update:modelValue', newValue);
      if (this.isCorrectValidationPredicate(newValue)) {
        this.$emit('update:inputCssClass', '');
      }
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');
.input-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.input-member{
  margin: 5pt;
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

.wrong-input {
  outline: 1pt solid red;
}

</style>