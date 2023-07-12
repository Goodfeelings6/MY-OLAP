<template>
  <div style="height: 100%;">
    <el-container style="height: 100%;">
      <!-- 顶部header -->
      <el-header style="border-bottom: 1px solid #ccc; line-height: 60px;">
        <Header></Header>
      </el-header>
      <!-- 内容主体 -->
      <el-container style="height: calc(100% - 60px);">
        <!-- 左菜单导航栏 -->
        <el-aside width="200px" style="height: 100%;">
          <AsideDataReport></AsideDataReport>
        </el-aside>
        <!-- 内容 -->
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from '@/components/Header.vue'
import AsideDataReport from '@/components/AsideDataReport.vue'

export default {
  name: 'Data-Report',
  components: {
    Header,
    AsideDataReport
  },
  data() {
    // const item = {
    //   date: '2016-05-02',
    //   name: '王小虎',
    //   address: '上海市普陀区金沙江路 1518 弄'
    // };
    // return {
    //   tableData: Array(10).fill(item)
    // }
    return {
      
    }
  }
}
</script>
