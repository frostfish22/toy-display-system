<template>
  <div>
    <div class="header">
      <div class="left">
        <img src="../assets/img/logo.png" width="100px" alt="">
        <h2>儿童玩具展示系统</h2>
      </div>
      <div class="center">
        <el-menu :default-active="$route.path" text-color="black" style="font-size: 20px" router class="el-menu-demo" mode="horizontal" >
          <el-menu-item index="/home">玩具检索</el-menu-item>
          <el-menu-item index="/display">玩具对比</el-menu-item>
        </el-menu>
      </div>
    </div>
    <router-view ></router-view>

  </div>
</template>
<script>
export default {
  data() {
    return {
      user: JSON.parse(localStorage.getItem('user') || '{}'),

    }
  },
  created(){
  },
  computed:{
  },
  methods:{
    logout(){
      localStorage.removeItem("user")
      this.$router.push('/index/home')
      location.reload()
    },
    updateUser(){
      this.user = JSON.parse(localStorage.getItem('user') || '{}')   // 重新获取下用户的最新信息

    },
  },
}
</script>
<style lang="scss" scoped>
.header{
  display: flex;
  height: 60px;
  border-bottom: 1px solid #e8e2e2;
  .left{
    width: 400px;
    display: flex;
    line-height: 60px;
    h2{
      margin-left: 15px;
      font-size: 28px;
      color: #9a7b7b;
    }
  }
  .center{
    flex: 1;
    font-size: 20px;
  }
  .right{
    width: 150px;
    line-height: 60px;
    margin-right: 20px;
  }
}

.el-dropdown-menu__item, .el-menu-item {
  font-size: 15px;

}
</style>
