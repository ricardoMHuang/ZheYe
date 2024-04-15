<template>
  <div>
    <div class="bookMain-background"></div>
    <div class="bookMain">

      <!--    轮播图-->
      <div>
        <el-carousel :interval="5000" arrow="always" type="card">
          <el-carousel-item v-for="item in imgInfo" :key="item.id">
            <img style="object-fit: scale-down; width: 100%;height: 100%;" :src="item.idviews"/>
          </el-carousel-item>
        </el-carousel>
      </div>

      <div style="margin: auto;width: 100%">


        <h2>每周精品</h2>
        <every-week :mostConcernBookList="mostConcernBookList"></every-week>

        <h2>读者推文</h2>
        <reader-article :articleList="articleList"></reader-article>

        <!--    分割线-->
        <el-divider></el-divider>


        <div style="margin-bottom: 30px">
          <h2>分类</h2>
          <classify :bookTypeList="bookTypeList"></classify>

        </div>

        <!--        分割线-->
        <el-divider><i class="el-icon-notebook-2"></i></el-divider>

        <h2>精选书评</h2>
        <book-comment :reviewList="reviewList"></book-comment>

        <h2>排行</h2>
        <rank></rank>
      </div>
    </div>
  </div>
</template>


<script>
import request from "@/utils/request";
import bookApi from "@/../api/book";
import bookTypeApi from "@/../api/booktype";
import authorApi from "../../../api/author"
import articleApi from "../../../api/article"
import Rank from "@/components/book/bookComponent/RecommentRank";
import ReaderArticle from "@/components/book/bookComponent/ReaderArticle";
import Classify from "@/components/book/Classify";
import BookComment from "@/components/book/bookComponent/BookComment";
import everyWeek from "@/components/book/bookComponent/EveryWeek";

export default {
  name: "Read",
  components: {Rank, ReaderArticle, Classify, BookComment, everyWeek},
  data() {
    return {
      tabPosition: 'left',
      isFixed: false,
      offsetTop: 0,
      value: 3.7,
      books: [],
      authors: [],
      imgInfo: [
        {id: 1, idviews: require("@/assets/photo-1.webp")},
        {id: 2, idviews: require("@/assets/photo-1.webp")},
        {id: 3, idviews: require("@/assets/photo-1.webp")}
      ],
      articleList: [],
      mostConcernBookList: [{}, {}, {}, {}, {}, {}],
      reviewList: [
        {
          image: "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fnimg.ws.126.net%2F%3Furl%3Dhttp%253A%252F%252Fdingyue.ws.126.net%252F2021%252F0926%252F51a52fe2j00r00wd2002dc000hs00jbc.jpg%26thumbnail%3D660x2147483647%26quality%3D80%26type%3Djpg&refer=http%3A%2F%2Fnimg.ws.126.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672665035&t=3afaff8f206bd19cdb18c2fa6ffdc5fc",
          title: "始于画笔，终于世界",
          userName: "阿联酋",
          bookName: "浮世绘女儿",
          rate: 3.5,
          article: "江户时代（1603—1867）是日本封建制度的最后一个时代。浮世绘正于此时兴起，是一种典型的花街柳巷艺术，主要描绘人们日常生活、风景和演剧。浮世绘常被认为专指彩色印刷的木版画（日语称为锦绘），但事实上也有",
        },
        {
          image: "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fnimg.ws.126.net%2F%3Furl%3Dhttp%253A%252F%252Fdingyue.ws.126.net%252F2021%252F0926%252F51a52fe2j00r00wd2002dc000hs00jbc.jpg%26thumbnail%3D660x2147483647%26quality%3D80%26type%3Djpg&refer=http%3A%2F%2Fnimg.ws.126.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672665035&t=3afaff8f206bd19cdb18c2fa6ffdc5fc",
          title: "始于画笔，终于世界",
          userName: "阿联酋",
          bookName: "浮世绘女儿",
          rate: 3.5,
          article: "江户时代（1603—1867）是日本封建制度的最后一个时代。浮世绘正于此时兴起，是一种典型的花街柳巷艺术，主要描绘人们日常生活、风景和演剧" +
              "。浮世绘常被认为专指彩色印刷的木版画（日语称为锦绘），但事实上也有色印刷的木版画（日语称为锦绘），但事实上也有色印刷的木版画（日语称为锦绘），但事实上也有色印刷的木版画（日语称为锦绘），但事实上也有",
        },
        {
          image: "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fnimg.ws.126.net%2F%3Furl%3Dhttp%253A%252F%252Fdingyue.ws.126.net%252F2021%252F0926%252F51a52fe2j00r00wd2002dc000hs00jbc.jpg%26thumbnail%3D660x2147483647%26quality%3D80%26type%3Djpg&refer=http%3A%2F%2Fnimg.ws.126.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672665035&t=3afaff8f206bd19cdb18c2fa6ffdc5fc",
          title: "始于画笔，终于世界",
          userName: "阿联酋",
          bookName: "浮世绘女儿",
          rate: 3.5,
          article: "江户时代（1603—1867）是日本封建制度的最后一个时代。浮世绘正于此时兴起，是一种典型的花街柳巷艺术，主要描绘人们日常生活、风景和演剧。浮世绘常被认为专指彩色印刷的木版画（日语称为锦绘），但事实上也有",
        },
      ],
      typeList: [
        {type: "科幻"},
        {type: "武侠"},
        {type: "文学"},
        {type: "小说"},
      ],
      bookTypeList: [
        {type: "小说"},
        {type: "随笔"},
        {type: "文学"},
        {type: "散文"},
        {type: "诗歌"},
        {type: "科幻"},
        {type: "财经"},
        {type: "推理"},
        {type: "儿童"},
        {type: "外国"},
        {type: "名著"},
      ],
    }
  },
  mounted() {
    this.init();
    window.addEventListener('scroll', this.initHeight);
    this.$nextTick(() => {
      this.offsetTop = document.querySelector('#boxFixed').offsetTop;
    })
  },
  methods: {

    /*页面初始化*/
    async init() {
      let res = await bookApi.bookItem();
      res = res.data;
      let books = res.data;
      this.books = books;
      console.log(books);//书表
      let authorId = new Array(books.length);
      for (let i = 0; i < books.length; i++)
        authorId[i] = books[i].authorId;
      // console.log(authorId);
      res = await authorApi.bookAuthor(authorId);
      let author = res.data.data;
      this.authors = author;
      console.log(author);//作者表
      // mostConcernBookList,最受关注图书
      for (let i = 0; i < books.length; i++) {
        if (books[i].authorId === author[i].id && books[i].score >= 4) {

          this.mostConcernBookList[i].bookName = books[i].name;
          this.mostConcernBookList[i].id = books[i].id;
          this.mostConcernBookList[i].rate = books[i].score;
          this.mostConcernBookList[i].author = author[i].name;
          this.mostConcernBookList[i].image = books[i].image;
        }
        if (i === 5)
          break;
        // console.log(i);
      }
      /*article列表获取*/
      res = await articleApi.getArticleItem();
      this.articleList = res.data.data;
      console.log(this.articleList);

      /*bookType获取*/
      res = await bookTypeApi.getBookType();
      this.bookTypeList = res.data.data;
      console.log(this.bookTypeList);
    },

    initHeight() {
      const scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
      this.isFixed = scrollTop > this.offsetTop;
    },
    //
    openBook(val) {
      // 变成 /user?id=1
      this.$router.push({
        path: '/book/bookDetail',
        query: {
          bookId: val,
        }
      })
    },
    openArticle(articleId, bookId) {
      this.$router.push({
        path: '/book/article',
        query: {
          articleId: articleId,
          bookId: bookId,
        }
      })
    },
    Find() {
      let this_ = this;
      this.$http.get('http://localhost:8882/book', {params: {"userName": this.userName}}).then(function (res) {
        if (res.data[0] == null) {
          console.log(res.data);
          this.$message.error("该用户不存在");
        } else {
          console.log(res.data);
          this_.listName = res.data;
        }
      }, function () {
        document.write('查找失败');
        console.log('查找失败');
      })
    },
//post请求
    sendRequest() {
      request.get('http://localhost:8882/bookType/list', {}).then(res => {
        // console.log(res.data);
        if (res.code === '200') {
          this.bookTypeList = res.data.list;
          this.total = res.data.total;
        }
      })
    }
  },

  destroyed() {
    window.removeEventListener('scroll', this.handleScroll)
  },

}
</script>

<style scoped>


.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

.image {
  width: 100%;
  display: block;
  cursor: pointer;
}

.is_fixed {
  position: fixed;
  top: 70px;
  width: 300px;
}

.textOverflow {
  -webkit-line-clamp: 3;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}

.el-main {
  margin-right: 50px;
}

.el-aside {
  margin-left: 20px;
}

h2 {
  margin-top: 60px;
}

.bookMain {
  padding: 0 20px;
  background-color: white;
}
</style>