<template>
  <div style="background-color: #fcfbf9;padding: 20px">
    <el-row>
      <el-col v-for="(article,i) in articleList" :span="11" :offset="i%2 > 0 ? 1 : 0" style="margin-top: 100px; ">
        <div style="position:relative;">
          <el-card :body-style="{ padding: '0px'}"
                   class="card">
            <el-image :src="article.bookImage" class="image"
                      @click="openBook(article.bookId)"></el-image>
          </el-card>
          <el-card class="articleCard">
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
      this.$router.push({
        path: '/book/article',
        query: {
          articleId: articleId,
          bookId: bookId,
        }
      })
    },
    openBook(val) {
      // 变成 /user?id=1
      this.$router.push({
        path: '/book/bookDetail',
        query: {
          bookId: val,
        }
      })
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

.card {
  width: 130px;
  height: 180px;
  position: absolute;
  top: -70px;
  left: 20px;
  z-index: 1;
  cursor: pointer;
  transition: 0.5s;
}

.card:hover {
  transform: scale(1.2);
}

.articleCard:hover {
  transform: scale(1.1);
}

</style>