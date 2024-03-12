<template>
  <div style="background-color: white">
    <el-page-header @back="goBack" content="发布文章">
    </el-page-header>

  </div>
</template>

<script>
export default {
  name: "editor",
  methods: {
    goBack() {
      this.$router.go(-1);
    }
  }

}
</script>

<style scoped>

</style>