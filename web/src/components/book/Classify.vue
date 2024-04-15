<template>
  <div>
    <el-row>
      <el-col v-for="(o,index) in bookTypeList" :span="6">
        <el-link v-if="index!==bookTypeList.length-1" :underline="false" @click="openCategory(o.id)">
          <h3 style="margin-bottom: 0">{{ o.type }}·{{ o.totalQuantity }}本 </h3>
          <el-divider></el-divider>
        </el-link>
        <el-link v-if="index===bookTypeList.length-1" @click="openCategory(1)"><h3>查看全部 >></h3></el-link>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import bookTypeApi from "../../../api/booktype";

export default {
  name: "Classify",
  data() {
    return {
      bookTypeList: [],

    }
  },
  mounted() {
    this.getBookTypeList();
  },
  methods: {
    openCategory(id) {
      this.$router.push({
        path: '/book/category',
        query: {
          id: id,
        }
      })
    },
    async getBookTypeList() {
      let res = await bookTypeApi.getBookType();
      res = res.data;
      console.log(res.message);
      console.log(res.data)
      this.bookTypeList = res.data;
    },
  }
}
</script>

<style scoped>

</style>