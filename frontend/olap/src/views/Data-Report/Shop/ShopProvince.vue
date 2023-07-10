<template>
  <div style="display: flex;flex-direction: column; justify-content: center;align-items: center;height: 100%;">
    <!-- <h1>各省份交易统计</h1> -->
    <el-row :gutter="20">
      <el-col :span="25">
        <el-card>
          <div class="fc mb-10">
            <b style="font-size: 20px;">各省份交易统计</b>
          </div>
          <div id="main1" style="width: 1000px;height:550px;"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
  
<script>
import * as echarts from 'echarts';
import '@/assets/china.js';

export default {
  name: 'ShopProvince',
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
        function randomData() {
          return Math.round(Math.random()*500);
        }
        var mydata = [
          {name: '北京',value: randomData(),value2: randomData() },{name: '天津',value: randomData(),value2: randomData() },
          {name: '上海',value: randomData(),value2: randomData()},{name: '重庆',value: randomData(),value2: randomData() },
          {name: '河北',value: randomData(),value2: randomData()},{name: '河南',value: randomData(),value2: randomData() },
          {name: '云南',value: randomData(),value2: randomData()},{name: '辽宁',value: randomData(),value2: randomData() },
          {name: '黑龙江',value: randomData(),value2: randomData() },{name: '湖南',value: randomData(),value2: randomData() },
          {name: '安徽',value: randomData(),value2: randomData() },{name: '山东',value: randomData(),value2: randomData() },
          {name: '新疆',value: randomData(),value2: randomData() },{name: '江苏',value: randomData(),value2: randomData() },
          {name: '浙江',value: randomData(),value2: randomData() },{name: '江西',value: randomData(),value2: randomData() },
          {name: '湖北',value: randomData(),value2: randomData() },{name: '广西',value: randomData(),value2: randomData() },
          {name: '甘肃',value: randomData(),value2: randomData() },{name: '山西',value: randomData(),value2: randomData() },
          {name: '内蒙古',value: randomData(),value2: randomData() },{name: '陕西',value: randomData(),value2: randomData() },
          {name: '吉林',value: randomData(),value2: randomData() },{name: '福建',value: randomData(),value2: randomData() },
          {name: '贵州',value: randomData(),value2: randomData() },{name: '广东',value: randomData(),value2: randomData() },
          {name: '青海',value: randomData(),value2: randomData() },{name: '西藏',value: randomData(),value2: randomData() },
          {name: '四川',value: randomData(),value2: randomData() },{name: '宁夏',value: randomData(),value2: randomData() },
          {name: '海南',value: randomData(),value2: randomData() },{name: '台湾',value: randomData(),value2: randomData() },
          {name: '香港',value: randomData(),value2: randomData() },{name: '澳门',value: randomData(),value2: randomData() }
        ];
        var option = {
          backgroundColor: '#FFFFFF',
          title: {
            text: '',
            subtext: '',
            y: '5%',
            x:'center'
          },
          tooltip : {
            trigger: 'item',
            formatter: function (param) {
            // console.log(param);
              if (param.data) {
                return param.marker + param.seriesName + " : " + param.name
                  + " <br/>  订单数 : " + param.value + " 件"
                  + " <br/>  订单金额 : " + param.data.value2 + " 元";
              } else {
                return null
              }
            }
          },

          //左侧小导航图标
          visualMap: {
            show : true,
            x: 'left',
            y: 'center',
            pieces: [{
                gt: 400,
                label: ">400 单",
                color: "#D22E2E"
              }, {
                gt: 300,
                lt: 400,
                label: "300-400 单",
                color: "#ff5428"
              }, {
                gt: 200,
                lt: 300,
                label: "200-300 单",
                color: "#ff8c71"
              },{
                gt: 100,
                lt: 200,
                label: "100-200 单",
                color: "#ffa168"
              },{
                lt: 100,
                label: "<100 单",
                color: "#FFddaa"
              }
            ]

            // 另一种左侧图例
            // splitList: [
            //   {start: 500, end:600},{start: 400, end: 500},
            //   {start: 300, end: 400},{start: 200, end: 300},
            //   {start: 100, end: 200},{start: 0, end: 100},
            // ],
            // color: ['#5475f5', '#9feaa5', '#85daef', '#74e2ca', '#e6ac53', '#9fb5ea'],
          },

          //配置属性
          series: [{
            name: '省份',
            type: 'map',
            mapType: 'china',
            roam: true,
            label: {
              normal: {
                show: true  //省份名称
              },
              emphasis: {
                show: false
              }
            },
            data:mydata  //数据
          }]
        };
        //初始化echarts实例
        var chartDom = document.getElementById('main1');
        var myChart = echarts.init(chartDom);
        //使用制定的配置项和数据显示图表
        myChart.setOption(option);

      // 请求数据
      this.request.post(this.ip + "/province", {
        dt: "2020-06-14",
        recent_days:"1"
      }).then(res => {
        console.log("请求ip", this.ip + "/province", "成功", res)
        var pn = this.collect(res, 'province_name')
        var oc = this.collect(res, 'order_count', 1)
        var ot = this.collect(res, 'order_total_amount', 1)

        let max = this.get_max(oc);
        let inter = max / 6;
        var pieces = [
          {
                gt: inter*4,
                label: ">"+inter*4+" 单",
                color: "#D22E2E"
              }, {
                gt: inter*3,
                lt: inter*4,
                label: inter*3+"-"+inter*4 +" 单",
                color: "#ff5428"
              }, {
                gt: inter*2,
                lt: inter*3,
                label: inter*2+"-"+inter*3 +" 单",
                color: "#ff8c71"
              },{
                gt: inter,
                lt: inter*2,
                label: inter+"-"+inter*2 +" 单",
                color: "#ffa168"
              },{
                lt: inter,
                label: "<"+inter+" 单",
                color: "#FFddaa"
              }]
        option.visualMap.pieces = pieces;

        mydata.forEach(e => {
          e.value = 0;
        });
        for (let i = 0; i < pn.length; i++){
          mydata[pn[i].name].value = oc[i];
          mydata[pn[i].name].value2 = ot[i];
        }

        option.series.data = mydata;
        myChart.setOption(option);
      }).catch(err => {
        console.log("err_msg:", err)
        console.log("err,请求ip", this.ip + "/province")
        myChart.setOption(option);

      })
    }
  }

}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
  
  