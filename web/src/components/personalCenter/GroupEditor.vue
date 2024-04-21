<template>
  <div style="width: 800px;margin: auto;position: relative;top: 20px">
    <h1 style="color: white">创建小组</h1>
    <el-form class="groupEditor" :model="ruleForm" :rules="rules" ref="ruleForm">
      <el-form-item prop="imgFileUrl">
        <el-upload
            v-model="ruleForm.imgFileUrl"
            action="http://localhost:8882/frontApi/group/upLoadImage"
            :limit="1"
            list-type="picture-card"
            :file-list="fileImgList"
            :on-success="handleAvatarSuccess"
            :on-remove="handleRemove"
            :class="{hide_box: upload_btn}"
        >
          <i class="el-icon-plus"></i>
        </el-upload>
      </el-form-item>
      <el-form-item prop="checkTitle">
        <el-input
            placeholder="请输入小组名称"
            maxlength="15"
            show-word-limit
            v-model="ruleForm.checkTitle">
        </el-input>
      </el-form-item>
      <el-form-item prop="checkTypeId">
        <el-select v-model="ruleForm.checkTypeId" placeholder="请选择小组类型">
          <el-option
              v-for="item in typeList"
              :key="item.id"
              :label="item.name"
              :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="checkContent">
        <el-input
            type="textarea"
            autosize
            maxlength="100"
            show-word-limit
            placeholder="请输入小组简介,简介不能为空"
            :autosize="{ minRows: 2, maxRows: 4}"
            v-model="ruleForm.checkContent">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit('ruleForm')">发布</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>

<script>
import groupTypeApi from "../../../api/groupType";
import groupApi from "../../../api/group";
import postApi from "../../../api/post";

export default {
  name: "GroupEditor",
  data() {
    return {
      typeList: [],
      fileImgList: [],//上传的图片集合
      upload_btn: false, // 是否隐藏上传图片按钮
      dialogImageUrl: "",
      dialogVisible: false,
      rules: {
        checkTitle: [{
          required: true, message: '请输入小组名称', trigger: 'blur',
        }],
        checkContent: [{
          required: true, message: '简介不能为空', trigger: 'blur',
        }],
        checkTypeId: [{
          required: true, message: '类型不能为空', trigger: 'blur',
        }],
        imgFileUrl: [{
          required: true, message: '需要上传封面', trigger: 'blur',
        }],
      },
      ruleForm: {
        checkTitle: "",
        checkContent: "",
        imgFileUrl: '',
        checkTypeId: "",
      },
    }
  },
  mounted() {
    this.getTypeList();
  },
  methods: {
    async getTypeList() {
      let res = await groupTypeApi.getAllType();
      res = res.data;
      console.log(res.data, res.message);
      this.typeList = res.data;
    },
    // 上传成功的回调
    handleAvatarSuccess(res, file) {
      if (file.response.code === 200) {
        this.ruleForm.imgFileUrl = file.response.data;
        // 上传成功后，隐藏上传按钮
        this.upload_btn = true;
      }
      console.log(this.ruleForm.imgFileUrl, this.upload_btn)
    },
    //图片的删除方法
    handleRemove(file, fileList) {
      console.log(file);
      let res = groupApi.deleteImage(file.response.data);
      this.fileImgList = fileList;
      this.ruleForm.imgFileUrl = null;
      this.upload_btn = false;
    },
    submit(formName) {
      console.log(formName)
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          this.$confirm('确认创建?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
          }).then(async () => {
            try {
              await this.createGroup(); // 假设createGroup返回一个Promise
              this.$message({
                type: 'success',
                message: '创建成功!'
              });
            } catch (error) {
              console.error('创建小组失败:', error);
              this.$message({
                type: 'error',
                message: '创建小组失败，请稍后再试!'
              });
            }
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消发布'
            });
          });
        } else {
          console.log('表单验证失败，无法提交!');
        }
      });
    },
    async createGroup() {
      // 这里应该是发送请求到后端创建小组的逻辑
      let res = await groupApi.create({
        image: this.ruleForm.imgFileUrl,
        brief: this.ruleForm.checkContent,
        typeId: this.ruleForm.checkTypeId,
        name: this.ruleForm.checkTitle,
        creatorId: JSON.parse(window.sessionStorage.getItem("userInfo")).id
      });
      res = res.data;
      if (res.code === 200) {
        // 处理成功逻辑，比如跳转到小组列表页面等
        await this.$router.push({
          path: "/personCenter/groupManage",
        });
      } else {
        // 处理失败逻辑，比如显示错误信息等
        alert(res.message)
        throw new Error(res.message || '创建小组失败');
      }
    },
  }
}
</script>

<style scoped>
.groupEditor {
  position: relative;
  padding: 20px;
  border: solid rgba(255, 255, 255, 0.2);
  border-width: 15px 15px 15px 10px;
  border-radius: 10px;
}

.groupEditor:before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url("../../assets/photo2.webp");
  filter: blur(5px);
  -webkit-filter: blur(5px); /* 兼容webkit浏览器 */
}
</style>