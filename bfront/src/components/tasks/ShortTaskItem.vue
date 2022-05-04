<template>
  <a :href="/task/ + taskId">
    <div class="short-task-container">
      <div>
        {{ title }}
      </div>
      <div>
        Опыт: {{ cost }}
      </div>
      <div>

      </div>
    </div>
  </a>
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
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');
a {
  text-decoration: none;
  border-radius: 15pt;
}

.short-task-container {
  width: 350pt;
  background: #272727;
  font-family: 'Ubuntu', sans-serif;
  font-size: 17pt;
  box-shadow: 0 5pt 5pt black;
  color: #e9e9e9;
  border-radius: 15pt;
  display: flex;
  flex-direction: column;
  padding: 15pt 12pt 15pt 12pt;
}

.short-task-container:hover {
  color: #f7a36a;
  background: #303030;
}

.short-task-container:active {
  color: #e36815;
  background-color: #080808;
}
</style>