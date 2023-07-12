<template>
  <div>
    <el-menu :default-active="activeIndex" :default-openeds="[]" 
      style="height: 100%;overflow-x: hidden;"
      router
      >
      <el-menu-item index="/data-report/outline">
        <i class="el-icon-data-line"></i>
        <span slot="title">概览</span>
      </el-menu-item>
      <el-submenu index="1">
        <template slot="title"><i class="el-icon-user"></i>用户分析</template>
        <!-- <el-menu-item-group> -->
          <!-- <template slot="title">分组一</template> -->
          <el-menu-item index="/data-report/user/change" class="el-icon-link">用户变动统计</el-menu-item>
          <el-menu-item index="/data-report/user/save" class="el-icon-link">用户留存统计</el-menu-item>
        <!-- </el-menu-item-group> -->
        <!-- <el-menu-item-group title="分组2"> -->
          <el-menu-item index="/data-report/user/newactive" class="el-icon-link">用户新增活跃统计</el-menu-item>
          <el-menu-item index="/data-report/user/actionanalysis" class="el-icon-link">用户行为漏斗分析</el-menu-item>
          <el-menu-item index="/data-report/user/neworder" class="el-icon-link">新增下单用户统计</el-menu-item>
        <!-- </el-menu-item-group> -->
        <!-- <el-submenu index="1-4">
          <template slot="title">选项4</template>
          <el-menu-item index="1-4-1">选项4-1</el-menu-item>
        </el-submenu> -->
      </el-submenu>
      <el-submenu index="2">
        <template slot="title"><i class="el-icon-s-data"></i>流量分析</template>
        <!-- <el-menu-item-group> -->
          <!-- <template slot="title">分组一</template> -->
          <el-menu-item index="/data-report/flow/channel" class="el-icon-link">各渠道流量统计</el-menu-item>
          <el-menu-item index="/data-report/flow/path" class="el-icon-link">路径统计</el-menu-item>
        <!-- </el-menu-item-group> -->
        <!-- <el-menu-item-group title="分组2"> -->
          <!-- <el-menu-item index="2-3">选项3</el-menu-item> -->
        <!-- </el-menu-item-group> -->
        <!-- <el-submenu index="2-4">
          <template slot="title">选项4</template>
          <el-menu-item index="2-4-1">选项4-1</el-menu-item>
        </el-submenu> -->
      </el-submenu>
      <el-submenu index="3">
        <template slot="title"><i class="el-icon-shopping-bag-1"></i>商品分析</template>
        <el-menu-item index="/data-report/shop/" class="el-icon-link">复购率统计</el-menu-item>
        <el-menu-item-group>
          <template slot="title">品牌</template>
          <el-menu-item index="/data-report/shop/BrandAddOrder" class="el-icon-link">各品牌商品下单统计</el-menu-item>
          <el-menu-item index="/data-report/shop/BrandReduceOrder" class="el-icon-link">各品牌商品退单统计</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="品类">
          <el-menu-item index="/data-report/shop/KindAddOrder" class="el-icon-link">各品类商品下单统计</el-menu-item>
          <el-menu-item index="/data-report/shop/KindReduceOrder" class="el-icon-link">各品类商品退单统计</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="其他">
          <!-- <el-menu-item index="/data-report/shop/" class="el-icon-link">SPU订单统计</el-menu-item> -->
          <!-- <el-menu-item index="/data-report/shop/" class="el-icon-link">购物车存量统计</el-menu-item> -->
          <el-menu-item index="/data-report/shop/Province" class="el-icon-link">各省份交易统计</el-menu-item>
        </el-menu-item-group>
        <!-- <el-submenu index="3-4">
          <template slot="title">选项4</template>
          <el-menu-item index="3-4-1">选项4-1</el-menu-item>
        </el-submenu> -->
      </el-submenu>
      <!-- <el-submenu index="4">
        <template slot="title"><i class="el-icon-shopping-cart-full"></i>订单分析</template>
        <el-menu-item-group> -->
          <!-- <template slot="title">分组一</template> -->
          <!-- <el-menu-item index="4-1" class="el-icon-link">订单统计</el-menu-item>
          <el-menu-item index="4-2" class="el-icon-link">退单统计</el-menu-item> -->
        <!-- </el-menu-item-group> -->
      <!-- </el-submenu> -->
      <el-submenu index="5">
        <template slot="title"><i class="el-icon-present"></i>优惠劵分析</template>
        <!-- <el-menu-item-group> -->
          <!-- <template slot="title">分组一</template> -->
          <!-- <el-menu-item index="5-1" class="el-icon-link">优惠劵使用率统计</el-menu-item> -->
          <!-- <el-menu-item index="5-2" class="el-icon-link">优惠劵金额</el-menu-item> -->
          <el-menu-item index="/data-report/coupon/Discount" class="el-icon-link">优惠劵补贴率</el-menu-item>
        <!-- </el-menu-item-group> -->
      </el-submenu>
      <el-submenu index="6">
        <template slot="title"><i class="el-icon-wind-power"></i>活动分析</template>
        <!-- <el-menu-item-group> -->
          <!-- <template slot="title">分组一</template> -->
          <el-menu-item index="/data-report/activity/Discount" class="el-icon-link">活动补贴率</el-menu-item>
        <!-- </el-menu-item-group> -->
      </el-submenu>
    </el-menu>
  </div>
</template>
  
<script>
export default {
  name: 'AsideDataReport',
  props: {

  },
  data() {
    return {
      activeIndex: "/data-report/outline" // 当前激活的菜单栏
    };
  },
  methods: {
    
  },
  created() {
    
  }
}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
  