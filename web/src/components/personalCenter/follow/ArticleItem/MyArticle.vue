<template>
  <div>
    <div v-if="articleList!==null">
      <reader-article :articleList="articleList"></reader-article>
    </div>
    <el-empty v-else description="你还没有发过文章哦！">
    </el-empty>

    <el-button @click="openEditor()">发布文章</el-button>

  </div>
</template>

<script>
import ReaderArticle from "@/components/book/BookMain/ReaderArticle";

export default {
  name: "MyArticle",
  components: {ReaderArticle},
  props: ['articleList'],
  data() {
    return {}
  },
  mounted() {
    console.log(this.articleList)
  },
  methods: {
    openEditor() {
      this.$router.push({
        path: '/editor',
      })
    }
  }
}
</script>

<style scoped>


</style>