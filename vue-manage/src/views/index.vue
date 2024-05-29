<template>
    <div>
      <el-container>
        <!-- 侧边栏 -->
        <el-aside :width="layout.asideWidth"  style="padding-top: 8px;min-height: 100vh; background-color:#b8d2ce;">
          <div class="logo" style="height: 60px;background-color:white;display: flex;align-items: center;padding: 0 5px;margin-top: 2px">
            <img src="@/assets/img/logo.png" height="58px" alt="">
            <span style="font-size: 15px;margin-left: 10px; font-weight: 700;color: #2c302e;">儿童玩具展示系统</span>
          </div>
          <el-menu
          background-color="#b8d2ce"
          router
          active-text-color="#fff"
          :default-active="$route.path"
          >
            <el-menu-item index="/index/user"><span class="el-icon-user"></span> 用户管理</el-menu-item>
            <el-submenu index="/index/home">
              <template slot="title">
                <span class="el-icon-suitcase"></span> 玩具信息管理
              </template>
              <el-menu-item index="/index/toyType">玩具类型</el-menu-item>
              <el-menu-item index="/index/toy">玩具信息</el-menu-item>
              <el-menu-item index="/index/toyBrand">玩具品牌</el-menu-item>
              <el-menu-item index="/index/toyMaterial">玩具材质</el-menu-item>
              <!---->
              <el-menu-item index="/index/toyChart">玩具信息可视化</el-menu-item>
              <!---->
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-container style="background-color: #e8e6e6">
          <!-- 头部 -->
          <el-header class="header" style>
            <el-breadcrumb   style="margin-top: 20px;" separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/' }">主页</el-breadcrumb-item>
              <el-breadcrumb-item :to="{ path: $route.path }">{{ $route.meta.name }}</el-breadcrumb-item>
            </el-breadcrumb>

            <div class="user-info" >
              <el-dropdown placement="bottom">
                <div style="display: flex; align-items: center; cursor: default">
                  <span>{{ user.username }}</span>
                  <img :src="user.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" alt="" style="width: 40px; height: 40px; border-radius: 50%; margin: 0 5px">
                </div>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
          </el-header>
          <!-- 中心内容 -->
          <el-main style="background-color: white;margin:2px 10px 0px">
            <router-view/>
          </el-main>
        </el-container>
      </el-container>
    </div>
</template>
<script>
export default {
   data() {
      return {

        layout:{
          asideWidth:'220px',
        },
        user: JSON.parse(localStorage.getItem('user') || '{}'),

      }
   },
   created(){
   },
   computed:{
   },
   methods:{
      logout(){
        localStorage.removeItem('user')
        this.$router.push('/')
      }
   },
}
</script>
<style lang="scss" scoped>
element.style {
}
//激活的菜单背景色
.el-menu-item.is-active{
  color: rgb(16, 16, 16)!important;
  background-color: rgb(245, 245, 245) !important;
}
//hover的菜单背景色
.el-menu-item:hover{
  background-color: #A2ADB5 !important;
}
// 菜单项的高度 边框等样式设置
.el-menu-item{
  height: 50px !important;
  line-height: 50px !important;
   //border-radius: 25px;
   margin-top: 10px;
}
//头部的样式
.header{
  height: 60px;
  //box-shadow: 2px 0 2px rgb(232, 226, 226);
  background-color: white;
  margin: 10px;
  display: flex;
  justify-content: start;

  .user-info{
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: flex-end;
    padding-right: 5px;
  }
}
</style>
