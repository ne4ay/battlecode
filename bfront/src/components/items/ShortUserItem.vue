<template>
  <div class="user-container">
    <span id="login">
      {{ login }}
    </span>
    <div class="row-wrapper">
      <div class="row-label">
        Уровень:
      </div>
      {{ level }}
    </div>
    <div class="row-wrapper">
      <div class="row-label">
        Опыт:
      </div>
      {{ experience }}
    </div>
    <ExperienceBar    id="experience-bar"
        :value-in-percent="percentValueToNextLevel"
                        :height="30"
                        :width="80"/>
  </div>
</template>

<script>
import ExperienceBar from "@/components/header/ExperienceBar";

export default {
  name: "ShortUserItem",
  components: {
    ExperienceBar,
  },
  props: {
    login: {
      type: String,
    },
    experience: {
      type: Number
    },
    level: {
      type: Number
    },
    percentValueToNextLevel: {
      type: Number
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');
a {
  text-decoration: none;
  border-radius: 15pt;
}

.user-container {
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

.row-wrapper {
  display: flex;
  flex-direction: row;
}

.row-label {
  width: 120pt;
}
#login {
  font-family: 'Russo One', sans-serif;
  font-size: 17pt;
}

#experience-bar {
  width: 80pt;
  height: 13pt;
}
</style>