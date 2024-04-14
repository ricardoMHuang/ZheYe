<template>
  <div style="width: 80%;text-align: center;margin: auto;">
    <!--返回上一页-->
    <el-page-header @back="goBack" content="分类榜单" style="padding: 20px"></el-page-header>

    <el-tabs :tab-position="tabPosition" v-model="activateId" @tab-click="handleClick">
      <el-tab-pane :label="o.type" v-for="(o,index) in bookTypeList" :name="o.id">
        <h2 style="margin-bottom: 80px;color: white">{{ o.type }}</h2>
        <div v-for="(l,i) in bookList" style="margin-bottom: 40px;display: flex;height: 150px">
          <div class="rank-father">
            <div class="rank">{{ i + 1 }}</div>
          </div>
          <el-card :body-style="{padding:0}" style="width: 100px;height: 140px">
            <img :src="l.image" class="image" @click="openBook(l.id)">
          </el-card>
          <div class="wenBen">
            <h3 style="margin-top: 0">{{ l.name }}</h3>
            <div>
              <el-rate
                  v-model="l.score"
                  disabled
                  show-score
                  text-color="#ff9900"
                  score-template="{value}">
              </el-rate>
            </div>
            <p class="textOverflow">
              {{ l.briefIntroduction }}
            </p>
            <el-divider></el-divider>
          </div>
        </div>
        <el-pagination
            background
            layout="prev, pager, next"
            :total="1000">
        </el-pagination>
      </el-tab-pane>
    </el-tabs>

  </div>


</template>

<script>
import bookApi from "@/../api/book"
import bookTypeApi from "../../../api/booktype";

export default {
  name: "category",
  data() {
    return {
      count: 0,
      activateId: parseInt(this.$route.query.id, 10),
      tabPosition: 'left',
      bookTypeList: [],
      bookList: [],
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    /*页面初始化*/
    async init() {
      await this.getBookTypeList();
      await this.getBookList();
    },
    //返回上一级
    goBack() {
      this.$router.back();
    },
    async getBookTypeList() {
      let res = await bookTypeApi.getBookType();
      res = res.data;
      console.log(res.message);
      console.log(res.data)
      this.bookTypeList = res.data;
    },
    async getBookList() {
      let res = await bookApi.getBookByType(this.activateId);
      res = res.data;
      console.log(res.message);
      console.log(res.data);
      this.bookList = res.data;
    },
    handleClick(tab, event) {
      console.log(tab, event); // 输出被点击的标签页信息和事件对象
      // 在这里可以添加你需要的逻辑处理代码
      this.getBookList();
    },
    load() {
      this.count += 2
    },
    openBook(id) {
      this.$router.push({
        path: "/book/bookDetail",
        query: {
          bookId: id,
        },
      })
    }
  }
}
</script>

<style scoped>
/deep/ .el-tabs {
  height: 1200px;
  /*background-color: red;*/
}

.image {
  width: 100%;
  display: block;
  cursor: pointer;
}

.textOverflow {
  -webkit-line-clamp: 3;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
  cursor: pointer;
}

.rank-father {
  position: relative;
  height: 50px;
  margin: auto;
}

.rank {
  position: relative;
  margin: auto;
  font-size: 25px;
  color: #e92758;
  padding: 2px;
  border: solid rgba(255, 255, 255, 0.5) 5px;
  border-radius: 20px;
  box-sizing: border-box; /* 包括 padding 和 border 在内的宽度和高度 */
}

.rank:after {
  z-index: -1;
  content: "";
  background-color: rgba(162, 160, 160, 0.85);
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  border-radius: 20px;
  position: absolute;
}

.wenBen {
  float: left;
  text-align: left;
  width: 70%;
  margin-left: 50px;
  background-image: linear-gradient(to right, #ff9569 0%, #e92758 100%);
  padding: 10px;
  border-radius: 5px;
}

/* 通过 CSS 类选择器来定位并修改 el-tabs 的标签字体大小 */
>>> .el-tabs__item {
  font-size: 20px; /* 设置你想要的字体大小 */
}
</style>