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
    <span class="description">
      {{ description }}
    </span>
      </div>
      <TaskCheckingForm id="form-editor"
                        v-model:active-lang="activeLang"
                        v-model:task-id="id"/>
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

export default {
  name: "SingleTaskView",
  components: {
    TaskCheckingForm,
    GlobalHeader
  },
  mixins: [
    commonUtilsMixin
  ],
  data() {
    return {
      id: 0,
      title: '',
      description: '',
      languages: [],
      activeLang: ''
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
</style>