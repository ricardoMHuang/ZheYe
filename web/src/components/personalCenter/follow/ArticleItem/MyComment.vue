<template>
  <div>
    <div v-if="commentList!==null">
      <el-card title="" class="commentCard" v-for="comment in commentList">
        <div style="display: flex">
          <p @click="toBook(comment.bookId)" class="pStyle">
          <span v-if="comment.reviewerName!==''"> 回复 <i class="el-icon-user"></i>{{
              comment.reviewerName
            }}:<br><br></span>
            <span v-else>发表评论:<br><br></span>
            {{ comment.content }}
          </p>
          <div style="line-height: 40px;width: 30%">
            <p>
              <i class="el-icon-time"></i>{{ comment.publishTime }}<br>
              <i class="el-icon-thumb"></i>{{ comment.supportNum }}<br>
              <i class="el-icon-notebook-2"></i>{{ comment.bookName }}<br>
              <i class="el-icon-star-on" style="color:#fda501;" v-if="comment.rate!==0">{{ comment.rate }}星</i><br>
            </p>
          </div>

        </div>
      </el-card>
    </div>
    <el-empty v-else description="暂无评论"></el-empty>
  </div>
</template>

<script>
import bookDetailApi from "../../../../../api/bookDetail";

export default {
  name: "MyComment",
  props: ['commentList'],
  data() {
    return {
      bookId: this.$route.query.bookId,
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

.comment-option {

  position: relative;
}

</style>