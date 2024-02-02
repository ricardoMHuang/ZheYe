<template>
  <div style="width: 70%;margin: auto">
    <el-page-header @back="goBack" content="帖子" style="padding: 20px"></el-page-header>
    <h2>帖子</h2>
    <el-card :body-style="{padding:0}" style="padding: 10px;position: relative">
      <el-image :src="book.image" :lazy="true" style="width: 40px;border-radius: 8px;">
      </el-image>
      <div style="display: inline-block; position: relative; left: 10px;bottom:10px">
        <div>{{ book.name }}</div>
        <div style="color: #999999"> {{ author.name }}</div>
      </div>
      <div style="display: inline-block;position:absolute;right: 15px;top: 17px">
        <el-button type="success" @click="addBook()">加入小组</el-button>
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
          <div style="color: #999999"> {{ article.publicTime }} 回复</div>
        </div>
      </div>
      <p style="width: 80%;margin-left: 50px">
        好家伙😂
      </p>
      <div style="float: right;margin:10px;">
        <el-button plain>赞</el-button>
        <el-button plain>回复</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "article",
  data() {
    return {
      article: {},
      book: {},
      author: {},
      replyList: [],
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    async init() {
      this.article = JSON.parse(this.$route.query.article);
      this.book = JSON.parse(this.$route.query.book);
      this.author = JSON.parse(this.$route.query.author);
      console.log(this.article);
      console.log(this.book);
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