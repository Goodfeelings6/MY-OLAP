<template>
  <div style="display: flex;flex-direction: column; justify-content: left;align-items: center;height: 100%;gap: 20px;">
    <!-- <h1>用户变动统计</h1> -->
    <div style="display: flex;justify-content: space-between;margin-top: 15px;margin-bottom: 15px">
      <div class="fc" >
        <el-date-picker v-model="date1" type="date" value-format="yyyy-MM-dd"
        placeholder="选择日期"  @change="checkout_date">
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
            <b style="font-size: 20px;">用户变动统计</b>
          </div>
          <div id="main1" style="width: 1000px;height:500px;"></div>
          <div class="fc" style="margin-top: -30px;">
            <b style="font-size: 15px;">日期</b>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <!-- <div class="fc mt-10" style="flex-direction: column;gap: 10px;">
      <div>{{ testval }}</div>
      <el-button type="primary" style="height: 40px;width: 100px;" @click="func_test">点击测试</el-button>
    </div> -->
  </div>
</template>
  
<script>
import * as echarts from 'echarts';
export default {
  name: 'UserChange',
  props: {
  },
  data() {
    return {
      testval: '测试前',
      // 控件
      date1: "2020-06-14",
      radio1: "最近1天"
    }

  },
  // 加载渲染数据
  mounted() {
    this.load1(this.date1);
  },
  methods: {
    func_test() {
      console.log("test");
      this.request.get(this.ip + "/test").then(res => {
        console.log("响应:", res)
        this.testval = res.data.testval
      }).catch(err => {
        console.log(err)
        console.log("请求接口", this.ip + "/test")
        this.testval = '测试失败'
      })
    },
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
          data: ['流失用户数', '回流用户数']
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
            name: '流失用户数',
            min: 0,
            max: 20,
            interval: 5,
            axisLabel: {
              formatter: '{value} 人'
            }
          },
          {
            type: 'value',
            name: '回流用户数',
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
            name: '流失用户数',
            type: 'line',
            tooltip: {
              valueFormatter: function (value) {
                return value + ' 人';
              }
            },
            data: [
              5, 2, 7, 6, 2
            ]
          },
          {
            name: '回流用户数',
            type: 'line',
            tooltip: {
              valueFormatter: function (value) {
                return value + ' 人';
              }
            },
            data: [
              10, 5, 11, 12, 6
            ]
          }
        ]
      };
      option && myChart.setOption(option);
      // 请求数据
      this.request.post(this.ip + "/user/change", {
        dt: dt,
      }).then(res => {
        console.log("请求ip", this.ip + "/user/change","成功",res)
        option.xAxis[0].data = this.collect(res, 'dt')
        option.series[0].data = this.collect(res, 'user_churn_count', 1)
        option.series[1].data = this.collect(res, 'user_back_count', 1)
        option.yAxis[0].max = this.get_max(option.series[0].data)*2
        option.yAxis[1].max = this.get_max(option.series[1].data)*2
        myChart.setOption(option);
      }).catch(err => {
        console.log("err_msg:", err)
        console.log("err,请求ip", this.ip+ "/user/change")
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
  