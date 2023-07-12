<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane>
        <span slot="label"><i class="el-icon-date"></i> 转化漏斗在线分析</span>
        <el-form :model="dynamicValidateForm" ref="dynamicValidateForm" label-width="100px" class="demo-dynamic"
          style="width: 50%;">
          <el-form-item prop="name" label="漏斗名称" :rules="[
            { required: true, message: '请输入漏斗名称', trigger: 'blur' }
          ]">
            <el-input v-model="dynamicValidateForm.name"></el-input>
          </el-form-item>

          <el-form-item prop="scope" label="漏斗窗口期" :rules="[
            { required: true, message: '请输入漏斗名称', trigger: 'blur' }]">
            <el-select v-model="dynamicValidateForm.scope" placeholder="请选择" >
              <el-option label="1天" value="1"></el-option>
              <el-option label="7天" value="2"></el-option>
              <el-option label="30天" value="3"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item v-for="(step, index) in dynamicValidateForm.steps" :label="'漏斗步骤 ' + (index + 1)" :key="step.key"
            :prop="'steps.' + index + '.value'" :rules="{
              required: true, message: '漏斗步骤不能为空', trigger: 'blur'
            }">
            <el-input v-model="step.value"></el-input><el-button @click.prevent="removeDomain(step)">删除</el-button>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm('dynamicValidateForm')">提交</el-button>
            <el-button @click="addDomain">新增步骤</el-button>
            <el-button @click="resetForm('dynamicValidateForm')">重置</el-button>
          </el-form-item>

        </el-form>
      </el-tab-pane>
      <el-tab-pane>
        <span slot="label"><i class="el-icon-set-up"></i> 用户行为路径分析</span>
        用户行为路径分析
      </el-tab-pane>
      <el-tab-pane>
        <span slot="label"><i class="el-icon-s-data"></i> 自定义SQL查询</span>
        自定义SQL查询
      </el-tab-pane>
      <el-tab-pane>
        <span slot="label"><i class="el-icon-user"></i> 用户分群查询</span>
        用户分群查询
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
  
<script>
import * as echarts from 'echarts';
export default {
  name: 'Analysis',
  props: {
  },
  data() {
    return {
      dynamicValidateForm: {
        steps: [{
          value: ''
        }],
        name: '',
        scope: ''
      }
    }
  },
  methods: {
    submitForm(formName) {
      // this.$refs[formName].validate((valid) => {
      //   if (valid) {
      //     alert('submit!');
      //   } else {
      //     console.log('error submit!!');
      //     return false;
      //   }
      // });
      console.log("submit!")
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    removeDomain(item) {
      var index = this.dynamicValidateForm.steps.indexOf(item)
      if (index !== -1) {
        this.dynamicValidateForm.steps.splice(index, 1)
      }
    },
    addDomain() {
      this.dynamicValidateForm.steps.push({
        value: '',
        key: Date.now()
      });
    }
  },
  mounted() {

  }
}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
  
  