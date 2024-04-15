<template>
  <div>
    <el-container>
      <!--      导航栏-->
      <div class="headerFather">
        <el-header class="main-header">
          <el-image :src="require('@/assets/logo.png')" style="padding: 5px">

          </el-image>
          <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal"
                   style="left: 20px;background-color: rgba(255,255,255,0)" active-text-color="#ffd04b" router>
            <el-menu-item index="/book">读书</el-menu-item>
            <el-menu-item index="/group">小组</el-menu-item>
          </el-menu>
          <!--          搜素-->

          <div style="margin:10px;border-radius: 8px; ">
            <el-autocomplete
                row="20"
                style="width: 250px"
                v-model="state1"
                :fetch-suggestions="querySearch"
                placeholder="请输入作者"
                @select="handleSelect"
            ></el-autocomplete>
            <i class="el-icon-search" style="color: rgba(118,148,255,1); margin-left: 15px" @click="searchBook"></i>
          </div>
          <!--头像-->
          <div style="padding: 5px 0">
            <div class="avatar" @click="infoView">
              <el-avatar shape="circle" :size="40" :src="useInfo.circleUrl"></el-avatar>
              <span @click="infoView" class="username">{{ useInfo.username }}</span>
            </div>
          </div>

        </el-header>
      </div>


      <el-container style="margin-top: 63px">
        <router-view/>
      </el-container>

      <!--        底部导航栏-->
      <el-footer height="140px">
        <div style="position: relative;left: 35%; width: 300px;margin: 0px;">
          <ul id="nav">
            <li><a href="">关于</a></li>
            <li><a href="">博客</a></li>
            <li><a href="">网站首页</a></li>
          </ul>
          <hr style="height:1px;border:none;border-top:1px solid #000;width: 220px;float:left;left: 70px;position: relative;top: 10px;"/>
          <el-image :src="require('@/assets/新浪微博.png')"
                    style="width: 45px;height:45px;float: left;position: relative;top: 37px;"/>
          <el-image :src="require('@/assets/邮件.png')"
                    style="width: 50px;height:50px;bottom:12px;position: relative;left:80px;"/>
        </div>

      </el-footer>
      <img src="//pweb.d.ireader.com/static/images/common/go_top.png" :class="{'toTop':display}" @click="toTop"
           alt="返回顶部">
    </el-container>
  </div>
</template>

<script>


export default {
  name: "Main",
  data() {
    return {
      display: false,
      restaurants: [],
      state1: '',
      state2: '',
      useInfo: {
        circleUrl: '',
        username: ''
      },
      activeIndex: '/book',
    };
  },
  methods: {
    init() {
      let useInfo = JSON.parse(window.sessionStorage.getItem('userInfo'));
      if (useInfo === null) {
        this.useInfo.circleUrl = 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', this.useInfo.username = '未登录'
      } else {
        this.useInfo.circleUrl = useInfo.avatar;
        this.useInfo.username = useInfo.nickname;
      }
    },
    toTop() {
      window.scroll({
        top: 0,
        left: 0,
        behavior: "smooth"
      })
    },
    handleScroll() {
      this.display = window.pageYOffset >= 30;
    },
    infoView() {
      if (this.useInfo.username === "未登录") {
        alert("请先登录")
        this.$router.push('/login')
      } else {
        console.log(this.useInfo)
        console.log("已登录")
        this.$router.push('/personCenter/info')
      }
    },
    searchBook() {
      this.$message.warning("还未实现");
    },
    querySearch(queryString, cb) {
      var restaurants = this.restaurants;
      var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    loadAll() {
      return [
        {"value": "三全鲜食（北新泾店）", "address": "长宁区新渔路144号"},
        {"value": "Hot honey 首尔炸鸡（仙霞路）", "address": "上海市长宁区淞虹路661号"},
        {"value": "新旺角茶餐厅", "address": "上海市普陀区真北路988号创邑金沙谷6号楼113"},
        {"value": "泷千家(天山西路店)", "address": "天山西路438号"},
        {"value": "胖仙女纸杯蛋糕（上海凌空店）", "address": "上海市长宁区金钟路968号1幢18号楼一层商铺18-101"},
        {"value": "贡茶", "address": "上海市长宁区金钟路633号"},
        {"value": "豪大大香鸡排超级奶爸", "address": "上海市嘉定区曹安公路曹安路1685号"},
      ];
    },
    handleSelect(item) {
      console.log(item);
    }
  },
  mounted() {
    this.restaurants = this.loadAll();
    window.addEventListener('scroll', this.handleScroll);
    this.init();
  }
}
</script>

<style scoped>


#nav ul {
  list-style: none;
}

#nav li {
  display: block;
  float: left;
  margin-left: 40px;
}

#nav a {
  text-decoration: none;
}


.el-footer {
  background-color: #f6f6f1;
  color: #333;
  line-height: 15px;
  /*width: 1200px;*/
  padding: 0px;
  /*margin-left: -160px;*/
}


.el-menu-demo {
  border: none;
  height: 60px;
  background-color: #f6f6f1;
  align: center;
  float: left;
  left: 10px;
}

.el-menu-item.is-active {
  background-color: rgba(53, 53, 53, 0) !important;
}

.el-menu-item:hover {
  background-color: rgba(53, 53, 53, 0) !important;
}

.avatar {
  margin-right: 20px;
}

.avatar:hover {
  animation: tada 1s ease both;
  -moz-animation: tada 1s ease both;
  cursor: pointer;
}

@keyframes tada {
  0% {
    -webkit-transform: scale(1);
  }

  10%,
  20% {
    -webkit-transform: scale(0.9) rotate(-3deg);
  }

  30%,
  50%,
  70%,
  90% {
    -webkit-transform: scale(1.1) rotate(3deg);
  }

  40%,
  60%,
  80% {
    -webkit-transform: scale(1.1) rotate(-3deg);
  }

  100% {
    -webkit-transform: scale(1) rotate(0);
  }
}

.username {
  position: relative;
  margin: 0 5px;
  top: -10px;
  align-content: center;
  font-weight: bold;
  font-family: 幼圆;
}


</style>
<style>

.toTop {
  width: 64px;
  height: 64px;
  position: fixed;
  right: 60px;
  bottom: 20px;
}

.el-header {
  line-height: 15px;
  padding: 0;
  z-index: 1000;
  position: fixed;
  width: 100%;

}

.main-header {
  display: flex;
  justify-content: space-between;
  border: solid rgba(22, 22, 22, 0.1);
  transition: 1s;
  transition-property: border;
}

.main-header:hover {
  border: solid rgba(22, 22, 22, 0.8);

}

.main-header::after {
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
  content: "";
  filter: blur(5px);
  width: 100%;
  height: 100%;
  background-image: url("../../assets/photo2.webp");
}

.headerFather {
  position: relative;
}
</style>