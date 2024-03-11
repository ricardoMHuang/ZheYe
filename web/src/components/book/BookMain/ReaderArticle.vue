<template>
  <div style="background-color: #fcfbf9;padding: 20px">
    <el-row>
      <el-col v-for="(article,i) in articleList" :span="11" :offset="i%2 > 0 ? 1 : 0" style="margin-top: 100px; ">
        <div style="position:relative;">
          <el-card :body-style="{ padding: '0px'}"
                   style=" width: 130px;height:180px; position:absolute;top:-70px;left: 20px;z-index: 1">
            <el-image :src="article.bookImage" class="image"
                      @click="openArticle(article.id,article.bookId)"></el-image>
          </el-card>
          <el-card style="">
            <h3 style="color: #42b983; position: relative;left: 160px;width: 320px">{{ article.title }}</h3>
            <h4 style="color: #D3DCE6; position: relative;left: 170px;">{{ article.userName }}</h4>
            <p class="textOverflow" style="-webkit-line-clamp: 3; font-size: 15px; cursor: pointer"
               @click="openArticle(article.id,article.bookId)">
              &nbsp;&nbsp;{{ article.content }}</p>
          </el-card>
        </div>


      </el-col>

    </el-row>
  </div>

</template>

<script>
export default {
  name: "ReaderArticle",
  props: ["articleList"],
  methods: {
    openArticle(articleId, bookId) {
      this.$parent.openArticle(articleId, bookId);
    },
  }
}
</script>

<style scoped>
.textOverflow {
  -webkit-line-clamp: 3;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>