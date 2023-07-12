<template>
  <div style="display: flex;flex-direction: column; justify-content: left;align-items: center;height: 100%;">
    <!-- <h1>各品牌商品退单统计</h1> -->
    <div style="display: flex;justify-content: space-between;margin-top: 15px;margin-bottom: 30px">
      <div class="fc" style="margin-right: 400px;">
        <el-date-picker v-model="date1" type="date" value-format="yyyy-MM-dd"
        placeholder="选择日期"  @change="checkout_date">
        </el-date-picker>
      </div>
      <div class="fc">
        <el-radio-group v-model="radio1" @change="checkout_scope">
          <el-radio-button label="最近1天"></el-radio-button>
          <el-radio-button label="最近7天"></el-radio-button>
          <el-radio-button label="最近30天"></el-radio-button>
        </el-radio-group>
      </div>
    </div>
    <el-row :gutter="20">
      <el-col :span="25">
        <el-card>
          <div class="fc mb-10">
            <b style="font-size: 20px;">各品牌商品退单统计</b>
          </div>
          <div class="fc">
            <div class="fc" style="margin-right: -30px;width: 10px;margin-left: 20px;">
              <b style="font-size: 15px;color: rgb(96, 96, 97);">品牌名称</b>
            </div>
          <div id="main1" style="width: 1000px;height:500px;"></div>
        </div>
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
      // 控件
      date1: "2020-06-14",
      radio1: "最近1天",
    }

  },
  // 加载渲染数据
  mounted() {
    this.load1(this.date1, this.getdays(this.radio1));
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
    getdays(para) {
      if (para == "最近1天")
        return "1";
      else if (para == "最近7天")
        return "7";
      else if (para == "最近30天")
        return "30";
    },
    load1(dt,recent_days)// main1
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
        dt: dt,
        recent_days: recent_days
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
    },
    // 切换统计时间范围
    checkout_scope() {
      // console.log(this.radio1);
      this.load1(this.date1, this.getdays(this.radio1));
    },
    // 切换日期
    checkout_date() {
      // console.log(this.date1);
      this.load1(this.date1, this.getdays(this.radio1));
    }
  }

}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
  
  