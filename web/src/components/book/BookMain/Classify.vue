<template>
  <div>
    <el-row>
      <el-col v-for="(o,index) in bookTypeList" :span="6">
        <el-link v-if="index!==bookTypeList.length-1" :underline="false" @click="openCategory(o.type)">
          <h3 style="margin-bottom: 0">{{ o.type }}·{{ o.totalQuantity }}本 </h3>
          <el-divider></el-divider>
        </el-link>
        <el-link v-if="index===bookTypeList.length-1" @click="openCategory"><h3>查看全部 >></h3></el-link>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Classify",
  props: ["bookTypeList"],
  methods: {
    openCategory(type) {
      this.$parent.openCategory(type)
    },
  }
}
</script>

<style scoped>

</style>