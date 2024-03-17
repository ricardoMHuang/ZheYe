<template>
  <div style="background-color: white;margin: auto;">
    <h1>你好！我是post编辑器,请选择封面</h1>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
      <el-form-item prop="checkCoverUrl">
        <el-upload
            v-model="ruleForm.checkCoverUrl"
            action="http://localhost:8882/frontApi/post/upLoad"
            :limit="1"
            :on-exceed="exceedImage"
            :file-list="fileImgList"
            list-type="picture-card"
            :on-success="handleAvatarSuccess"
            :on-remove="handleRemove"
            :class="{hide_box: upload_btn}"
        >
          <i class="el-icon-plus"></i>
        </el-upload>
      </el-form-item>
      <el-form-item prop="checkTitle">
        <el-input
            type="textarea"
            autosize
            placeholder="请输入标题"
            v-model="ruleForm.checkTitle">
        </el-input>
      </el-form-item>
      <el-form-item prop="checkContent">
        <quill-editor
            v-model="ruleForm.checkContent"
            ref="myQuillEditor"
            :options="editorOption"
            @blur="onEditorBlur($event)"
            @focus="onEditorFocus($event)"
            @change="onEditorChange($event)"
            @ready="onEditorReady($event)">
        </quill-editor>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit('ruleForm')">发布</el-button>
      </el-form-item>
    </el-form>


  </div>
</template>

<script>
import {quillEditor} from 'vue-quill-editor'

import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import postApi from "../../../api/post"

export default {
  name: "Editor",
  components: {
    quillEditor
  },
  data() {
    return {
      upload_btn: false, // 是否隐藏上传图片按钮
      fileImgList: [],//上传的图片集合
      rules: {
        checkTitle: [{
          required: true, message: '请输入标题', trigger: 'blur',
        }],
        checkContent: [{
          required: true, message: '内容不能为空', trigger: 'blur',
        }],
        checkCoverUrl: [{
          required: true, message: '需要上传封面', trigger: 'blur',
        }],
      },
      ruleForm: {
        checkTitle: "",
        checkContent: "",
        checkCoverUrl: '',
      },
      onUploadHandler: [],
      groupId: JSON.parse(this.$route.query.groupId),
      userId: JSON.parse(window.sessionStorage.getItem("userInfo")).id,
      // 富文本编辑器配置
      editorOption: {
        modules: {
          toolbar: {
            container: [
              ['bold', 'italic', 'underline', 'strike'], // 加粗 斜体 下划线 删除线
              ['blockquote', 'code-block'], // 引用  代码块
              [{header: 1}, {header: 2}], // 1、2 级标题
              [{list: 'ordered'}, {list: 'bullet'}], // 有序、无序列表
              [{script: 'sub'}, {script: 'super'}], // 上标/下标
              [{indent: '-1'}, {indent: '+1'}], // 缩进
              [{direction: 'rtl'}], // 文本方向
              [{size: ['12', '14', '16', '18', '20', '22', '24', '28', '32', '36']}], // 字体大小
              [{header: [1, 2, 3, 4, 5, 6]}], // 标题
              [{color: []}, {background: []}], // 字体颜色、字体背景颜色
              // [{ font: ['songti'] }], // 字体种类
              [{align: []}], // 对齐方式
              ['clean'], // 清除文本格式
              ['image', 'video'] // 链接、图片、视频
            ],
            handlers: {
              'image': function (value) {
                if (value) { // value === true
                  document.querySelector('.avatar-uploader input').click()
                } else {
                  this.quill.format('image', false)
                }
              }
            },
          },
        },
        placeholder: '请输入正文'
      },
      // toolbar标题
      titleConfig: [
        {Choice: '.ql-insertMetric', title: '跳转配置'},
        {Choice: '.ql-bold', title: '加粗'},
        {Choice: '.ql-italic', title: '斜体'},
        {Choice: '.ql-underline', title: '下划线'},
        {Choice: '.ql-header', title: '段落格式'},
        {Choice: '.ql-strike', title: '删除线'},
        {Choice: '.ql-blockquote', title: '块引用'},
        {Choice: '.ql-code', title: '插入代码'},
        {Choice: '.ql-code-block', title: '插入代码段'},
        {Choice: '.ql-font', title: '字体'},
        {Choice: '.ql-size', title: '字体大小'},
        {Choice: '.ql-list[value="ordered"]', title: '编号列表'},
        {Choice: '.ql-list[value="bullet"]', title: '项目列表'},
        {Choice: '.ql-direction', title: '文本方向'},
        {Choice: '.ql-header[value="1"]', title: 'h1'},
        {Choice: '.ql-header[value="2"]', title: 'h2'},
        {Choice: '.ql-align', title: '对齐方式'},
        {Choice: '.ql-color', title: '字体颜色'},
        {Choice: '.ql-background', title: '背景颜色'},
        {Choice: '.ql-image', title: '图像'},
        {Choice: '.ql-video', title: '视频'},
        {Choice: '.ql-link', title: '添加链接'},
        {Choice: '.ql-formula', title: '插入公式'},
        {Choice: '.ql-clean', title: '清除字体格式'},
        {Choice: '.ql-script[value="sub"]', title: '下标'},
        {Choice: '.ql-script[value="super"]', title: '上标'},
        {Choice: '.ql-indent[value="-1"]', title: '向左缩进'},
        {Choice: '.ql-indent[value="+1"]', title: '向右缩进'},
        {Choice: '.ql-header .ql-picker-label', title: '标题大小'},
        {Choice: '.ql-header .ql-picker-item[data-value="1"]', title: '标题一'},
        {Choice: '.ql-header .ql-picker-item[data-value="2"]', title: '标题二'},
        {Choice: '.ql-header .ql-picker-item[data-value="3"]', title: '标题三'},
        {Choice: '.ql-header .ql-picker-item[data-value="4"]', title: '标题四'},
        {Choice: '.ql-header .ql-picker-item[data-value="5"]', title: '标题五'},
        {Choice: '.ql-header .ql-picker-item[data-value="6"]', title: '标题六'},
        {Choice: '.ql-header .ql-picker-item:last-child', title: '标准'},
        {Choice: '.ql-size .ql-picker-item[data-value="small"]', title: '小号'},
        {Choice: '.ql-size .ql-picker-item[data-value="large"]', title: '大号'},
        {Choice: '.ql-size .ql-picker-item[data-value="huge"]', title: '超大号'},
        {Choice: '.ql-size .ql-picker-item:nth-child(2)', title: '标准'},
        {Choice: '.ql-align .ql-picker-item:first-child', title: '居左对齐'},
        {Choice: '.ql-align .ql-picker-item[data-value="center"]', title: '居中对齐'},
        {Choice: '.ql-align .ql-picker-item[data-value="right"]', title: '居右对齐'},
        {Choice: '.ql-align .ql-picker-item[data-value="justify"]', title: '两端对齐'}
      ],
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    async init() {
      for (let item of this.titleConfig) {
        let tip = document.querySelector('.quill-editor ' + item.Choice)
        if (!tip) continue
        tip.setAttribute('title', item.title)
      }
    },

    submit(formName) {
      console.log(formName)
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          this.$confirm('确认发布?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
          }).then(() => {
            this.submitPost();
            this.$message({
              type: 'success',
              message: '发布成功!'
            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消发布'
            });
          });
        } else {
          console.log('error submit!!');
        }
      });
    },
    async submitPost() {
      let time = this.getTodayBegin();
      let res = await postApi.submitPost({
        creatorId: this.userId,
        title: this.ruleForm.checkTitle,
        groupId: this.groupId,
        image: this.ruleForm.checkCoverUrl,
        commentId: this.commentId,
        createTime: time,
        content: this.ruleForm.checkContent,
      });
      console.log(res.data)
      this.$message({
        message: '已评论',
        type: 'success'
      });
      await this.openGroup();
    },
    getTodayBegin() {
      var date = new Date();
      var month = date.getMonth() + 1;
      var strDate = date.getDate();
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
      }
      var currentdate = date.getFullYear() + "-" + month + "-" + strDate + " 00:00:00";
      return currentdate;
    },
    openGroup() {
      this.$router.push({
        path: '/group/groupDetail',
        query: {
          groupId: this.groupId,
        }
      })
    },
    // 失去焦点事件
    onEditorBlur(quill) {
      console.log('editor blur!', quill)
    },
// 获得焦点事件
    onEditorFocus(quill) {
      console.log('editor focus!', quill)
    },
// 准备富文本编辑器
    onEditorReady(quill) {
      console.log('editor ready!', quill)
    },
    // 内容改变事件
    onEditorChange({quill, html, text}) {
      console.log('editor change!', quill, html, text)
      this.content = html
    },
    handlePictureCardPreview(file) {
      this.ruleForm.checkCoverUrl = file.url;
      this.dialogVisible = true;
    },
    // 上传成功的回调
    handleAvatarSuccess(res, file) {
      console.log(file);
      if (file.response.code === 200) {
        this.ruleForm.checkCoverUrl = file.response.data;
        // 上传成功后，隐藏上传按钮
        this.upload_btn = true;
      }
      console.log(this.ruleForm.checkCoverUrl)
    },
    //图片的删除方法
    handleRemove(file, fileList) {
      console.log(file);
      let res = postApi.deleteImage(file.response.data);
      this.fileImgList = fileList;
      this.ruleForm.checkCoverUrl = null;
    },
    //文件超出上传个数时
    exceedImage(files, fileList) {
      this.$message({
        message: "只能选择一张封面！",
        type: 'success'
      });
      console.log("只能选择一张图片作为封面");
    },
  }
}
</script>

<style scoped>
.hide_box /deep/ .el-upload--picture-card {
  display: none;
}

</style>