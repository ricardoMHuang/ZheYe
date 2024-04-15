<template>
  <div>
    <el-tabs v-model="activeName">
      <el-tab-pane label="我的评论" name="first">
        <h3> 我的评论</h3>
        <my-comment :commentList="commentList"></my-comment>
      </el-tab-pane>
      <el-tab-pane label="回复我的" name="second">
        <h3>回复我的</h3>
        <my-reviewer :myReviewerList="myReviewerList"></my-reviewer>
      </el-tab-pane>
      <el-tab-pane label="评论过的书" name="third">
        <h3>评论过的书</h3>
        <reviewed-books :bookList="bookList"></reviewed-books>
      </el-tab-pane>
      <el-tab-pane label="文章" name="four">
        <h3>文章</h3>
        <my-article :articleList="articleList"></my-article>
      </el-tab-pane>
    </el-tabs>

  </div>
</template>

<script>


import bookDetailApi from "../../../../api/bookDetail";
import bookApi from "../../../../api/book";
import userApi from "../../../../api/user"
import MyComment from "@/components/personalCenter/follow/articleItem/MyComment";
import MyReviewer from "@/components/personalCenter/follow/articleItem/MyReviewer";
import ReviewedBooks from "@/components/personalCenter/follow/articleItem/ReviewedBooks";
import articleApi from "../../../../api/article";
import MyArticle from "@/components/personalCenter/follow/articleItem/MyArticle";

export default {
  components: {MyComment, MyReviewer, ReviewedBooks, MyArticle},
  name: "ArticleItem",
  data() {
    return {
      userId: JSON.parse(window.sessionStorage.getItem("userInfo")).id,
      activeName: 'first',
      articleList: [],
      commentList: [],
      myReviewerList: [],
      reviewedBooks: [],
      bookList: [],
    }

  },
  mounted() {
    this.init();
  },

  computed: {},
  methods: {
    //初始化
    async init() {
      await this.getCommentList();
      await this.getMyReviewer();
      await this.getReviewedBooks();
      await this.getArticleList();
    },
    //获得评论列表
    async getCommentList() {
      let commentList = await bookDetailApi.getCommentByUserId(this.userId);
      commentList = commentList.data;
      if (commentList.code === 200) {
        this.commentList = commentList.data;
      } else {
        this.commentList = null;
      }
      await this.addBookName();
      await this.addReviewer();
      await this.addUserName();
      console.log(commentList.message);
      console.log(this.commentList);
    },
    //在commentList中添加用户名的属性
    async addUserName() {
      for (const comment of this.commentList) {
        let nickname = await userApi.selectNickname(comment.userId);
        nickname = nickname.data;
        if (nickname.code === 200) {
          nickname = nickname.data;
          this.$set(comment, "nickname", nickname)
        }
      }
    },
    //在commentList中添加书名的属性
    async addBookName() {
      for (const comment of this.commentList) {
        let bookName = await bookApi.getBookName(comment.bookId);
        bookName = bookName.data;
        if (bookName.code === 200) {
          bookName = bookName.data
          this.$set(comment, "bookName", bookName)
        }
      }
    },
    //在commentList中添加回复对象的属性
    async addReviewer() {
      for (const comment of this.commentList) {
        if (comment.commentId !== 0) {
          let parentComment = await bookDetailApi.getCommentById(comment.commentId);
          let userId;
          parentComment = parentComment.data;
          if (parentComment.code === 200) {
            parentComment = parentComment.data
            userId = parentComment.userId;
            this.$set(comment, "reviewerComment", parentComment.content)
          }
          let reviewerName = await userApi.selectNickname(userId);
          reviewerName = reviewerName.data;
          if (reviewerName.code === 200) {
            reviewerName = reviewerName.data
            this.$set(comment, "reviewerName", reviewerName)
          }
        } else {
          this.$set(comment, "reviewerName", "")

        }
      }
    },
    //获得回复的评论
    async getMyReviewer() {
      let reviewerList;
      this.myReviewerList = this.commentList;
      for (let comment of this.myReviewerList) {
        this.$set(comment, "comment", comment);
        reviewerList = await bookDetailApi.selectCommentByCommentId(comment.id)
        reviewerList = reviewerList.data;
        if (reviewerList.code === 200) {
          reviewerList = reviewerList.data;
          let nickname;
          for (let item of reviewerList) {
            nickname = await userApi.selectNickname(item.userId);
            nickname = nickname.data
            if (nickname.code === 200) {
              nickname = nickname.data;
              this.$set(item, "nickname", nickname);
            }
          }

          this.$set(comment, "reviewerList", reviewerList);

        } else {
          this.$set(comment, "reviewerList", "");
        }
      }
      console.log("myReviewerList:")
      console.log(this.myReviewerList)
    },
    //获得我评论过的书
    async getReviewedBooks() {
      const bookList = [];
      for (let item of this.commentList) {
        let book = await bookApi.getBookById(item.bookId);
        book = book.data;
        console.log(book.message);
        if (book.code === 200) {

          bookList.push(book.data);
        }
      }
      // 对bookList去重
      //排序
      bookList.sort((a, b) => a.id - b.id);
      let i = 0, j = 1;
      const newBookList = [];
      while (j < bookList.length) {
        if (bookList[i].id === bookList[j].id) {
          j++;
        } else {
          newBookList.push(bookList[i]);
          i = j;
        }
      }
      if (j === bookList.length) {
        newBookList.push(bookList[i]);
      }
      this.bookList = newBookList;
      console.log("评论过的书：");
      console.log(this.bookList);
    },
    //获取我的文章
    async getArticleList() {
      let articleList = await articleApi.getArticleByUserId(this.userId);
      articleList = articleList.data;
      this.articleList = articleList.data;
      console.log(articleList.message);
      console.log(this.articleList)
    }
  }
}

</script>
<style>

/*.el-tabs__item {*/
/*  color: #FFFFFF!* 非激活状态下的字体颜色为黑色 *!*/
/*}*/


</style>