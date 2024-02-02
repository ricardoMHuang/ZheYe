<template>
  <div style="width: 80%;margin: auto">

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
    <div class="bookDetail">
      <h2 style="text-align: center;margin-top: 0;padding-top: 50px;">{{ book.name }}</h2>
      <div style="text-align: center">
        <el-avatar size="large" :src="author.image"></el-avatar>
        <h5 style="margin: 0">{{ author.name }}</h5>
        <h5>{{ book.publish }}</h5>
        <h5>{{ book.page }}页</h5>
        <div style="height: 50px">
          <el-button type="primary" @click="addBook()">加入书架</el-button>
        </div>
        <el-tag size="mini">爱情</el-tag>&nbsp;
        <el-tag size="mini">科幻</el-tag>
      </div>
      <!--    评分-->
      <div style="margin: auto; padding: 10px;">
        <el-rate v-model="value"
                 disabled
                 show-score
                 text-color="#ff9900"
                 score-template="{value}" style="text-align: center">
        </el-rate>
      </div>
    </div>

    <!--    <el-divider></el-divider>&lt;!&ndash; 分割线&ndash;&gt;-->

    <!--图书状态和评价-->
    <div style="text-align:center;">
      <el-button @click="open(1)">想读</el-button>
      <el-button @click="open(2)">在读</el-button>
      <el-button @click="open(3)">读过</el-button>
    </div>
    <!--      内容简介-->
    <div>
      <h2 style="background-color: #fcfbf9;">内容简介</h2>
      <p style="width: 90%;margin: auto;line-height: 3ch">{{ book.briefIntroduction }}</p>
    </div>
    <!--      作者简介-->
    <div>
      <h2 style="background-color: #fcfbf9">作者简介</h2>
      <p style="width: 90%;margin: auto;line-height: 3ch">
        &nbsp;&nbsp;{{ author.briefIntroduction }}
      </p>
    </div>
    <!--      目录-->
    <div>
      <h2 style="background-color: #fcfbf9">目录</h2>
      <el-collapse v-model="activeName" accordion style="width: 100%;margin: auto">
        <el-collapse-item :title="'第一章：'+directory.chapterOne" name="1">
          <div>与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；</div>
          <div>在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。</div>
        </el-collapse-item>
        <el-collapse-item :title="'第二章：'+directory.chapterTwo" name="2">
          <div>控制反馈：通过界面样式和交互动效让用户可以清晰的感知自己的操作；</div>
          <div>页面反馈：操作后，通过页面元素的变化清晰地展现当前状态。</div>
        </el-collapse-item>
        <el-collapse-item :title="'第三章：'+directory.chapterThree" name="3">
          <div>简化流程：设计简洁直观的操作流程；</div>
          <div>清晰明确：语言表达清晰且表意明确，让用户快速理解进而作出决策；</div>
          <div>帮助用户识别：界面简单直白，让用户快速识别而非回忆，减少用户记忆负担。</div>
        </el-collapse-item>
        <el-collapse-item :title="'第四章：'+directory.chapterFour" name="4">
          <div>用户决策：根据场景可给予用户操作建议或安全提示，但不能代替用户进行决策；</div>
          <div>结果可控：用户可以自由的进行操作，包括撤销、回退和终止当前操作等。</div>
        </el-collapse-item>
        <el-collapse-item :title="'第五章：'+directory.chapterFive" name="5">
          <div>用户决策：根据场景可给予用户操作建议或安全提示，但不能代替用户进行决策；</div>
          <div>结果可控：用户可以自由的进行操作，包括撤销、回退和终止当前操作等。</div>
        </el-collapse-item>
        <el-collapse-item :title="'第六章：'+directory.chapterSix" name="6">
          <div>用户决策：根据场景可给予用户操作建议或安全提示，但不能代替用户进行决策；</div>
          <div>结果可控：用户可以自由的进行操作，包括撤销、回退和终止当前操作等。</div>
        </el-collapse-item>
      </el-collapse>
    </div>

    <h2 style="background-color:  #fcfbf9">&nbsp;</h2>

    <!--      读者推文-->
    <div style="position:relative; margin-top: 100px">
      <h3>读者推文</h3>
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
      <el-button style="position:relative; left: 850px" plain @click="openArticle()">查看推文</el-button>
    </div>
    <!--      评论-->
    <h3>评论</h3>
    <div v-for="(comment,index) in commentList" v-if="index<5" style="position:relative;">
      <div style="margin-top: 40px">
        <el-avatar size="large" :src="users[index].avatar"></el-avatar>
        <div style="display: inline-block;padding: 10px">
          <div>{{ users[index].nickname }}
          </div>
          <div style="color: #999999"> {{ comment.publishTime }} 回复</div>
        </div>
      </div>
      <p style="margin: 10px 50px;height: 80px">
        {{ comment.content }}
      </p>

      <div style="position: relative; left: 83%; margin:10px; display: inline-block">
        <el-button>赞</el-button>
        <el-button>回复</el-button>
      </div>
      <el-divider></el-divider>
    </div>
    <div style="text-align: center;margin: 30px">
      <el-button type="primary">展开更多</el-button>
    </div>

  </div>

</template>

<script>
import directoryApi from "@/../api/directory";
import articleApi from "../../../api/article";
import bookDetailApi from "../../../api/bookDetail";
import userApi from "../../../api/user";
import bookApi from "../../../api/user";

export default {
  name: "newBook",
  data() {
    var validateRate = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请评分'));
      } else {
        callback();
      }
    };

    return {
      dialogVisible: false,
      dialogTwoVisible: false,
      title: '',
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
      id: this.$route.query.id,
      value: 4,
      author: {name: "作者名字", image: "https://img3.doubanio.com/icon/up263944680-3.jpg",},
      book: {},
      directory: {},
      article: {},
      commentList: [],
      users: [],
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    async init() {
      this.author = JSON.parse(this.$route.query.author);
      this.book = JSON.parse(this.$route.query.book);
      console.log(this.author);
      console.log(this.book);
      //请求目录
      let res = await directoryApi.getDirectory(this.$route.query.id);
      if (res.data.data) {
        this.directory = res.data.data;
      } else {
        this.directory = "";
      }
      console.log(this.directory);
      console.log(res.data.data);
      // 请求读者推文
      res = await articleApi.getArticleInt(this.book.articleId);
      if (res.data.data) {
        this.article = res.data.data;
      } else {
        this.article = "";
        console.log(this.article);
      }

      //根据书的id请求评论表
      res = await bookDetailApi.getComment(this.book.id);
      this.commentList = res.data.data;
      console.log(this.commentList);

      if (this.commentList) {
        for (let i = 0; i < this.commentList.length; i++) {
          let userResult = await userApi.getReplyer(this.commentList[i].userId);
          if (userResult.data.data) {
            this.users.push(userResult.data.data);
            console.log(this.users);
          } else {
            this.users = "";
            console.log(this.users);
          }
        }
      } else {
        this.commentList = "";
        console.log(this.commentList);
      }
    },
    clear() {
      this.dialogVisible = false;
      this.ruleForm.comment = "";
      this.ruleForm.rate = "";
    },
    async submit(formName) {
      var dt = new Date();
      let year = dt.getFullYear();
      let month = dt.getMonth() + 1;
      let day = dt.getDate();
      let time = year + "-" + month + "-" + day;
      console.log(time);

      // 提交评论和评分
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: ''
          }).then(async () => {
            let res = await bookDetailApi.submitComment({
              userId: JSON.parse(window.sessionStorage.getItem("userInfo")).id,
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
          book: JSON.stringify(this.book),
          article: JSON.stringify(this.article),
          author: JSON.stringify(this.author),
        }
      })
    },
    //加入书架
    async addBook() {
      let res = bookApi.addBookCollection({
        userId: JSON.parse(window.sessionStorage.getItem("userInfo")).id,
        book: this.book
      });
      console.log(res);
    },
    //返回上一级
    goBack() {
      this.$router.back();
    }
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

.bookDetail {
  background-image: linear-gradient(to top, rgba(241, 238, 220, 0), rgb(241, 238, 220
  ));

}

.textOverflow {
  -webkit-line-clamp: 3;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>