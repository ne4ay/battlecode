<template>
  <div class="input-container">
    <span class="input-member" id="input-label" v-if="labelText">
      {{ labelText }}
    </span>
    <input :value="modelValue"
           @input="updateInput"
           :class="this.baseCssClass + this.inputCssClass"
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
    labelText: {
      type: String,
      default: ''
    },
    inputCssClass: {
      type: String
    },
    isCorrectValidationPredicate: {
      type: Function
    },
    inputType: {
      type: String
    },
    isDarkBackground: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      baseCssClass: ''
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
  },
  created() {
    this.baseCssClass = 'input-member ';
    if (this.isDarkBackground) {
      this.baseCssClass += 'dark-background ';
    }
    if (this.inputType === 'number') {
      this.baseCssClass += 'short ';
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');

.input-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-content: center;
}

.input-member {
  margin: 5pt;
  align-self: center;
}

.dark-background {
  background: #181818;
}

#input-label {
  font-family: 'Russo One', sans-serif;
  font-size: 17pt;
  color: #e5e5e5;
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

.short {
  width: 29pt;
}

input:focus {
  outline: 1pt solid #f7a36a !important;
}

input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  display: none;
}

input[type=number] {
  -moz-appearance:textfield; /* Firefox */
}

.wrong-input {
  outline: 1pt solid red;
}

</style>