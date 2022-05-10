<template>
  <GlobalHeader :is-needed-to-display-profile-info="true"/>
  <div class="content">
    <a href="/admin/task/add">
      <div class="add-button">
        + Добавить
      </div>
    </a>
    <MainBackground />
      <div id="tasks-wrapper">
        <AdminBackLink />
        <AdminShortTaskItem v-for="(task, index) in tasks"
                            :key="index"
                            class="task-item"
                            :task-id="task.taskId"
                            :title="task.title"
                            :cost="task.cost"
                            :status="task.status"
                            :languages="task.languages"
                            :remove-listener="() => tasks.splice(index, 1)"/>
      </div>
      <RowPagination v-model:count-of-pages="countOfPages"
                     v-model:active-page-num="activePage"
                     :changing-listener="changePage"/>
  </div>
</template>

<script>
import GlobalHeader from "@/components/header/GlobalHeader";
import authenticationMixin from "@/mixins/authenticationMixin";
import Roles from "@/components/enums/Roles";
import router from "@/router/router";
import axios from "axios";
import Properties from "@/Properties";
import AdminShortTaskItem from "@/components/admin/AdminShortTaskItem";
import RowPagination from "@/components/pagination/RowPagination";
import AdminBackLink from "@/components/admin/AdminBackLink";
import MainBackground from "@/components/home/MainBackground";
import responseProcessingMixin from "@/mixins/commonUtilsMixin";

export default {
  name: "AdminTasksView",
  components: {
    RowPagination,
    GlobalHeader,
    AdminShortTaskItem,
    AdminBackLink,
    MainBackground
  },
  mixins: [
    authenticationMixin,
    responseProcessingMixin
  ],
  data() {
    return {
      tasks: [],
      pageSize: 20,
      activePage: 1,
      countOfPages: 1,
      isFormActive: false,
    }
  },
  created() {
    if (!authenticationMixin.methods.getProfileInfo().roles.includes(Roles.GLOBAL_ADMINISTRATOR.id)) {
      router.push('/error?error=Недостаточно прав для просмотра данной страницы!');
      return;
    }
    this.makeGetRequest(1);
  },
  methods: {
    makeGetRequest(pageNum) {
      axios.get(Properties.BBACK_ADDRESS + '/admin/tasks/show', {
        withCredentials: true,
        params: {
          size: this.pageSize,
          page: pageNum,
        }
      })
          .then(response => {
            const respModel = response.data;
            if (respModel.exception || !respModel.response) {
              router.push('/error?error=' + respModel.exception);
              return;
            }
            this.countOfPages = respModel.response.countOfPages;
            this.tasks = respModel.response.tasks;
          }).catch(exception => {
        responseProcessingMixin.methods.handleException(exception);
      });
    },
    changePage(pageNum) {
      this.activePage = pageNum;
      this.makeRequest(pageNum);
    }
  },
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