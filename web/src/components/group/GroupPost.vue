<template>
  <div style="margin: auto;width: 1000px">
    <el-page-header @back="goBack" content="帖子" style="padding: 20px"></el-page-header>
    <el-card :body-style="{padding:0}" style="padding: 10px;position: relative">
      <el-image :src="group.image" :lazy="true" class="group-image" @click="openGroup">
      </el-image>
      <div style="display: inline-block; position: relative; left: 10px;bottom:10px">
        <div>{{ this.group.name }}</div>
        <div style="color: #999999"> {{ this.post.creator.name }}</div>
      </div>
      <div style="display: inline-block;position:absolute;right: 15px;top: 30px">
        <el-button v-if="collectState" type="success" @click="quitGroup()">已加入</el-button>
        <el-button v-else type="primary" @click="collectGroup()">加入小组</el-button>
      </div>
    </el-card>
    <h2>帖子</h2>
    <!--    头像-->
    <div style="margin-top: 40px">
      <el-avatar size="large" :src="post.creator.avatar"></el-avatar>
      <div style="display: inline-block;padding: 10px">
        <div>{{ post.creator.nickname }}</div>
        <div style="color: #999999"> {{ post.createTime }} 发表</div>
      </div>
    </div>

    <!--    文章-->
    <div>
      <h1 style="font-weight: bold;font-size: 30px;background-color: white;padding: 10px;border-radius: 5px;text-align: center">
        {{ post.title }}</h1>
      <img style="width:100%;" :src="post.image" alt="">
    </div>

    <div v-html="post.content" style="background-color: white;padding: 20px;border-radius: 5px;margin-top: 20px"></div>

    <div style="float: right;margin-top: 50px;">
      <el-button plain>赞</el-button>
      <el-button plain>回复</el-button>
      <el-button plain>分享</el-button>
    </div>
    <!--    回复-->

  </div>
</template>

<script>
import postApi from "../../../api/post"
import groupApi from "../../../api/group";
import userApi from "../../../api/user";
import groupCollectApi from "../../../api/groupCollect";

export default {
  name: "article",
  data() {
    return {
      postId: JSON.parse(this.$route.query.postId),
      userId: JSON.parse(window.sessionStorage.getItem("userInfo")).id,
      post: {},
      group: {},
      collectState: false,
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    async init() {
      await this.getPost();
      await this.getGroup();
      await this.isCollect();
    },
    //判断是否收藏
    async isCollect() {
      let response = await groupCollectApi.selectGroup({
        "userId": this.userId,
        "groupId": this.group.id,
      });
      response = response.data;
      console.log(response.message);
      if (response.code === 200) {
        this.collectState = true;
      }

    },
    async getPost() {
      let postRes = await postApi.getPost(this.postId);
      postRes = postRes.data;
      this.post = postRes.data;
      let creatorRes = await userApi.getUserInfo(this.post.creatorId);
      creatorRes = creatorRes.data;
      this.post.creator = creatorRes.data;
      console.log(postRes.message);
      console.log(this.post);
    },
    async getGroup() {
      let groupRes = await groupApi.getGroup(this.post.groupId);
      groupRes = groupRes.data;
      this.group = groupRes.data;
      console.log(groupRes.message);
      console.log(this.group);
    },
    //退出小组
    async quitGroup() {
      let response = await groupCollectApi.deleteGroupCollect({
        "groupId": this.group.id,
        "userId": this.userId
      })
      response = response.data;
      console.log(response.message);
      this.collectState = false;
      await groupApi.reduceNumber(this.group.id);
      location.reload();
    },
    //加入小组
    async collectGroup() {
      let dt = new Date();
      let year = dt.getFullYear();
      let month = dt.getMonth() + 1;
      let day = dt.getDate();
      let time = year + "-" + month + "-" + day;
      let response = await groupCollectApi.insertGroupCollect({
        "collectTime": time,
        "groupId": this.group.id,
        "userId": this.userId
      })
      response = response.data;
      console.log(response.message);
      this.collectState = true;
      await groupApi.addNumber(this.group.id);
      location.reload();
    },
    //打开小组
    openGroup() {
      this.$router.push({
        path: "/group/groupDetail",
        query: {
          groupId: this.group.id,
        }
      })
    },
    //返回上一级
    goBack() {
      this.$router.back();
    }
  },
}
</script>

<style scoped>
.group-image {
  cursor: pointer;
  width: 80px;
  height: 80px;
  transition: 0.8s;
}

.group-image:hover {
  transform: scale(0.8);
}

</style>