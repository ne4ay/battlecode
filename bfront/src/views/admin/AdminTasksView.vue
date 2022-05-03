<template>
  <GlobalHeader :is-needed-to-display-profile-info="true"/>
  <div class="content">
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
    ShortTaskItem
  },
  data() {
    return {
      tasks: []
    }
  },
  created() {
    if(!authenticationMixin.methods.getProfileInfo().roles.includes(Roles.GLOBAL_ADMINISTRATOR)) {
      router.push('/error?error=Недостаточно прав для просмотра данной страницы!');
    }
    axios.get(Properties.BBACK_ADDRESS + '/')
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}

template {
  background: #202020;
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
</style>