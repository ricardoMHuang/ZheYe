<template>
  <div style="width: 100%;min-height: 1000px">
    <el-page-header @back="goBack" content="我的小组" style="margin: 30px 0"></el-page-header>
    <div style="display: flex;justify-content: space-between">
      <!--    我的小组-->
      <div style="width:50%;">
        <h2 style="text-align: center">我的小组</h2>
        <el-empty v-if="groupEmptyVisible" description="你还没有加入任何小组哦">
          <el-button @click="openAllGroup()">去看看</el-button>
        </el-empty>

        <div v-else style="display: flex;flex-wrap: wrap">
          <div v-for="group in myGroupList" style="margin: 0 50px">
            <el-card :body-style="{ padding: '0px'}" style="width: 85px">
              <img :src="group.image" class="group-image" @click="openGroup(group.id)">
            </el-card>
            <p class="group-title">{{ group.name }}</p>
          </div>
        </div>
      </div>

      <!--热门帖子-->
      <div style="width: 40%;margin-left: 20px;display: inline-block">
        <h2>我的帖子</h2>
        <div style="margin-left: 10%; ">
          <el-empty v-if="myPostList.length===0"></el-empty>
          <el-row v-else>
            <el-col v-for="(post,i) in myPostList" :span="7" :key="i" :offset="i%2 > 0 ? 0.5 : 0"
                    style="padding-bottom: 25px">
              <el-card :body-style="{ padding: '0px'}" class="my-post" @click.native="openPost(post.id)">
                <img :src="post.image" class="image" style="height: 110px">
                <div style="padding: 10px;">
                  <p style="font-size: 13px;overflow: hidden;margin: 0;">
                    <span class="post-title">{{ post.title }}</span><br>
                    <i class="el-icon-thumb"></i>{{ post.supportNum }}<br>
                    <i class="el-icon-mouse"></i>{{ post.commentsNum }}<br>
                    <i class="el-icon-timer"></i>{{ post.createTime }}<br>
                  </p>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </div>
    </div>

  </div>


</template>

<script>
import groupApi from "../../../api/group"
import postApi from "../../../api/post"
import groupCollectApi from "../../../api/groupCollect"

export default {
  name: "Group_OldUser",
  data() {
    return {
      userId: JSON.parse(window.sessionStorage.getItem("userInfo")).id,
      btnStatus: false,
      link: "",
      myPostList: [],
      groupEmptyVisible: true,
      myGroupList: [],
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    async init() {
      await this.getMyGroupList()
      await this.getMyPostList();
    },
    async getMyGroupList() {
      let res = await groupCollectApi.selectGroupByUserId(this.userId);
      res = res.data;
      let list;
      if (res.code === 200) {
        list = res.data;
        for (const listElement of list) {
          let group = await groupApi.getGroup(listElement.groupId);
          group = group.data;
          if (group.code === 200) {
            this.myGroupList.push(group.data);
          }
        }
      }
      if (this.myGroupList.length > 0) {
        this.groupEmptyVisible = false;
      }
      console.log(this.myGroupList);
    },
    async getMyPostList() {
      let myPostListRes = await postApi.getPostByUserId(this.userId);
      myPostListRes = myPostListRes.data;
      this.myPostList = myPostListRes.data;
    },
    goBack() {
      this.$router.go(-1);
    },
    getPath(groupName) {
      return "/group/group_main/" + groupName
    },
    open() {

      this.$confirm('确认加入小组?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '成功加入!',

        });
        console.log("success");
        this.sum = this.sum + 1;
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });

      });
    },
    openGroup(id) {
      this.$router.push({
        path: '/group/groupDetail',
        query: {
          groupId: id,
        }
      })
    },
    openPost(postId) {
      this.$router.push({
        path: "/group/groupPost",
        query: {
          postId: postId,
        }
      })
    },
    openAllGroup() {
      this.$router.push({
        path: "/group"
      })
    }
  }
}

</script>

<style scoped>
.image {
  width: 100%;
  display: block;
}

.post-title {
  font-size: 22px;
  font-weight: bold;
  -webkit-line-clamp: 2;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}

.group-image {
  display: block;
  cursor: pointer;
  width: 85px;
  height: 85px;
  transition: 0.8s;

}

.group-image:hover {
  transform: scale(0.8);
}

.group-title {
  overflow: hidden;
  color: white;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 85px;
}

.my-post {
  width: 120px;
  height: 230px;
  cursor: pointer;
  transition: 1s;
}

.my-post:hover {
  transform: scale(0.9);
}
</style>