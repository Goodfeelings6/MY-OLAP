<template>
  <div style="display: flex;flex-direction: column; justify-content: center;align-items: center;height: 100%;">
    <!-- <h1>各品牌商品退单统计</h1> -->
    <el-row :gutter="20">
      <el-col :span="25">
        <el-card>
          <div class="fc mb-10">
            <b style="font-size: 20px;">各品牌商品退单统计</b>
          </div>
          <div id="main1" style="width: 1000px;height:500px;"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
  
<script>
import * as echarts from 'echarts';
export default {
  name: 'ShopBrandReduceOrder',
  props: {
  },
  data() {
    return {
    }

  },
  // 加载渲染数据
  mounted() {
    this.load1();
  },
  methods: {
    // 将响应数据整合成name字段组成的数组
    collect(data, name, stoi = 0) {
      var arr = [];
      for (let i = 0; i < data.length; i++) {
        let tmp = data[i][name]
        if (stoi) tmp = eval(tmp)
        arr.push(tmp)
      }
      return arr;
    },
    get_max(data) {
      let m = 0;
      data.forEach(element => {
        if (element > m) m = element;
      });
      return m;
    },
    load1()// main1
    {
      var chartDom = document.getElementById('main1');
      var myChart = echarts.init(chartDom);
      var option;

      option = {
        title: {
          text: ''
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {},
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'value',
          boundaryGap: [0, 0.01]
        },
        yAxis: {
          type: 'category',
          data: ['三星', '香奈儿', '苹果', '华为', 'TCL', '小米']
        },
        series: [
          {
            name: '退单数',
            type: 'bar',
            data: [38203, 23489, 89034, 104970, 131744, 230230]
          },
          {
            name: '退单人数',
            type: 'bar',
            data: [39325, 23438, 71000, 121594, 134141, 281807]
          }
        ]
      };

      option && myChart.setOption(option);


      // 请求数据
      this.request.post(this.ip + "/brand/reduce_order", {
        dt: "2020-06-14",
        recent_days: "1"
      }).then(res => {
        console.log("请求ip", this.ip + "/brand/reduce_order", "成功", res)
        option.series[0].data = this.collect(res, 'order_refund_count', 1)
        option.series[1].data = this.collect(res, 'order_refund_user_count', 1)
        option.yAxis.data = this.collect(res, "tm_name")
        myChart.setOption(option);
      }).catch(err => {
        console.log("err_msg:", err)
        console.log("err,请求ip", this.ip + "/brand/reduce_order")
      })
    }
  }

}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
  
  