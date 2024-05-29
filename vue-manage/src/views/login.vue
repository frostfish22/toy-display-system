<template>
    <div class="loginPage" >
       <div class="container">
        <div class="left">
            <img src="@/assets/img/login.png" alt="">
        </div>
        <div class="right">
            <h1>儿童玩具展示系统</h1>
            <el-form class="loginForm" :rules="rules" ref="loginData"  :model="loginData" >
                <el-form-item prop="username">
                 <el-input  v-model="loginData.username" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                 <el-input v-model="loginData.password" placeholder="请输入密码" show-password></el-input>
                </el-form-item>
                <div class="button-group">
                    <el-button @click="login" type="primary" style="flex: 2;border: none;background-color: rgb(186 190 189);" round>登录</el-button>
                    <el-button  @click="$router.push('/register')" type="primary" style="flex: 1;border: none;background-color: rgb(186 190 189)" round>注册</el-button>
                </div>
            </el-form>
        </div>
       </div>
    </div>
</template>
<script>
import request from '@/utils/request'
export default {
   data() {
      return {
        loginData:{},
        rules: {
          username: [
            { required: true, message: '请输入活动名称', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请填写密码', trigger: 'blur' }
          ]
        }
      }
   },
   created(){
   },
   computed:{
   },
   methods:{
    login(){
        this.$refs['loginData'].validate((valid) => {
          if (valid) {
            request.post('/web/user/login', this.loginData).then(res => {
            if (res.code === '200') {
              localStorage.setItem("user", JSON.stringify(res.data))  // 存储用户数据
              this.$router.push('/index')  // 跳转主页
              this.$message.success('登录成功')
            } else {
              this.$message.error(res.message)
            }
          })
          } else {
            console.log('表单验证失败');
            return false;
          }
        });

    }
   },
}
</script>
<style lang="scss" scoped>
.container {
  overflow: hidden;
  border-radius: 20px;
  transition: box-shadow 0.3s ease;
  &:hover{
    box-shadow: 4px 2px 12px 7px rgb(80 135 92 / 30%);
  }
}
.loginPage{
    display: flex;
    align-items: center;
    height: 100vh;
    .container{
      margin: 0 auto;
      width: 800px;
      height: 400px;
      display: flex;
      .left {
        img{
            width: 400px;
            height: 400px;
        }
      }
      .right{
        width: 400px;
        height: 400px;
        padding: 20px 10px;
        text-align: center;
        background-color: rgb(240, 243, 240);
        .loginForm{
            padding: 0 20px;
            .button-group{
                margin-top: 50px;
                display: flex;
            }
        }
        h1{
            margin-bottom: 50px;
        }
      }
    }
}
</style>
