<template>
  <GlobalHeader :is-needed-to-display-profile-info="true"/>
  <div class="content">
    <a href="/admin/task/add">
      <div class="add-button">
        + Добавить
      </div>
    </a>
    <MainBackground>
      <div id="tasks-wrapper">
        <ShortTaskItem v-for="(task, index) in tasks"
                       :key="index" class="task-item"/>
      </div>
    </MainBackground>
  </div>
</template>

<script>
import GlobalHeader from "@/components/header/GlobalHeader";
import authenticationMixin from "@/mixins/authenticationMixin";
import Roles from "@/components/enums/Roles";
import router from "@/router/router";
import axios from "axios";
import Properties from "@/Properties";
import ShortTaskItem from "@/components/tasks/ShortTaskItem";

export default {
  name: "AdminTasksView",
  components: {
    GlobalHeader,
    ShortTaskItem,
  },
  data() {
    return {
      tasks: [],
      isFormActive: false,
    }
  },
  created() {
    if (!authenticationMixin.methods.getProfileInfo().roles.includes(Roles.GLOBAL_ADMINISTRATOR)) {
      router.push('/error?error=Недостаточно прав для просмотра данной страницы!');
    }
    axios.get(Properties.BBACK_ADDRESS + '/')
  },
  methods: {},
  computed: {}
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');

* {
  margin: 0;
  padding: 0;
}

template {
  background: #202020;
}

a {
  text-decoration: none;
}

.content {
  position: absolute;
  z-index: -1;
  height: calc(100% - 65pt);
  width: 100%;
  background-color: #202020;
}

#tasks-wrapper {
  margin-top: 12pt;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.task-item {
  margin: 8pt;
  align-self: center;
}

.add-button {
  margin-top: 4pt;
  padding: 10pt 10pt;
  font-family: 'Russo One', sans-serif;
  font-size: 19pt;
  color: #e5e5e5;
  background-color: #282828;
  justify-self: center;
  text-align: center;
}

.add-button:hover {
  color: #f7a36a;
  background-color: #202020;
}

.add-button:active {
  color: #e36815;
  background-color: #080808;
}

.fold-enter-active,
.fold-leave-active {
  /*transition: all 0.6s cubic-bezier(.42, 0, .77, 1.53);*/
  height: 330pt;
  transition: all 0.3s cubic-bezier(.23, .25, .23, 1);
}

.fold-enter-from,
.fold-leave-to {
  transform: translateY(-400pt);
  height: 0;
}
</style>