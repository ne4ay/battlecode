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

export default {
  name: "AdminTaskAddingView",
  components: {
    GlobalHeader,
    AddNewTaskForm
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

#content-wrapper {
  width: 540pt;
  margin: 12pt auto 10pt auto;
}
</style>