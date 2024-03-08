<template>
  <div style="width: 95%;margin-left: 2%;margin-right: 2%">
    <!--      左侧-->
    <div style="float: left;width: 65%;display: inline-block;padding-left: 4%">
      <el-page-header @back="goBack" content="小组详情" style="margin: 30px 0"></el-page-header>

      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1" @click="no=1">热门帖子</el-menu-item>
        <el-menu-item index="2" @click="no=2">我关注的</el-menu-item>
        <el-menu-item index="3" @click="no=3">我回复的</el-menu-item>
        <el-menu-item index="4" @click="no=4">我发布的</el-menu-item>
      </el-menu>
      <div style="">
        <el-row v-show="no===1" style="" v-for="(item,i) in classes" :key="1">
          <el-col v-for="(type,j) in item.detail" :span="24" :key="2" style="margin-bottom: 30px">
            <el-card
                style="">
              <div>
                <el-card :body-style="{ padding: '0px'} " style="width: 85px;display: inline-block;float:left;">
                  <img :src="type.url" class="image" :key="3"
                       style="display: inline-block;width: 85px;height:85px;float: left">
                </el-card>
                <div style="padding: 10px; margin-bottom: 10px; display: inline-block;float: left">
                  <div style="font-size: 20px">{{ type.groupName }}</div>
                  <div style="font-size: 20px">最近更新：{{ type.title }}</div>
                  <div style="font-size: 15px">{{ type.counts }}成员</div>
                </div>
              </div>

            </el-card>
          </el-col>


        </el-row>

        <el-row v-show="no===2">
          <el-card>我关注的</el-card>
        </el-row>
        <el-row v-show="no===3">
          <el-card>我回复的</el-card>
        </el-row>
        <el-row v-show="no===4">
          <el-card>我发布的</el-card>
        </el-row>
      </div>
      <!--        分页-->
      <div class="block" style="margin-left: 200px">
        <el-pagination
            layout="prev, pager, next"
            :total="100">
        </el-pagination>
      </div>
    </div>

    <!--      右侧-->
    <div style="width: 28%;margin-left: 20px;display: inline-block">
      <br>
      <el-card style="background-color: #F9F9F9">
        <el-avatar :size="76" :src="user.photo" style="display: inline-block;float: left"></el-avatar>
        <h2>{{ user.userName }}</h2>
        <router-link to="/personCenter" style="text-decoration-line: none">者也主页</router-link>
      </el-card>
    </div>
    <div style="width: 28%;margin-left: 20px;display: inline-block">
      <br>
      <h2 style="display: inline-block;font-size: 20px;margin-left: 79px">推荐加入</h2>
      <router-link to="/group/oldUser" style="  text-decoration: none;font-size: 15px;float: right;margin-top: 20px">
        小组主页<i class="el-icon-d-arrow-right"></i>
      </router-link>
      <div v-for="(item,i) in classes" :key="1">
        <el-card :span="5" v-for="(type,j) in item.detail" :key="2" :offset="j%4 > 0 ? 1 : 0"
                 style="padding-bottom: 25px">
          <div :body-style="{ padding: '0px'} ">
            <img :src="type.url" class="image" :key="3"
                 style="display: inline-block;width: 85px;height:85px;float: left">
            <div style="padding: 10px;">
              <div style="font-size: 20px">{{ type.groupName }}</div>
              <div style="font-size: 15px">{{ type.counts }}个成员</div>
              <div>
                <el-button type="text" class="el_link" style="color:#CA7158">+加入小组</el-button>
              </div>
            </div>
          </div>
        </el-card>
      </div>
    </div>

  </div>


</template>

<script>
export default {
  name: "Group_All",
  data() {
    return {
      no: 1,
      activeIndex: '1',
      activeIndex2: '1',
      user: {
        photo: "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
        userName: "江西网友",
      },
      classes: [{
        type: "兴趣",
        detail: [
          {
            url: "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
            groupName: "种花种草",
            title: "求助",
            counts: "206541",
          },
          {
            url: "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
            groupName: "画画评评楼",
            counts: "21733",
          },
          {
            url: "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
            groupName: "书法",
            counts: "155304",
          },],
      },
      ],
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    goBack() {
      this.$router.go(-1);
    },
  }
}
</script>

<style scoped>

</style>