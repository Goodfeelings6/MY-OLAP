<template>
  <div style="display: flex;flex-direction: column; justify-content: center;align-items: center;height: 100%;">
    <!-- <h1>新增下单用户统计</h1> -->
    <el-row :gutter="20">
      <el-col :span="25">
        <el-card>
          <div class="fc mb-10">
            <b style="font-size: 20px;">新增下单用户统计</b>
          </div>
          <div id="main1" style="width: 1000px;height:500px;"></div>
          <div class="fc" style="margin-top: -30px;">
            <b style="font-size: 15px;">日期</b>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
  
<script>
import * as echarts from 'echarts';
export default {
  name: 'UserNewOrder',
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
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            crossStyle: {
              color: '#999'
            }
          }
        },
        toolbox: {
          feature: {
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ['bar', 'line'] },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        legend: {
          data: ['新增下单人数', '新增支付人数']
        },
        xAxis: [
          {
            type: 'category',
            data: ['2020-6-15', '2020-6-16', '2020-6-17', '2020-6-18', '2020-6-19'],
            axisPointer: {
              type: 'shadow'
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            name: '新增下单人数',
            min: 0,
            max: 250,
            interval: 5,
            axisLabel: {
              formatter: '{value} 人'
            }
          },
          {
            type: 'value',
            name: '新增支付人数',
            min: 0,
            max: 25,
            interval: 5,
            axisLabel: {
              formatter: '{value} 人'
            }
          }
        ],
        series: [
          {
            name: '新增下单人数',
            type: 'bar',
            tooltip: {
              valueFormatter: function (value) {
                return value + " 人";
              }
            },
            data: [
              100,124,56,93,121
            ]
          },
          {
            name: '新增支付人数',
            type: 'bar',
            tooltip: {
              valueFormatter: function (value) {
                return value + '  人';
              }
            },
            data: [
              120,84,160,91,51
            ]
          }
        ]
      };

      option && myChart.setOption(option);

      // 请求数据
      this.request.post(this.ip + "/user/order", {
        dt: "2020-06-14",
        recent_days:"1"
      }).then(res => {
        console.log("请求ip", this.ip + "/user/order", "成功", res)
        option.xAxis[0].data = this.collect(res, 'dt')
        option.series[0].data = this.collect(res, 'new_order_user_count', 1)
        option.series[1].data = this.collect(res, 'new_payment_user_count', 1)
        option.yAxis[0].max = this.get_max(option.series[0].data) * 2
        option.yAxis[1].max = this.get_max(option.series[1].data) * 2
        option.yAxis[0].interval = option.yAxis[0].max / 8
        option.yAxis[1].interval = option.yAxis[1].max / 8
        myChart.setOption(option);
      }).catch(err => {
        option.yAxis[0].max = this.get_max(option.series[0].data) * 2
        option.yAxis[1].max = this.get_max(option.series[1].data) * 2
        option.yAxis[0].interval = option.yAxis[0].max / 8
        option.yAxis[1].interval = option.yAxis[1].max / 8
        myChart.setOption(option);

        console.log("err_msg:", err)
        console.log("err,请求ip", this.ip + "/user/order")
      })
    }
  }

}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
  
  