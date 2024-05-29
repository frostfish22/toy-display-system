<template>
  <div>
    <div class="top" style="width: 1200px; margin: 20px auto 40px;height: 40px;line-height: 40px">
      <h1 style="color: white;height: 60px;line-height: 60px;background-color: #95c5b9;margin: 40px 0 20px;padding-left: 10px;">（{{toyInfo.name}}）详情</h1>
    </div>
    <div class="car">
      <div class="left">
        <el-carousel trigger="click" style="width: 450px" >
          <el-carousel-item style="width: 450px" v-for="item in fileList" :key="item">
            <img :src=item.url width="350px" alt="">
          </el-carousel-item>
        </el-carousel>
      </div>
      <div class="right">
        <div class="car-desc"><span>玩具名称</span>{{ toyInfo.name }}</div>
        <div class="car-desc"><span>详细类型</span>{{ toyInfo.detailedType }}</div>
        <div class="car-desc"><span>适用年龄</span>{{ toyInfo.applicableAge }}</div>
        <div class="car-desc"><span>价格</span>{{ toyInfo.price }}元</div>
        <div class="car-desc"><span>样式</span>{{ toyInfo.style }}</div>
      </div>
    </div>
    <div style="width: 1200px;margin: 10px auto;border-bottom: 1px solid grey;font-size: 15px;font-weight: bold;">玩具详情</div>
    <div class="w-e-text">
      <div v-html="toyInfo.details"></div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  data() {
    return {
      id:this.$route.query.id,
      toyInfo:{},
      fileList:[],
    }
  },
  created() {
    this.getCarDetails(this.id)
  },
  methods: {
    getCarDetails(id){
      request.get(`/toy/${id}`).then(res=>{
        if (res.code==='200'){
          this.toyInfo = res.data
          const urls = this.toyInfo.image.split(',')
          for(let i = 0; i < urls.length; i++) {
            const obj = {
              name:' ',
              url: urls[i],
            };
            this.fileList.push(obj);
          }
          console.log(this.fileList)
        }
      })
    },
  }
}
</script>

<style scoped lang="scss">
.car{
  width: 1200px;
  margin: 20px auto;
  display: flex;
  background-color: #f5f9f9;
  padding: 0 20px;

  .left{
    width: 600px;
    height: 400px;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-right: 20px;
    padding: 10px;
    transition: all 0.1s;
    cursor: pointer;
    &:hover{
      scale: 1.02;
    }
  }
  .right{
    padding-top: 20px;
    width: 600px;
    border-bottom: 1px solid #e8e2e2;
    height: 400px;
    .car-desc{
      display: flex;
      height: 40px;
      font-size: 17px;
      margin-top: 20px;
      line-height: 40px;
      margin-left: 80px;
      font-weight: normal;
      border-bottom: 0.5px solid #d9d8c1;
      span{
        font-size: 17px;
        font-weight: bold;
        margin-right: 150px;
        width: 100px;
      }
    }
  }
}
.comment{
  width: 1200px;
  margin: 10px auto;
}
.w-e-text{
  width: 1200px;
  line-height: 40px;
  font-size: 15px;
  margin: 0 auto;
  margin-bottom: 40px;
}
</style>
