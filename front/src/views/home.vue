<template>
  <div class="container">
    <el-divider>
      <h1 style="color: #7ecbd3">玩具信息</h1>
    </el-divider>
    <div class="search" style="display: flex; margin: 30px 0; width: 600px">
      <el-input placeholder="请输入内容" v-model="name">
        <template slot="prepend">玩具名称</template>
      </el-input>
      <el-button type="success" @click="search" style="margin-left: 20px"
        >搜索</el-button
      >
    </div>
    <div class="condition">
      <div
        class="c1"
        style="display: flex; align-items: center; margin-bottom: 14px"
      >
        <div
          style="
            font-size: 15px;
            border-radius: 10px;
            height: 40px;
            width: 80px;
            text-align: center;
            padding-top: 10px;
            background-color: #f5e4b0;
            margin-right: 40px;
          "
        >
          玩具类别
        </div>
        <div
          style="
            font-size: 15px;
            border-right: 1px solid grey;
            padding: 0 20px;
            cursor: pointer;
          "
          v-for="item in types"
          @click="choose(item.id)"
          :class="{ active: selected1 === item.id }"
        >
          {{ item.typeName }}
        </div>
      </div>
      <div
        class="c1"
        style="display: flex; align-items: center; margin-bottom: 14px"
      >
        <div
          style="
            font-size: 15px;
            border-radius: 10px;
            height: 40px;
            width: 80px;
            text-align: center;
            padding-top: 10px;
            background-color: #f5e4b0;
            margin-right: 40px;
          "
        >
          玩具品牌
        </div>
        <div
          style="
            font-size: 15px;
            border-right: 1px solid grey;
            padding: 0 20px;
            cursor: pointer;
          "
          v-for="item in brands"
          @click="choose1(item.id)"
          :class="{ active: selected2 === item.id }"
        >
          {{ item.brandName }}
        </div>
      </div>
      <div
        class="c1"
        style="display: flex; align-items: center; margin-bottom: 14px"
      >
        <div
          style="
            font-size: 15px;
            border-radius: 10px;
            height: 40px;
            width: 80px;
            text-align: center;
            padding-top: 10px;
            background-color: #f5e4b0;
            margin-right: 40px;
          "
        >
          玩具材质
        </div>
        <div
          style="
            font-size: 15px;
            border-right: 1px solid grey;
            padding: 0 20px;
            cursor: pointer;
          "
          v-for="item in materials"
          @click="choose2(item.id)"
          :class="{ active: selected3 === item.id }"
        >
          {{ item.material }}
        </div>
      </div>
    </div>
    <div v-if="toys.length > 0" class="list">
      <el-row :gutter="10">
        <el-col :span="6" v-for="item in toys">
          <el-card
            class="car-desc"
            :shadow="'hover'"
            @click.native="toDetail(item.id)"
          >
            <img :src="item.cover" width="260px" height="180px" class="image" />
            <div
              style="
                padding: 14px;
                display: flex;
                justify-content: space-between;
                padding: 0 20px;
              "
            >
              <span style="font-size: 18px">{{ item.name }}</span>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <div v-else>
      <el-empty description="暂无信息"></el-empty>
    </div>
    <!-- 分页-->
    <div style="margin: 10px 0">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNumber"
        :page-size="pageSize"
        :page-sizes="[1, 5, 10, 20]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  data() {
    return {
      toys: [],
      pageNumber: 1, // 当前的页码
      pageSize: 5, // 每页显示的个数
      total: 0, //总数
      types: [],
      brands: [],
      materials: [],
      selected1: null,
      selected2: null,
      selected3: null,
      name: "",
      name1: null,
      name2: null,
      name3: null,
    };
  },
  created() {
    this.loadToy();
    this.loadTypes();
    this.loadMaterial();
    this.loadBrand();
  },
  methods: {
    choose(id) {
      if (this.name1 == id) {
        this.name1 = null;
        this.selected1 = null;
        this.loadToy();
        return;
      }
      this.selected1 = id;
      this.name1 = id;
      this.loadToy();
    },
    choose1(id) {
      if (this.name2 == id) {
        this.name2 = null;
        this.selected2 = null;
        this.loadToy();
        return;
      }
      this.selected2 = id;
      this.name2 = id;
      this.loadToy();
    },
    choose2(id) {
      if (this.name3 == id) {
        this.name3 = null;
        this.selected3 = null;
        this.loadToy();
        return;
      }
      this.selected3 = id;
      this.name3 = id;
      this.loadToy();
    },
    search() {
      this.loadToy();
    },
    loadTypes() {
      request.get("/toyType/getAll").then((res) => {
        if (res.code === "200") {
          this.types = res.data;
        }
      });
    },
    loadMaterial() {
      request.get("/toyMaterial/getAll").then((res) => {
        if (res.code === "200") {
          this.materials = res.data;
        }
      });
    },
    loadBrand() {
      request.get("/toyBrand/getAll").then((res) => {
        if (res.code === "200") {
          this.brands = res.data;
        }
      });
    },
    loadToy() {
      request
        .get("/toy", {
          params: {
            pageSize: this.pageSize,
            pageNumber: this.pageNumber,
            name: this.name,
            typeId: this.name1,
            brandId: this.name2,
            materialId: this.name3,
          },
        })
        .then((res) => {
          if (res.code === "200") {
            console.log(res.data);
            this.toys = res.data.dataList;
            this.total = res.data.total;
          } else {
            this.$message.error(res.message);
          }
        });
    },

    //选择每页条数后触发
    handleSizeChange(pageSize) {
      (this.pageSize = pageSize), this.loadToy();
    },
    // 当前页改变出发
    handleCurrentChange(val) {
      this.pageNumber = val;
      this.loadToy();
    },
    toDetail(id) {
      const targetPath = "/details";
      this.$router.push({ path: targetPath, query: { id: id } });
    },
  },
};
</script>

<style scoped lang="scss">
.container {
  width: 1200px;
  margin: 0 auto;
  padding-top: 20px;

  .condition {
    border-bottom: 2px solid #d9d8c1;
  }
}

.list {
  padding-top: 20px;
}

.active {
  color: #2fbed7;
}
</style>
