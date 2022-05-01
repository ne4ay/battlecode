<template>
  <a :href="'/tasks/' + languageName">
    <div class="lang-container">
    <span>
      {{ languageName }}
    </span>
      <span>
      {{ countOfTasks }} задач
    </span>
    </div>
  </a>
</template>

<script>
export default {
  name: "LanguageItem",
  props: {
    languageName: {
      type: String,
      default: ''
    },
    countOfTasks: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {}
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');
a {
  text-decoration: none;
}

.lang-container {
  width: 350pt;
  background: #272727;
  font-family: 'Ubuntu', sans-serif;
  font-size: 19pt;
  box-shadow: 0 5pt 5pt black;
  color: #e9e9e9;
  border-radius: 15pt;
  display: flex;
  justify-content: space-between;
  padding: 15pt 12pt 15pt 12pt;
}

.lang-container:hover {
  background: #303030;
}

</style>