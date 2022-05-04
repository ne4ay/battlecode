<template>
  <GlobalHeader :is-needed-to-display-profile-info="true"/>
  <div class="content">
    <MainBackground>
      <div id="content-wrapper">
       <AddNewTaskForm />
      </div>
    </MainBackground>
  </div>
</template>

<script>
import GlobalHeader from "@/components/header/GlobalHeader";
import AddNewTaskForm from "@/components/forms/AddNewTaskForm";
import authenticationMixin from "@/mixins/authenticationMixin";
import Roles from "@/components/enums/Roles";
import router from "@/router/router";

export default {
  name: "AdminTaskAddingView",
  components: {
    GlobalHeader,
    AddNewTaskForm
  },

  created() {
    if (!authenticationMixin.methods.getProfileInfo().roles.includes(Roles.GLOBAL_ADMINISTRATOR)) {
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
}

.content {
  position: absolute;
  z-index: -1;
  width: 100%;
  background-color: #202020;
}

#content-wrapper {
  width: 540pt;
  margin: 12pt auto 10pt auto;
}
</style>