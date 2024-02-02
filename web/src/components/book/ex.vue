<template>
  <div>
    <el-carousel :interval="5000" arrow="always" type="card" style="width: 1050px">
      <el-carousel-item v-for="item in imgInfo" :key="item.id">
        <img style="object-fit: scale-down; width: 100%;height: 100%;" :src="item.idviews"/>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
export default {
  name: "ex",
  data() {
    return {
      type: this.$route.query.type,
      list: [],
      imgInfo: [{id: 1, idviews: require("@/assets/photo-1.webp")}, {
        id: 2,
        idviews: require("@/assets/photo-1.webp")
      }, {id: 3, idviews: require("@/assets/photo-1.webp")}],

    }
  },
  methods() {
  },
  created() {

  },

}
</script>

<style scoped>

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>