<template>
  <div>
    <el-card title="" class="commentCard" v-for="comment in commentList">
      <div style="display: flex">
        <div style="line-height: 50px;width: 30%">
          <i class="el-icon-time"></i>{{ comment.publishTime }}<br>
          <i class="el-icon-thumb"></i>{{ comment.supportNum }}<br>
          <i class="el-icon-notebook-2"></i>{{ comment.bookName }}<br>
          <i class="el-icon-star-on" style="color:#fda501;" v-if="comment.rate!==0">{{ comment.rate }}星</i><br>
        </div>
        <p @click="toBook(comment.bookId)" class="pStyle">
          <span v-if="comment.reviewerName!==''"> 回复 <i class="el-icon-user"></i>{{
              comment.reviewerName
            }}:<br><br></span>
          <span v-else>发表评论:<br><br></span>
          {{ comment.content }}
        </p>
      </div>

    </el-card>
  </div>
</template>

<script>
export default {
  name: "MyComment",
  props: ['commentList'],
  data() {
    return {
      bookId: this.$route.query.bookId
    }
  },
  methods: {
    toBook(bookId) {
      this.$router.push({
        path: '/book/bookDetail',
        query: {
          bookId: bookId,
        }
      })
    },
  }

}
</script>

<style scoped>
.commentCard {
  margin: 20px;
  width: 40%;
  display: inline-block;
  text-align: left;
}

.pStyle {
  background-color: #f5f4ee;
  margin: 0 15px 0 15px;
  width: 60%;
  padding: 15px;
  cursor: pointer;
  border-radius: 5px;
  text-align: left;
}
</style>