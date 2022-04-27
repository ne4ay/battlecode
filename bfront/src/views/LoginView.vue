<template>
  <div class="main">
    <GlobalHeader id="header" :is-needed-to-display-profile-info="false"/>
    <div class="content">
      <MainBackground>
        <AuthWindow id="window" :tabs="tabs"/>
      </MainBackground>
    </div>
  </div>
</template>

<script>
import GlobalHeader from "@/components/header/GlobalHeader";
import AuthFormType from "@/components/header/AuthFormType";
import AuthWindow from "@/components/AuthWindow";
import MainBackground from "@/components/home/MainBackground";

export default {
  name: "AuthView",
  components: {
    GlobalHeader,
    MainBackground,
    AuthWindow
  },
  data() {
    return {
      tabs: [AuthFormType.AUTHORIZATION, AuthFormType.REGISTRATION],
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

.main {
  height: max-content;
}

.content {
  position: absolute;
  z-index: -1;
  height: calc(100% - 65pt);
  width: 100%;
  background-color: #202020;
}

#window {
  margin-top: 80pt;
}

</style>