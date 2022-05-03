<template>
  <div class="profile-info-wrapper">
    <a id="profile-label" @click="toggleDropDown">
      {{ getName }}
    </a>
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

export default {
  name: "ProfileInfo",
  mixins: [
    authenticationMixin
  ],
  data() {
    return {
      isOpen: false,
      isAdmin: false,
    }
  },
  methods: {
    logout() {
      const backAddress = Properties.BBACK_ADDRESS;
      axios.post(backAddress + '/getout', {})
          .then(response => {
            const respModel = response.data;
            if (!respModel.response.isSuccessful) {
              router.push('/error')
              return;
            }
            authenticationMixin.methods.resetProfileInfo();
            router.go('/');
          })
          .catch(error => {
            console.log(error);
          })
    },
    toggleDropDown() {
      this.isOpen = !this.isOpen;
    }
  },
  computed: {
    getName() {
      return authenticationMixin.methods.getProfileInfo().login;
    }
  },
  created() {
    this.isAdmin = authenticationMixin.methods
        .getProfileInfo()
        .roles
        .includes(Roles.GLOBAL_ADMINISTRATOR);
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

#profile-label {
  font-family: 'Russo One', sans-serif;
  font-stretch: condensed;
  font-size: 22pt;
  color: #e5e5e5;
  margin: 0 10pt;
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
  margin-top: 46pt;
  background-color: #202020;
  display: flex;
  flex-direction: column;
  box-shadow: 0 5pt 5pt black;
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
  transition: all 0.6s cubic-bezier(.32,.18,.3,.99);
}

.fall-enter-from,
.fall-leave-to {
  transform: translateX(300pt);
}
</style>