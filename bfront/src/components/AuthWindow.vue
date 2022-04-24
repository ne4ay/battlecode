<template>
  <div class="auth-window-container">
    <ul>
      <li v-for="(tab, index) in tabs" :key="index" @click.self="changeActiveForm(tab)">
        {{ tab.title }}
      </li>
    </ul>
    <div class="window-content">
      <KeepAlive>
        <component :is="dialogContentAuthComponent"/>
      </KeepAlive>
    </div>
  </div>
</template>

<script>
import RegistrationForm from "@/components/forms/RegistrationForm";
import AuthorizationForm from "@/components/forms/AuthorizationForm";
import AuthFormType from "@/components/header/AuthFormType";

export default {
  name: "AuthWindow",
  components: {
    AuthorizationForm,
    RegistrationForm,
  },
  props: {
    tabs: {
      type: Array,
    },
  },
  data() {
    return {
      activeComponent: AuthFormType.AUTHORIZATION,
    }
  },
  methods: {
    changeActiveForm(formType) {
      this.activeComponent = formType;
    }
  },
  computed: {
    dialogContentAuthComponent() {
      return this.activeComponent.name + 'Form';
    }
  },
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');
.auth-window-container {
  z-index: 2;
  margin: auto auto;
  background: #181818;
  border-radius: 12pt;
  min-height: 50pt;
  width: 320pt;
  font-family: 'Ubuntu', sans-serif;
  font-size: 17pt;
  box-shadow: 0 5pt 5pt black;
}

.window-content {
  color: #ead1c6;
  padding: 5pt 10pt 10pt 15pt;
}

ul {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  border-bottom: 3pt solid #e36815;
}

ul li {
  display: inline-block;
  font-family: 'Ubuntu', sans-serif;
  font-size: 17pt;
  padding: 10pt;
  width: 100%;
  text-align: center;
  list-style: none;
  color: #ead1c6;
}
ul li:hover {
  color: #f7a36a;
  background-color: #222222;
}
ul li:active {
  color: #e36815;
  background-color: #080808;
}
</style>