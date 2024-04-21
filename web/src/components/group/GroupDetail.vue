<template>
  <div style="width: 80%;margin: auto">
    <!--      左侧-->
    <div style="  float: left;width: 50%;display: inline-block;">
      <el-page-header @back="goBack" content="小组详情" style="margin: 30px 0"></el-page-header>

      <div class="group-message-father">
        <div class="group-message-background"></div>
        <div class="group-message">
          <div style="margin: 20px">
            <img :src="group.image" alt="小组图片" class="group-image">
            <h1>{{ this.group.name }}</h1>
            <el-tag>{{ this.group.type }}</el-tag>
            <div style="display: flex;justify-content: left;margin: 15px 0">
              <el-avatar avatar :src="group.creator.avatar" alt="用户头像"
                         @click.native="openUserInformation(group.creatorId)"></el-avatar>
              <span style="padding: 10px">组长: {{ this.group.creator.nickname }}</span>
            </div>
            <p>小组当前有 {{ this.group.number }} 个成员</p>
            <p>创建时间: {{ this.group.creatTime }}</p>
            <p>简介: {{ this.group.brief }}</p>
            <div>
              <el-button v-if="collectState" type="success" @click="quitGroup()">已加入</el-button>
              <el-button v-else type="primary" @click="collectGroup()">加入小组</el-button>
              <el-button type="primary" @click="openEditor()">+发帖</el-button>
            </div>
          </div>
        </div>
      </div>

      <el-menu :default-active="activeIndex" style="display: flex;justify-content: center">
        <el-menu-item index="1" @click="no=1">帖子</el-menu-item>
        <el-menu-item index="2" @click="no=2">小组成员</el-menu-item>
      </el-menu>
      <div>
        <el-row v-show="no===1">
          <el-empty v-if="postList.length === 0" description="暂无人发表帖子哦"></el-empty>
          <div v-else v-for="post in postList" style="background-color: rgba(255,255,255,0)">
            <div class="post-card" @click="openPost(post.id)">
              <img :src="post.image" alt="" class="group-image">
              <div style="margin-left: 10px">
                <h2>{{ post.title }}</h2>
                <div>
                  <i class="el-icon-thumb">{{ post.supportNum }}</i>
                  <i class="el-icon-chat-line-round">{{ post.commentsNum }}</i>
                  <i class="el-icon-time">{{ post.createTime }}</i>
                </div>
              </div>
            </div>
          </div>

        </el-row>

        <el-row v-show="no===2">
          <el-card>
            小组成员
            <div style="display: flex;justify-content: left">
              <div v-for="member in groupMemberList" style="margin: 10px">
                <el-avatar avatar :src="member.avatar" alt="用户头像"
                           @click.native="openUserInformation(member.id)"></el-avatar>
              </div>
            </div>
          </el-card>
        </el-row>
      </div>
    </div>
    <!--用户主页弹框-->
    <el-dialog
        :title="user.nickname"
        :visible.sync="showInformation"
        width="40%">
      <div style="overflow: scroll;height: 400px">
        <div style="display: flex;">
          <el-avatar :src="user.avatar"></el-avatar>
          <h3> ip:{{ this.user.area }} 爱好:{{ this.user.hobby }}</h3>
        </div>
        <h3>ta的书架</h3>
        <div style="display: flex;flex-wrap: wrap;">
          <div v-for="book in this.userBookList" style="width:60px; margin: 10px">
            <el-card :body-style="{padding: 0}" style="width: 60px;height: 80px;">
              <img :src="book.imageUrl" class="image" @click="openBook(book.bookId)">
            </el-card>
            <div>{{ book.bookName }}</div>
          </div>
        </div>
        <h3>ta的小组</h3>
        <div style="display: flex;flex-wrap: wrap;">
          <div v-for="group in uColtGroupList" style="width:85px; margin: 10px;">
            <el-card :body-style="{padding: 0}" style="width:85px;height: 85px;">
              <img :src="group.image" class="group-image" @click="openGroup(group.id)">
            </el-card>
            <div>{{ group.name }}</div>
          </div>
        </div>
        <h3>ta的帖子</h3>
        <div v-for="post in uPostList">
          <div class="post-card" @click="openPost(post.id)" style="border:1px solid rgba(1,22,22,0.5);margin: 10px 0">
            <img :src="post.image" alt="" class="group-image">
            <div style="margin-left: 10px">
              <h2>{{ post.title }}</h2>
              <div>
                <i class="el-icon-thumb">{{ post.supportNum }}</i>
                <i class="el-icon-chat-line-round">{{ post.commentsNum }}</i>
                <i class="el-icon-time">{{ post.createTime }}</i>
              </div>
            </div>
          </div>
        </div>
      </div>


    </el-dialog>

    <!--      右侧-->
    <div style="width: 30%;margin-left: 10%;display: inline-block">
      <br>
      <h2 style="display: inline-block;font-size: 20px;margin-left: 79px">推荐加入</h2>
      <div v-for="(item,i) in classes" :key="1">
        <el-card :body-style="{ padding: '0px'} " :span="5" v-for="(type,j) in item.detail" :key="2"
                 :offset="j%4 > 0 ? 1 : 0"
                 style="margin-bottom: 25px">
          <div style="display: flex;">
            <img :src="type.url" class="group-image" :key="3"
                 style="display: inline-block;width: 85px;height:85px">
            <div style="padding: 10px">
              <div style="font-size: 20px">{{ type.groupName }}</div>
              {{ type.counts }}个成员
              <el-button type="text" class="el_link" style="color:#CA7158">+加入小组</el-button>
            </div>
          </div>
        </el-card>
      </div>
    </div>

  </div>

</template>

<script>
import groupApi from "../../../api/group"
import groupTypeApi from "../../../api/groupType"
import userApi from "../../../api/user";
import groupCollectApi from "../../../api/groupCollect"
import bookCollectApi from "../../../api/bookCollect";
import postApi from "../../../api/post";

export default {
  name: "GroupDetail",
  data() {
    return {
      postList: [],
      uPostList: [],
      showInformation: false,
      user: {},
      userBookList: [],
      groupMemberList: [],
      uColtGroupList: [],
      userId: JSON.parse(window.sessionStorage.getItem("userInfo")).id,
      groupId: JSON.parse(this.$route.query.groupId),
      no: 1,
      activeIndex: '1',
      activeIndex2: '1',
      group: {},
      collectState: false,
      classes: [
        {
          type: "兴趣",
          detail: [
            {
              url: "https://i03piccdn.sogoucdn.com/a58efff3c504565c",
              groupName: "种花种草",
              title: "求助",
              counts: "206541",
            },
            {
              url: "https://i02piccdn.sogoucdn.com/c65e793de9d20ff6",
              groupName: "画画评评楼",
              counts: "21733",
            },
            {
              url: "https://i01piccdn.sogoucdn.com/da9206f37599203e",
              groupName: "书法",
              counts: "155304",
            },],
        }],
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    async init() {
      await this.getGroup();
      await this.isCollect();
      await this.getGroupMemberList();
      await this.getPosts();
    },
    //获取小组信息
    async getGroup() {
      let response1 = await groupApi.getGroup(this.groupId);
      response1 = response1.data;
      console.log(response1.message);
      let group = response1.data;
      let response2 = await groupTypeApi.getGroupType(group.typeId);
      response2 = response2.data;
      console.log(response2.message);
      let type = response2.data;
      group.type = type.name
      let response3 = await userApi.getUserInfo(group.creatorId);
      response3 = response3.data;
      console.log(response3.message);
      let creator = response3.data;
      group.creator = creator;
      this.group = group;
      console.log(this.group)
    },
    //获取该小组的帖子
    async getPosts() {
      let res = await postApi.getPostItem(this.groupId);
      res = res.data;
      this.postList = res.data;
      console.log(res.message);
      console.log(this.postList);
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
      if (month < 10) {
        month = "0" + month;
      }
      let day = dt.getDate();
      if (day < 10) {
        day = "0" + day;
      }
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
    //打开用户主页弹框
    async openUserInformation(userId) {
      this.showInformation = !this.showInformation
      //获取用户名称和头像
      let userResponse = await userApi.getUserInfo(userId);
      userResponse = userResponse.data;
      console.log(userResponse.message);
      this.user = userResponse.data;
      console.log(this.user)
      //获取用户收藏的书
      let uColtBookRes = await bookCollectApi.bookCollection({userId: userId});
      uColtBookRes = uColtBookRes.data;
      this.userBookList = uColtBookRes.data;
      console.log(this.userBookList)
      //获取用户的小组
      let response = await groupCollectApi.selectGroupByUserId(userId)
      response = response.data;
      if (response.code === 200) {
        console.log(response.message);
        let collectList = response.data
        this.uColtGroupList = [];
        for (let collect of collectList) {
          let groupRes = await groupApi.getGroup(collect.groupId);
          groupRes = groupRes.data
          let group = groupRes.data
          this.uColtGroupList.push(group);
        }
      }
      console.log(this.uColtGroupList)
      //获取用户发布的贴子
      let postListRes = await postApi.getPostByUserId(userId);
      postListRes = postListRes.data;
      this.uPostList = postListRes.data;
      console.log(postListRes.message);
      console.log(this.uPostList);
    },
    //获取小组成员信息
    async getGroupMemberList() {
      let response = await groupCollectApi.selectByGroupId(this.group.id);
      response = response.data;
      console.log(response.message);
      console.log(response.data)
      if (response.code === 200) {
        let memberIdList = response.data;
        for (let collect of memberIdList) {

          let res = await userApi.getUserInfo(collect.userId)
          res = res.data;
          console.log(res.message);
          this.groupMemberList.push(res.data);
        }
      }
      console.log(this.groupMemberList);
    },
    //打开书籍
    openBook(bookId) {
      this.$router.push({
        path: '/book/bookDetail',
        query: {
          bookId: bookId,
        }
      })
    },
    //查看小组详情
    openGroup(id) {
      this.$router.push({
        path: '/group/groupDetail',
        query: {
          groupId: id
        }
      })
      location.reload()
    },
    //打开帖子编辑页面
    openEditor() {
      if (this.collectState === false) {
        this.$message({
          type: "warning",
          message: "加入小组后才能发言",
        })
      } else {
        this.$router.push({
          path: '/group/postEditor',
          query: {
            groupId: this.groupId
          }
        })
      }
    },
    //打开帖子详情
    openPost(postId) {
      this.$router.push({
        path: "/group/groupPost",
        query: {
          postId: postId,
        }
      })
    },
    goBack() {
      this.$router.go(-1);
    },
  }
}

</script>

<style scoped>
.group-message {
  position: relative;
  box-sizing: border-box;
  height: 500px;
  border-radius: 15px;
  border: solid 10px rgba(22, 22, 22, 0.4);
  transition-property: border;
  transition: 1s;
  background-color: rgba(255, 255, 255, 0.2);

}

.group-message:hover {
  border: solid 20px rgba(22, 22, 22);

}

.group-message-background {
  -webkit-filter: blur(8px); /* 兼容webkit浏览器 */
  filter: blur(8px);
  background-image: url("../../assets/photo2.webp");
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  transition-property: filter;
}

.group-message-father {
  position: relative;
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

[avatar] {
  cursor: pointer;
  transition: 0.5s;
}

[avatar]:hover {
  transform: scale(1.2);
}

.image {
  width: 100%;
  cursor: pointer;
}

.post-card {
  display: flex;
  justify-content: left;
  cursor: pointer;
  border-radius: 8px;
  transition: 1s;
  background-color: white;
  margin: 10px 0;
}

.post-card:hover {
  transform: scale(0.9);
}

div i {
  margin-left: 15px;
}
</style>