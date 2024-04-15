<template>
  <div>
    <div v-if="bookList!==null">
      <el-row style="margin-bottom: 80px;">
        <el-col v-for="(item,index) in bookList" :span="8" :offset="index%2 > 0 ? 3 : 0"
                style="margin: 15px 0;">
          <el-card :body-style="{padding: 0}" style="width: 120px;height:170px; float: left;">
            <img :src="item.image" class="image" @click="openBook(item.id)">
          </el-card>
          <div style="float: left; padding: 10px; margin-left: 10px;">
            <h3 style="">{{ item.name }}</h3>
            <h4 style="color: #999999">{{ item.publish }}</h4>
            <el-rate
                v-model="item.score"
                disabled
                show-score
                text-color="#ff9900"
                score-template="{value}">
            </el-rate>
          </div>
        </el-col>
      </el-row>
    </div>
    <el-empty v-else description="还未评论过书籍" :image="require('@/assets/空状态.png')"></el-empty>
  </div>

</template>

<script>
export default {
  name: "ReviewedBooks",
  props: ["bookList"],
  methods: {
    openBook(val) {
      // 变成 /user?id=1
      this.$router.push({
        path: '/book/bookDetail',
        query: {
          bookId: val,
        }
      })
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
</style>