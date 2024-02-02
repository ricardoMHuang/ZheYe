<template>
  <div class="main-box">
    <div :class="['container', 'container-register', { 'is-txl': isLogin }]">
      <form>
        <h2 class="title">创建账户</h2>
        <span class="text">or use email for registration</span>
        <input class="form__input" type="text" placeholder="Name" v-model="registerForm.username"/>
        <input class="form__input" placeholder="Email" v-model="registerForm.email"/>
        <div style="display: inline-flex">
          <input class="form__input" style="width: 240px;margin-left: -7px;" type="text"
                 placeholder="Please enter email code" v-model="emailCode"/>
          <el-button class="emailJudge" @click="sendEmailCode" v-if="isSend" type="primary" size="small">发送验证码
          </el-button>
          <el-button class="emailJudge" v-if="!isSend">{{ sendMsg }}</el-button>
        </div>
        <input class="form__input" type="password" placeholder="Password" v-model="registerForm.password"/>
        <div class="primary-btn" @click="register">立即注册</div>
      </form>
    </div>
    <div
        :class="['container', 'container-login', { 'is-txl is-z200': isLogin }]"
    >
      <form>
        <h2 class="title">登录</h2>
        <span class="text">or use email for registration</span>
        <input class="form__input" type="text" placeholder="Email" v-model="loginForm.email"/>
        <input class="form__input" type="password" placeholder="Password" v-model="loginForm.password"/>
        <div class="primary-btn" @click="login">立即登录</div>
      </form>
    </div>
    <div :class="['switch', { login: isLogin }]">
      <div class="switch__circle"></div>
      <div class="switch__circle switch__circle_top"></div>
      <div class="switch__container">
        <h2>{{ isLogin ? 'Hello Friend !' : 'Welcome Back !' }}</h2>
        <p>
          {{
            isLogin
                ? 'Enter your personal details and start journey with us'
                : 'To keep connected with us please login with your personal info'
          }}
        </p>
        <div class="primary-btn" @click="isLogin = !isLogin">
          {{ isLogin ? '立即注册' : '立即登录' }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import userApi from "../../api/user"

export default {
  name: 'Login',
  data() {
    return {
      isLogin: false,
      emailCode1: '000000',
      emailCode: '',
      isSend: true,
      sendMsg: "",
      loginForm: {
        email: '',
        password: '',
      },
      registerForm: {
        username: '',
        emailCode: '',
        email: '',
        password: '',
      },
    }
  },
  methods: {
    /*登录验证*/
    async login() {
      let res = await userApi.login(this.loginForm);
      console.log(res);
      res = res.data;
      console.log(res);
      if (res.success) {
        window.sessionStorage.setItem("userInfo", JSON.stringify(res.data));
        await this.$router.replace('/main');
      } else {
        this.$message.error('用户不存在或密码错误');
        return false;
      }
      return;
    },
    /*注册验证*/
    async register() {

      if (this.emailCode1 === this.emailCode) {
        let res = await userApi.register(this.registerForm);
        res = res.data;
        if (res.success) {
          this.$message.success("注册成功");
          this.isLogin = true;
        }

      }
    },
    //发送验证码
    async sendEmailCode() {
      if (!this.registerForm.email)
        this.$message.warning('请输入邮箱');
      else if (this.registerForm.email.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) === -1)
        this.$message.error('邮箱格式错误');
      else {
        let res = await userApi.registerEmail(this.registerForm.email);
        res = res.data;
        if (res.success) {
          this.emailCode1 = res.data;
          this.$message.success("已发送");
        }
        console.log(res.data);
        this.isSend = false;
        let timer = 60;
        this.sendMsg = timer + "s";
        this.timeFun = setInterval(() => {
          --timer;
          this.sendMsg = timer + "s";
          if (timer === 0) {
            this.isSend = true;
            this.sendMsg = "重新发送";
            clearInterval(this.timeFun);
          }
        }, 1000);
      }
    },
  },
}
</script>

<style scoped>
.main-box {

  position: relative;
  left: 50px;
  top: 40px;
  width: 1000px;
  min-width: 1000px;
  min-height: 600px;
  height: 600px;
  padding: 25px;
  background-color: #ecf0f3;
  box-shadow: 10px 10px 10px #d1d9e6, -10px -10px 10px #f9f9f9;
  border-radius: 12px;
  overflow: hidden;
}

.main-box .container {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  width: 600px;
  height: 100%;
  padding: 25px;
  background-color: #ecf0f3;
  transition: all 1.25s;
}

.main-box .container form {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 100%;
  height: 100%;
  color: #a0a5a8;
}

.main-box .container form .title {
  font-size: 34px;
  letter-spacing: 5px;
  font-weight: 700;
  line-height: 3;
  color: #181818;
}

.main-box .container form .text {
  margin-top: 30px;
  margin-bottom: 12px;
}

.main-box .container form .form__input {
  width: 350px;
  height: 40px;
  margin: 4px 0;
  padding-left: 25px;
  font-size: 13px;
  letter-spacing: 0.15px;
  border: none;
  outline: none;
  background-color: #ecf0f3;
  transition: 0.25s ease;
  border-radius: 8px;
  box-shadow: inset 2px 2px 4px #d1d9e6, inset -2px -2px 4px #f9f9f9;
}

.main-box .container form .form__input::placeholder {
  color: #a0a5a8;
}

.main-box .container-register {
  z-index: 100;
  left: calc(100% - 600px);
}

.main-box .container-login {
  left: calc(100% - 600px);
  z-index: 0;
}

.main-box .is-txl {
  left: 0;
  transition: 1.25s;
  transform-origin: right;
}

.main-box .is-z200 {
  z-index: 200;
  transition: 1.25s;
}

.main-box .switch {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 400px;
  padding: 50px;
  z-index: 200;
  transition: 1.25s;
  background-color: #ecf0f3;
  overflow: hidden;
  box-shadow: 4px 4px 10px #d1d9e6, -4px -4px 10px #f9f9f9;
  color: #a0a5a8;
}

.main-box .switch .switch__circle {
  position: absolute;
  width: 500px;
  height: 500px;
  border-radius: 50%;
  background-color: #ecf0f3;
  box-shadow: inset 8px 8px 12px #d1d9e6, inset -8px -8px 12px #f9f9f9;
  bottom: -60%;
  left: -60%;
  transition: 1.25s;
}

.main-box .switch .switch__circle_top {
  top: -30%;
  left: 60%;
  width: 300px;
  height: 300px;
}

.main-box .switch .switch__container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  position: absolute;
  width: 400px;
  padding: 50px 55px;
  transition: 1.25s;
}

.main-box .switch .switch__container h2 {
  font-size: 34px;
  font-weight: 700;
  line-height: 3;
  color: #181818;
}

.main-box .switch .switch__container p {
  font-size: 14px;
  letter-spacing: 0.25px;
  text-align: center;
  line-height: 1.6;
}

.main-box .login {
  left: calc(100% - 400px);
}

.main-box .login .switch__circle {
  left: 0;
}

.main-box .primary-btn {
  width: 180px;
  height: 50px;
  border-radius: 25px;
  margin-top: 50px;
  text-align: center;
  line-height: 50px;
  font-size: 14px;
  letter-spacing: 2px;
  background-color: #4b70e2;
  color: #f9f9f9;
  cursor: pointer;
  box-shadow: 8px 8px 16px #d1d9e6, -8px -8px 16px #f9f9f9;
}

.main-box .primary-btn:hover {
  box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, 0.5), -4px -4px 6px 0 rgba(116, 125, 136, 0.5), inset -4px -4px 6px 0 rgba(255, 255, 255, 0.2), inset 4px 4px 6px 0 rgba(0, 0, 0, 0.4);
}

.emailJudge {
  width: 90px;
  height: 40px;
  margin-left: 10px;
  margin-top: 5px;
}
</style>

