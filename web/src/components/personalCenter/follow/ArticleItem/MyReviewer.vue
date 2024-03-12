<template>
  <div>
    <div v-if="myReviewerList.length!==0">
      <el-card title="" class="commentCard" v-for="comment in myReviewerList">
        <div class="myReviewer">
        <span @click="toBook(comment.bookId)" style="cursor: pointer;">{{ comment.nickname }}:{{
            comment.content
          }}</span>
          <el-divider></el-divider>
          <div v-if="comment.reviewerList.length==1" v-for="item in comment.reviewerList" class="childComment">
            {{ item.nickname }} 回复: {{ item.content }}
          </div>
          <div v-if="comment.reviewerList.length>1" class="childComment">
            {{ comment.reviewerList[0].nickname }} 回复: {{ comment.reviewerList[0].content }}
            <i class="el-icon-arrow-right" id="iId" @click="showAllComment(comment)">更多</i>
          </div>

          <div v-if="comment.reviewerList.length===0" class="childComment" style="background-color:#e0dfdf">暂无人回复哦
          </div>
        </div>


      </el-card>
      <el-dialog
          :title="comment.content"
          :visible.sync="allCommentShow"
          width="30%">
        <div v-for="item in reviewerList" class="childComment">
          {{ item.nickname }} 回复: {{ item.content }}
        </div>
      </el-dialog>

    </div>
    <el-empty v-else description="暂无回复"></el-empty>
  </div>

</template>

<script>
export default {
  name: "MyReviewer",
  props: ['myReviewerList'],
  data() {
    return {
      bookId: this.$route.query.bookId,
      allCommentShow: false,
      reviewerList: [],
      comment: {},
    }
  },
  methods: {
    showAllComment(comment) {
      this.allCommentShow = !this.allCommentShow;
      this.comment = comment;
      this.reviewerList = comment.reviewerList;
    },

    toBook(bookId) {
      console.log(bookId)
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
.childComment {
  background-color: #f5f4ee;
  padding: 10px;
  margin: 10px;
}

.commentCard {
  margin: 20px;
  width: 40%;
  display: inline-block;
  text-align: left;

}

#iId {
  color: rgb(114, 174, 174);
  float: right;
  cursor: pointer;
}
</style>