<template>
  <div style="width: 1200px;margin: auto">
    <!--    弹出框-->
    <div>
      <el-dialog :visible.sync="dialogVisible" :title="title">
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm">
          <el-form-item label="评分" prop="rate" style="position:relative;">
            <el-rate v-model="ruleForm.rate" style="position: relative;top: 40px;"></el-rate>
          </el-form-item>
          <el-form-item label="评论" prop="comment">
            <el-input v-model="ruleForm.comment" type="textarea"></el-input>
          </el-form-item>
          <!--          <span slot="footer" class="dialog-footer"></span>-->
          <el-form-item style="left: 540px;position:relative;">
            <el-button @click="clear()">取消</el-button>
            <el-button type="primary" @click="submit('ruleForm')">确定</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
    <!--返回上一页-->
    <el-page-header @back="goBack" :content="book.name" style="padding: 20px"></el-page-header>

    <!--          书本的基本内容和评分-->
    <book-information :book="book" :state="state" :author="author" :value="value"></book-information>

    <!--      内容简介-->
    <h2 class="title">内容简介</h2>
    <el-card>
      <p style="width: 90%;margin: auto;line-height: 3ch">{{ book.briefIntroduction }}</p>
    </el-card>
    <!--      作者简介-->
    <div>
      <h2 class="title">作者简介</h2>
      <el-card>
        <p style="width: 90%;margin: auto;line-height: 3ch">
          &nbsp;&nbsp;{{ author.briefIntroduction }}
        </p>
      </el-card>

    </div>
    <!--      目录-->
    <div>
      <h2 class="title">目录</h2>
      <el-card>
        <el-collapse v-model="activeName" accordion>
          <el-collapse-item :title="'第一章：'+directory.chapter1" name="1">
            <div>{{ directory.content }}</div>
          </el-collapse-item>
          <el-collapse-item :title="'第二章：'+directory.chapter2" name="2">
            <div>{{ directory.content }}</div>
          </el-collapse-item>
          <el-collapse-item :title="'第三章：'+directory.chapter3" name="3">
            <div>{{ directory.content }}</div>
          </el-collapse-item>
          <el-collapse-item :title="'第四章：'+directory.chapter4" name="4">
            <div>{{ directory.content }}</div>
          </el-collapse-item>
          <el-collapse-item :title="'第五章：'+directory.chapter5" name="5">
            <div>{{ directory.content }}</div>
          </el-collapse-item>
          <el-collapse-item :title="'第六章：'+directory.chapter6" name="6">
            <div>{{ directory.content }}</div>
          </el-collapse-item>
        </el-collapse>
      </el-card>

    </div>
    <!--      读者推文-->

    <h3 class="title">读者推文</h3>
    <el-card style="position:relative;">
      <!--    头像-->
      <div style="margin-top: 40px">
        <el-avatar size="large" :src="article.userImage"></el-avatar>
        <div style="display: inline-block;padding: 10px">
          <div>{{ article.userName }}</div>
          <div style="color: #999999"> {{ article.publicTime }} 发表</div>
        </div>
      </div>
      <!--    文章-->
      <h3>{{ article.title }}</h3>
      <p style="line-height: 3ch;" class="textOverflow">
        &nbsp;&nbsp;{{ article.content }}
      </p>
      <el-button style="position:relative; left: 1000px" plain @click="openArticle()">查看推文</el-button>
    </el-card>
    <!--      评论-->
    <h3 class="title">评论</h3>
    <comment-detail :commentList="commentList"></comment-detail>
    <!--    回复评论框-->
    <el-drawer
        :title="reviewerTitle"
        :visible.sync="drawer"
        :direction="direction" style="width: 60%;margin: auto">
      <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="100px">
        <el-form-item label="评论框" prop="checkComment" style="margin: 20px">
          <el-input type="textarea" v-model="ruleForm2.checkComment" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm2')">提交</el-button>
          <el-button @click="resetForm('ruleForm2')">重置</el-button>
        </el-form-item>
      </el-form>

    </el-drawer>
    <!--    空评论状态-->
    <el-empty description="等待你的评论哦" v-if="!commentList.length"></el-empty>
  </div>

</template>

<script>
import directoryApi from "../../../api/directory";
import articleApi from "../../../api/article";
import bookDetailApi from "../../../api/bookDetail";
import supportApi from "../../../api/support";
import userApi from "../../../api/user";
import bookCollectApi from "../../../api/bookCollect";
import bookApi from "../../../api/book"
import authorApi from "../../../api/author"
import BookInformation from "@/components/book/bookDetailComponent/BookInformation";
import CommentDetail from "@/components/book/bookDetailComponent/CommentDetail";

export default {
  name: "newBook",
  components: {BookInformation, CommentDetail},
  data() {
    let validateRate = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请评分'));
      } else {
        callback();
      }
    };
    return {
      bookId: JSON.parse(this.$route.query.bookId),
      drawer: false,
      direction: 'btt',
      reviewer: "",
      state: false,
      dialogVisible: false,
      dialogTwoVisible: false,
      title: '',
      textarea: "请友善发言",
      ruleForm: {
        comment: '',
        rate: 0,
      },
      rules: {
        comment: [{required: true, message: '评论不能为空', trigger: 'change'}],
        rate: [
          {validator: validateRate, trigger: 'blur'}
        ],
      },
      ruleForm2: {
        checkComment: '',
      },
      rules2: {
        checkComment: [{required: true, message: "评论不能为空", trigger: 'blur'}],

      },
      id: this.$route.query.id,
      value: 4,
      author: {name: "作者名字", image: "https://img3.doubanio.com/icon/up263944680-3.jpg",},
      book: {},
      directory: {},
      article: {},
      commentList: [],
      userId: JSON.parse(window.sessionStorage.getItem("userInfo")).id,
      supportColor: "black",
      reviewerTitle: "",
      commentId: "",
    }
  },
  mounted() {
    this.init();
  },

  computed: {},

  methods: {
    async init() {
      await this.getBookAndAuthor();
      await this.isState();
      await this.getDirectory();
      await this.getArticle();
      await this.getCommentList();

    },
    // 请求读者推文
    async getArticle() {
      let article = await articleApi.getArticleInt(this.book.articleId);
      if (article.data.code === 200) {
        this.article = article.data.data;
      } else {
        this.article = "";
      }
      console.log(article.data.message);
      console.log(article)
      console.log(this.article);
    },
    //请求目录
    async getDirectory() {
      let res = await directoryApi.getDirectory(this.bookId);
      if (res.data.data) {
        this.directory = res.data.data;
      } else {
        this.directory = "";
      }
    },
    //获取书和作者信息
    async getBookAndAuthor() {
      let book = await bookApi.getBookById(this.bookId);
      let author;
      book = book.data;
      if (book.code === 200) {
        this.book = book.data;
        author = await authorApi.getAuthorById(this.book.authorId);
        author = author.data;
        if (author.code === 200) {
          this.author = author.data;
        }
      }
      console.log(author.message)
      console.log(book.message)
    },
    //根据bookId和userId查询收藏表，返回state判断该用户是否收藏
    async isState() {
      let isCollect = await bookCollectApi.selectBookCollect({
        userId: JSON.parse(window.sessionStorage.getItem("userInfo")).id,
        bookId: this.book.id
      })
      isCollect = isCollect.data;
      if (isCollect.data != null && isCollect.data.state) {
        this.state = isCollect.data.state;
      } else {
        this.state = false;
      }
    },
    //获取评论列表
    async getCommentList() {
      //根据书的id请求评论表
      let commentList = await bookDetailApi.getComment(this.book.id);
      this.commentList = commentList.data.data;
      this.commentList.forEach(item => {
        this.$set(item, 'showChildComment', 'false');
      });
      await this.addColorFlag();
      await this.addNickname();
      await this.addReviewerName();
      if (this.commentList.length !== 0) {
        console.log(this.commentList);
      } else {
        this.commentList = "";
        console.log(this.commentList + "暂无评论");
      }
    },
    //取消
    clear() {
      this.dialogVisible = false;
      this.ruleForm.comment = "";
      this.ruleForm.rate = "";
    },

    async addColorFlag() {
      for (const item of this.commentList) {
        let colorFlag = await supportApi.select({userId: this.userId, supportCommentId: item.id})
        colorFlag = colorFlag.data
        if (colorFlag.code === 200) {
          this.$set(item, "supportColor", 'red')
        } else {
          this.$set(item, "supportColor", 'black')
        }
        for (const childItem of item.childCommentList) {
          colorFlag = await supportApi.select({userId: this.userId, supportCommentId: childItem.id})
          colorFlag = colorFlag.data
          if (colorFlag.code === 200) {
            this.$set(childItem, "supportColor", 'red')
          } else {
            this.$set(childItem, "supportColor", 'black')
          }
        }
      }
    },
    async addNickname() {
      for (const item of this.commentList) {
        let nickname;
        nickname = await userApi.selectNickname(item.userId)
        nickname = nickname.data
        if (nickname.code === 200) {
          nickname = nickname.data
          this.$set(item, "nickname", nickname)
        }
        for (const childItem of item.childCommentList) {
          nickname = await userApi.selectNickname(childItem.userId)
          nickname = nickname.data
          if (nickname.code === 200) {
            nickname = nickname.data
            this.$set(childItem, "nickname", nickname)
          }
        }
      }
    },
    async addReviewerName() {
      for (const item of this.commentList) {
        let ReviewerName;
        for (const childItem of item.childCommentList) {
          let comment = await bookDetailApi.getCommentById(childItem.commentId);
          comment = comment.data;
          let userId;
          if (comment.code === 200) {
            comment = comment.data
            userId = comment.userId;
          }
          ReviewerName = await userApi.selectNickname(userId)
          ReviewerName = ReviewerName.data
          if (ReviewerName.code === 200) {
            ReviewerName = ReviewerName.data
            this.$set(childItem, "reviewerName", ReviewerName)
          }
        }
      }
    },
    //提交评论
    async submitForm(formName) {
      console.log(formName)
      this.$refs[formName].validate(async (valid) => {
        console.log(valid);
        if (valid) {
          let dt = new Date();
          let year = dt.getFullYear();
          let month = dt.getMonth() + 1;
          let day = dt.getDate();
          let time = year + "-" + month + "-" + day;
          console.log(time)
          let res = await bookDetailApi.submitComment({
            userId: this.userId,
            content: this.ruleForm2.checkComment,
            bookId: this.book.id,
            commentId: this.commentId,
            publish_time: time,
            rate: 0,
          });
          console.log(res.data)
          this.drawer = false;
          this.$message({
            message: '已评论',
            type: 'success'
          });
          await this.getCommentList();
        } else {
          console.log('error submit!!');
        }
      });
    },
    // 提交评论和评分
    async submit(formName) {
      var dt = new Date();
      let year = dt.getFullYear();
      let month = dt.getMonth() + 1;
      let day = dt.getDate();
      let time = year + "-" + month + "-" + day;
      console.log(time);

      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: ''
          }).then(async () => {
            let res = await bookDetailApi.submitComment({
              userId: this.userId,
              content: this.ruleForm.comment,
              rate: this.ruleForm.rate,
              bookId: this.book.id,
              publish_time: time
            });
            this.$router.go(0);
            this.$message({
              type: 'success',
              message: '提交成功!'
            });
            this.dialogVisible = false;
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消'
            });
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

    resetForm(formName) {
      console.log(this.ruleForm2)
      this.$refs[formName].resetFields();
    },
    open(buttonNumber) {
      //打开提交框
      this.dialogVisible = true;
      if (buttonNumber === 1) {
        this.title = "想读";
      } else if (buttonNumber === 2) {
        this.title = "在读";
      } else if (buttonNumber === 3) {
        this.title = "读完";
      }

    },
    // 打开读者推文
    openArticle() {
      this.$router.push({
        path: '/book/article',
        query: {
          bookId: this.bookId,
          articleId: this.article.id,
        }
      })
    },
    //打开书籍阅读
    openBook() {
      this.$router.push({
        path: '/bookContent',
        query: {
          bookId: this.bookId,
        }
      })
    },
    //加入书架
    async addBookCollect() {
      console.log(this.book);
      let res = await bookCollectApi.addBookCollection({
        userId: this.userId,
        bookName: this.book.name,
        bookId: this.book.id,
        imageUrl: this.book.image,
        author: this.author.name,
        detail: this.book.briefIntroduction,
        country: this.author.country,
        state: 1
      });
      console.log(res);
      console.log(res.data);
      if (res.data.success) {
        this.state = true;
        await this.isState();
      }
      this.$message({
        message: '已加入书架',
        type: 'success'
      });
    },
    //移出书架
    async deleteBookCollect() {
      let res = await bookCollectApi.deleteBookCollection({
        userId: JSON.parse(window.sessionStorage.getItem("userInfo")).id,
        bookName: this.book.name,
        bookId: this.book.id,
        imageUrl: this.book.image,
        author: this.author.name,
        detail: this.book.briefIntroduction,
        country: this.author.country,
        state: 1
      });
      console.log(res);
      console.log(res.data);
      if (res.data.success) {
        this.state = true;
        await this.isState();
      }
      this.$message({
        message: '已从书架移除',
        type: 'success'
      });
    },
    //点赞
    async support(comment) {
      let res1;
      if (comment.supportColor === "black") {
        res1 = await supportApi.insertSupport({userId: this.userId, supportCommentId: comment.id});
        if (res1.data.code === 200) {
          comment.supportNum += 1;
          comment.supportColor = "red"
        }
      } else {
        res1 = await supportApi.deleteSupport({userId: this.userId, supportCommentId: comment.id});
        if (res1.data.code === 200) {
          comment.supportNum -= 1;
          comment.supportColor = "black"
        }
      }
      res1 = res1.data;
      console.log(res1);
      let res2;
      if (res1.code === 200) {
        res2 = await bookDetailApi.updateComment(comment);
      }
      res2 = res2.data
      console.log(res2)

    },
    //获得回复对象
    async getReviewer(commentId) {
      let comment = await bookDetailApi.getCommentById(commentId);
      comment = comment.data;
      console.log(comment)
      let user;
      if (comment.code === 200) {
        comment = comment.data
        user = await userApi.getReplyer(comment.userId);
        user = user.data;
      }
      if (user.code === 200) {
        user = user.data;
      }
      console.log(user)
      this.reviewer = user.nickname;
    },
    //显示评论框
    showInput(comment) {
      this.drawer = true
      let reviewerName = comment.nickname;
      this.reviewerTitle = "回复 " + reviewerName;
      this.commentId = comment.id
    },
    //返回上一级
    goBack() {
      this.$router.back();
    },
  }
}
</script>

<style scoped>
.el-aside {
  background-color: #42b983;
  width: 30%;
}

.el-main {
  /*background-color: #1890ff;*/
  width: 70%;
}



.textOverflow {
  -webkit-line-clamp: 3;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}

.title {
  color: white;
  padding: 10px;
  margin: 0px;
}
</style>