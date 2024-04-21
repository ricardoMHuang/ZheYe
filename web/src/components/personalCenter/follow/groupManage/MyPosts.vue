<template>
  <div style="width: 40%;margin-left: 20px;display: inline-block">
    <div style="margin-left: 10%; ">
      <el-empty v-if="myPostList.length===0" description="你还没发表过帖子诶"></el-empty>
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
</template>

<script>
import postApi from "../../../../../api/post";

export default {
  name: "MyPosts",
  data() {
    return {
      myPostList: [],
    }
  },
  methods: {
    async getMyPostList() {
      let myPostListRes = await postApi.getPostByUserId(this.userId);
      myPostListRes = myPostListRes.data;
      this.myPostList = myPostListRes.data;
    },
  }

}
</script>

<style scoped>

</style>