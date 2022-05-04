<template>
  <div class="case-container">
    <div class="section-container">
      <div class="case-info-container">
      <span class="test-case-label">
        Ввод:
      </span>
        <textarea :value="inputCase"
                  @input="updateUpperInput"
                  :class="cssClasses.baseCssClass + cssClasses.inputCssClass"
                  placeholder="Ввод"
                  class="test-case-input"
                  id="upper-input"/>
      </div>
      <div class="case-info-container">
      <span class="test-case-label">
        Вывод:
      </span>
        <textarea :value="expectedOutput"
                  @input="updateLowerInput"
                  :class="cssClasses.baseCssClass + cssClasses.inputCssClass"
                  placeholder="Вывод"
                  class="test-case-input"
                  id="lower-input"/>

      </div>
    </div>
    <button id="minus-button" @click="removeButtonClickListener" v-if="isNecessaryToShowRemoveButton">
        <span id="minus-text">
          -
        </span>
    </button>
  </div>
</template>

<script>
import FormValidationUtils from "@/components/forms/FormValidationUtils";

export default {
  name: "TestCaseItem",
  components: {},
  props: {
    inputCase: {
      type: String,
    },
    expectedOutput: {
      type: String
    },
    isNecessaryToShowRemoveButton: {
      type: Boolean,
    },
    removeButtonClickListener: {
      type: Function
    }
  },
  data() {
    return {
      cssClasses: {
        baseCssClass: 'test-case-input',
        inputCaseCssClass: '',
        expectedOutputCssClass: ''
      },
      isNotEmpty: FormValidationUtils.fieldIsNotEmpty,
    }
  },
  methods: {
    updateUpperInput(event) {
      const newValue = event.target.value;
      this.$emit('update:inputCase', newValue);
      if (this.isNotEmpty(newValue)) {
        this.cssClasses.inputCaseCssClass = this.cssClasses.baseCssClass;
      }
    },
    updateLowerInput(event) {
      const newValue = event.target.value;
      this.$emit('update:expectedOutput', newValue);
      if (this.isNotEmpty(newValue)) {
        this.cssClasses.expectedOutputCssClass = this.cssClasses.baseCssClass;
      }
    },
  },
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');

@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');

.case-container {
  width: 475pt;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.section-container {
  width: 400pt;
  display: flex;
  flex-direction: column;
}

.case-info-container {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.test-case-label {
  font-family: 'Russo One', sans-serif;
  font-size: 16pt;
  color: #f3ece4;
  align-self: center;
}

.test-case-input {
  background: #181818;
  font-family: 'Ubuntu', sans-serif;
  font-size: 15pt;
  color: #f3ece4;
  padding: 5pt 20pt;
  border: none;
  resize: none;
  -webkit-appearance: textfield;
  -moz-appearance: textfield;
  appearance: none;

}

.test-case-input:focus {
  outline: 1pt solid #f7a36a !important;

}

#upper-input {
  border-radius: 30pt 30pt 0 0;
  border-bottom: 1pt solid #f7a36a;
}

#lower-input {
  border-radius: 0 0 30pt 30pt;
  border-top: 1pt solid #f7a36a;
}

#minus-button {
  border-radius: 100%;
  height: 50pt;
  width: 50pt;
  border: none;
  font-family: 'Russo One', sans-serif;
  font-size: 45pt;
  vertical-align: center;
  horiz-align: center;
  background: #595959;
  color: #111;
  padding: 10pt 10pt 10pt 10pt;
  text-align: center;
  justify-self: center;
  align-self: center;
  -webkit-user-select: none; /* Chrome/Safari/Opera */
  -moz-user-select: none; /* Firefox */
  -ms-user-select: none; /* Internet Explorer/Edge */
  user-select: none; /* Non-prefixed version, currently */;
}

#minus-button:hover {
  background-color: #ead1c6;
}

#minus-button:active {
  background-color: #f7a36a;
}

#minus-text {
  display: inline-block;
  transform: translate(0, -10pt);
}
</style>