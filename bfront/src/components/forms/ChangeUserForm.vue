<template>
  <form @submit.prevent class="form-container">
    <div class="row-container">
      <span>

      </span>
    </div>
  </form>
</template>

<script>
export default {
  name: "ChangeUserForm",
  components: {
  }
}
</script>

<style scoped>
.form-container {
  display: flex;
  flex-direction: column;
  align-content: center;
  justify-content: center;
  text-align: center;
  padding: 10pt;
}

.row-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
}
</style>