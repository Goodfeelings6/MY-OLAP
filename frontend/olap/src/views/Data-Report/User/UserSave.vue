<template>
  <div style="display: flex;flex-direction: column; justify-content: left;align-items: center;;height: 100%;">
    <!-- <h1>用户留存统计</h1> -->
    <div style="display: flex;justify-content: space-between;margin-top: 15px;margin-bottom: 30px">
      <div class="fc" >
        <el-date-picker v-model="date1" type="date" value-format="yyyy-MM-dd"
        placeholder="选择日期" :picker-options="pickerOptions" @change="checkout_date">
        </el-date-picker>
      </div>
      <!-- <div class="fc">
        <el-radio-group v-model="radio1" @change="checkout_scope">
          <el-radio-button label="最近1天"></el-radio-button>
          <el-radio-button label="最近7天"></el-radio-button>
          <el-radio-button label="最近30天"></el-radio-button>
        </el-radio-group>
      </div> -->
    </div>
    <el-row :gutter="20">
      <el-col :span="25">
        <el-card>
          <div class="fc mb-10">
            <b style="font-size: 20px;">用户留存统计</b>
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
  name: 'UserSave',
  props: {
  },
  data() {
    return {
      // 控件
      date1: "2020-06-14",
    }
  },
  // 加载渲染数据
  mounted() {
    this.load1(this.date1);
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
    load1(dt)// main1
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
            magicType: { show: true, type: ['line', 'bar'] },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        legend: {
          data: ['用户留存率']
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
            name: 'rate',
            min: 0,
            max: 1,
            interval: 0.1,
            axisLabel: {
              formatter: '{value}'
            }
          }
        ],
        series: [
          {
            name: '用户留存率',
            type: 'line',
            tooltip: {
              valueFormatter: function (value) {
                return value;
              }
            },
            data: [
              0.5, 0.2, 0.7, 0.6, 0.2
            ]
          }
        ]
      };
      option && myChart.setOption(option);
      // 请求数据
      this.request.post(this.ip + "/user/retained", {
        dt: dt,
      }).then(res => {
        console.log("请求ip", this.ip + "/user/retained","成功",res)
        option.xAxis[0].data = this.collect(res, 'dt')
        option.series[0].data = this.collect(res, 'retention_rate', 1)
        myChart.setOption(option);
      }).catch(err => {
        console.log("err_msg:", err)
        console.log("err,请求ip", this.ip+ "/user/retained")
      }) 
    },
    // 切换日期
    checkout_date() {
      // console.log(this.date1);
      this.load1(this.date1);
    }
  }

}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
  
  