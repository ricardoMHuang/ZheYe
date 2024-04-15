<template>
  <div>
    <el-row>
      <el-col v-for="(item,index) in mostConcernBookList" :span="8" :offset="index%2 > 0 ? 3 : 0"
              style="margin: 15px 0;">
        <el-card :body-style="{padding: 0}" class="card">
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
  </div>
</template>

<script>
export default {
  name: "everyWeek",
  props: ["mostConcernBookList"],
  methods: {
    openBook(val) {
      this.$parent.openBook(val);
    },
  }
}
</script>

<style scoped>
.image {
  width: 100%;
  display: block;
  cursor: pointer;
}

.card {
  width: 120px;
  height: 170px;
  float: left;
  transition: 0.5s;

}

.card:hover {
  transform: scale(1.2);
}
</style>