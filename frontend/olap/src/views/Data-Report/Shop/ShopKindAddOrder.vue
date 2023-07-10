<template>
  <div style="display: flex;flex-direction: column; justify-content: center;align-items: center;height: 100%;">
    <!-- <h1>各品类商品下单统计</h1> -->
    <el-row :gutter="20">
      <el-col :span="25">
        <el-card>
          <div class="fc mb-10">
            <b style="font-size: 20px;">各品类商品下单统计</b>
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
  name: 'ShopKindAddOrder',
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
          text: '',
          subtext: '',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          // formatter: '{a} <br/>{b} : {c} 件({d}%)'
          formatter: function (param) {
            // console.log(param);
            return param.marker + param.seriesName + " : " + param.name
              + " <br/>  订单数 : " + param.value + " 件 (" + param.percent + "%)"
              + " <br/>  订单人数 : " + param.data.value2 + " 人";
      }
        },
        legend: {
          type: 'scroll',
          orient: 'vertical',
          right: 10,
          top: 20,
          bottom: 20,
          data: ["食品饮料、保健食品-粮油调味-米面杂粮","手机-手机通讯-手机","家用电器-大家电-平板电视","个护化妆-香水彩妆-香水","个护化妆-香水彩妆-唇部"]
        },
        series: [
          {
            name: '品类名',
            type: 'pie',
            radius: '55%',
            center: ['40%', '50%'],
            data: [
              { name: "食品饮料、保健食品-粮油调味-米面杂粮", value: 27 ,value2:20},
              { name: "手机-手机通讯-手机", value: 136 ,value2:72},
              { name: "家用电器-大家电-平板电视", value: 52 ,value2:41},
              { name: "个护化妆-香水彩妆-香水",  value: 7 ,value2:6},
              { name: "个护化妆-香水彩妆-唇部", value: 63 ,value2:30}
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
      this.request.post(this.ip + "/kind/add_order", {
        dt: "2020-06-14",
        recent_days: "1"
      }).then(res => {
        console.log("请求ip", this.ip + "/kind/add_order", "成功", res)
        var legendData = [];
        var seriesData = [];

        var c1 = this.collect(res, 'order_count', 1)
        var c2 = this.collect(res, 'order_user_count', 1)

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
        console.log("err,请求ip", this.ip + "/kind/add_order")
      })
    }
  }

}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
  
  