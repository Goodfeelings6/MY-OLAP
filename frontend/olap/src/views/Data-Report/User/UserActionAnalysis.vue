<template>
  <div style="display: flex;flex-direction: column; justify-content: left;align-items: center;;height: 100%;">
    <!-- <h1>用户留存统计</h1> -->
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
            <b style="font-size: 20px;">用户行为漏斗分析</b>
          </div>
          <div id="main1" style="width: 900px;height:550px;"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
  
<script>
import * as echarts from 'echarts';
export default {
  name: 'UserActionAnalysis',
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
        title: {
          text: '漏斗分析'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c}%'
        },
        toolbox: {
          feature: {
            dataView: { readOnly: false },
            restore: {},
            saveAsImage: {}
          }
        },
        legend: {
          data: ['首页浏览人数', '商品详情页浏览人数', '加入购物车人数', '下单人数', '支付人数']
        },
        series: [
          {
            name: '',
            type: 'funnel',
            left: '10%',
            top: 60,
            bottom: 60,
            width: '80%',
            min: 0,
            max: 100,
            minSize: '0%',
            maxSize: '100%',
            sort: 'descending',
            gap: 2,
            label: {
              show: true,
              position: 'inside',
              color: "#ffffff",
              fontSize: 15,
            },
            labelLine: {
              length: 10,
              lineStyle: {
                width: 1,
                type: 'solid'
              }
            },
            itemStyle: {
              borderColor: '#fff',
              borderWidth: 1
            },
            emphasis: {
              label: {
                fontSize: 20,
                color:"#000000"
              }
            },
            data: [
              { value: 100, name: '首页浏览人数' },
              { value: 80, name: '商品详情页浏览人数' },
              { value: 60, name: '加入购物车人数' },
              { value: 40, name: '下单人数' },
              { value: 20, name: '支付人数' }
            ]
          }
        ]
      };

      option && myChart.setOption(option);
      // 请求数据
      this.request.post(this.ip + "/user/action", {
        dt: dt,
        recent_days:recent_days
      }).then(res => {
        console.log("请求ip", this.ip + "/user/action", "成功", res)
        let maxs =  res[0]["home_count"]
        option.series[0].data[0].value = res[0]["home_count"]/maxs*100
        option.series[0].data[1].value = res[0]["good_detail_count"]/maxs*100
        option.series[0].data[2].value = res[0]["cart_count"]/maxs*100
        option.series[0].data[3].value = res[0]["order_count"]/maxs*100
        option.series[0].data[4].value = res[0]["payment_count"]/maxs*100

        myChart.setOption(option);
      }).catch(err => {
        console.log("err_msg:", err)
        console.log("err,请求ip", this.ip + "/user/action")
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
  
  
  