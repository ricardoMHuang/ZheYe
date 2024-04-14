<template>
  <div>
    <el-card v-if="!operateFlag" style="padding-top: 40px;">
      <el-form label-position="right" label-width="80px" :model="userInfo">
        <el-row style="position:relative;left: 15%;">
          <el-form-item label="头像:" prop="avater">
            <el-upload
                class="avatar-uploader"
                action="https://jsonplaceholder.typicode.com/posts/"
                :show-file-list="false"
                :before-upload="beforeAvatarUpload"
                :on-success="handleAvatarSuccess">
              <img v-if="userInfo.avatar" :src="userInfo.avatar" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-col span="8">
            <el-form-item label="昵称:">
              <el-input v-model="userInfo.nickname"></el-input>
            </el-form-item>
            <el-form-item label="性别:">
              <el-radio v-model="userInfo.sex" label="男"><i class="el-icon-male"></i></el-radio>
              <el-radio v-model="userInfo.sex" label="女"><i class="el-icon-female"></i></el-radio>
            </el-form-item>
            <el-form-item label="手机号码:">
              <el-input v-model="userInfo.phone"></el-input>
            </el-form-item>
            <el-form-item label="兴趣爱好:">
              <el-input v-model="userInfo.hobby"></el-input>
            </el-form-item>
          </el-col>
          <el-col span="8" offset="2">

            <el-form-item label="年龄:">
              <el-select v-model="userInfo.age" @change="yearChange" placeholder="请选择" clearable>
                <el-option
                    v-for="item in years"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="Email:">
              <el-input v-model="userInfo.email"></el-input>
            </el-form-item>
            <el-form-item label="地区:">
              <el-input v-model="userInfo.area"></el-input>
            </el-form-item>
            <el-form-item label="注册日期:" required prop="userInfo.createData">
              <el-date-picker type="date" placeholder="选择日期" v-model="userInfo.createDate"
                              style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-button type="primary" v-loading.fullscreen.lock="fullscreenLoading" @click="submitAll" size="small">确认
        </el-button>
      </el-form>
    </el-card>
    <el-card v-if="operateFlag" style="padding-top: 40px;">
      <el-descriptions class="margin-top" :column="2" border>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-picture-outline"></i>
            头像
          </template>
          <img class="img" :src="userInfo.avatar" alt=""/>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            账户名
          </template>
          {{ userInfo.account }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-custom"></i>
            昵称
          </template>
          {{ userInfo.nickname }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-odometer"></i>
            年龄
          </template>
          {{ userInfo.age + "岁" }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-male"></i>
            <i class="el-icon-female"></i>
            性别
          </template>
          <el-tag size="small">{{ userInfo.sex }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-message"></i>
            邮箱Email
          </template>
          {{ userInfo.email }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-mobile-phone"></i>
            手机号码
          </template>
          {{ userInfo.phone }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-location-outline"></i>
            地区
          </template>
          {{ userInfo.area }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-basketball"></i>
            兴趣爱好
          </template>
          {{ userInfo.hobby }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-date"></i>
            注册日期
          </template>
          {{ userInfo.createDate |formatDate('yyyy-MM-dd') }}
        </el-descriptions-item>
      </el-descriptions>
      <el-button type="primary" v-if="$route.params.id===$store.state.id" v-loading.fullscreen.lock="fullscreenLoading"
                 @click="openFullScreen1" size="small" style="margin: 20px 10px 0 0 ">点击修改
      </el-button>
      <el-button type="warning" @click="quit()" size="small" style="margin: 20px 0 0 0 ">退出登录
      </el-button>
    </el-card>
  </div>
</template>

<script>
/*import { userInfo } from "@/api/user.js";*/
import userApi from "@/../api/user"

export default {
  name: "Info",
  data() {

    return {
      years: [],
      imageUrl: '',
      fullscreenLoading: false,
      operateFlag: true,
      userInfo: {
        avatar: '',
        account: '未填写',
        age: '未填写',
        email: '未填写',
        phone: '未填写',
        area: '未填写',
        createDate: '未填写',
        nickname: '未填写',
        sex: '',
        work: '未填写',
        hobby: '未填写',
        design: '未填写',
      }
    };
  },
  mounted() {

  },
  filters: {
    formatDate: function (value, args) {
      var dt = new Date(value);
      let year = dt.getFullYear();
      let month = dt.getMonth() + 1;
      let date = dt.getDate();
      return `${year}-${month}-${date}`;
    }
  },
  watch: {
    userInfo: {
      deep: true,  // 深度检测 不论你的obj 有多少层
      //只要有一个变化 就会触发 obj 中的一个函数  这个函数叫 handler
      handler: function (newValue, oldValue) {
        //  handler里的this 也是指向  vue实例
      },
    },
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.$message.success("上传成功")
      this.userInfo.avatar = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    openFullScreen1() {
      this.fullscreenLoading = true;
      setTimeout(() => {
        this.fullscreenLoading = false;
        this.operateFlag = false;
      }, 1000);
    },
    submitAll() {
      this.fullscreenLoading = true;
      setTimeout(() => {
        this.fullscreenLoading = false;
        this.operateFlag = true;
        let userInfo = this.userInfo;
        let res = userApi.updateInfo(userInfo);
        window.sessionStorage.setItem("userInfo", JSON.stringify(userInfo));
      }, 1000);
    },
    load() {
      this.userInfo = JSON.parse(window.sessionStorage.getItem("userInfo"));
    },

    /*年龄选择*/
    init() {
      var myDate = new Date;
      var year = myDate.getFullYear();//获取当前年
      this.initSelectYear(year);
    },
    initSelectYear(year) {
      this.years = [];
      for (let i = 0; i < 60; i++) {
        this.years.push({value: (year - i), label: (year - i) + "年"});
      }
    },
    yearChange(value) {
      var myDate = new Date();
      var cur = myDate.getFullYear();
      this.userInfo.age = (cur - value);
    },
    //退出登录
    quit() {
      this.$router.push({
        path: "/login",
      },)
    }
  },
  created() {

    this.init();
    this.load();
  },
};
</script>

<style scoped>
.el-descriptions-item {
  height: 80px;
}

.img {
  width: 80px;
  height: 80px;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}

.avatar-uploader {
  width: 200px;
  position: relative;
  left: 250px;
}


</style>
