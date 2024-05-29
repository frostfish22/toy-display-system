<template>
  <div >
    <!-- 搜索区域 -->
    <div class="serach-region">
      <el-input style="width: 200px;margin-right:20px" placeholder="输入关键字" v-model="name"></el-input>
      <!-- 可以加入其他条件 -->
      <!-- <el-input style="width: 200px; margin: 0 5px" placeholder="查询姓名"  v-model="name"></el-input> -->
      <el-button type="primary" @click="load()">查询</el-button>
      <el-button type="info" @click="reset">重置</el-button>
    </div>
    <!-- 操作区域 -->
    <div class="operate-region" style="margin: 10px 0">
      <el-button type="primary" plain @click="handleAdd">新增</el-button>
      <el-button type="danger" plain @click="delBatch">批量删除</el-button>
    </div>

    <!-- 表格数据 -->
    <el-table :data="tableData" stripe border  :header-cell-style="{ backgroundColor: 'aliceblue', color: '#666' }" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column label="封面">
        <template slot-scope="scope">
          <el-image style="width: 100px; height: 100px" :src="scope.row.cover" :preview-src-list="[scope.row.cover]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column prop="typeId" label="类型ID"></el-table-column>
      <el-table-column prop="materialId" label="材质ID"></el-table-column>
      <el-table-column prop="brandId" label="品牌ID"></el-table-column>
      <el-table-column prop="detailedType" label="详细类型"></el-table-column>
      <el-table-column prop="applicableAge" label="适用年龄"></el-table-column>
      <el-table-column prop="style" label="款式"></el-table-column>
      <el-table-column prop="size" label="尺寸"></el-table-column>
      <el-table-column prop="price" label="价格"></el-table-column>
      <el-table-column prop="details" :show-overflow-tooltip='true'  label="内容"></el-table-column>


      <el-table-column label="操作" align="center" width="180">
        <template v-slot="scope">
          <el-button size="small"  @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="small" type="danger"  @click="del(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页-->
    <div style="margin: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNumber"
          :page-size="pageSize"
          :page-sizes="[1, 5, 10, 20]"
          layout="total, sizes, prev, pager, next, jumper"
          :total=total>
      </el-pagination>
    </div>

    <!-- 新增 修改表单 -->
    <el-dialog title="玩具信息" :visible.sync="formVisible" width="60%" @close="cancel" :close-on-click-modal="false">
      <el-form :model="formData" ref="formRef" :rules="rules">
                <el-form-item prop="name" label="名称" label-width="120px">
          <el-input v-model="formData.name" autocomplete="off" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="封面" label-width="120px">
          <el-upload
              class="avatar-uploader"
              :action="uploadUrl"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
          >
            <img v-if="formData.cover" width="80px" height="80px" class="avatar" :src="formData.cover" >
            <i v-if="!formData.cover"  class="el-icon-plus avatar-uploader-icon">上传图片</i>
          </el-upload>
        </el-form-item>
        <el-form-item label="轮播图片" label-width="120px">
          <el-upload
              :action="uploadUrl"
              list-type="picture-card"
              :on-remove="fileRemove"
              :on-success="handleImgListSuccess"
              :file-list="fileList"
              :before-upload="beforeAvatarUpload"
          >
            <i class="el-icon-plus" style="font-size: 18px">上传图片</i>
          </el-upload>
          <el-dialog :visible.sync="ImgVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>
        <el-form-item prop="typeId" label="类型" label-width="120px">
          <el-select v-model="formData.typeId" @change=""  placeholder="选择标签">
            <el-option v-for="(item,index) in typeList" :key="item.value" :label=item.typeName :value=item.id ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item prop="materialId" label="材质" label-width="120px">
          <el-select v-model="formData.materialId" @change=""  placeholder="选择标签">
            <el-option v-for="(item,index) in materialList" :key="item.value" :label=item.material :value=item.id ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="brandId" label="品牌" label-width="120px">
          <el-select v-model="formData.brandId" @change=""  placeholder="选择标签">
            <el-option v-for="(item,index) in brandList" :key="item.value" :label=item.brandName :value=item.id ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="detailedType" label="详细类型" label-width="120px">
          <el-input v-model="formData.detailedType" autocomplete="off" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item prop="applicableAge" label="适用年龄" label-width="120px">
          <el-input v-model="formData.applicableAge" autocomplete="off" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item prop="style" label="款式" label-width="120px">
          <el-input v-model="formData.style" autocomplete="off" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item prop="size" label="尺寸" label-width="120px">
          <el-input v-model="formData.size" autocomplete="off" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item prop="price" label="价格" label-width="120px">
          <el-input v-model="formData.price" autocomplete="off" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item prop="details" label="详情" label-width="120px">
          <div id="editor"></div>
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
import Editor from "wangeditor";

export default {
  data() {
    return {
      //用户数据
      user: JSON.parse(localStorage.getItem('user') || '{}'),
      //表格列表数据
      tableData:[],
      //查询条件
      name:'',
      //类别list
      typeList:[],
      //材质list
      materialList:[],
      //品牌list
      brandList:[],
      pageNumber: 1,   // 当前的页码
      pageSize: 5,  // 每页显示的个数
      total: 0,     //总数
      //弹出框是否可见
      formVisible:false,
      //弹出框表单的数据
      formData:{},
      //表单规则验证
      rules:{},
      //选中删除的id数组
      idList:[],
      //上传图片路径
      uploadUrl:'http://localhost:9090/file/upload',
      //编辑器上传图片路径
      editorUpUrl:'http://localhost:9090/file/editor/upload',
      ImgVisible:false,
      fileList:[],
      //全屏图片的路径url
      dialogImageUrl:''
    }
  },
  created(){
    this.load()
  },
  computed:{
  },
  methods:{
    // 成功回调
    handleAvatarSuccess(response, file, fileList) {
      // 把user的头像属性换成上传的图片的链接
      // 使用$set,否则不会立刻回显
      console.log(response.data)
      this.$set(this.formData, 'cover', response.data);
    },
    handleImgListSuccess(response, file, fileList) {
      console.log(fileList)
      this.$message.success("上传成功")
      this.fileList=fileList
      console.log(fileList)
      console.log(this.fileList)
    },
    // 删除文件的钩子
    fileRemove(file, fileList){
      console.log(this.fileList)
      this.fileList = this.fileList.filter(item => item.uid !== file.uid)
      console.log(this.fileList)
      this.$message.success('移除成功')
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
    loadType(){
      request.get('/toyType/getAll').then(res=>{
        if (res.code==='200'){
          this.typeList = res.data
        }
      })
    },
    loadMaterial(){
      request.get('/toyMaterial/getAll').then(res=>{
        if (res.code==='200'){
          this.materialList = res.data
        }
      })
    },
    loadBrand(){
      request.get('/toyBrand/getAll').then(res=>{
        if (res.code==='200'){
          this.brandList = res.data
        }
      })
    },
    // 新增
    handleAdd(){
      this.formVisible=true
      //创建编辑器的节点
      this.setRichText()
      this.loadType()
      this.loadMaterial()
      this.loadBrand()
    },
    save() {   // 保存按钮触发的逻辑  它会触发新增或者更新
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          let content = this.editor.txt.html()
          this.formData.details = content
          //将图片路径组装成字符串，用，分割
          //注意修改时，从数据库查出来的没有response,url就是路径
          this.formData.image =this.fileList.map(obj => obj.response?obj.response.data:obj.url).join(',');
          console.log(this.formData.image)
          request({
            url: '/toy',
            method: this.formData.id ? 'PUT' : 'POST',
            data: this.formData
          }).then(res => {
            if (res.code === '200') {  // 表示成功保存
              this.$message.success('保存成功')
              this.load()
              this.formData={}
              this.formVisible = false
            } else {
              this.$message.error(res.msg)  // 弹出错误的信息
            }
          })
        }
      })
    },
    //取消 清空表单
    cancel(){
      this.formVisible = false
      this.formData= {}
      this.fileList= []
      this.closeDialog()

    },
    // 修改
    handleEdit(row){
      this.formData=JSON.parse(JSON.stringify(row))//深度拷贝
      let urls;
      //谨记，字符串空的时候默认为''
      if (this.formData.image==''){
        this.fileList=[]
      }else{
        urls = this.formData.image.split(',')
        for(let i = 0; i < urls.length; i++) {
          const obj = {
            name:' ',
            url: urls[i],
          };
          this.fileList.push(obj);
        }
      }
      this.setRichText()
      setTimeout(() => {
        this.editor.txt.html(row.details)  // 设置富文本内容
      }, 0)
      this.loadType()
      this.loadMaterial()
      this.loadBrand()
      this.formVisible = true   // 打开弹窗
    },
    // 删除
    del(id){
      this.$confirm('此操作将永久删除信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        request.delete(`/toy/${id}`).then(res=>{
          if(res.code==='200'){
            this.$message.success('操作成功')
            this.load()
          }else{
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
        request.delete('/toy/deleteBatch',{data:this.idList}).then(res=>{
          if(res.code==='200'){
            this.$message.success('操作成功')
            this.load()
          }else{
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
    load(){
      request.get('/toy',
          {
            params:{
              pageSize:this.pageSize,
              pageNumber:this.pageNumber,
              name:this.name
            }
          }
      ).then(res => {
        if (res.code==='200'){
          console.log(res.data)
          this.tableData = res.data.dataList
          this.total = res.data.total
        }else {
          this.$message.error(res.message)
        }

      })
    },
    // 清空查询条件
    reset(){
      this.name='',
          this.load()
    },
    //选择每页条数后触发
    handleSizeChange(pageSize){
      this.pageSize=pageSize,
          this.load()
    },
    // 当前页改变出发
    handleCurrentChange(val){
      this.pageNumber=val
      this.load()
    },
    //文本编辑器配置
    setRichText() {
      this.$nextTick(() => {
        this.editor = new Editor(`#editor`)
        this.editor.config.uploadImgServer = this.editorUpUrl
        this.editor.config.uploadFileName = 'file'
        this.editor.config.uploadImgHeaders = {
          token: this.user.token
        }
        this.editor.config.uploadImgParams = {
          type: 'img',
        }
        this.editor.create()  // 创建
      })
    },
    closeDialog() {
      // 销毁编辑器
      this.editor.destroy()
      this.editor = null
    },
  },


}
</script>
<style lang="scss" scoped>

</style>
