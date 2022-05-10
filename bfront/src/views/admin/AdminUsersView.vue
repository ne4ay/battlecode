<template>
  <GlobalHeader :is-needed-to-display-profile-info="true"/>
  <div class="content">
    <MainBackground/>
    <div id="users-wrapper">
      <AdminBackLink/>
      <AdminShortUserItem v-for="(user, index) in users"
                          :key="index"
                          class="user-item"
                          :user-id="user.userId"
                          :login="user.login"
                          :roles="user.roles"
      />

    </div>
    <RowPagination v-model:count-of-pages="countOfPages"
                   v-model:active-page-num="activePage"
                   :changing-listener="changePage"/>
  </div>
</template>

<script>
import GlobalHeader from "@/components/header/GlobalHeader";
import RowPagination from "@/components/pagination/RowPagination";
import AdminBackLink from "@/components/admin/AdminBackLink";
import AdminShortUserItem from "@/components/admin/AdminShortUserItem";
import authenticationMixin from "@/mixins/authenticationMixin";
import Roles from "@/components/enums/Roles";
import router from "@/router/router";
import axios from "axios";
import Properties from "@/Properties";
import commonUtilsMixin from "@/mixins/commonUtilsMixin";

export default {
  name: "AdminUsersView",
  components: {
    AdminShortUserItem,
    GlobalHeader,
    RowPagination,
    AdminBackLink,
  },
  mixins: [
    authenticationMixin,
    commonUtilsMixin
  ],
  data() {
    return {
      users: [],
      pageSize: 20,
      activePage: 1,
      countOfPages: 1,
    }
  },
  created() {
    if (!authenticationMixin.methods.getProfileInfo().roles.includes(Roles.GLOBAL_ADMINISTRATOR.id)) {
      router.push('/error?error=Недостаточно прав для просмотра данной страницы!');
      return;
    }
    this.makeRequest(this.activePage);
  },
  methods: {
    makeRequest(pageNum) {
      axios.get(Properties.BBACK_ADDRESS + '/admin/users/show', {
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
            this.users = respModel.response.users;
          }).catch(exception => {
        commonUtilsMixin.methods.handleException(exception);
      })
    },
    changePage(pageNum) {
      this.activePage = pageNum;
      this.makeRequest(pageNum);
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
  position: absolute;
  z-index: -1;
  min-height: 100%;
  width: 100%;
  background-color: #202020;
}

#users-wrapper {
  margin-top: 12pt;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.user-item {
  margin: 8pt;
  align-self: center;
}

</style>