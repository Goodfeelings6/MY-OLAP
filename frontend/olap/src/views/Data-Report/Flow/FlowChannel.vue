<template>
  <div class="fc" style="flex-direction: column;gap: 25px;">
    <h1 style="font-size: 20px;">各渠道流量统计</h1>
    <el-row :gutter="20">
      <el-col :span="24">
        <el-card class="fc">
          <div class="fc mb-10">
            <b style="font-size: 15px;">各渠道独立访客数</b>
          </div>
          <div id="main1" style="width: 800px;height:350px;"></div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="30">
        <el-card class="fc">
          <div class="fc mb-10">
            <b style="font-size: 15px;">各渠道会话统计</b>
          </div>
          <div id="main2" style="width: 1200px;height:500px;"></div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col>
        <el-card>
          <div class="fc mb-10">
            <b style="font-size: 15px;">各渠道跳出率</b>
          </div>
          <div id="main3" style="width: 800px;height:500px;"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
  
<script>
import * as echarts from 'echarts';
export default {
  name: 'FlowChannel',
  props: {
  },
  data() {
    return {

    }
  },
  methods: {
    // 将响应数据整合成name字段组成的数组
    collect(data, name, stoi=0) {
      var arr = [];
      for (let i = 0; i < data.length; i++) {
        let tmp = data[i][name]
        if(stoi) tmp = eval(tmp)
        arr.push(tmp)
      }
      return arr;
    },
    load1()// main1
    {
      var app = {};
      var chartDom = document.getElementById('main1');
      var myChart = echarts.init(chartDom);
      var option;
      const posList = [
        'left',
        'right',
        'top',
        'bottom',
        'inside',
        'insideTop',
        'insideLeft',
        'insideRight',
        'insideBottom',
        'insideTopLeft',
        'insideTopRight',
        'insideBottomLeft',
        'insideBottomRight'
      ];
      app.configParameters = {
        rotate: {
          min: -90,
          max: 90
        },
        align: {
          options: {
            left: 'left',
            center: 'center',
            right: 'right'
          }
        },
        verticalAlign: {
          options: {
            top: 'top',
            middle: 'middle',
            bottom: 'bottom'
          }
        },
        position: {
          options: posList.reduce(function (map, pos) {
            map[pos] = pos;
            return map;
          }, {})
        },
        distance: {
          min: 0,
          max: 100
        }
      };
      app.config = {
        rotate: 0,
        align: 'center',
        verticalAlign: 'bottom',
        position: 'top',
        distance: 0,
        onChange: function () {
          const labelOption = {
            rotate: app.config.rotate,
            align: app.config.align,
            verticalAlign: app.config.verticalAlign,
            position: app.config.position,
            distance: app.config.distance
          };
          myChart.setOption({
            series: [
              {
                label: labelOption
              }
            ]
          });
        }
      };
      const labelOption = {
        show: true,
        position: app.config.position,
        distance: app.config.distance,
        align: app.config.align,
        verticalAlign: app.config.verticalAlign,
        rotate: app.config.rotate,
        formatter: '{c}',
        fontSize: 16,
        rich: {
          name: {}
        }
      };
      option = {
        color: ["#409EFF"],
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        toolbox: {
          show: true,
          orient: 'vertical',
          left: 'right',
          top: 'center',
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ['line', 'bar', 'stack'] },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        xAxis: [
          {
            type: 'category',
            axisTick: { show: false },
            data: ['360', 'vivo', 'oppo', 'huawei', 'xiaomi']
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '独立访客数',
            type: 'bar',
            barGap: 0,
            label: labelOption,
            emphasis: {
              focus: 'series'
            },
            data: [320, 332, 301, 334, 390]
          }
        ]
      };
      option && myChart.setOption(option);
      // 请求数据
      this.request.post(this.ip + "/flow/cust_num", {
        dt: "2020-06-14",
        recent_days: "1"
      }).then(res => {
        console.log("请求ip", this.ip + "/flow/cust_num","成功",res)
        option.xAxis[0].data = this.collect(res, 'channel')
        option.series[0].data = this.collect(res, 'uv_count', 1)
        myChart.setOption(option);
      }).catch(err => {
        console.log("err_msg:", err)
        console.log("err,请求ip", this.ip+ "/flow/cust_num")
      })
    },
    load2()// main2
    {
      var chartDom = document.getElementById('main2');
      var myChart = echarts.init(chartDom);
      var option;

      const colors = ['#11ffff', '#67C23A', '#EE6666'];
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
        dt: "2020-06-14",
        recent_days: "1"
      }).then(res => {
        console.log("请求ip", this.ip + "/flow/jump","成功",res)
        option.xAxis[0].data = this.collect(res, 'channel')
        option.series[0].data = this.collect(res, 'sv_count')
        option.series[1].data = this.collect(res, 'avg_page_count')
        option.series[2].data = this.collect(res, 'avg_duration_sec')
        myChart.setOption(option);
      }).catch(err => {
        console.log("err_msg:",err)
        console.log("err,请求ip", this.ip+ "/flow/session")
      })
    },
    load3()// main3
    {
      var app = {};
      var chartDom = document.getElementById('main3');
      var myChart = echarts.init(chartDom);
      var option;
      const posList = [
        'left',
        'right',
        'top',
        'bottom',
        'inside',
        'insideTop',
        'insideLeft',
        'insideRight',
        'insideBottom',
        'insideTopLeft',
        'insideTopRight',
        'insideBottomLeft',
        'insideBottomRight'
      ];
      app.configParameters = {
        rotate: {
          min: -90,
          max: 90
        },
        align: {
          options: {
            left: 'left',
            center: 'center',
            right: 'right'
          }
        },
        verticalAlign: {
          options: {
            top: 'top',
            middle: 'middle',
            bottom: 'bottom'
          }
        },
        position: {
          options: posList.reduce(function (map, pos) {
            map[pos] = pos;
            return map;
          }, {})
        },
        distance: {
          min: 0,
          max: 100
        }
      };
      app.config = {
        rotate: 0,
        align: 'center',
        verticalAlign: 'bottom',
        position: 'top',
        distance: 0,
        onChange: function () {
          const labelOption = {
            rotate: app.config.rotate,
            align: app.config.align,
            verticalAlign: app.config.verticalAlign,
            position: app.config.position,
            distance: app.config.distance
          };
          myChart.setOption({
            series: [
              {
                label: labelOption
              }
            ]
          });
        }
      };
      const labelOption = {
        show: true,
        position: app.config.position,
        distance: app.config.distance,
        align: app.config.align,
        verticalAlign: app.config.verticalAlign,
        rotate: app.config.rotate,
        formatter: '{c}',
        fontSize: 16,
        rich: {
          name: {}
        }
      };
      option = {
        color:["#FF7F50"],
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        toolbox: {
          show: true,
          orient: 'vertical',
          left: 'right',
          top: 'center',
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ['line', 'bar', 'stack'] },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        xAxis: [
          {
            type: 'category',
            axisTick: { show: false },
            data: ['360', 'vivo', 'oppo', 'huawei', 'xiaomi']
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '跳出率',
            type: 'bar',
            barGap: 0,
            label: labelOption,
            emphasis: {
              focus: 'series'
            },
            data: [0.13, 0.3, 0.14, 0.22, 0.7]
          }
        ]
      };
      option && myChart.setOption(option);
      // 请求数据
      this.request.post(this.ip + "/flow/jump", {
        dt: "2020-06-14",
        recent_days: "1"
      }).then(res => {
        console.log("请求ip", this.ip + "/flow/jump","成功",res)
        option.xAxis[0].data = this.collect(res, 'channel')
        option.series[0].data = this.collect(res, 'bounce_rate')
        myChart.setOption(option);
      }).catch(err => {
        console.log("err_msg:",err)
        console.log("err,请求ip", this.ip + "/flow/jump")
      })
    }
  },
  mounted() {
    this.load1();
    this.load2();
    this.load3();
  }
}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
  