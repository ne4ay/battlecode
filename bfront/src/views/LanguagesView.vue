<template>
  <GlobalHeader :is-needed-to-display-profile-info="true"/>
  <div class="content">
    <MainBackground />
      <div id="langs-wrapper">
        <LanguageItem v-for="(lang, index) in langs"
                      :key="index"
                      :language-name="lang.name"
                      :count-of-tasks="lang.countOfTasks" class="lang-item"/>
      </div>
  </div>
</template>

<script>
import GlobalHeader from "@/components/header/GlobalHeader";
import MainBackground from "@/components/home/MainBackground";
import LanguageItem from "@/components/tasks/LanguageItem";
import axios from "axios";
import router from "@/router/router";
import Properties from "@/Properties";
import authenticationMixin from "@/mixins/authenticationMixin";

export default {
  name: "LanguagesView",
  components: {
    LanguageItem,
    GlobalHeader,
    MainBackground,

  },
  data() {
    return {
      langs: []
    }
  },
  created() {
    if (!this.isAuth) {
      router.push('/auth');
    }
    axios.get(Properties.BBACK_ADDRESS + '/languages', {
      withCredentials: true,
    })
        .then(response => {
          this.langs = response.data.response.langs;
        })
        .catch(exception => {
          if (exception.response.status === 401) {
            authenticationMixin.methods.resetProfileInfo();
            router.push('/auth');
          }
        })
  },
  computed: {
    isAuth() {
      return authenticationMixin.methods.getAuth();
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

#langs-wrapper {
  margin-top: 12pt;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.lang-item {
  margin: 8pt;
  align-self: center;
}
</style>