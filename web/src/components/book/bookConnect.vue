<template>
  <div>

  </div>
</template>

<script>

export default {
  name: "bookConnect",
  data() {
    return {
      book: {},
      author: {},
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    async init() {
      this.author = JSON.parse(this.$route.query.author);
      this.book = JSON.parse(this.$route.query.book);
    }
  }
}
</script>

<style scoped>

</style>