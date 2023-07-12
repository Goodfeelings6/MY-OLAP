<template>
  <div style="display: flex;flex-direction: column; justify-content: left;align-items: center;height: 100%;">
    <!-- <h1>各品类商品退单统计</h1> -->
    <div style="display: flex;justify-content: space-between;margin-top: 15px;margin-bottom: 30px">
      <div class="fc" style="margin-right: 400px;">
        <el-date-picker v-model="date1" type="date" value-format="yyyy-MM-dd" placeholder="选择日期"
           @change="checkout_date">
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
            <b style="font-size: 20px;">各品类商品退单统计</b>
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
  name: 'ShopKindReduceOrder',
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
    load1(dt, recent_days)// main1
    {
      var chartDom = document.getElementById('main1');
      var myChart = echarts.init(chartDom);
      var option;

      option = {
        title: {
          text: '',
          subtext: '',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: function (param) {
            // console.log(param);
            if (param.data) {
              return param.marker + param.seriesName + " : " + param.name
                + " <br/>  退单数 : " + param.value + " 件 (" + param.percent + "%)"
                + " <br/>  退单人数 : " + param.data.value2 + " 人";
            }
            else {
              return null
            }
          }
        },
        legend: {
          type: 'scroll',
          orient: 'vertical',
          right: 10,
          top: 20,
          bottom: 20,
          data: ["食品饮料、保健食品-粮油调味-米面杂粮", "手机-手机通讯-手机", "家用电器-大家电-平板电视", "个护化妆-香水彩妆-香水", "个护化妆-香水彩妆-唇部"]
        },
        series: [
          {
            name: '品类名',
            type: 'pie',
            radius: '55%',
            center: ['40%', '50%'],
            data: [
              { name: "食品饮料、保健食品-粮油调味-米面杂粮", value: 2, value2: 2 },
              { name: "手机-手机通讯-手机", value: 21, value2: 20 },
              { name: "家用电器-大家电-平板电视", value: 8, value2: 8 },
              { name: "个护化妆-香水彩妆-香水", value: 0, value2: 0 },
              { name: "个护化妆-香水彩妆-唇部", value: 4, value2: 4 }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };

      option && myChart.setOption(option);

      // 请求数据
      this.request.post(this.ip + "/kind/reduce_order", {
        dt: dt,
        recent_days: recent_days
      }).then(res => {
        console.log("请求ip", this.ip + "/kind/reduce_order", "成功", res)
        var legendData = [];
        var seriesData = [];

        var c1 = this.collect(res, 'order_refund_count', 1)
        var c2 = this.collect(res, 'order_refund_user_count', 1)

        for (let i = 0; i < res.length; i++) {
          let tmp = res[i]["category1_name"] + "-" + res[i]["category2_name"] + "-" + res[i]["category3_name"]
          legendData.push(tmp)
          seriesData.push({
            name: tmp,
            value: c1[i],
            value2: c2[i]
          });
        }

        option.legend.data = legendData;
        option.series[0].data = seriesData;
        myChart.setOption(option);
      }).catch(err => {
        console.log("err_msg:", err)
        console.log("err,请求ip", this.ip + "/kind/reduce_order")
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
  
  