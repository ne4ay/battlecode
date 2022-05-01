<template>
  <div class="profile-info-wrapper">
    <a class="profile-label">
      {{ getName }}
    </a>
    <span class="delimiter">
      |
    </span>
    <a class="profile-label" @click="logout">
      Выйти
    </a>
  </div>
</template>

<script>
import axios from "axios";
import Properties from "@/Properties";
import authenticationMixin from "@/mixins/authenticationMixin";

export default {
  name: "ProfileInfo",
  mixins: [
      authenticationMixin
  ],
  methods: {
    logout() {
      const backAddress = Properties.BBACK_ADDRESS;
      axios.post(backAddress + '/getout', {})
          .then(response => {
            const respModel = response.data;
            if (!respModel.response.isSuccessful) {
              return;
            }
            authenticationMixin.methods.resetProfileInfo();
          })
          .catch(error => {
            console.log(error);
          })
    }
  },
  computed: {
    getName() {
      return authenticationMixin.methods.getProfileInfo().login;
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');

.profile-info-wrapper {
  width: available;
}

.profile-label {
  font-family: 'Russo One', sans-serif;
  font-stretch: condensed;
  font-size: 21pt;
  color: #e5e5e5;
  margin: 0 5pt;
}

.delimiter {
  font-family: 'Ubuntu', sans-serif;
  font-stretch: condensed;
  font-size: 26pt;
  color: #e5e5e5;
  margin: 0 5pt;
}

.profile-label:hover {
  color: #f7a36a;
}

.profile-label:active {
  color: #e36815;
}
</style>