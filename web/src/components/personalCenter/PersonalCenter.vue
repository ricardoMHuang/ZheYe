<template>
    <div style="text-align: center;">
      <el-container>
        <el-header>
          <el-page-header @back="returnPage" content="个人中心">
          </el-page-header>
          <el-card :body-style="{ padding: '0px' }" style="display: flex;justify-content: center">
            <el-menu
                router
                active-text-color="#00c3ff"
                mode="horizontal"
                :default-active="autoIndex"
            >
              <el-menu-item index="/personCenter/info">
                <i class="el-icon-user"></i>
                <span slot="title">个人简介</span>
              </el-menu-item>
              <el-menu-item index="/personCenter/groupManage">
                <i class="el-icon-edit-outline"></i>
                <span slot="title">小组管理</span>
              </el-menu-item>
              <el-menu-item index="/personCenter/myArticle">
                <i class="el-icon-news"></i>
                <span slot="title">消息</span>
              </el-menu-item>
              <el-menu-item index="/personCenter/myCollect">
                <i class="el-icon-tickets"></i>
                <span slot="title">书架</span>
              </el-menu-item>

            </el-menu>
          </el-card>
      </el-header>
        <el-main style="position:relative;top:100px;width: 1200px;margin: auto;">
          <router-view></router-view>
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
      image1: '',
      autoIndex: '/personCenter/info',
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