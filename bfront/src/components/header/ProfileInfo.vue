<template>
  <div class="profile-info-wrapper">
    <div class="profile-label-container">
      <div id="profile-login-container">
        <a id="profile-label" @click="toggleDropDown">
          {{ getName }}
        </a>
      </div>
      <div id="profile-exp-container">
        <span class="exp-content level-number">
          {{getLevel}}
        </span>
        <ExperienceBar class="exp-content"
            id="experience-bar"
            :value-in-percent="getValueInPercent"
                       :height="30"
                       :width="80"/>
      </div>
    </div>
    <transition name="fall">
      <div v-if="isOpen" class="dropdown-content">
        <a href="/admin" class="dropdown-item" v-if="isAdmin">
          Админ-панель
        </a>
        <a class="dropdown-item" @click="logout">
          Выйти
        </a>
      </div>
    </transition>
  </div>
</template>

<script>
import axios from "axios";
import Properties from "@/Properties";
import authenticationMixin from "@/mixins/authenticationMixin";
import router from "@/router/router";
import Roles from "@/components/enums/Roles";
import ExperienceBar from "@/components/header/ExperienceBar";

export default {
  name: "ProfileInfo",
  components: {
    ExperienceBar
  },
  mixins: [
    authenticationMixin
  ],
  data() {
    return {
      isOpen: false,
      isAdmin: false,
    }
  },
  props: {
    unAuthListener: {
      type: Function,
    }
  },
  methods: {
    logout() {
      const backAddress = Properties.BBACK_ADDRESS;
      axios.post(backAddress + '/getout', {})
          .then(() => {
            authenticationMixin.methods.resetProfileInfo();
            this.unAuthListener();
            router.push('/home');
          })
          .catch(error => {
            router.push('/error?error=' + error);
          })
    },
    toggleDropDown() {
      this.isOpen = !this.isOpen;
    }
  },
  computed: {
    getName() {
      return authenticationMixin.methods.getProfileInfo().login;
    },
    getLevel() {
      return authenticationMixin.methods.getProfileInfo().level;
    },
    getValueInPercent() {
      return authenticationMixin.methods.getProfileInfo().percentValueToNextLevel;
    }
  },
  created() {
    this.isAdmin = authenticationMixin.methods
        .getProfileInfo()
        .roles
        .includes(Roles.GLOBAL_ADMINISTRATOR.id);
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');

.profile-info-wrapper {
  width: available;
  display: flex;
  flex-direction: column;
}

#profile-login-container {
  align-self: center;
}

#profile-label {
  font-family: 'Russo One', sans-serif;
  font-stretch: condensed;
  font-size: 22pt;
  color: #e5e5e5;
  margin: 0 10pt;
}


.profile-label-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

#profile-exp-container {
  display: flex;
  flex-direction: row;
  align-content: center;
}

#experience-bar {
  width: 80pt;
  height: 13pt;
}

.exp-content {
  justify-self: center;
  align-self: center;
  margin: 0 3pt;
}

.level-number {
  font-size: 16pt;
  color: #e5e5e5;
  font-family: 'Ubuntu', sans-serif;
}

#profile-label:hover {
  color: #f7a36a;
}

#profile-label:active {
  color: #e36815;
}

.dropdown-content {
  position: fixed;
  width: max-content;
  margin-top: 55pt;
  background-color: #202020;
  display: flex;
  flex-direction: column;
  box-shadow: 0 5pt 5pt black;
  z-index: 1;
}

.dropdown-item {
  padding: 10pt 35pt 10pt 10pt;
  font-family: 'Ubuntu', sans-serif;
  text-decoration: none;
  font-size: 17pt;
  color: #e5e5e5;
}

.dropdown-item:hover {
  color: #f7a36a;
  background-color: #272727;
}

.dropdown-item:active {
  color: #e36815;
  background-color: #080808;
}

.fall-enter-active,
.fall-leave-active {
  transition: all 0.4s cubic-bezier(.32, .18, .3, .99);
}

.fall-enter-from,
.fall-leave-to {
  transform: translateX(300pt);
}
</style>