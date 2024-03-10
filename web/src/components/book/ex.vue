<template>
  <div id="app">
    <el-link :underline="false" class="el_link" @click="showInput()">回复</el-link>

    <el-drawer
        title="dsas"
        :visible.sync="drawer"
        :direction="direction">
      <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="100px">
        <el-form-item label="评论框" prop="checkComment">
          <el-input type="textarea" v-model="ruleForm2.checkComment" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm2')">提交</el-button>
          <el-button @click="resetForm('ruleForm2')">重置</el-button>
        </el-form-item>
      </el-form>

    </el-drawer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      direction: 'btt',
      drawer: false,
      ruleForm2: {
        checkComment: '',
      },
      rules2: {
        checkComment: [
          {required: true, message: "请输入", trigger: 'blur'}
        ],

      }
    };
  },
  methods: {
    showInput() {
      this.drawer = true

    },
    async submitForm(formName) {
      console.log(formName)
      this.$refs[formName].validate((valid) => {
        if (valid) {

          alert('submit!');
        } else {
          console.log('error submit!!');
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>

<style scoped>


</style>