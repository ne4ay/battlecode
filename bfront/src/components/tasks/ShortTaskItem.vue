<template>
  <!--  <a :href="'/task/' + taskId">-->
  <div class="short-task-container">
      <span id="task-title">
        {{ title }}
      </span>
    <div class="section-container">
      <div>
      <span class="description-label">
        <span class="definition-label">
          Опыт:
        </span>
        {{ cost }}
       </span>
      </div>
      <a  :href="'/task/' + taskId + '?language=' + activeLang" id="link-button">
        {{ isDone ? '✔ Решено' : 'Решить' }}
      </a>
    </div>
  </div>
  <!--  </a>-->
</template>

<script>
export default {
  name: "ShortTaskItem",
  props: {
    taskId: {
      type: Number,
      default: 0
    },
    isDone: {
      type: Boolean,
      default: false
    },
    cost: {
      type: Number,
      default: 0
    },
    title: {
      type: String,
      default: ''
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
      default: () => [],
    },
    activeLang: {
      type: String
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');
a {
  text-decoration: none;
  border-radius: 15pt;
}

.short-task-container {
  width: 350pt;
  background: #272727;
  font-family: 'Ubuntu', sans-serif;
  font-size: 14pt;
  box-shadow: 0 5pt 5pt black;
  color: #e9e9e9;
  border-radius: 15pt;
  display: flex;
  flex-direction: column;
  padding: 15pt 20pt 15pt 20pt;
}



.section-container {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.definition-label {
  display: inline-block;
  width: 50pt;
}

#task-title {
  font-family: 'Russo One', sans-serif;
  font-size: 18pt;

}

#link-button {
  font-family: 'Russo One', sans-serif;
  font-size: 18pt;
  background-color: #181818;
  padding: 10pt;
  color: #e9e9e9;
  text-decoration: none;
  -webkit-user-select: none;   /* Chrome/Safari/Opera */
  -moz-user-select: none;      /* Firefox */
  -ms-user-select: none;       /* Internet Explorer/Edge */
  user-select: none;           /* Non-prefixed version, currently */
}

#link-button:visited {
  color: #e9e9e9;
}

#link-button:hover {
  color: #f7a36a;
  background-color: #383838;
}

#link-button:active {
  color: #e36815;
  background-color: #111111;
}
</style>