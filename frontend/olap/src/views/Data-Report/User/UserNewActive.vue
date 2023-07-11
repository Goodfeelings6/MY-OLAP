<template>
  <div style="display: flex;flex-direction: column; justify-content: left;align-items: center;height: 100%;">
    <!-- <h1>用户新增活跃统计</h1> -->
    <div style="display: flex;justify-content: space-between;margin-top: 15px;margin-bottom: 30px">
      <div class="fc" style="margin-right: 400px;">
        <el-date-picker v-model="date1" type="date" value-format="yyyy-MM-dd"
        placeholder="选择日期" :picker-options="pickerOptions" @change="checkout_date">
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
            <b style="font-size: 20px;">用户新增活跃统计</b>
          </div>
          <div id="main1" style="width: 1000px;height:500px;"></div>
          <div class="fc">
            <div style="font-size: 15px;color: rgb(0, 133, 243);" color="#ffffff">日期</div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
  
<script>
import * as echarts from 'echarts';
export default {
  name: 'UserNewActive',
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
      var myChart = echarts.init(chartDom, 'dark');
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
          data: ['新增用户数', '活跃用户数']
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
            name: '新增用户数',
            min: 0,
            max: 250,
            interval: 5,
            axisLabel: {
              formatter: '{value} 人'
            }
          },
          {
            type: 'value',
            name: '活跃用户数',
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
            name: '新增用户数',
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
            name: '活跃用户数',
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
      this.request.post(this.ip + "/user/active", {
        dt: dt,
        recent_days:recent_days
      }).then(res => {
        console.log("请求ip", this.ip + "/user/active", "成功", res)
        option.xAxis[0].data = this.collect(res, 'dt')
        option.series[0].data = this.collect(res, 'new_user_count', 1)
        option.series[1].data = this.collect(res, 'active_user_count', 1)
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
        console.log("err,请求ip", this.ip + "/user/active")
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
  
  