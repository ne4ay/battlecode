<template>
  <button :class="cssClass"
      @click="toggleSelect">
    {{ languageName }}
  </button>
</template>

<script>
const baseCssClass = 'language-select ';

export default {
  name: "SingleLanguageOption",
  props: {
    languageName: {
      type: String
    },
    activationListener: {
      type: Function
    },
    deactivationListener: {
      type: Function
    },
    isActive: {
      type: Boolean
    }
  },
  data() {
    return {
      cssClass: 'language-select ',
    }
  },
  methods: {
    toggleSelect() {
      if (this.isActive) {
        this.deactivationListener(this.languageName);
        this.cssClass = baseCssClass;
      } else {
        this.activationListener(this.languageName);
        this.cssClass = baseCssClass + ' active ';
      }
      this.$emit('update:isActive', !this.isActive);
    }
  },
  created() {
    if (this.isActive) {
      this.cssClass = baseCssClass + ' active ';
    } else {
      this.cssClass = baseCssClass;
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@500&display=swap');
.language-select {
  border-radius: 15pt;
  border: none;
  font-family: 'Ubuntu', sans-serif;
  font-size: 17pt;
  background-color: #181818;
  padding: 8pt 14pt;
  color: #e5e5e5;
  -webkit-user-select: none;   /* Chrome/Safari/Opera */
  -moz-user-select: none;      /* Firefox */
  -ms-user-select: none;       /* Internet Explorer/Edge */
  user-select: none;           /* Non-prefixed version, currently */
}

.active {
  background-color: #1d5b98;
}

.language-select:hover {
  color: #f7a36a;
  background: rgba(0, 0, 0, .6);
}

.language-select:active {
  color: #e36815;
  background: rgba(0, 0, 0, .4);
}

.language-select:focus {
  outline: 1pt solid #f7a36a !important;
}
</style>