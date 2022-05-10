<template>
  <form @submit.prevent class="form-container">
    <div class="row-container">
      <TextInput id="input-title"
                 v-model="task.title"
                 v-model:input-css-class="cssClasses.title"
                 v-model:is-correct-validation-predicate="isNotEmpty"
                 :is-dark-background="true"
                 :input-type="'text'"
                 placeholder-text="Заголовок"
                 label-text="Заголовок"
                 class="form-member"/>
      <TextInput id="input-cost"
                 v-model="task.cost"
                 v-model:input-css-class="cssClasses.cost"
                 v-model:is-correct-validation-predicate="isNotNegativeAndShorterThanFour"
                 :is-dark-background="true"
                 :input-type="'number'"
                 label-text="Опыт"
                 class="form-member"/>
    </div>
    <textarea id="input-description"
              cols="90"
              rows="10"
              v-model="task.description"
              class="form-member">
   </textarea>
    <span class="error-label">
      {{ errorMessage }}
    </span>
    <div class="row-container">
      <span :class="cssClasses.langLabel">Языки: </span>
    </div>
    <div class="form-member languages-container">
      <SingleLanguageOption v-for="(lang, index) in availableLangs"
                            class="language-option"
                            :key="index"
                            v-model:is-active="lang.isActive"
                            :language-name="lang.name"
                            :activation-listener="addLangToArr"
                            :deactivation-listener="removeLangFromArr"/>
    </div>
    <div class="form-member">
      <TestCaseItem v-for="(item, index) in task.testCases"
                    :key="index"
                    :is-necessary-to-show-remove-button="index > 1"
                    class="test-case"
                    v-model:input-case="task.testCases[index].inputCase"
                    v-model:expected-output="task.testCases[index].expectedOutput"
                    :remove-button-click-listener="() => removeTestCase(index)"
      />
    </div>
    <button class="add-test-case-button" @click="addNewTestCase" v-if="task.testCases.length < 10">
      Добавить тест-кейс
    </button>
    <SimpleSingleButton type="submit" class="submit-button" @click="tryToSendNewTask">
      {{ operationActionName }}
    </SimpleSingleButton>
  </form>
</template>

<script>
import TextInput from "@/components/forms/TextInput";
import SingleLanguageOption from "@/components/forms/SingleLanguageOption";
import FormValidationUtils from "@/components/forms/FormValidationUtils";
import router from "@/router/router";
import axios from "axios";
import Properties from "@/Properties";
import authenticationMixin from "@/mixins/authenticationMixin";
import SimpleSingleButton from "@/components/forms/SimpleSingleButton";
import TestCaseItem from "@/components/forms/TestCaseItem";
import Operation from "@/components/enums/Operation";
import responseProcessingMixin from "@/mixins/commonUtilsMixin";
import Roles from "@/components/enums/Roles";

export default {
  name: "NewTaskForm",
  components: {
    TestCaseItem,
    TextInput,
    SingleLanguageOption,
    SimpleSingleButton
  },
  mixins: [
    authenticationMixin,
    responseProcessingMixin
  ],
  props: {
    operation: {
      type: String
    },
    operationActionName: {
      type: String
    },
    processUrl: {
      type: String
    },
    id: {
      type: Number
    }
  },
  data() {
    return {
      task: {
        title: '',
        description: '',
        cost: 0,
        languages: [],
        testCases: [
          {
            inputCase: '',
            expectedOutput: ''
          },
          {
            inputCase: '',
            expectedOutput: ''
          }
        ],
      },
      availableLangs: [],
      isNotEmpty: FormValidationUtils.fieldIsNotEmpty,
      cssClasses: {
        title: '',
        cost: '',
        description: '',
        langLabel: 'form-member simple-label',
      },
      errorMessage: '',
    }
  },
  methods: {
    isNotNegativeAndShorterThanFour(field) {
      return FormValidationUtils.numberFieldIsNonNegative(field) && FormValidationUtils.isShorterThan(field, 4);
    },
    addLangToArr(lang) {
      this.task.languages.push(lang);
    },
    removeLangFromArr(lang) {
      this.task.languages = this.task.languages.filter(val => val !== lang);
    },
    removeTestCase(index) {
      this.task.testCases.splice(index, 1);
    },
    addNewTestCase() {
      this.task.testCases.push({
        inputCase: '',
        expectedOutput: ''
      });
    },
    tryToSendNewTask() {
      const wrongInputClass = 'wrong-input';
      let isSomeFieldIsNotFilled = false;
      if (FormValidationUtils.fieldIsEmpty(this.task.title)) {
        this.cssClasses.title += wrongInputClass;
        isSomeFieldIsNotFilled = true;
      }
      if (!this.isNotNegativeAndShorterThanFour(this.task.cost)) {
        this.cssClasses.cost += wrongInputClass;
        isSomeFieldIsNotFilled = true;
      }
      if (FormValidationUtils.fieldIsEmpty(this.task.description)) {
        isSomeFieldIsNotFilled = true;
      }
      if (this.task.languages.length === 0) {
        this.cssClasses.langLabel += ' error-label ';
        isSomeFieldIsNotFilled = true;
        setTimeout(() => {
          this.cssClasses.langLabel.replace(' error-label ', '');
        }, 800);
      }
      if (isSomeFieldIsNotFilled) {
        this.errorMessage = 'Заполните все необходимые поля!';
        setTimeout(() => {
          this.errorMessage = '';
        }, 2000);
        return;
      }
      this.getRequestMethod()(this.processUrl, {
        task: this.task
      }, {
        withCredentials: true
      })
          .then(response => {
            const respModel = response.data;
            if (respModel.exception) {
              router.push('/error?error=' + respModel.exception);
              return;
            }
            this. successfullySent();
          }).catch(exception => {
        responseProcessingMixin.methods.handleException(exception);
      });
    },
    getRequestMethod() {
      switch (this.operation) {
        case Operation.UPDATE:
          return axios.put;
        case Operation.ADD:
          return axios.post;
        default:
          return axios.post;
      }
    },
    successfullySent() {
      if (this.operation === Operation.ADD) {
        this.clearForm();
        return;
      }
      if (this.operation === Operation.UPDATE) {
        router.push('/admin/tasks');
        return;
      }
    },
    clearForm() {
      this.task.title = '';
      this.task.cost = 0;
      this.task.description = '';
      this.task.languages = [];
      this.task.categories = [];
      this.task.complexity = '';
      this.task.testCases = this.getTwoEmptyTestCases();
    },
    getTwoEmptyTestCases() {
      return [
        {
          inputCase: '',
          expectedOutput: ''
        },
        {
          inputCase: '',
          expectedOutput: ''
        }
      ];
    },
    unique(arr) {
      let result = [];
      for (let str of arr) {
        if (!result.includes(str)) {
          result.push(str);
        }
      }
      return result;
    }
  },
  created() {
    if (!authenticationMixin.methods.getAuth()) {
      router.push('/auth');
    }
    if (!authenticationMixin.methods.getProfileInfo().roles.includes(Roles.GLOBAL_ADMINISTRATOR.id)) {
      router.push('/error?error=Недостаточно прав для просмотра данной страницы!');
    }
    const allLangs = [];
    axios.get(Properties.BBACK_ADDRESS + '/languages', {
      withCredentials: true,
    })
        .then(response => {
          response.data.response.langs.forEach(elem => {
            allLangs.push({
              name: elem.name,
              isActive: false
            });
          })
          if (this.operation === Operation.ADD) {
            this.availableLangs = allLangs;
          }
        })
        .catch(exception => {
          responseProcessingMixin.methods.handleException(exception);
        });
    if (this.operation === Operation.UPDATE) {
      axios.get(Properties.BBACK_ADDRESS + '/tasks/get/' + this.id, {
        withCredentials: true
      })
          .then(response => {
            const responseModel = response.data;
            if (responseModel.exception) {
              router.push('/error?error=' + responseModel.exception);
              return;
            }
            const receivedTask = responseModel.response.task;
            this.task.title = receivedTask.title;
            this.task.cost = receivedTask.cost;
            this.task.description = receivedTask.description;
            this.task.languages = this.unique(receivedTask.languages);
            this.task.testCases = receivedTask.testCases;
            allLangs.forEach(elem => {
              if ( this.task.languages.includes(elem.name)) {
                elem.isActive = true;
              }
            });
            this.availableLangs = allLangs;
          }).catch(exception => {
        responseProcessingMixin.methods.handleException(exception);
      })
    }

  },
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');

.form-container {
  display: flex;
  flex-direction: column;
  align-content: center;
  justify-content: center;
  text-align: center;
  padding: 10pt;
}

.form-member {
  margin: 5pt 10pt;
}

.row-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.error-label {
  color: #e84a4a;
  font-family: 'Russo One', sans-serif;
  font-size: 17pt;
}

#input-cost {

}

#input-description {
  resize: none;
  width: 500pt;
  align-self: center;
  justify-self: center;
  background-color: #181818;
  border: none;
  border-radius: 15pt;
  padding: 10pt;
  color: #e5e5e5;
  font-size: 14pt;
}

#input-description:focus {
  outline: none !important;
  border: 1pt solid #f7a36a;
}

.simple-label {
  font-family: 'Russo One', sans-serif;
  font-size: 17pt;
  color: #e5e5e5;
}

.languages-container {
  display: flex;
  justify-content: center;
}

.language-option {
  margin: 0 5pt;
}

.test-case {
  margin: 10pt 0;
}

.add-test-case-button {
  font-family: 'Russo One', sans-serif;
  border: none;
  font-size: 17pt;
  padding: 10pt;
  border-radius: 15pt;
  color: #272727;
  background-color: #ead1c6;
  align-self: start;
  -webkit-user-select: none; /* Chrome/Safari/Opera */
  -moz-user-select: none; /* Firefox */
  -ms-user-select: none; /* Internet Explorer/Edge */
  user-select: none; /* Non-prefixed version, currently */;
}

.add-test-case-button:hover {
  background-color: #f7a36a;
}

.add-test-case-button:active {
  background-color: #e36815;
}

.submit-button {
  width: max-content;
  padding: 5pt 15pt;
  justify-self: center;
  align-self: end;
}
</style>