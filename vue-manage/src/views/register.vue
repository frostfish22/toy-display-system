<template>
    <div class="loginPage" >
       <div class="container">
        <div class="left">
            <img src="@/assets/img/register.png" alt="">
        </div>
        <div class="right">
            <h1>儿童玩具展示系统</h1>
            <el-form class="registerForm" :rules="rules" ref="registerForm"  :model="registerData" >
                <el-form-item prop="username">
                 <el-input v-model="registerData.username" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                 <el-input v-model="registerData.password"  show-password placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item prop="confirmPass">
                  <el-input placeholder="请确认密码" show-password  v-model="registerData.confirmPass"></el-input>
                </el-form-item>
                <el-button @click="register" type="primary" style="width: 100%;border: none;background-color: rgb(186 190 189)" round>注册</el-button>
            </el-form>
        </div>
       </div>
    </div>
</template>
<script>
import request from '@/utils/request'
export default {
   data() {
      const validatePassword = (rule, confirmPass, callback) => {
        if (confirmPass === '') {
          callback(new Error('请确认密码'))
        } else if (confirmPass !== this.registerData.password) {
          callback(new Error('两次输入的密码不一致'))
        } else {
          callback()
        }
      }

      return {
        registerData:{},
        rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
        confirmPass: [
          // 自定义校验规则
          { validator: validatePassword, trigger: 'blur' }
        ]
      }
      }
   },
   created(){
   },
   computed:{
   },
   methods:{
    register(){
        this.$refs['registerForm'].validate((valid) => {
          if (valid) {
            request.post('/web/user/register', this.registerData).then(res => {
            if (res.code === '200') {
              this.$router.push('/')  // 跳转登录
              this.$message.success('注册成功')
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

.loginPage{
    display: flex;
    align-items: center;

  height: 100vh;
    .container{
      margin: 0 auto;
      width: 800px;
      height: 400px;
      display: flex;
      border-radius: 20px;
      transition: box-shadow 0.3s ease;
      &:hover{
        box-shadow: 4px 2px 12px 7px rgb(80 135 92 / 30%);
      }
      // 调换左右方向
      // flex-direction: row-reverse;
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
        background-color: rgb(184, 210, 206);
        .registerForm{
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
