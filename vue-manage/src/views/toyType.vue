<template>
  <div>
    <!-- 操作区域 -->
    <div class="operate-region" style="margin: 10px 0">
      <el-button type="primary" plain @click="handleAdd">新增</el-button>
      <el-button type="danger" plain @click="delBatch">批量删除</el-button>
    </div>

    <!-- 表格数据 -->
    <el-table :data="tableData" stripe border :header-cell-style="{ backgroundColor: 'aliceblue', color: '#666' }"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="typeName" label="类型名称"></el-table-column>
      <el-table-column prop="createTime" label="创建时间"></el-table-column>

      <el-table-column label="操作" align="center" width="180">
        <template v-slot="scope">
          <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="del(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页-->
    <div style="margin: 10px 0">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNumber"
        :page-size="pageSize" :page-sizes="[1, 5, 10, 20]" layout="total, sizes, prev, pager, next, jumper"
        :total=total>
      </el-pagination>
    </div>

    <!-- 新增 修改表单 -->
    <el-dialog title="玩具类型信息" :visible.sync="formVisible" width="30%" @close="cancel" :close-on-click-modal="false">
      <el-form :model="formData" ref="formRef" :rules="rules">
        <el-form-item prop="typeName" label="类型名称" label-width="120px">
          <el-input v-model="formData.typeName" autocomplete="off" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item prop="createTime" label="创建时间" label-width="120px">
          <el-date-picker style="width: 80%" v-model="formData.createTime" type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择日期时间"></el-date-picker>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import request from '@/utils/request';

export default {
  data() {
    return {
      //用户数据
      user: JSON.parse(localStorage.getItem('admin') || '{}'),
      //表格列表数据
      tableData: [],
      //查询条件
      sname: '',
      pageNumber: 1,   // 当前的页码
      pageSize: 5,  // 每页显示的个数
      total: 0,     //总数
      //弹出框是否可见
      formVisible: false,
      //弹出框表单的数据
      formData: {},
      //表单规则验证
      rules: {},
      //选中删除的id数组
      idList: [],
      //上传图片路径
      uploadUrl: 'http://localhost:9090/file/upload',

    }
  },
  created() {
    this.load()
  },
  computed: {
  },
  methods: {
    //上传文件
    // 成功回调
    handleAvatarSuccess(response, file, fileList) {
      // 把user的头像属性换成上传的图片的链接
      // 使用$set,否则不会立刻回显
      console.log(response.data)
      this.$set(this.formData, 'avatar', response.data);
    },
    // 上传前校验
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt5M = file.size / 1024 / 1024 < 5;

      if (!isJPG) {
        this.$message.error('上传图片只能是 JPG 格式!');
      }
      if (!isLt5M) {
        this.$message.error('上传文件大小不能超过 2MB!');
      }
      return isJPG && isLt5M;
    },
    // 新增
    handleAdd() {
      this.formVisible = true
    },
    save() {   // 保存按钮触发的逻辑  它会触发新增或者更新
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          request({
            url: '/toyType',
            method: this.formData.id ? 'PUT' : 'POST',
            data: this.formData
          }).then(res => {
            if (res.code === '200') {  // 表示成功保存
              this.$message.success('保存成功')
              this.load()
              this.formData = {}
              this.formVisible = false
            } else {
              this.$message.error(res.msg)  // 弹出错误的信息
            }
          })
        }
      })
    },
    //取消 清空表单
    cancel() {
      this.formVisible = false
      this.formData = {}
    },
    // 修改
    handleEdit(row) {
      this.formData = JSON.parse(JSON.stringify(row))//深度拷贝
      this.formVisible = true   // 打开弹窗
    },
    // 删除
    del(id) {
      this.$confirm('此操作将永久删除信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        request.delete(`/toyType/${id}`).then(res => {
          if (res.code === '200') {
            this.$message.success('操作成功')
            this.load()
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    // 批量删除
    handleSelectionChange(rows) {   // 当前选中的所有的行数据
      this.idList = rows.map(v => v.id)
    },
    delBatch() {
      if (!this.idList.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        request.delete('/toyType/deleteBatch', { data: this.idList }).then(res => {
          if (res.code === '200') {
            this.$message.success('操作成功')
            this.load()
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    // 查询   分页查询
    load() {
      request.get('/toyType',
        {
          params: {
            pageSize: this.pageSize,
            pageNumber: this.pageNumber,
            sname: this.sname
          }
        }
      ).then(res => {
        if (res.code === '200') {
          console.log(res.data)
          this.tableData = res.data.dataList
          this.total = res.data.total
        } else {
          this.$message.error(res.message)
        }

      })
    },
    // 清空查询条件
    reset() {
      this.sname = '',
        this.load()
    },
    //选择每页条数后触发
    handleSizeChange(pageSize) {
      this.pageSize = pageSize,
        this.load()
    },
    // 当前页改变出发
    handleCurrentChange(val) {
      this.pageNumber = val
      this.load()
    },
  },


}
</script>
<style lang="scss" scoped></style>
