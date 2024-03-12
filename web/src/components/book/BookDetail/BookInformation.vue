<template>
  <div class="box">
    <div class="blur-container">
      <div class="blur-background"></div>

      <div class="bookDetail">
        <h2 style="text-align: center;margin-top: 0;padding-top: 50px;">{{ book.name }}</h2>
        <div style="text-align: center">
          <el-avatar size="large" :src="author.image"></el-avatar>
          <h5 style="margin: 0">{{ author.name }}</h5>
          <h5>{{ book.publish }}</h5>
          <h5>{{ book.page }}页</h5>
          <div style="height: 50px">
            <el-button type="success" @click="openBook()">阅读</el-button>
            <el-button type="primary" @click="addBookCollect()" v-show="!state">加入书架</el-button>
            <el-button type="success" @click="deleteBookCollect()" v-show="state">已加入</el-button>
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
        <!--图书状态和评价-->
        <div style="text-align:center;">
          <el-button @click="open(1)">想读</el-button>
          <el-button @click="open(2)">在读</el-button>
          <el-button @click="open(3)">读过</el-button>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  name: "BookInformation",
  props: ["book", "state", "author", "value"],
  methods: {
    openBook() {
      this.$parent.openBook();
    },
    addBookCollect() {
      this.$parent.addBookCollect();
    },
    deleteBookCollect() {
      this.$parent.deleteBookCollect();
    },
    open(buttonNumber) {
      this.$parent.open(buttonNumber);
    },
    mounted() {

    },

  }
}
</script>

<style scoped>
.box {
  width: 100%;
  height: 600px;
  background-image: url("../../../assets/photo2.webp");
}

.blur-container {
  position: relative;
  width: 30%;
  height: 79%;
  left: 400px;
  top: 50px;
  transition: 0.5s;
}

.blur-background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url("../../../assets/photo2.webp");
  filter: blur(5px);
  -webkit-filter: blur(5px); /* 兼容webkit浏览器 */

}

.bookDetail {
  position: relative;
  z-index: 1;
  font-size: 20px;
  padding: 10px;
  border: solid rgba(255, 255, 255, 0.2);
  border-width: 15px 15px 15px 10px;
  border-radius: 15px;
  box-shadow: 5px -1px rgba(255, 255, 255, 0.2), 10px -2px rgba(255, 255, 255, 0.3), 15px -3px rgba(255, 255, 255, 0.4), 20px -4px rgba(255, 255, 255, 0.5);
}

.blur-container:hover {

  transform: scale(1.05);

}


</style>