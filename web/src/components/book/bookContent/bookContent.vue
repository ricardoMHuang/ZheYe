<template>
  <div :style="{backgroundColor:this.bgColor,color: fontColor}">
    <el-page-header @back="goBack" :content="this.book.name" style="padding: 20px ">
    </el-page-header>
    <el-divider></el-divider>
    <el-empty description="空空如也！" v-if="emptyBookContent"></el-empty>

    <div v-else>
      <!--    目录-->
      <el-button type="success" icon="el-icon-s-fold" circle @click="drawerOne = true;"
                 style="margin: 10px"></el-button>
      <el-drawer
          :title="this.book.name"
          :visible.sync="drawerOne"
          :direction="direction">
        <el-link :underline="false" v-for="item in directory" :style="{}" style="margin:0 20px;display:block;">{{
            item
          }}
          <el-divider/>
        </el-link>
      </el-drawer>
      <!--      改变字体大 小-->
      <el-button type="success" icon="el-icon-circle-plus" circle @click="drawerTwo = true"
                 style="margin: 10px"></el-button>
      <!--开关灯-->
      <el-button type="success" icon="el-icon-sunny" circle @click="drawerTwo = true;lightControl()"
                 style="margin: 10px"></el-button>


      <el-skeleton style="margin: 20px" animated>
      </el-skeleton>

      <el-pagination
          background
          layout="prev, pager, next"
          :total="100" style="margin-top: 20px; text-align: center">
      </el-pagination>
    </div>
  </div>
</template>

<script>

import directoryApi from "../../../../api/directory";

export default {
  name: "bookConnect",
  data() {
    return {
      book: {},
      author: {},
      drawerOne: false,
      drawerTwo: false,
      direction: 'rtl',
      bgColor: 'white',
      fontColor: 'black',
      directory: [],
      emptyBookContent: true,
      bookContent: {},
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    async init() {
      this.author = JSON.parse(this.$route.query.author);
      this.book = JSON.parse(this.$route.query.book);
      console.log(this.book)
      await this.getBookContent(this.book.id);
      this.isEmptyContent();
    },
    async getBookContent(bookId) {
      let res = await directoryApi.getBookContent(bookId);
      this.bookContent = res.data.data;
      console.log(res.data.data);
      console.log(this.bookContent);

    },
    goBack() {
      this.$router.back();
    },
    lightControl() {
      if (this.bgColor === "white" && this.fontColor === "black") {
        this.bgColor = 'black';
        this.fontColor = 'white';
      } else {
        this.bgColor = 'white';
        this.fontColor = 'black';
      }
    },
    isEmptyContent() {
      console.log(this.bookContent);
      this.emptyBookContent = this.bookContent === null;
    }
  }

}
</script>

<style scoped>

</style>