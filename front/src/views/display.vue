<template>
  <div style="width: 1200px;;margin: 0 auto;">
    <el-divider>
      <h1 style="color:#3cc7a7;">玩具信息对比</h1>
    </el-divider>
    <div style="width: 1000px;margin-left:200px; margin-top: 50px">
      <div class="select" style="display: flex">
        <div class="region1" style="width: 200px;font-size: 20px;height: 40px;  line-height: 40px;">属性</div>
        <el-select v-model="name1" clearable filterable placeholder="请选择" style="margin:0 10px">
          <el-option
              v-for="item in options"
              :key="item.name"
              :label="item.name"
              @click.native="load1(item.id)"
              :value="item.name">
          </el-option>
        </el-select>
        <el-select v-model="name2" clearable filterable placeholder="请选择">
          <el-option
              v-for="item in options"
              :key="item.name"
              :label="item.name"
              @click.native="load2(item.id)"
              :value="item.name">
          </el-option>
        </el-select>
      </div>
      <div class="region">
        <div class="field1">
          <div  class="name1">玩具封面</div>
          <img :src=toy1.cover style="border-bottom: 1px solid #c7c5c5;height: 200px;margin-right: 10px;" width="220px"  class="image">
          <img :src=toy2.cover style="border-bottom: 1px solid #c7c5c5;height: 200px;margin-right: 10px;" width="220px"  class="image">
<!--          <div style="cursor: pointer" @click="toDetail(toy1.id)" class="content">{{toy1.name}}</div>-->
<!--          <div style="cursor: pointer" @click="toDetail(toy2.id)" class="content">{{toy2.name}}</div>-->
        </div>
        <div class="field">
          <div  class="name">玩具名称</div>
          <div style="cursor: pointer" @click="toDetail(toy1.id)" class="content">{{toy1.name}}</div>
          <div style="cursor: pointer" @click="toDetail(toy2.id)" class="content">{{toy2.name}}</div>
        </div>

        <div class="field">
          <div class="name">详细类型</div>
          <div class="content">{{toy1.detailedType}}</div>
          <div class="content">{{toy2.detailedType}}</div>
        </div>
        <div class="field">
          <div class="name">适合年龄</div>
          <div class="content">{{toy1.applicableAge}}</div>
          <div class="content">{{toy2.applicableAge}}</div>
        </div>
        <div class="field">
          <div class="name">款式</div>
          <div class="content">{{toy1.style}}</div>
          <div class="content">{{toy2.style}}</div>
        </div>
        <div class="field">
          <div class="name">尺寸</div>
          <div class="content">{{toy1.size}}</div>
          <div class="content">{{toy2.size}}</div>
        </div>
        <div class="field">
          <div class="name">价格</div>
          <div class="content">{{toy1.price}}</div>
          <div class="content">{{toy2.price}}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  data() {
    return {
      name1:'',
      name2: '',
      options:[],
      toy1:{},
      toy2:{},
    }
  },
  created() {
    this.loadToy()
    // this.load1()
    // this.load2()
  },
  methods: {
    loadToy(){
      request.get('/toy/getAll').then(res=>{
        if (res.code==='200'){
          this.options = res.data
        }
      })
    },
    load1(id){
      request.get(`/toy/${id}`).then(res=>{
        if (res.code==='200'){
          this.toy1 = res.data
        }
      })
    },
    load2(id){
      request.get(`/toy/${id}`).then(res=>{
        if (res.code==='200'){
          this.toy2 = res.data
        }
      })
    },
    toDetail(id){
      const targetPath = '/details';
      this.$router.push({ path: targetPath, query: { id: id } });
    },
  }
}
</script>

<style scoped lang="scss">
.region{
  width: 1000px;
}
.field{
  height: 50px;
  width: 1000px;
  font-size: 15px;
  line-height: 50px;
  display: flex;
  justify-content: flex-start;
  .name {
    width: 200px;
    margin-right: 10px;
    border-bottom: 1px solid #c7c5c5;

  }
  .content {
    width: 220px;
    margin-right: 10px;
    border-bottom: 1px solid #c7c5c5;

  }
}
.field1{
  height: 200px;
  width: 1000px;
  font-size: 15px;
  line-height: 200px;
  display: flex;
  justify-content: flex-start;
  .name1 {
    width: 200px;
    margin-right: 10px;
    border-bottom: 1px solid #c7c5c5;
  }
  .content {
    width: 220px;
    margin-right: 10px;
    border-bottom: 1px solid #c7c5c5;
  }
}
</style>
