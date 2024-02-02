<template>
  <div>
    <el-container>
      <!--      导航栏-->
      <el-header height="60px">
        <el-image :src="require('@/assets/logo.png')"
                  style=" height:50px;float: left; border-radius: 5px;left: 10px;top:5px;"></el-image>
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" active-text-color="#ffd04b"
                 router>
          <el-menu-item index="/book" style="left:10px;width:100px;font-weight: bold;">读书</el-menu-item>
          <el-menu-item index="/group" style="width:100px;font-weight: bold;">小组</el-menu-item>
        </el-menu>
        <div style="width: 200px;height: 60px;float: right; ">
          <div class="avater" @click="infoView">
            <el-avatar shape="circle" :size="50" :src="useInfo.circleUrl"></el-avatar>
          </div>
          <div class="username">
            <span @click="infoView">{{ useInfo.username }}</span>
          </div>
        </div>

        <div
            style="width: 450px;height: 30px;float: left;left:100px;top:15px;position: relative;box-shadow: #333333;padding:0px;background-color: #ffffff;border-radius: 8px; ">
          <el-autocomplete
              row="20"
              class="inline-input"
              v-model="state1"
              :fetch-suggestions="querySearch"
              placeholder="请输入作者"
              @select="handleSelect"
          ></el-autocomplete>
          <i class="el-icon-search" style="color: rgba(118,148,255,1);" @click="searchBook"></i>
        </div>
      </el-header>

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
        this.$router.push('/personCenter')
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
        {"value": "茶芝兰（奶茶，手抓饼）", "address": "上海市普陀区同普路1435号"},
        {"value": "十二泷町", "address": "上海市北翟路1444弄81号B幢-107"},
        {"value": "星移浓缩咖啡", "address": "上海市嘉定区新郁路817号"},
        {"value": "阿姨奶茶/豪大大", "address": "嘉定区曹安路1611号"},
        {"value": "新麦甜四季甜品炸鸡", "address": "嘉定区曹安公路2383弄55号"},
        {"value": "Monica摩托主题咖啡店", "address": "嘉定区江桥镇曹安公路2409号1F，2383弄62号1F"},
        {"value": "浮生若茶（凌空soho店）", "address": "上海长宁区金钟路968号9号楼地下一层"},
        {"value": "NONO JUICE  鲜榨果汁", "address": "上海市长宁区天山西路119号"},
        {"value": "CoCo都可(北新泾店）", "address": "上海市长宁区仙霞西路"},
        {"value": "快乐柠檬（神州智慧店）", "address": "上海市长宁区天山西路567号1层R117号店铺"},
        {"value": "Merci Paul cafe", "address": "上海市普陀区光复西路丹巴路28弄6号楼819"},
        {"value": "猫山王（西郊百联店）", "address": "上海市长宁区仙霞西路88号第一层G05-F01-1-306"},
        {"value": "枪会山", "address": "上海市普陀区棕榈路"},
        {"value": "纵食", "address": "元丰天山花园(东门) 双流路267号"},
        {"value": "钱记", "address": "上海市长宁区天山西路"},
        {"value": "壹杯加", "address": "上海市长宁区通协路"},
        {"value": "唦哇嘀咖", "address": "上海市长宁区新泾镇金钟路999号2幢（B幢）第01层第1-02A单元"},
        {"value": "爱茜茜里(西郊百联)", "address": "长宁区仙霞西路88号1305室"},
        {"value": "爱茜茜里(近铁广场)", "address": "上海市普陀区真北路818号近铁城市广场北区地下二楼N-B2-O2-C商铺"},
        {"value": "鲜果榨汁（金沙江路和美广店）", "address": "普陀区金沙江路2239号金沙和美广场B1-10-6"},
        {"value": "开心丽果（缤谷店）", "address": "上海市长宁区威宁路天山路341号"},
        {"value": "超级鸡车（丰庄路店）", "address": "上海市嘉定区丰庄路240号"},
        {"value": "妙生活果园（北新泾店）", "address": "长宁区新渔路144号"},
        {"value": "香宜度麻辣香锅", "address": "长宁区淞虹路148号"},
        {"value": "凡仔汉堡（老真北路店）", "address": "上海市普陀区老真北路160号"},
        {"value": "港式小铺", "address": "上海市长宁区金钟路968号15楼15-105室"},
        {"value": "蜀香源麻辣香锅（剑河路店）", "address": "剑河路443-1"},
        {"value": "北京饺子馆", "address": "长宁区北新泾街道天山西路490-1号"},
        {"value": "饭典*新简餐（凌空SOHO店）", "address": "上海市长宁区金钟路968号9号楼地下一层9-83室"},
        {"value": "焦耳·川式快餐（金钟路店）", "address": "上海市金钟路633号地下一层甲部"},
        {"value": "动力鸡车", "address": "长宁区仙霞西路299弄3号101B"},
        {"value": "浏阳蒸菜", "address": "天山西路430号"},
        {"value": "四海游龙（天山西路店）", "address": "上海市长宁区天山西路"},
        {"value": "樱花食堂（凌空店）", "address": "上海市长宁区金钟路968号15楼15-105室"},
        {"value": "壹分米客家传统调制米粉(天山店)", "address": "天山西路428号"},
        {"value": "福荣祥烧腊（平溪路店）", "address": "上海市长宁区协和路福泉路255弄57-73号"},
        {"value": "速记黄焖鸡米饭", "address": "上海市长宁区北新泾街道金钟路180号1层01号摊位"},
        {"value": "红辣椒麻辣烫", "address": "上海市长宁区天山西路492号"},
        {"value": "(小杨生煎)西郊百联餐厅", "address": "长宁区仙霞西路88号百联2楼"},
        {"value": "阳阳麻辣烫", "address": "天山西路389号"},
        {"value": "南拳妈妈龙虾盖浇饭", "address": "普陀区金沙江路1699号鑫乐惠美食广场A13"}
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

.el-header {
  background-color: #f6f6f1;
  color: #333;
  height: 300px;
  line-height: 15px;
  width: 1200px;
  padding: 0px;
  z-index: 1000;
  position: fixed;
  margin: auto;
}

.el-footer {
  background-color: #f6f6f1;
  color: #333;
  line-height: 15px;
  width: 1200px;
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

.avater {
  float: left;
  left: 20px;
  position: relative;
  top: 4px;
}

.avater:hover {
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
  float: left;
  left: 30px;
  top: 25px;
  position: relative;
  font-weight: bold;
  font-family: 幼圆;
}


</style>
<style>
.el-input__inner {
  height: 30px;
  border: none;
  width: 400px;
  border-radius: 0px;
}

.toTop {
  width: 64px;
  height: 64px;
  position: fixed;
  right: 60px;
  bottom: 20px;
}
</style>