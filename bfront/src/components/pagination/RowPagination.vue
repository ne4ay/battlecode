<template>
  <div class="pagination-container" v-if="countOfPages > 1">
    <PaginationNode v-for="(page, index) in pages"
                    :key="index"
                    :num="page"
                    :is-active="activePageNum === page"
                    :is-clickable="page !== '...'"
                    @click="changeThePage(page)"/>
  </div>
</template>

<script>
import PaginationNode from "@/components/pagination/PaginationNode";
import router from "@/router/router";

export default {
  name: "RowPagination",
  components: {
    PaginationNode
  },
  props: {
    activePageNum: {
      type: Number,
      default: 1,
    },
    countOfPages: {
      type: Number,
      default: 1
    },
    changingListener: {
      type: Function
    }
  },
  data() {
    return {
      pages: []
    }
  },
  created() {
    this.updatePagesState();
  },
  updated() {
    this.updatePagesState();
  },
  methods: {
    createOrderedArr(size) {
      let arr = []
      for (let i = 1; i <= size; i++) {
        arr.push(i);
      }
      return arr;
    },
    updatePagesState() {
      if (this.activePageNum > this.countOfPages) {
        router.push('/error?error=' + 'Как ты это сделал?');
      }
      if (this.countOfPages <= 4) {
        this.pages = this.createOrderedArr(this.countOfPages);
        return;
      }
      let arr = [];
      arr.push(1);
      if (this.activePageNum === 1) {
        arr.push(2);
      }
      if (this.activePageNum - 1 - 1 > 1) {
        arr.push('...');
      }
      if (this.activePageNum > 1 && !arr.includes(this.activePageNum - 1)) {
        arr.push(this.activePageNum - 1);
      }
      if (this.activePageNum > 1 && !arr.includes(this.activePageNum)) {
        arr.push(this.activePageNum);
      }
      if (this.activePageNum + 1 < this.countOfPages && !arr.includes(this.activePageNum + 1)) {
        arr.push(this.activePageNum + 1);
      }
      if (this.activePageNum + 1 + 1 < this.countOfPages) {
        arr.push('...');
      }
      if (!arr.includes(this.countOfPages)) {
        arr.push(this.countOfPages);
      }
      this.pages = arr;
    },
    changeThePage(page) {
      if (page === '...' || page === this.activePageNum) {
        return;
      }
      this.$emit('update:activePageNum', page);
      this.changingListener(page);

    }
  }
}
</script>

<style scoped>
.pagination-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.pagination-node {
  margin: 10pt;
}


</style>