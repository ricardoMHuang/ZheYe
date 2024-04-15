<template>
  <div>
    <div style="">
      <!--     我的小组-->
      <el-tabs v-model="activeName">
        <el-tab-pane label="加入的小组" name="first">
          <h3>加入的小组</h3>
          <my-group></my-group>
        </el-tab-pane>
        <el-tab-pane label="创建的小组" name="second">
          <h3>创建的小组</h3>
          <create-group></create-group>
        </el-tab-pane>
        <el-tab-pane label="我的帖子" name="third">
          <h3>我的帖子</h3>
          <my-post></my-post>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import groupApi from "../../../../api/group";
import groupCollectApi from "../../../../api/groupCollect";
import MyGroup from "@/components/personalCenter/follow/groupManage/MyGroup";

export default {
  name: "GroupManage",
  components: {MyGroup},
  data() {
    return {
      activeName: "first",
    }
  },

  methods: {
    openCreateGroup() {
      this.$router.push({
        path: "/personalCenter/createGroup"
      })

    },

  }
}
</script>

<style scoped>

</style>