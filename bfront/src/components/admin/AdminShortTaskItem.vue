<template>
  <div class="task-container">
    <div id="task-description" class="section-container">
      <a :href="'/task/' + taskId" class="clickable title">
        {{ title }}
      </a>
      <span class="description-label">
        <span class="definition-label">
          Опыт:
        </span>
        {{ cost }}
      </span>
      <span class="description-label">
        <span class="definition-label">
          Статус:
        </span>
        {{ status }}
      </span>
      <span class="description-label">
         <span class="definition-label">
           Языки:
         </span>
        {{ languages.toString() }}
      </span>
    </div>
    <div class="section-container">
      <a :href="'/admin/task/update/' + taskId" class="clickable task-button">
        Изменить
      </a>
    </div>
    <div class="section-container">
      <button class="clickable task-button" @click="deleteTask">
        Удалить
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Properties from "@/Properties";
import router from "@/router/router";

export default {
  name: "AdminShortTaskItem",
  props: {
    taskId: {
      type: Number,
      default: 0
    },
    cost: {
      type: Number,
      default: 0
    },
    title: {
      type: String,
      default: ''
    },
    status: {
      type: String
    },
    complexity: {
      type: String,
      default: ''
    },
    categories: {
      type: Array,
      default: () => []
    },
    languages: {
      type: Array,
      default: () => []
    },
    removeListener: {
      type: Function
    }
  },
  methods: {
    deleteTask() {
      axios.delete(Properties.BBACK_ADDRESS + '/admin/tasks/delete/' + this.taskId, {
        withCredentials: true
      })
      .then(() => {
        this.removeListener();
      }).catch(exception => {
        router.push('/error?error=' + exception);
      })
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');

.task-container {
  width: 400pt;
  background: #272727;
  padding: 5pt 10pt;
  font-family: 'Ubuntu', sans-serif;
  box-shadow: 0 4pt 4pt black;
  color: #e9e9e9;
  border-radius: 15pt;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  text-decoration: none;
}

.section-container {
  margin: 5pt;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
}

#task-description {
  flex-grow: 2;
  display: flex;
  flex-direction: column;
}

.description-label {

}

.clickable:hover {
  color: #f7a36a;
}

.clickable:active {
  color: #e36815;
}

.clickable:focus {
  border: none;
  outline: none;
  color: #f7a36a;
  text-shadow: 0 5pt 5pt black;;
}

.title {
  font-size: 17pt;
  font-family: 'Russo One', sans-serif;
  text-decoration: none;
  color: #4581fc;
}

a {
  text-decoration: none;
  color: #4581fc;
}

a:visited {
  color: inherit;
}

.task-button {
  font-size: 17pt;
  font-family: 'Ubuntu', sans-serif;
  color: #e5e5e5;
  border: none;
  background: none;
  text-align: center;
  vertical-align: center;
}

.definition-label {
  display: inline-block;
  width: 50pt;
}
</style>