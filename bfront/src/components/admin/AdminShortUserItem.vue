<template>
  <div class="user-container">
    <div id="user-description" class="section-container">
      <a :href="'/admin/user/' + userId" class="clickable title">
        {{ login }}
      </a>
      <div class="row-wrapper">
          Роли:
        {{ printableRoles }}
      </div>
    </div>
  </div>
</template>

<script>
import commonUtilsMixin from "@/mixins/commonUtilsMixin";

export default {
  name: "AdminShortUserItem",
  mixins: [
      commonUtilsMixin
  ],
  props: {
    userId: {
      type: Number,
      default: 0
    },
    login: {
      type: String,
      default: ''
    },
    roles: {
      type: Array,
      default: () => []
    }
  },
  computed: {
    printableRoles() {
      return commonUtilsMixin.methods.convertToPrintable(this.roles);
    }
  }
}
</script>

<style scoped>
.user-container {
  width: 400pt;
  background: #272727;
  padding: 5pt 10pt;
  font-family: 'Ubuntu', sans-serif;
  box-shadow: 0 4pt 4pt black;
  color: #e9e9e9;
  border-radius: 15pt;
  font-size: 14pt;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  text-decoration: none;
}

.section-container {
  margin: 5pt;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
}

.clickable:hover {
  color: #f7a36a;
}

.clickable:active {
  color: #e36815;
}

.title {
  color: #e9e9e9;
  font-family: 'Russo One', sans-serif;
  font-size: 17pt;
  text-decoration: none
}
.row-wrapper {
  display: flex;
  flex-direction: row;
}

.row-label {
  width: 120pt;
}

#user-container {
  flex-grow: 2;
  display: flex;
  flex-direction: column;
}
</style>