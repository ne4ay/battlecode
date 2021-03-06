<template>
  <GlobalHeader :is-needed-to-display-profile-info="true"/>
  <div class="content">
    <MainBackground />
      <div id="tasks-wrapper">
        <ShortTaskItem v-for="(task, index) in tasks"
                       :key="index"
                       :title="task.title"
                       :cost="task.cost"
                       :task-id="task.taskId"
                       :active-lang="lang"
                       :is-done="task.isDone"
                       class="task-item"/>
      </div>
      <RowPagination v-model:active-page-num="activePage"
                    v-model:count-of-pages="countOfPages"
                      :changing-listener="changePage"/>
  </div>
</template>

<script>
import GlobalHeader from "@/components/header/GlobalHeader";
import MainBackground from "@/components/home/MainBackground";
import ShortTaskItem from "@/components/items/ShortTaskItem";
import authenticationMixin from "@/mixins/authenticationMixin";
import router from "@/router/router";
import axios from "axios";
import Properties from "@/Properties";
import RowPagination from "@/components/pagination/RowPagination";
import commonUtilsMixin from "@/mixins/commonUtilsMixin";

export default {
  name: "TasksView",
  components: {
    GlobalHeader,
    MainBackground,
    ShortTaskItem,
    RowPagination
  },
  mixins: [
    authenticationMixin,
    commonUtilsMixin
  ],
  data() {
    return {
      lang: '',
      pageSize: 20,
      countOfPages: 1,
      activePage: 1,
      tasks: [],
    }
  },
  created() {
    if (!authenticationMixin.methods.getAuth()) {
      router.push('/auth');
    }
    this.lang = this.$route.path.replace('/tasks/', '');
    this.makeGetRequest(1);
  },
  methods: {
    makeGetRequest(pageNum) {
      axios.get(Properties.BBACK_ADDRESS + '/tasks/' + this.lang, {
        withCredentials: true,
        params: {
          size: this.pageSize,
          page: pageNum,
        }
      }).then(response => {
        const data = response.data;
        if (data.exception) {
          router.push('/error?error=' + data.exception);
          return;
        }
        this.countOfPages = data.response.countOfPages;
        this.tasks = data.response.tasks;
      }).catch(exception => {
        commonUtilsMixin.methods.handleException(exception);
      })
    },
    changePage(page) {
      this.activePage = page;
      this.makeRequest(page);
    }
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
  position: fixed;
  z-index: -1;
  min-height: calc(100% - 65pt);
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