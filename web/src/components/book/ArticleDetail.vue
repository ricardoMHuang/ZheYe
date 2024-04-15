<template>
  <div style="margin: auto;padding: 100px; background-color: white">
    <el-page-header @back="goBack" content="读者推文" style="padding: 20px"></el-page-header>
    <h2>读者推文</h2>
    <el-card :body-style="{padding:0}" style="padding: 10px;position: relative">
      <el-image :src="book.image" :lazy="true" style="width: 40px;border-radius: 8px;">
      </el-image>
      <div style="display: inline-block; position: relative; left: 10px;bottom:10px">
        <div>{{ book.name }}</div>
        <div style="color: #999999"> {{ author.name }}</div>
      </div>
      <div style="display: inline-block;position:absolute;right: 15px;top: 17px">
        <el-button type="primary" @click="openBook()">阅读</el-button>
        <el-button type="primary" @click="addBookCollect()" v-show="!state">加入书架</el-button>
        <el-button type="success" @click="deleteBookCollect()" v-show="state">已加入</el-button>
      </div>
    </el-card>
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
    <p style="line-height: 5ch;">
      &nbsp;&nbsp;{{ article.content }}
    </p>

    <div style="float: right;margin-top: 50px;">
      <el-button plain>赞</el-button>
      <el-button plain>回复</el-button>
      <el-button plain>分享</el-button>
    </div>
    <!--    回复-->
    <div style="margin-top:150px">
      <h3 style="">回复</h3>

      <div style="margin-top: 40px">
        <el-avatar size="large" :src="article.userImage"></el-avatar>
        <div style="display: inline-block;padding: 10px">
          <div>{{ article.userName }}
            <el-tag type="success" size="mini">作者</el-tag>
          </div>
          <div style="color: #999999"> {{ article.publicTime }} 回复</div>
        </div>
      </div>
      <p style="width: 80%;margin-left: 50px">
        好家伙，突然想来上一句，键盘给你你来😂
      </p>
      <div style="float: right;margin:10px;">
        <el-button plain>赞</el-button>
        <el-button plain>回复</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import bookCollectApi from "../../../api/bookCollect";
import bookApi from "../../../api/book";
import authorApi from "../../../api/author";
import articleApi from "../../../api/article";

export default {

  name: "article",
  data() {
    return {
      article: {},
      book: {},
      author: {},
      replyList: [],
      state: "",
      bookId: JSON.parse(this.$route.query.bookId),
      articleId: JSON.parse(this.$route.query.articleId),
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    async init() {
      await this.getBookAndAuthor();
      await this.getArticle();
      await this.isState();
    },
    //获取文章信息
    async getArticle() {
      let article = await articleApi.getArticleInt(this.articleId);
      article = article.data;
      if (article.code === 200) {
        this.article = article.data;
      }
      console.log(article.message);

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
        bookId: this.bookId
      })
      isCollect = isCollect.data;
      if (isCollect.data != null && isCollect.data.state) {
        this.state = isCollect.data.state;
      } else {
        this.state = false;
      }
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
    //加入书架
    async addBookCollect() {
      console.log(this.book);
      let res = await bookCollectApi.addBookCollection({
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
        message: '已加入书架',
        type: 'success'
      });
    },
    openBook() {
      this.$router.push({
        path: '/bookContent',
        query: {
          bookId: this.bookId,
        }
      })
    },
    //返回上一级
    goBack() {
      this.$router.back();
    }
  },
}
</script>

<style scoped>

</style>