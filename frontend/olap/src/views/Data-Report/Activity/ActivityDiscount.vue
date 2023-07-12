<template>
  <div style="display: flex;flex-direction: column; justify-content: left;align-items: center;height: 100%;">
    <!-- <h1>活动补贴率</h1> -->
    <div style="display: flex;justify-content: space-between;margin-top: 15px;margin-bottom: 30px">
      <div class="fc">
        <el-date-picker v-model="date1" type="date" value-format="yyyy-MM-dd" placeholder="选择日期"
           @change="checkout_date">
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
            <b style="font-size: 20px;">活动补贴率</b>
          </div>
          <div id="main1" style="width: 1000px;height:500px;"></div>
          <div class="fc" style="margin-top: -30px;">
            <b style="font-size: 15px;color: rgb(96, 96, 97);">活动名称</b>
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
      date1: "2020-06-14"
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
    get_max(data) {
      let m = 0;
      data.forEach(element => {
        if (element > m) m = element;
      });
      return m;
    },
    load1(dt)// main1
    {
      var chartDom = document.getElementById('main1');
      var myChart = echarts.init(chartDom);
      var option;

      option = {
        color: ["#aacc50"],
        tooltip: {
          trigger: 'axis',
          formatter: function (param) {
            // console.log(param);
            param = param[0]
            if (param.data) {
              return param.marker + "活动名称 : " + param.name
                + " <br/>  活动补贴率 : " + param.value
                + " <br/>  发布日期 : " + param.data.start_time
            } else return null;
          },
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
          data: ['活动补贴率']
        },
        xAxis: [
          {
            type: 'category',
            data: ["活动1", "活动2", "活动3", "活动4", "活动5"],
            axisPointer: {
              type: 'shadow'
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            name: '活动补贴率',
            min: 0,
            max: 1,
            interval: 0.2,
            axisLabel: {
              formatter: '{value}'
            }
          }
        ],
        series: [
          {
            name: '活动补贴率',
            type: 'bar',
            tooltip: {
              valueFormatter: function (value) {
                return value;
              }
            },
            data: [
              { value: 0.1, start_time: "2020-06-12" },
              { value: 0.6, start_time: "2020-06-10" },
              { value: 0.5, start_time: "2020-06-11" },
              { value: 0.7, start_time: "2020-06-19" },
              { value: 0.3, start_time: "2020-06-04" },
            ]
          }
        ]
      };

      option && myChart.setOption(option);

      // 请求数据
      this.request.post(this.ip + "/activity/discount", {
        dt: dt,
      }).then(res => {
        console.log("请求ip", this.ip + "/activity/discount", "成功", res)
        var axis = []
        var mydata = []
        res.forEach(obj => {
          axis.push(obj.activity_name);
          var tmp = {}
          tmp.value = obj.reduce_rate;
          tmp.start_time = obj.start_date;
          mydata.push(tmp);
        });

        option.series[0].data = mydata;
        option.xAxis[0].data = axis;
        myChart.setOption(option);
      }).catch(err => {
        console.log("err_msg:", err)
        console.log("err,请求ip", this.ip + "/activity/discount")
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
  
  
  
  