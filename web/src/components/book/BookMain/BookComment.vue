<template>
  <div style="background-color: #fcfbf9;padding: 20px">
    <el-row style="margin-bottom: 15px;">
      <el-col :span="12" v-for="(o,index) in reviewList">
        <el-card :body-style="{padding:0}" style="float: left;width: 130px">
          <img :src="o.image" class="image" @click="openArticle(o.id)">
        </el-card>
        <div style="float: left; padding: 10px; margin-left: 10px; width: 67%;">
          <h3 style="margin-top: 0">{{ o.title }}</h3>
          <div style="height: 20px">
            <span style="float: left">{{ o.userName }} <strong>评论</strong> 《{{ o.bookName }}》</span>
            <el-rate
                v-model="o.rate"
                disabled
                show-score
                text-color="#ff9900"
                score-template="{value}"></el-rate>
          </div>
          <p class="textOverflow" style="cursor: pointer" @click="openArticle(o.articleId,)">&nbsp;&nbsp;{{
              o.article
            }}</p>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "BookComment",
  props: ["reviewList"],
  methods: {
    openArticle(articleId, bookId) {
      this.$parent.openArticle(articleId, bookId);
    }
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

.image {
  width: 100%;
  display: block;
  cursor: pointer;
}
</style>