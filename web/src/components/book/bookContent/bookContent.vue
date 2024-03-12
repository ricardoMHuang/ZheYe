<template>
  <div :style="{backgroundColor:this.bgColor,color: fontColor}">
    <el-page-header @back="goBack" :content="this.book.name" style="padding: 20px ">
    </el-page-header>
    <el-divider></el-divider>

    <el-empty v-if="!emptyBookContent" description="该书还未上架！"></el-empty>
    <div v-else>
      <!--功能区-->
      <div id="container">
        <!--    目录-->
        <el-button type="success" icon="el-icon-s-fold" circle @click="drawerOne = true;"
                   style="margin: 10px"></el-button>
        <el-drawer
            :title="this.book.name"
            :visible.sync="drawerOne"
            :direction="direction">
          <el-link :underline="false" v-for="(item,index) in bookChapters" :style="{}"
                   style="margin:0 20px;display:block;" @click="openChapter(index)"
                   v-loading.fullscreen.lock="fullscreenLoading">
            {{ index }} {{ item }}
            <el-divider/>
          </el-link>
        </el-drawer>
        <!--      改变字体大 小-->
        <el-button type="success" icon="el-icon-circle-plus" circle
                   @click="showFontChangeInput = !showFontChangeInput;moveButton()"
                   style="margin: 10px" id="fontControl"></el-button>

        <transition name="el-zoom-in-center">
          <el-input v-show="showFontChangeInput" style="width: 90px;margin-right: 10px" type="number" v-model="fontSize"
                    min="10" max="30"
                    size="mini" placeholder="大小" prefix-icon="el-icon-circle-plus-outline"/> <!-- 输入框用于动态修改字体大小 -->
        </transition>
        <transition name="el-zoom-in-center">
          <el-input v-show="showFontChangeInput" style="width: 90px" type="number" v-model="lineHeight" min="1" max="10"
                    size="mini" placeholder="行间距"
                    prefix-icon="el-icon-s-operation"/> <!-- 输入框用于动态修改字体大小 -->
        </transition>

        <!--开关灯-->

        <el-button type="success" icon="el-icon-sunny" circle @click="lightControl()"
                   style="margin: 10px;" id="openLight"></el-button>
      </div>

      <div v-if="chapterContentSplit!=null">
        <!--章节内容-->
        <el-skeleton style="margin: 20px" :loading="loading" animated>
        </el-skeleton>
        <h1 style="margin: 25px;">{{ this.chapter }}</h1>
        <div v-for="item in paginatedArticles ">
          <p v-if="!loading" :style="{ fontSize: fontSize + 'px' ,lineHeight:lineHeight}"
             style="margin: 25px; ">
            {{ item }}</p>
        </div>

        <!--      分页-->
        <el-pagination
            background
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="pageSize"
            layout="prev, pager, next"
            :total="chapterContentSplit.length" style="margin-top: 20px; text-align: center">
        </el-pagination>
      </div>

    </div>
  </div>
</template>

<script>

import directoryApi from "../../../../api/directory";
import bookApi from "../../../../api/book";
import authorApi from "../../../../api/author";

export default {
  name: "bookConnect",
  data() {
    return {
      book: {},
      drawerOne: false,
      direction: 'rtl',
      bgColor: 'white',
      fontColor: 'black',
      directory: [],
      emptyBookContent: true,
      bookContent: "",//整本书
      bookChapters: {},//所有章节名
      loading: true,//骨架框
      chapterContent: "",//对应章节内容
      chapterContentSplit: [],
      chapter: "",
      fullscreenLoading: false,//控制加载
      fontSize: 20,//控制字体大小
      lineHeight: 2,//控制字体行高
      showFontChangeInput: false,//控制是否显示更改字体输入框
      pageSize: 2, // Number of articles per page
      currentPage: 1, // Current page number
      bookId: JSON.parse(this.$route.query.bookId),

    }
  },
  mounted() {
    this.init();
  },
  computed: {
    paginatedArticles() {
      const startIndex = (this.currentPage - 1) * this.pageSize;
      return this.chapterContentSplit.slice(startIndex, startIndex + this.pageSize);
    }
  },
  methods: {
    async init() {
      await this.getBook();
      await this.getBookChapter(this.book.id);
      await setTimeout(() => {
        this.getBookContent(this.book.id)
        console.log(this.bookContent)
      }, 500);
    },
    //获取书的基本信息
    async getBook() {
      let book = await bookApi.getBookById(this.bookId);
      book = book.data;
      if (book.code === 200) {
        this.book = book.data;
      }
      console.log(book.message)
    },
    //获取整本书
    async getBookContent(bookId) {
      let res = await directoryApi.getBookContent(bookId);
      // this.splitSegment(this.bookContent["chapter1Content"])
      if (res.data.data != null) {
        this.bookContent = res.data.data;
        this.chapterContent = this.bookContent["chapter1Content"];
        this.splitChapter(this.chapterContent);
        this.chapter = this.bookContent["chapter1"];
        this.emptyBookContent = true;
      } else {
        this.emptyBookContent = false;
        this.bookContent = "";
      }
      console.log(this.bookContent)
      this.loading = false
    },
    //获取所有章节名
    async getBookChapter(bookId) {
      let res = await directoryApi.getBookChapter(bookId);
      this.bookChapters = res.data.data;
      console.log(this.bookChapters)
    },
    //打开对应章节
    openChapter(index) {
      this.fullscreenLoading = true;
      setTimeout(() => {
        this.currentPage = 1
        this.fullscreenLoading = false;
        if (this.bookContent[index + "Content"].length > 0) {
          this.chapterContent = this.bookContent[index + "Content"]//章节内容
          this.chapter = this.bookContent[index];//章节名
          // this.splitSegment(this.chapterContent);
        } else {
          this.chapterContent = "数据库中暂无本章！"
        }
        this.splitChapter(this.chapterContent);
        this.loading = false
      }, 1000)

    },
    //分页
    splitChapter(chapterContent) {
      if (chapterContent === "数据库中暂无本章！") {
        this.chapter = "";
      }
      this.chapterContentSplit = [];
      let i, j;
      for (i = 0; i < chapterContent.length; i++) {
        if (i % 400 === 0 && i > 0) {
          this.chapterContentSplit.push(chapterContent.slice(i - 400, i));
          j = i;
        }
      }
      this.chapterContentSplit.push(chapterContent.slice(j, chapterContent.length - 1));
    },

//分段（未实现）
    splitSegment(chapterContent) {

      for (let i = 0; i < chapterContent.length; i++) {
        if (chapterContent.charCodeAt(i) === 13)
          console.log(chapterContent[i])
        if (chapterContent.charCodeAt(i) === 10)
          console.log(chapterContent[i])
        if (chapterContent[i] === 13)
          console.log("空格")
        if (i + 4 < chapterContent.length && chapterContent[i] === '。') {

        }
      }
      this.chapterContent = chapterContent;
    }
    ,
    handleCurrentChange(page) {
      this.currentPage = page;
    }
    ,
    goBack() {
      this.$router.back();
    }
    ,
    lightControl() {
      if (this.bgColor === "white" && this.fontColor === "black") {
        this.bgColor = 'black';
        this.fontColor = 'white';
      } else {
        this.bgColor = 'white';
        this.fontColor = 'black';
      }
    }
    ,
    moveButton() {
      let marginLeft = document.querySelector('#fontControl').offsetLeft;
      let fontControlButton = document.querySelector('#fontControl');
      let openLightButton = document.querySelector('#openLight');
      let containerWidth = document.querySelector('#container').offsetWidth;
      let fontControlButtonWidth = fontControlButton.offsetWidth;
      if (openLightButton.style.left < containerWidth - fontControlButtonWidth * 2 + 'px') {
        openLightButton.style.left = containerWidth - fontControlButtonWidth * 2 + 'px';
      } else {
        openLightButton.style.left = fontControlButtonWidth * 3 + 'px';
      }
    }
    ,

  }

}
</script>

<style scoped>
#fontControl {
  position: relative;
  transition: left 0.3s;

}

#openLight {
  position: absolute;
  transition: left 0.3s;
}

#container {
  position: relative;
  display: inline-block;
  width: 400px;
  height: 60px;
  margin-left: 25px;
  background-color: #f0f0f0;
  border-radius: 25px;
  overflow: hidden;
}
</style>