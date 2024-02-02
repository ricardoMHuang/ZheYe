<template>
  <div style="text-align: center;">

    <el-header height="200px;" style="padding: 0px;height: 200px;">
      <el-image :src="require('@/assets/背景墙.jpg')"></el-image>
      <el-avatar shape="square" :size="100" :src="image1"
                 style="position:relative;float: bottom;right: 400px;bottom:50px;z-index: 10"></el-avatar>
      <h1 class="text">{{ nickname }}</h1>
      <el-button icon="el-icon-arrow-left" @click="returnPage"
                 style="position:relative;float: top;right:545px;bottom:380px;z-index: 10;width: 100px;border: none;background-color: rgba(4,94,239,0);font-size: 16px;">
        返回首页
      </el-button>
    </el-header>

    <el-container>
      <el-header style="width: 1200px">
        <div class="person_body_left">
          <el-card class="box-card" :body-style="{ padding: '0px' }">

            <el-menu
                router
                active-text-color="#00c3ff"
                mode="horizontal"
                default-active="autoIndex"
                style="width: 1200px"
            >
              <el-menu-item
                  index="/personCenter/info"
              >
                <i class="el-icon-user"></i>
                <span slot="title">个人简介</span>
              </el-menu-item>
              <el-menu-item
                  index="/personCenter/myarticle"
              >
                <i class="el-icon-edit-outline"></i>
                <span slot="title">发帖</span>
              </el-menu-item>
              <el-menu-item
                  index="/personCenter/mycollect"
              >
                <i class="el-icon-document"></i>
                <span slot="title">我的书架</span>
              </el-menu-item>
            </el-menu>
          </el-card>
        </div>
      </el-header>
      <el-main>
        <div class="person_body_right">
          <router-view></router-view>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  autoIndex: '/personCenter/info',
  name: "PersonalCenter",
  data() {
    return {
      nickname: '',
      image1: ''
    }
  },
  methods: {
    returnPage() {
      this.$router.push('/main');
    },
    init() {
      let Info = JSON.parse(window.sessionStorage.getItem("userInfo"));
      if (Info == null) {
        this.nickname = '未登录', this.image1 = 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
      } else {
        this.nickname = Info.nickname, this.image1 = Info.avatar;
      }
    },
  },
  mounted() {
    this.init();
  }
}
</script>

<style scoped>
body {
  font-family: 方正粗黑宋简体 sans-serif;
  width: 1200px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: rgba(255, 255, 255, 1);
  color: #333;
  text-align: center;
}

.person_body_left {


  margin-right: 3%;
  text-align: center;
  width: 1200px;
  right: 20px;
  position: relative;
}

.el-menu-item {
  float: right;
  width: 200px;
}

.text {
  position: relative;
  float: bottom;
  left: 240px;
  bottom: 120px;
  z-index: 10;
  width: 200px;
  letter-spacing: 0.2rem;
  font-size: 2.2rem;
  background-image: -webkit-linear-gradient(left, #147B96, #E6D205 25%, #147B96 50%, #E6D205 75%, #147B96);
  -webkit-text-fill-color: transparent;
  -webkit-background-clip: text;
  -webkit-background-size: 200% 100%;
}
</style>