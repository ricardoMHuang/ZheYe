<template>
  <div>
    <div>
      <el-carousel :interval="5000" arrow="always" type="card">
        <el-carousel-item v-for="item in imgInfo" :key="item.id">
          <img style="object-fit: scale-down; width: 100%;height: 100%;" :src="item.idviews"/>
        </el-carousel-item>
      </el-carousel>
    </div>

    <div style="margin: auto;width: 100%">

      <!--    每周精品-->
      <h2>每周精品</h2>
      <el-row style="margin-bottom: 80px;">
        <el-col v-for="(item,index) in mostConcernBookList" :span="8" :offset="index%2 > 0 ? 3 : 0"
                style="margin: 15px 0;">
          <el-card :body-style="{padding: 0}" style="width: 120px;height:170px; float: left;">
            <img :src="item.image" class="image" @click="openBook(item.id)">
          </el-card>
          <div style="float: left; padding: 10px; margin-left: 10px;">
            <h3 style="">{{ item.bookName }}</h3>
            <h4 style="color: #999999">{{ item.author }}</h4>
            <el-rate
                v-model="item.rate"
                disabled
                show-score
                text-color="#ff9900"
                score-template="{value}">
            </el-rate>
          </div>
        </el-col>
      </el-row>

      <h2>新书速递</h2>
      <div>
          <span style="float: right;padding-top: 20px">
        </span>
        <el-tabs type="card">
          <el-tab-pane :label="item.type" v-for="(item,i) in newBookList">
            <el-row>
              <el-col :span="3" v-for="(newBook,j) in item.detail" v-if="newBook" :offset="2"
                      style="padding-bottom: 25px">
                <el-card :body-style="{ padding: '0px'}" style="width: 120px;height:170px;">
                  <img :src="newBook.url" class="image" @click="openBook(newBook.id)">
                </el-card>
                <div style="padding: 10px;">
                  <strong>{{ newBook.bookName }}</strong>
                  <div style="margin-top: 10px">[{{ newBook.country }}]{{ newBook.author }}</div>
                </div>
              </el-col>
            </el-row>
          </el-tab-pane>
        </el-tabs>
      </div>

      <!--    读者推文-->
      <h2>读者推文</h2>
      <div style="background-color: #fcfbf9;padding: 20px">
        <el-row>
          <el-col v-for="(article,i) in articleList" :span="11" :offset="i%2 > 0 ? 1 : 0" style="margin-top: 100px; ">
            <div style="position:relative;">
              <el-card :body-style="{ padding: '0px'}"
                       style=" width: 130px;height:180px; position:absolute;top:-70px;left: 20px;z-index: 1">
                <el-image :src="article.bookImage" class="image" @click="openArticle(article.id,article)"></el-image>
              </el-card>
              <el-card style="">
                <h3 style="color: #42b983; position: relative;left: 160px;width: 320px">{{ article.title }}</h3>
                <h4 style="color: #D3DCE6; position: relative;left: 170px;">{{ article.userName }}</h4>
                <p class="textOverflow" style="-webkit-line-clamp: 3; font-size: 15px; cursor: pointer"
                   @click="openArticle(article.id,article)">
                  &nbsp;&nbsp;{{ article.content }}</p>
              </el-card>
            </div>


          </el-col>

        </el-row>
      </div>


      <!--    分割线-->
      <el-divider></el-divider>


      <!--      分类-->
      <div style="margin-bottom: 30px">
        <h2>分类</h2>
        <el-row>
          <el-col v-for="(o,index) in bookTypeList" :span="6">
            <el-link v-if="index!==bookTypeList.length-1" :underline="false" @click="openCategory(o.type)">
              <h3 style="margin-bottom: 0">{{ o.type }}·{{ o.totalQuantity }}本 </h3>
              <el-divider></el-divider>
            </el-link>
            <el-link v-if="index===bookTypeList.length-1" @click="openCategory"><h3>查看全部 >></h3></el-link>
          </el-col>
        </el-row>
      </div>

      <!--        分割线-->
      <el-divider><i class="el-icon-notebook-2"></i></el-divider>

      <!--    精选书评-->
      <h2>精选书评</h2>
      <div style="background-color: #fcfbf9;padding: 20px">
        <el-row style="margin-bottom: 15px;">
          <el-col :span="12" v-for="(o,index) in reviewList">
            <el-card :body-style="{padding:0}" style="float: left;width: 130px">
              <img :src="o.image" class="image" @click="openArticle">
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
              <p class="textOverflow" style="cursor: pointer" @click="openArticle">&nbsp;&nbsp;{{ o.article }}</p>
            </div>
          </el-col>
        </el-row>
      </div>

      <h2>热门标签</h2>
      <div>
        <el-row>
          <el-col v-for="(o,index) in typeList" :key="o" :span="3" :offset="1 " style="padding-bottom: 10px">
            <el-button size="mini" plain @click="openCategory">{{ o.type }}</el-button>
          </el-col>
        </el-row>
      </div>

      <!--    排行榜-->
      <h2>排行</h2>
      <el-row style="margin-bottom: 20px;margin-left: 40px">
        <!--      畅销榜-->
        <el-col :span="7">
          <el-card style="background-color:#fcfbf9;" :body-style="{padding:0}">
            <h3 style="padding-left: 20px">畅销榜</h3>
            <el-row>
              <el-col v-for="(o,index) in sellingList" :key="o" style="margin-top: 10px;">
                <el-card shadow="hover" :body-style="{padding: '12px'}"
                         style="border-style: hidden;background-color: #fcfbf9">
                  <el-card :body-style="{padding: 0}" style="width: 90px;float: left;">
                    <img :src="o.image" class="image" @click="openBook">
                  </el-card>
                  <div style="float: left; margin-left: 10px; line-height: 45px">
                    <strong>{{ o.bookName }}</strong>
                    <el-rate
                        v-model="value"
                        disabled
                        show-score
                        text-color="#ff9900"
                        score-template="{value}">
                    </el-rate>
                    {{ o.author }}
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
        <!--      新人榜-->
        <el-col :span="7" offset="1">
          <el-card style="background-color:#fcfbf9;" :body-style="{padding:0}">
            <h3 style="padding-left: 20px">新人榜</h3>
            <el-row>
              <el-col v-for="(o,index) in sellingList" :key="o" style="margin-top: 10px;">
                <el-card shadow="hover" :body-style="{padding: '12px'}"
                         style="border-style: hidden;background-color: #fcfbf9">
                  <el-card :body-style="{padding: 0}" style="width: 90px;float: left;">
                    <img :src="o.image" class="image" @click="openBook(o.id)">
                  </el-card>
                  <div style="float: left; margin-left: 10px; line-height: 45px">
                    <strong>{{ o.bookName }}</strong>
                    <el-rate
                        v-model="value"
                        disabled
                        show-score
                        text-color="#ff9900"
                        score-template="{value}">
                    </el-rate>
                    {{ o.author }}
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
        <!--      连载榜-->
        <el-col :span="7" offset="1">
          <el-card style="background-color:#fcfbf9;" :body-style="{padding:0}">
            <h3 style="padding-left: 20px">连载榜</h3>
            <el-row>
              <el-col v-for="(o,index) in sellingList" :key="o" style="margin-top: 10px;">
                <el-card shadow="hover" :body-style="{padding: '12px'}"
                         style="border-style: hidden;background-color: #fcfbf9">
                  <el-card :body-style="{padding: 0}" style="width: 90px;float: left;">
                    <img :src="o.image" class="image" @click="openBook">
                  </el-card>
                  <div style="float: left; margin-left: 10px; line-height: 45px">
                    <strong>{{ o.bookName }}</strong>
                    <el-rate
                        v-model="value"
                        disabled
                        show-score
                        text-color="#ff9900"
                        score-template="{value}">
                    </el-rate>
                    {{ o.author }}
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>


<script>
import request from "@/utils/request";
import bookApi from "@/../api/book";
import bookTypeApi from "@/../api/booktype";
import authorApi from "../../../api/author"
import articleApi from "../../../api/article"

export default {
  name: "Read",
  data() {
    return {
      tabPosition: 'left',
      isFixed: false,
      offsetTop: 0,
      value: 3.7,
      books: [],
      authors: [],
      imgInfo: [{id: 1, idviews: require("@/assets/photo-1.webp")}, {
        id: 2,
        idviews: require("@/assets/photo-1.webp")
      }, {id: 3, idviews: require("@/assets/photo-1.webp")}],
      newBookList: [
        {
          type: "科幻",
          detail: [{}, {}, {}, {}, {}, {}, {}, {}, {}],
        },
        {
          type: "武侠",
          detail: [{}, {}, {}, {}, {}, {}, {}, {}],
        },
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
      sellingList: [
        {
          image: "https://img2.baidu.com/it/u=3355660545,3331057139&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=645",
          bookName: "平凡的世界",
          author: "路遥",
        },
        {
          image: "https://img2.baidu.com/it/u=3355660545,3331057139&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=645",
          bookName: "平凡的世界",
          author: "路遥",
        },
        {
          image: "https://img2.baidu.com/it/u=3355660545,3331057139&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=645",
          bookName: "平凡的世界",
          author: "路遥",
        },
        {
          image: "https://img2.baidu.com/it/u=3355660545,3331057139&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=645",
          bookName: "平凡的世界",
          author: "路遥",
        },
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

      //newBookList,新书速递
      for (let i = 0; i < this.newBookList.length; i++) {
        for (let j = 0; j < books.length; j++) {
          if (this.newBookList[i].type === books[j].bookType) {
            this.newBookList[i].detail[j].url = books[j].image;
            this.newBookList[i].detail[j].id = books[j].id;
            this.newBookList[i].detail[j].bookName = books[j].name;
            this.newBookList[i].detail[j].country = author[j].country;
            this.newBookList[i].detail[j].author = author[j].name;
          } else {
            this.newBookList[i].detail[j] = null;
          }
        }
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
      let book, author;
      for (let i = 0; i < this.books.length; i++) {
        if (this.books[i].id === val) {
          book = this.books[i];
          author = this.authors[i];
        }

      }
      // 变成 /user?id=1
      this.$router.push({
        path: '/book/bookDetail',
        query: {
          id: val,
          book: JSON.stringify(book),
          author: JSON.stringify(author)
        }
      })
    },
    openCategory(val) {
      let type = new Array(this.typeList.length);
      for (let i = 0; i < this.bookTypeList.length; i++) type[i] = this.bookTypeList[i].type;
      this.$router.push({
        path: '/book/category',
        query: {
          id: val,
          typeList: JSON.stringify(type)
        }
      })
    },
    openArticle(articleId, article) {
      let book, author;
      for (let i = 0; i < this.books.length; i++) {
        if (this.books[i].articleId === articleId) {
          book = this.books[i];
          author = this.authors[i];
        }

      }
      this.$router.push({
        path: '/book/article',
        query: {
          id: articleId,
          article: JSON.stringify(article),
          book: JSON.stringify(book),
          author: JSON.stringify(author),
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
  created() {

  },
  mounted() {
    this.init();
    window.addEventListener('scroll', this.initHeight);
    this.$nextTick(() => {
      this.offsetTop = document.querySelector('#boxFixed').offsetTop;
    })
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
</style>