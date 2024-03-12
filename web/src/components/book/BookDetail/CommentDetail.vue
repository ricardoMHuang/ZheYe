<template>
  <div style="">
    <div class="contain">
      <div class="background"></div>

      <el-card v-for="(comment,index) in commentList " class="commentCard">

        <div style="margin-top: 40px">
          <el-avatar size="large" :src="comment.avatar"></el-avatar>
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
              <el-avatar size="large" :src="childComment.avatar"></el-avatar>
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
  </div>

</template>

<script>
export default {
  name: "CommentDetail",
  props: ["commentList"],
  methods: {
    support(comment) {
      this.$parent.support(comment);
    },
    showInput(childComment) {
      this.$parent.showInput(childComment);
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
  transform: scale(1.01);
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
</style>