<template>
  <GlobalHeader :is-needed-to-display-profile-info="true"/>
</template>

<script>
import GlobalHeader from "@/components/header/GlobalHeader";

export default {
  name: "TasksView",
  components: {
    GlobalHeader,
  },
}
</script>

<style scoped>

</style>