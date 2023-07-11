<template>
  <div style="display: flex;flex-direction: column; justify-content: left;align-items: center;height: 100%;">
    <!-- <h1>复购率统计</h1> -->
    <div style="display: flex;justify-content: space-between;margin-top: 15px;margin-bottom: 30px">
      <div class="fc" style="margin-right: 400px;">
        <el-date-picker v-model="date1" type="date" value-format="yyyy-MM-dd" placeholder="选择日期"
          :picker-options="pickerOptions" @change="checkout_date">
        </el-date-picker>
      </div>
      <div class="fc">
        <el-radio-group v-model="radio1" @change="checkout_scope">
          <el-radio-button label="最近7天"></el-radio-button>
          <el-radio-button label="最近30天"></el-radio-button>
        </el-radio-group>
      </div>
    </div>
    <el-row :gutter="20">
      <el-col :span="25">
        <el-card>
          <div class="fc mb-10">
            <b style="font-size: 20px;">复购率统计</b>
          </div>
          <div id="main1" style="width: 1200px;height:500px;"></div>
          <div class="fc" style="margin-top: -30px;">
            <b style="font-size: 15px;color: rgb(96, 96, 97);">品牌名称</b>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
  
<script>
import * as echarts from 'echarts';
export default {
  name: 'ShopBrandRebuy',
  props: {
  },
  data() {
    return {
      // 控件
      date1: "2020-06-14",
      radio1: "最近7天",
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
        color: ["#eedd00"],
        tooltip: {
          trigger: 'axis',
          formatter: function (param) {
            // console.log(param);
            param = param[0]
            if (param.data) {
              return param.marker + "品牌名称 : " + param.name
                + " <br/>  复购率 : " + param.value
                + " <br/>  品牌ID : " + param.data.id
            }
            else {
              return null
            }
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
          data: ['复购率']
        },
        xAxis: [
          {
            type: 'category',
            data: ["品牌1", "品牌2", "品牌3", "品牌4", "品牌5"],
            axisPointer: {
              type: 'shadow'
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            name: '复购率',
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
            name: '复购率',
            type: 'bar',
            tooltip: {
              valueFormatter: function (value) {
                return value;
              }
            },
            data: [
              { value: 0.1, id: "2" },
              { value: 0.6, id: "0" },
              { value: 0.5, id: "1" },
              { value: 0.7, id: "9" },
              { value: 0.3, id: "4" }
            ]
          }
        ]
      };

      option && myChart.setOption(option);

      // 请求数据
      this.request.post(this.ip + "/brand/rebuy", {
        dt: dt,
        recent_days: recent_days
      }).then(res => {
        console.log("请求ip", this.ip + "/brand/rebuy", "成功", res)
        var axis = []
        var mydata = []
        res.forEach(obj => {
          axis.push(obj.tm_name);
          var tmp = {}
          tmp.value = obj.order_repeat_rate;
          tmp.id = obj.tm_id;
          mydata.push(tmp);
        });
        // console.log(axis);
        console.log(mydata);
        option.series[0].data = mydata;
        option.xAxis[0].data = axis;
        myChart.setOption(option);
      }).catch(err => {
        console.log("err_msg:", err)
        console.log("err,请求ip", this.ip + "/brand/rebuy")
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
  
  
  
  