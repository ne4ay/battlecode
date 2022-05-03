<template>
  <a :href="href">
    <div class="link-container">
      {{ text }}
    </div>
  </a>
</template>

<script>
export default {
  name: "SimpleAdminLinkItem",
  props: {
    href: {
      type: String,
      default: '/'
    },
    text: {
      type: String,
      default: ''
    }
  }
}
</script>

<style scoped>
a {
  text-decoration: none;
  border-radius: 15pt;
}
.link-container {
  width: 350pt;
  background: #272727;
  font-family: 'Ubuntu', sans-serif;
  font-size: 19pt;
  box-shadow: 0 5pt 5pt black;
  color: #e9e9e9;
  border-radius: 15pt;
  display: flex;
  justify-content: space-between;
  padding: 15pt 12pt 15pt 12pt;
}

.link-container:hover {
  color: #f7a36a;
  background: #303030;
}

.link-container:active {
  color: #e36815;
  background-color: #080808;
}
</style>