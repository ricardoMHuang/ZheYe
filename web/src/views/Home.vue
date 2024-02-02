<template>
  <div>
    <el-input v-model="input" placeholder="请输入搜索姓名" style="width: 150px; "></el-input>&nbsp;
    <el-button type="primary" @click="Search">查询</el-button>
    <el-table
        :data="tableData"
        style="width: 100%">
      <el-table-column
          prop="number"
          label="日期"
          width="180">
      </el-table-column>
      <el-table-column
          prop="userName"
          label="姓名"
          width="180">
      </el-table-column>
      <el-table-column
          prop="price"
          label="地址">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import useApi from '../../api/user'

export default {

  name: "Home",
  data() {
    return {
      input: '',
      tableData: []
    }
  },
  methods: {
    async Search() {
      console.log(this.input);
      let res = await useApi.SearchList({userName: this.input});
      console.log(res);
      res = res.data;
      if (res.success) {
        this.tableData = res.data;
      }
    }
  }
}
</script>

<style scoped>

</style>