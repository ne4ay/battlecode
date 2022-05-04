<template>
  <GlobalHeader :is-needed-to-display-profile-info="true"/>
  <div class="content">
    <MainBackground>
      <div id="links-wrapper">
        <SimpleAdminLinkItem href="/admin/tasks"
                             text="Задачи" class="link-item"/>
        <SimpleAdminLinkItem href="/admin/users"
                             text="Пользователи" class="link-item"/>
      </div>
    </MainBackground>
  </div>
</template>

<script>
import GlobalHeader from "@/components/header/GlobalHeader";
import SimpleAdminLinkItem from "@/components/admin/AdminSimpleLinkItem";
import authenticationMixin from "@/mixins/authenticationMixin";
import Roles from "@/components/enums/Roles";
import router from "@/router/router";

export default {
  name: "AdminPanel",
  components: {
    GlobalHeader,
    SimpleAdminLinkItem,

  },
  mixins: [
      authenticationMixin
  ],
  created() {
    if(!authenticationMixin.methods.getProfileInfo().roles.includes(Roles.GLOBAL_ADMINISTRATOR)) {
      router.push('/error?error=Недостаточно прав для просмотра данной страницы!');
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
  height: calc(100% - 65pt);
  width: 100%;
  background-color: #202020;
}

#links-wrapper {
  margin-top: 12pt;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.link-item {
  margin: 8pt;
  align-self: center;
}
</style>