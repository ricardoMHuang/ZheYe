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
          <created-group></created-group>
        </el-tab-pane>
        <el-tab-pane label="我的帖子" name="third">
          <h3>我的帖子</h3>
          <my-posts></my-posts>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import groupApi from "../../../../api/group";
import groupCollectApi from "../../../../api/groupCollect";
import MyGroup from "@/components/personalCenter/follow/groupManage/MyGroups";
import CreatedGroup from "@/components/personalCenter/follow/groupManage/CreatedGroup";
import MyPosts from "@/components/personalCenter/follow/groupManage/MyPosts";

export default {
  name: "GroupManage",
  components: {MyGroup, CreatedGroup, MyPosts},
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