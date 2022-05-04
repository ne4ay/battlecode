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
                 v-model:input-css-class="cssClasses.description"
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
    <div class="row-container">
      <span class="form-member simple-label">Языки: </span>
      <span class="form-member simple-label"></span>
    </div>
    <div class="form-member languages-container">
      <SingleLanguageOption v-for="(lang, index) in availableLangs"
                            :key="index"
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
    <div class="add-test-case-button" @click="addNewTestCase" v-if="task.testCases.length < 10">
      Добавить тест-кейс
    </div>
    <SimpleSingleButton type="submit" class="submit-button" @click="tryToSendNewTask">
      Добавить
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

export default {
  name: "AddNewTaskForm",
  components: {
    TestCaseItem,
    TextInput,
    SingleLanguageOption,
    SimpleSingleButton
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
        title: ''
      }
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
      this.task.languages = this.task.languages.filter(val => val === lang);
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
      axios.post(Properties.BBACK_ADDRESS + '/admin/task/add', this.task, {
        withCredentials: true
      })
          .then(response => {
            const respModel = response.data;
            if (respModel.exception) {
              router.push('/error?error=' + respModel.exception);
              return;
            }
            this.clearForm();
          }).catch(exception => {
        if (exception.response.status === 401 || exception.response.status === 403) {
          authenticationMixin.methods.resetProfileInfo();
          router.push('/auth');
          return;
        }
      });
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
    }
  },
  created() {
    if (!authenticationMixin.methods.getAuth()) {
      router.push('/auth');
    }
    axios.get(Properties.BBACK_ADDRESS + '/languages', {
      withCredentials: true,
    })
        .then(response => {
          this.availableLangs = response.data.response.langs;
        })
        .catch(exception => {
          if (exception.response.status === 401 || exception.response.status === 403) {
            authenticationMixin.methods.resetProfileInfo();
            router.push('/auth');
            return;
          }
          router.push('/error?error=' + exception);
        })
  }
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

.test-case {
  margin: 10pt 0;
}

.add-test-case-button {
  font-family: 'Russo One', sans-serif;
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