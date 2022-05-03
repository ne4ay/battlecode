<template>
  <div :class="cssClass">
    {{ num }}
  </div>
</template>

<script>
export default {
  name: "PaginationNode",
  props: {
    num: {
      default: 1
    },
    isActive: {
      type: Boolean,
      default: false
    },
    isClickable: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      cssClass: ''
    }
  },
  created() {
    this.updateState();
  },
  updated() {
    this.updateState();
  },
  methods: {
    updateState() {
      let nodeClass = 'pagination-node';
      if (this.isActive) {
        nodeClass += ' active';
      }
      if (this.isClickable) {
        nodeClass += ' clickable';
      }
      this.cssClass = nodeClass;
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Russo+One&display=swap');

.pagination-node {
  border-radius: 12pt;
  box-shadow: 0 1pt 1pt 1pt black;
  background: rgba(100, 100, 100, 0.4);
  color: #e5e5e5;
  min-width: 20pt;
  padding: 8pt;
  text-align: center;
  font-family: 'Russo One', sans-serif;
  font-size: 21pt;
}

.active {
  box-shadow: 0 1pt 1pt 1pt #f7a36a;
}

.clickable:hover {
  color: #f7a36a;
  background-color: #222222;
}

.clickable:active {
  color: #e36815;
  background: rgba(0, 0, 0, .4);
}
</style>