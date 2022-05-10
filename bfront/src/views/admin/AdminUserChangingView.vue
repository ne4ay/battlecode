<template>
  <GlobalHeader :is-needed-to-display-profile-info="true"/>
  <div class="content">
    <MainBackground />
    <div id="content-wrapper">
      <ChangeUserView />
    </div>
  </div>
</template>

<script>
import MainBackground from "@/components/home/MainBackground";
import GlobalHeader from "@/components/header/GlobalHeader";
import ChangeUserView from "@/components/forms/ChangeUserForm";
import authenticationMixin from "@/mixins/authenticationMixin";
import Roles from "@/components/enums/Roles";
import router from "@/router/router";
import commonUtilsMixin from "@/mixins/commonUtilsMixin";

export default {
  name: "AdminUserChangingView",
  components: {
    GlobalHeader,
    MainBackground,
    ChangeUserView
  },
  mixins: [
      commonUtilsMixin
  ],
  data() {
    return {
      id: 0
    }
  },
  created() {
    if (!authenticationMixin.methods.getAuth()) {
      router.push('/auth');
    }
    if (!authenticationMixin.methods.getProfileInfo().roles.includes(Roles.GLOBAL_ADMINISTRATOR.id)) {
      router.push('/error?error=Недостаточно прав для просмотра данной страницы!');
    }
    this.id = commonUtilsMixin.methods.determineId(this.$route.path);
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}

template {

}

.content {
  position: absolute;
  z-index: -1;
  min-height: 100%;
  width: 100%;
  background-color: #202020;
}

#content-wrapper {
  width: 540pt;
  margin: 12pt auto 10pt auto;
}
</style>