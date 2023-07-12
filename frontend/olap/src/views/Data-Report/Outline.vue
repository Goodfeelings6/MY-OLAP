<template>
  <div>
    <div style="display: flex;justify-content: space-around;">
      <div class="fc" style="margin-top: 10px;">
        <el-date-picker v-model="date1" type="date" value-format="yyyy-MM-dd"
        placeholder="选择日期"  @change="checkout_date">
        </el-date-picker>
      </div>
      <div>
        <h1 style="font-size: 25px;">数据概览</h1>
      </div>
      <div class="fc">
        <el-radio-group v-model="radio1" style="margin-top: 10px;" @change="checkout_scope">
          <el-radio-button label="最近1天"></el-radio-button>
          <el-radio-button label="最近7天"></el-radio-button>
          <el-radio-button label="最近30天"></el-radio-button>
        </el-radio-group>
      </div>
    </div>

    <el-row :gutter="20" style="margin-bottom: 20px;margin-left: 180px;margin-top: 10px;">
      <el-col :span="10">
        <el-card style="color: #67C23A;">
          <div style="margin-bottom: 10px;font-size: 20px;font-weight: 1000;"><i class="el-icon-money">订单总额GMV</i></div>
          <div class="fc" style="font-size: 20px;">￥{{ gmv }}</div>
        </el-card>
      </el-col>
      <el-col :span="10">
        <el-card style="color: #409EFF;">
          <div style="margin-bottom: 10px;font-size: 20px;font-weight: 1000;"><i class="el-icon-s-order">订单数量</i></div>
          <div class="fc" style="font-size: 20px;">{{ order_num }}</div>
        </el-card>
      </el-col>

    </el-row>
    <el-row :gutter="20" style="margin-bottom: 20px;">
      <el-col :span="8">
        <el-card style="color: #409EFF;">
          <div style="margin-bottom: 10px;font-size: 20px;font-weight: 1000;"><i class="el-icon-s-custom">下单人数</i></div>
          <div class="fc" style="font-size: 20px;">{{ order_user }}</div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card style="color: #F56C6C;">
          <div style="margin-bottom: 10px;font-size: 20px;font-weight: 1000;"><i class="el-icon-delete">退单数</i></div>
          <div class="fc" style="font-size: 20px;">{{ rebund_num }}</div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card style="color: #F56C6C;">
          <div style="margin-bottom: 10px;font-size: 20px;font-weight: 1000;"><i class="el-icon-s-custom">退单人数</i></div>
          <div class="fc" style="font-size: 20px;">{{ rebund_user }}</div>
        </el-card>
      </el-col>
    </el-row>
    <div class="fc" style="gap: 10px;">
      <el-row :gutter="20">
      <el-col :span="30">
        <el-card>
          <div class="fc mb-10">
            <b style="font-size: 20px;">用户新增活跃统计</b>
          </div>
          <div id="main1" style="width: 500px;height:400px;"></div>
          <div class="fc">
            <div style="font-size: 15px;color: rgb(0, 133, 243);" color="#ffffff">日期</div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="30">
        <el-card class="fc">
          <div class="fc mb-10">
            <b style="font-size: 15px;">各渠道会话统计</b>
          </div>
          <div id="main2" style="width: 660px;height:425px;"></div>
          <div class="fc" style="margin-top: -30px;">
            <b style="font-size: 15px;color: rgb(96, 96, 97);">渠道</b>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
  </div>
</template>
  
<script>
import * as echarts from 'echarts';
export default {
  name: 'Outline',
  props: {
  },
  data() {
    return {
      // 控件
      date1: "2020-06-14",
      radio1: "最近1天",
      // 数据
      gmv: 12043,
      order_num: 102,
      order_user: 93,
      rebund_num: 10,
      rebund_user: 7
    }
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
    load_outline(dt, recent_days) {
      // 请求数据
      this.request.post(this.ip + "/outline", {
        dt: dt,
        recent_days: recent_days
      }).then(res => {
        console.log("请求ip", this.ip + "/outline", "成功", res)
        res = res[0];
        this.gmv = res.order_total_amount;
        this.order_num = res.order_count;
        this.order_user = res.order_user_count;
        this.rebund_num = res.order_refund_count;
        this.rebund_user = res.order_refund_user_count;
      }).catch(err => {
        console.log("err_msg:", err)
        console.log("err,请求ip", this.ip + "/outline")
      })
    },
    load_user(dt,recent_days)// main1
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
            interval: 40,
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

        // 按 dt 升序
        res.sort((a, b) => {
          if (a.dt < b.dt) return -1;
          else if (a.dt == b.dt) return 0;
          else return 1;
        })

        option.xAxis[0].data = this.collect(res, 'dt')
        option.series[0].data = this.collect(res, 'new_user_count', 1)
        option.series[1].data = this.collect(res, 'active_user_count', 1)
        option.yAxis[0].max = this.get_max(option.series[0].data) * 2
        option.yAxis[1].max = this.get_max(option.series[1].data) * 2
        option.yAxis[0].interval = option.yAxis[0].max / 8
        option.yAxis[1].interval = option.yAxis[1].max / 8
        myChart.setOption(option);
      }).catch(err => {
        // option.yAxis[0].max = this.get_max(option.series[0].data) * 2
        // option.yAxis[1].max = this.get_max(option.series[1].data) * 2
        // option.yAxis[0].interval = option.yAxis[0].max / 8
        // option.yAxis[1].interval = option.yAxis[1].max / 8
        // myChart.setOption(option);

        console.log("err_msg:", err)
        console.log("err,请求ip", this.ip + "/user/active")
      })
    },
    load_channel(dt, recent_days)// main2
    {
      var chartDom = document.getElementById('main2');
      var myChart = echarts.init(chartDom);
      var option;

      const colors = ['#11aaff', '#67cc3A', '#EE6666'];
      option = {
        color: colors,
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          }
        },
        grid: {
          right: '20%'
        },
        toolbox: {
          feature: {
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        legend: {
          data: ['会话总数', '会话平均浏览页面数', '会话平均停留时长']
        },
        xAxis: [
          {
            type: 'category',
            axisTick: {
              alignWithLabel: true
            },
            // prettier-ignore
            data: ['360', 'vivo', 'oppo', 'huawei', 'xiaomi']
          }
        ],
        yAxis: [
          {
            type: 'value',
            name: '总数量',
            position: 'right',
            alignTicks: true,
            axisLine: {
              show: true,
              lineStyle: {
                color: colors[0]
              }
            },
            axisLabel: {
              formatter: '{value} 个'
            }
          },
          {
            type: 'value',
            name: '页面数',
            position: 'right',
            alignTicks: true,
            offset: 80,
            axisLine: {
              show: true,
              lineStyle: {
                color: colors[1]
              }
            },
            axisLabel: {
              formatter: '{value} 个'
            }
          },
          {
            type: 'value',
            name: '时长',
            position: 'left',
            alignTicks: true,
            axisLine: {
              show: true,
              lineStyle: {
                color: colors[2]
              }
            },
            axisLabel: {
              formatter: '{value} s'
            }
          }
        ],
        series: [
          {
            name: '会话总数',
            type: 'bar',
            data: [320, 332, 301, 334, 390]
          },
          {
            name: '会话平均浏览页面数',
            type: 'bar',
            yAxisIndex: 1,
            data: [220, 182, 191, 234, 290]
          },
          {
            name: '会话平均停留时长',
            type: 'line',
            yAxisIndex: 2,
            data: [150, 232, 201, 154, 190]
          }
        ]
      };
      option && myChart.setOption(option);
      // 请求数据
      this.request.post(this.ip + "/flow/session", {
        dt: dt,
        recent_days: recent_days
      }).then(res => {
        console.log("请求ip", this.ip + "/flow/jump", "成功", res)
        option.xAxis[0].data = this.collect(res, 'channel')
        option.series[0].data = this.collect(res, 'sv_count')
        option.series[1].data = this.collect(res, 'avg_page_count')
        option.series[2].data = this.collect(res, 'avg_duration_sec')
        myChart.setOption(option);
      }).catch(err => {
        console.log("err_msg:", err)
        console.log("err,请求ip", this.ip + "/flow/session")
      })
    },
    // 切换统计时间范围
    checkout_scope() {
      // console.log(this.radio1);
      this.load_user(this.date1, this.getdays(this.radio1));
      this.load_outline(this.date1, this.getdays(this.radio1));
      this.load_channel(this.date1, this.getdays(this.radio1));
    },
    // 切换日期
    checkout_date() {
      // console.log(this.date1);
      this.load_user(this.date1, this.getdays(this.radio1));
      this.load_outline(this.date1, this.getdays(this.radio1));
      this.load_channel(this.date1, this.getdays(this.radio1));
    }
  },
  mounted() {
    this.load_user(this.date1, this.getdays(this.radio1));
    this.load_outline(this.date1, this.getdays(this.radio1));
    this.load_channel(this.date1, this.getdays(this.radio1));
  }
}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
  
  