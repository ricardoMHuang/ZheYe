<template>
  <div style="">
    <div class="contain">
      <div class="background"></div>

      <el-card v-for="(comment,index) in commentList " class="commentCard">

        <div style="margin-top: 40px">

          <el-avatar avatar @click.native="openUserInformation(comment.userId)" size="large"
                     :src="comment.avatar"></el-avatar>

          <div style="display: inline-block;padding: 10px">
            <div>{{ comment.nickname }}</div>
            <div> {{ comment.publishTime }} 回复</div>
          </div>
        </div>

        <div style="display: flex;flex-wrap: wrap">
          <p style="margin: 0 50px;width: 65%; ">
            {{ comment.content }}
          </p>
          <el-link :underline="false" @click="support(comment)" class="el_link">{{ comment.supportNum }} <i
              class="el-icon-thumb" :style="{color:comment.supportColor }"></i></el-link>
          <el-link :underline="false" class="el_link" @click="showInput(comment)">回复</el-link>
          <!--        子评论组件-->
          <el-card v-for="(childComment,index) in comment.childCommentList" class="childComment"
                   v-if="!comment.showChildComment">
            <div style="margin-top: 40px">
              <el-avatar avatar @click.native="openUserInformation(childComment.userId)" size="large"
                         :src="childComment.avatar"></el-avatar>
              <div style="display: inline-block;padding: 10px">
                <div>{{ childComment.nickname }}</div>
                <div style="color: #999999"> {{ childComment.publishTime }} 回复 {{ childComment.reviewerName }}</div>
              </div>
            </div>
            <div style="display: flex">
              <p style="margin: 0 50px;width: 65%; ">
                {{ childComment.content }}
              </p>
              <el-link :underline="false" class="el_link" @click="support(childComment)">{{ childComment.supportNum }}
                <i
                    class="el-icon-thumb" :style="{color:childComment.supportColor }"></i></el-link>
              <el-link :underline="false" class="el_link" @click="showInput(childComment)">回复</el-link>
            </div>
          </el-card>

          <el-link class="el_link" v-if="comment.childCommentList.length"
                   @click="comment.showChildComment = !comment.showChildComment;"
                   :underline="false">{{ comment.childCommentList.length }} 条<i class="el-icon-arrow-down"></i>
          </el-link>
        </div>
      </el-card>
    </div>
    <!--    用户主页弹框-->
    <el-dialog
        :title="user.nickname"
        :visible.sync="showInformation"
        width="40%">
      <div style="display: flex">
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
        <div v-for="group in uColtGroupList" style="width:85px; margin: 10px">
          <el-card :body-style="{padding: 0}" style="width:85px;height: 85px;">
            <img :src="group.image" class="group-image" @click="openGroup(group.id)">
          </el-card>
          <div>{{ group.name }}</div>
        </div>
      </div>
    </el-dialog>
  </div>

</template>

<script>
import userApi from "../../../../api/user"
import bookCollectApi from "../../../../api/bookCollect";
import groupCollectApi from "../../../../api/groupCollect";
import groupApi from "../../../../api/group";

export default {
  name: "CommentDetail",
  props: ["commentList"],
  data() {
    return {
      uColtGroupList: [],
      showInformation: false,
      user: {},
      userBookList: {},
    }
  },
  methods: {
    support(comment) {
      this.$parent.support(comment);
    },
    showInput(childComment) {
      this.$parent.showInput(childComment);
    },
    async openUserInformation(userId) {
      this.showInformation = !this.showInformation
      let userResponse = await userApi.getUserInfo(userId);
      userResponse = userResponse.data;
      console.log(userResponse.message);
      this.user = userResponse.data;
      console.log(this.user)
      let uColtBookRes = await bookCollectApi.bookCollection({userId: userId});
      uColtBookRes = uColtBookRes.data;
      this.userBookList = uColtBookRes.data;
      console.log(this.userBookList)
      let response = await groupCollectApi.selectGroupByUserId(userId)
      response = response.data;
      if (response.code === 200) {
        console.log(response.message);
        let collectList = response.data
        for (let collect of collectList) {
          let groupRes = await groupApi.getGroup(collect.groupId);
          groupRes = groupRes.data
          let group = groupRes.data
          this.uColtGroupList.push(group);
        }
      }
      console.log(this.uColtGroupList)
    },
    openBook(val) {
      // 变成 /user?id=1
      this.$router.push({
        path: '/book/bookDetail',
        query: {
          bookId: val,
        }
      })
      location.reload();
    },
    //查看小组详情
    openGroup(id) {
      this.$router.push({
        path: '/group/groupDetail',
        query: {
          groupId: id
        }
      })
    },
  }
}
</script>

<style scoped>
.el_link {
  font-size: 18px;
  color: black;

  height: 40px;
  width: 8%;
}

.commentCard {
  position: relative;
  background-color: rgba(255, 255, 255, 0.4);
  margin-bottom: 5px;
  color: black;
  font-size: 18px;
  transition-property: background-color;
  transition: 0.5s;
}

.commentCard:hover {
  transform: scale(0.97);
  background-color: white;
}

.childComment {
  background-color: rgba(235, 235, 235, 0.83);
  border-radius: 10px;
  width: 100%;
  padding: 0 0 0 20px;
  margin: 5px 80px 5px 100px;
}

.contain {
  position: relative;
  width: 100%;
  transition: 0.5s;
}

.background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;
  background-image: url("../../../assets/photo2.webp");
  filter: blur(5px);
  -webkit-filter: blur(5px); /* 兼容webkit浏览器 */
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
  display: block;
  cursor: pointer;
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

</style>