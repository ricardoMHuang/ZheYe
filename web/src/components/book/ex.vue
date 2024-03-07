<template>
  <div id="app">
    <div v-for="article in paginatedArticles" :key="article.id">
      <p>{{ article.content }}</p>
    </div>

    <el-pagination
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pageSize"
        :total="articles.length">
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "ex",
  data() {
    return {
      articles: [
        {id: 1, title: 'Article 1', content: 'Content of article 1'},
        {id: 2, title: 'Article 2', content: 'Content of article 2'},
        {id: 3, title: 'Article 3', content: 'Content of article 3'},
        // Add more articles as needed
      ],
      pageSize: 2, // Number of articles per page
      currentPage: 1 // Current page number
    }
  },
  computed: {
    paginatedArticles() {
      const startIndex = (this.currentPage - 1) * this.pageSize;
      return this.articles.slice(startIndex, startIndex + this.pageSize);
    }
  },
  methods: {
    handleCurrentChange(page) {
      this.currentPage = page;
    }
  },
  created() {

  },

}
</script>

<style scoped>


</style>