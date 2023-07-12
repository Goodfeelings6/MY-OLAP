<template>
  <div>
    <div style="display: flex;justify-content: space-around;">
      <div class="fc" style="margin-top: 10px;">
        <el-date-picker v-model="date1" type="date" value-format="yyyy-MM-dd" placeholder="选择日期" @change="checkout_date">
        </el-date-picker>
      </div>
      <div>
        <h1 style="font-size: 25px;">用户情感分析</h1>
      </div>
      <div class="fc">
        <el-radio-group v-model="radio1" style="margin-top: 10px;" @change="checkout_scope">
          <el-radio-button label="最近1天"></el-radio-button>
          <el-radio-button label="最近7天"></el-radio-button>
          <el-radio-button label="最近30天"></el-radio-button>
        </el-radio-group>
      </div>
    </div>

    <div style="display: flex;margin-left: 50px; gap: 10px;">
      <!-- 左侧 -->
      <div style="margin-top: 80px;">
        <el-row class="fc">
          <el-col :span="9">
            <el-tag>
              <div class="fc" style="font-size: 15px;">
                商品SPU-ID选择:
              </div>
            </el-tag>
          </el-col>
          <el-col :span="8">
            <!-- 商品选择 -->
            <el-select v-model="value" placeholder="spu_id" size="small" @change="checkout_spu">
              <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-col>
        </el-row>
        <!-- 饼图 -->
        <el-row class="fc" style="margin-top: 20px;">
          <el-card class="fc">
            <el-row>
              <el-col :span="4">
                <div id="main1" style="height: 300px;width: 400px;" class="fc"></div>
              </el-col>
            </el-row>
          </el-card>
        </el-row>
      </div>
      <!-- 右侧 -->
      <div style="flex-direction: column;margin-left: -170px;margin-top: 40px;" class="fc">
        <!-- 切换 -->
        <el-radio-group v-model="radio" @change="checkout_emo" style="z-index: 1000000;">
          <el-radio :label="0" :border=true size="medium">好评</el-radio>
          <el-radio :label="1" :border=true size="medium">中立</el-radio>
          <el-radio :label="2" :border=true size="medium">差评</el-radio>
        </el-radio-group>
        <!-- 词云图 -->
        <iframe id="iframeA" ref="iframe" :src="htmlSrc[radio]" frameborder="0"
          style="height: 800px;width: 1050px;margin-top: -100px;">
        </iframe>
      </div>
    </div>
  </div>
</template>
  
<script>
import * as echarts from 'echarts';
export default {
  name: 'Emo',
  props: {
  },
  data() {
    return {
      radio: 0,
      htmlSrc: ['static/good.html', 'static/general.html', 'static/bad.html'], // 注意: 直接写 static ,前面不需要加任何路径和字符。
      // 控件
      date1: "2020-06-14",
      radio1: "最近1天",
      // 数据
      spu_id: 'all',
      comment_datas: [
        [
          { value: 1960260, name: "好评数" },
          { value: 246222, name: "中评数" },
          { value: 250206, name: "差评数" }
        ],
        [
          { value: 122341, name: "好评数" },
          { value: 74543, name: "中评数" },
          { value: 12123, name: "差评数" }
        ],
        [
          { value: 94323, name: "好评数" },
          { value: 44322, name: "中评数" },
          { value: 23435, name: "差评数" }
        ],
        [
          { value: 2143, name: "好评数" },
          { value: 3553, name: "中评数" },
          { value: 65, name: "差评数" }
        ]
      ],
      options: [
        {
          value: '0',
          label: 'all'
        },
        {
          value: '1',
          label: 'iPhoneX'
        },
        {
          value: '2',
          label: 'HuaiWei'
        }, {
          value: '3',
          label: 'Vivo'
        }
      ],
      value: '0'
    }
  },
  methods: {
    checkout_scope(v) {
      console.log('选择时间范围', v)
      // this.load_pie(this.comment_datas[i])
    },
    checkout_date(v) {
      console.log('选择日期', v)
    },
    checkout_emo(v) {
      console.log('选择情感', v)
    },
    checkout_spu(v) {
      console.log('选择spu', v)
      this.load_pie(this.comment_datas[v])
    },
    load_pie(data) {
      var chartDom = document.getElementById('main1');
      var myChart = echarts.init(chartDom);
      var option;
      option = {
        title: {
          text: '商品评论分析',
          subtext: '',
          left: 'center',

        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'horizontal',
          // left: 'left'
          top: '20%'
        },
        series: [
          {
            name: '评论',
            type: 'pie',
            radius: '50%',
            top: '40%',
            data: data,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      option && myChart.setOption(option);
    }
  },
  mounted() {
    this.load_pie(this.comment_datas[0])
  }
}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#iframeA {
  transform: scale(.8, .8);
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
}
</style>
  
  