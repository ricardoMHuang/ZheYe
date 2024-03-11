<template>
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
</template>

<script>
export default {
  name: "Rank",
  props: ["sellingList"],
  methods: {
    openBook(bookId) {
      this.$parent.openBook(bookId);
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