<template>
  <el-row style="margin-bottom: 20px;margin-left: 40px">
    <el-col :span="7">
      <el-card style="background-color:#fcfbf9;" :body-style="{padding:0}">
        <h2 style="padding-left: 20px">推荐榜</h2>
        <el-row>
          <el-col v-for="(o,index) in recommendList" :key="o" style="margin-top: 10px;">
            <el-card shadow="hover" :body-style="{padding: '12px'}"
                     style="border-style: hidden;background-color: #fcfbf9">
              <div style="display:flex; gap: 20px">
                <strong style="font-size: 35px; color: red;font-style: italic;margin-top: 20px">{{ index + 1 }}</strong>
                <el-card :body-style="{padding: 0}" style="width: 90px;">
                  <img :src="o.image" class="image" @click="openBook(o.id)">
                </el-card>
                <div style="display: flex;flex-direction: column;gap: 30px;padding: 10px">
                  <strong>{{ o.name }}</strong>
                  {{ o.publish }}
                  <div>
                    <el-rate
                        v-model="o.score"
                        disabled
                        show-score
                        text-color="#ff9900"
                        score-template="{value}">
                    </el-rate>
                  </div>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-card>
    </el-col>
    <el-col :span="7" offset="1">
      <el-card style="background-color:#fcfbf9;" :body-style="{padding:0}">
        <h2 style="padding-left: 20px">评分榜</h2>
        <el-row>
          <el-col v-for="(o,index) in rateList" :key="o" style="margin-top: 10px;">
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
    <el-col :span="7" offset="1">
      <el-card style="background-color:#fcfbf9;" :body-style="{padding:0}">
        <h2 style="padding-left: 20px">收藏榜</h2>
        <el-row>
          <el-col v-for="(o,index) in collectList" :key="o" style="margin-top: 10px;">
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
</template>

<script>
import bookApi from "../../../../api/book";
export default {
  name: "Rank",
  data() {
    return {
      recommendList: [],
      rateList: [],
      collectList: [],
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    async init() {
      await this.getRecommendList();
      await this.getRateList();
      await this.getCollectList();
    },
    async getRecommendList() {
      let hotRes = await bookApi.getRecommendList(JSON.parse(window.sessionStorage.getItem("userInfo")).id);
      hotRes = hotRes.data;
      console.log(hotRes.message);
      console.log(hotRes.data)
      this.recommendList = hotRes.data;
    },
    async getRateList() {
      let rateRes = await bookApi.getRateList();
      rateRes = rateRes.data;
      console.log(rateRes.message);
      this.rateList = rateRes.data;
    },
    async getCollectList() {
      let collectRes = await bookApi.getCollectList();
      collectRes = collectRes.data;
      console.log(collectRes.message);
      this.collectList = collectRes.data;
    },
    openBook(bookId) {
      this.$router.push({
        path: "/book/bookDetail",
        query: {bookId: bookId}
      })
    }
  }
}
</script>

<style scoped>
.image {
  width: 100%;
  display: block;
  cursor: pointer;
}
</style>