<template>

  <body>
    <el-card id="poster">
      <el-form ref="ruleForm2"
               label-width="0px"
               class="login_container">
        <h3 class="login_title">后台管理</h3>
        <el-form-item>
          <el-input type="text"
                    prefix-icon="el-icon-user"
                    v-model="loginForm.username"
                    placeholder="账号"
                    auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input type="password"
                    prefix-icon="el-icon-lock"
                    v-model="loginForm.password"
                    placeholder="密码"
                    auto-complete="off"
                    show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"
                     @click="login"
                     style="width: 100%;background: #505458;border :none">登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </body>
</template>
<script>
import {login} from "@/api/login";
export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        username: 'hs',
        password: '123456'
      }
    }
  },
  methods: {
    login () {
        login(this.loginForm.username,this.loginForm.password)
        .then(successResponse => {
          if (successResponse.code === 200) {
            localStorage.setItem('user', JSON.stringify(successResponse.data.user))
            localStorage.setItem('token', successResponse.data.token)
            this.$router.replace({ path: '/' })
            this.successMsg('登录成功')
          } else {
            this.errorMsg(successResponse.data.message)
          }
        })
        .catch(failResponse => {
          this.errorMsg(failResponse+'请求失败')
        })
    }
  }
}
</script>
<style>
.login_container {
  border-radius: 15px;
  background-clip: padding-box;
  /* margin 边缘（与外部的距离） */
  margin: 90px auto;
  width: 350px;
  /* 填充（主要是内部边缘的填充）分别是上 右 下 左 */
  padding: 35px 35px 35px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
#poster {
  background: url('~@/assets/BingWallpaper.jpg') no-repeat;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
body {
  margin: 0px;
}
</style>
