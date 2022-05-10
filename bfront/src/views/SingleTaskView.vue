<template>
  <GlobalHeader :is-needed-to-display-profile-info="true"/>
  <div class="content">
    <MainBackground/>
    <div id="content-wrapper">
      <div class="title-container">
        <span class="title">
          {{ title }}
        </span>
      </div>
      <div class="description-container">
        <pre>
          <span class="description">
            {{ description }}
          </span>
        </pre>
      </div>
      <TaskCheckingForm id="form-editor"
                        v-model:active-lang="activeLang"
                        v-model:task-id="id"
                        :response-handler="handleResponse"/>
      <div v-if="isResultReceived" class="checking-result-container">
        <span class="checking-result-title">
          {{ resultTitle }}
        </span>
        <div v-if="!isSolutionCorrect">
          <div class="result-block">
            Ввод: {{ testCase.inputCase }}
          </div>
          <div class="result-block">
            Ожидаемый вывод: {{ testCase.expectedOutput }}
          </div>
          <div>
            Реальный вывод:
            <pre class="error">{{ testCase.actualOutput }}
            </pre>
          </div>
        </div>
        <div v-else class="back-link-container">
          <span class="checking-result-title">
            Получено опыта: {{ receivedExperience }}
          </span>
          <div class="back-link">
            <a :href="'/tasks/' + activeLang">
              ← К задачам
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import GlobalHeader from "@/components/header/GlobalHeader";
import axios from "axios";
import commonUtilsMixin from "@/mixins/commonUtilsMixin";
import Properties from "@/Properties";
import router from "@/router/router";
import TaskCheckingForm from "@/components/forms/TaskCheckingForm";
import authenticationMixin from "@/mixins/authenticationMixin";

export default {
  name: "SingleTaskView",
  components: {
    TaskCheckingForm,
    GlobalHeader
  },
  mixins: [
    authenticationMixin,
    commonUtilsMixin
  ],
  data() {
    return {
      id: 0,
      title: '',
      description: '',
      languages: [],
      activeLang: '',
      isResultReceived: false,
      resultTitle: '',
      isSolutionCorrect: false,
      testCase: {
        inputCase: '',
        expectedOutput: '',
        actualOutput: '',
      },
      receivedExperience: 0,
      solutionException: '',
    }
  },
  methods: {
    handleResponse(response) {
      const responseModel = response.data;
      const exception = responseModel.exception;
      this.isResultReceived = true;
      if (exception) {
        this.resultTitle = exception;
        return;
      }
      const result = responseModel.response;
      console.log(result);
      this.isSolutionCorrect = result.isSuccessful;
      this.resultTitle = result.isSuccessful ? 'Задание выполнено успешно!!' : 'Задание провалено: ';
      if (result.isSuccessful) {
        this.receivedExperience = result.countOfExperience;
        authenticationMixin.methods.getBasicProfileInfo().then(response => {
          if (response.data.exception) {
            this.$store.state.isAuth = false;
            return;
          }
          authenticationMixin.methods.updateProfileInfo(response);
        })
            .catch(exception => {
              commonUtilsMixin.methods.handleException(exception);
            })
      } else {
        this.testCase.inputCase = result.testCases[0].inputCase;
        this.testCase.expectedOutput = result.testCases[0].expectedOutput;
        this.testCase.actualOutput = result.testCases[0].actualOutput;
        this.solutionException = result.exception;
      }
    }
  },
  created() {
    this.id = commonUtilsMixin.methods.determineId(this.$route.path);
    this.activeLang = this.$route.query.language;
    axios.get(Properties.BBACK_ADDRESS + '/tasks/get/' + this.id, {
      withCredentials: true
    })
        .then(response => {
          const responseModel = response.data;
          if (responseModel.exception) {
            router.push('/error?error=' + responseModel.exception);
            return;
          }
          const task = responseModel.response.task;
          this.title = task.title;
          this.description = task.description;
          this.languages = task.languages;
        }).catch(exception => {
      commonUtilsMixin.methods.handleException(exception);
    })
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');

* {
  margin: 0;
  padding: 0;
  overflow-y: auto;
}

template {
  background: #202020;
}

a:visited {
  color: inherit;
  text-decoration: none;
}

.content {
  position: fixed;
  z-index: -1;
  height: calc(100% - 65pt);
  width: 100%;
  background-color: #202020;
}

#content-wrapper {
  margin-top: 12pt;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.title-container {
  width: 600pt;
  margin: 25pt 10pt 15pt 10pt;
  align-self: center;

}

.title {
  color: #e9e9e9;
  font-family: 'Russo One', sans-serif;
  font-size: 24pt;
}

.description-container {
  align-self: center;
  width: 600pt;
}

.description {
  color: #e9e9e9;
  font-family: 'Ubuntu', sans-serif;
  font-size: 15pt;
}

#form-editor {
  margin-top: 15pt;
  align-self: center;
}

.checking-result-container {
  width: 600pt;
  margin: 25pt 10pt 15pt 10pt;
  align-self: center;
  color: #e9e9e9;
  font-family: 'Ubuntu', sans-serif;
  font-size: 15pt;
  border-radius: 15pt;
  background-color: #181818;
  padding: 13pt;
}

.checking-result-title {

}

.error {
  color: #e84a4a;
}

.back-link-container {
  display: flex;
  flex-direction: column;
}

.back-link {
  align-self: end;
  text-decoration: none;
}

.back-link a {
  text-decoration: none;
  font-size: 19pt;
}
</style>