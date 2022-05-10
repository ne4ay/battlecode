<template>
  <GlobalHeader :is-needed-to-display-profile-info="true"/>
  <div class="content">
    <MainBackground />
    <div id="users-wrapper">
      <ShortUserItem  v-for="(user, index) in users"
                      :key="index"
                      :login="user.login"
                      :level="user.level"
                      :experience="user.experience"
                      :percent-value-to-next-level="user.percentValueToNextLevel"
          class="user-item"

      />
    </div>
    <RowPagination v-model:active-page-num="activePage"
                   v-model:count-of-pages="countOfPages"
                   :changing-listener="changePage"/>
  </div>
</template>

<script>
import MainBackground from "@/components/home/MainBackground";
import GlobalHeader from "@/components/header/GlobalHeader";
import axios from "axios";
import Properties from "@/Properties";
import router from "@/router/router";
import commonUtilsMixin from "@/mixins/commonUtilsMixin";
import authenticationMixin from "@/mixins/authenticationMixin";
import ShortUserItem from "@/components/items/ShortUserItem";
import RowPagination from "@/components/pagination/RowPagination";

export default {
  name: "RatingView",
  components: {
    GlobalHeader,
    MainBackground,
    ShortUserItem,
    RowPagination
  },
  mixins: [
    authenticationMixin,
    commonUtilsMixin,
  ],
  data() {
    return {
      pageSize: 20,
      countOfPages: 1,
      activePage: 1,
      users: []
    }
  },
  created() {
    if (!authenticationMixin.methods.getAuth()) {
      router.push('/auth');
    }
    this.makeGetRequest(1);
  },
  methods: {
    makeGetRequest(pageNum) {
      axios.get(Properties.BBACK_ADDRESS + '/rating', {
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
        this.users = data.response.users;
      }).catch(exception => {
        commonUtilsMixin.methods.handleException(exception);
      })
    },
    changePage(pageNum) {
      this.activePage = pageNum;
      this.makeGetRequest(pageNum);
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