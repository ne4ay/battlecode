<template>
  <GlobalHeader :is-needed-to-display-profile-info="true"/>
  <div class="content">
    <MainBackground>
      <div id="content-wrapper">
       <TaskForm v-model:operation="operation"/>
      </div>
    </MainBackground>
  </div>
</template>

<script>
import GlobalHeader from "@/components/header/GlobalHeader";
import TaskForm from "@/components/forms/TaskForm";
import authenticationMixin from "@/mixins/authenticationMixin";
import Roles from "@/components/enums/Roles";
import router from "@/router/router";
import Operation from "@/components/enums/Operation";

export default {
  name: "AdminTaskAddingView",
  components: {
    GlobalHeader,
    TaskForm
  },
  data() {
    return {
      operation: '',
    }
  },
  created() {
    if (!authenticationMixin.methods.getProfileInfo().roles.includes(Roles.GLOBAL_ADMINISTRATOR)) {
      router.push('/error?error=Недостаточно прав для просмотра данной страницы!');
    }
    const upperCasePath = this.$route.path.toUpperCase();
    const operations = [Operation.UPDATE, Operation.ADD];
    const operation = this.determineOperation(upperCasePath, operations);
    if (!operation) {
      router.push('/error?error=Не балуйся!');
      return;
    }
    this.operation = operation;
  },
  methods: {
    determineOperation(path, operations) {
      let operation = null;
      operations.forEach(elem => {
        if (path.indexOf(elem) + 1 > 0) {
          operation = elem;
        }
      });
      return operation;
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