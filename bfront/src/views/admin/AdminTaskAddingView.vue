<template>
  <GlobalHeader :is-needed-to-display-profile-info="true"/>
  <div class="content">
    <MainBackground />
    <div id="content-wrapper">
      <NewTaskForm v-model:operation="operation"
                v-model:operation-action-name="operationAction"
                v-model:process-url="processUrl"
                v-model:id="id"/>
    </div>
  </div>
</template>

<script>
import GlobalHeader from "@/components/header/GlobalHeader";
import NewTaskForm from "@/components/forms/NewTaskForm";
import MainBackground from "@/components/home/MainBackground";
import authenticationMixin from "@/mixins/authenticationMixin";
import Roles from "@/components/enums/Roles";
import router from "@/router/router";
import Operation from "@/components/enums/Operation";
import Properties from "@/Properties";
import commonUtilsMixin from "@/mixins/commonUtilsMixin";

export default {
  name: "AdminTaskAddingView",
  components: {
    GlobalHeader,
    NewTaskForm,
    MainBackground
  },
  mixins: [
      commonUtilsMixin
  ],
  data() {
    return {
      operation: '',
      operationAction: '',
      processUrl: '',
      id: 0,
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
    if (operation === Operation.UPDATE) {
      this.id = this.determineId(operation);
    }
    this.operationAction = this.determineOperationAction(operation);
    this.processUrl = this.determineProcessUrl(operation);
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
    },
    determineOperationAction(operation) {
      switch (operation) {
        case Operation.UPDATE:
          return 'Изменить';
        case Operation.ADD:
          return 'Добавить';
        default:
          return '';
      }
    },
    determineId(operation) {
      if (operation !== Operation.UPDATE) {
        return 0;
      }
      return commonUtilsMixin.methods.determineId(this.$route.path);
    },
    determineProcessUrl(operation) {
      switch (operation) {
        case Operation.UPDATE: {
          const id = this.determineId(operation);
          return Properties.BBACK_ADDRESS + '/admin/tasks/update/' + id;
        }
        case Operation.ADD: {
          return Properties.BBACK_ADDRESS + '/admin/tasks/add';
        }
        default:
          return '';
      }
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