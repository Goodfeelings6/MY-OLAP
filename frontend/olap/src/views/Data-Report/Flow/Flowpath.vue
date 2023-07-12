<template>
  <div style="display: flex;flex-direction: column; justify-content: left;align-items: center;height: 100%;">
    <!-- <h1>路径统计</h1> -->
    <div style="display: flex;justify-content: space-between;margin-top: 15px;margin-bottom: 30px">
      <div class="fc" style="margin-right: 400px;">
        <el-date-picker v-model="date1" type="date" value-format="yyyy-MM-dd"
        placeholder="选择日期"  @change="checkout_date">
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
            <b style="font-size: 20px;">用户跳转页面路径分析</b>
          </div>
          <div id="main" style="width: 1200px;height:500px;"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
  
<script>
import * as echarts from 'echarts';
export default {
  name: 'Flowpath',
  props: {
  },
  data() {
    return {
      // 控件
      date1: "2020-06-14",
      radio1: "最近1天",
    }
  },
  methods: {
    getdays(para) {
      if (para == "最近1天")
        return "1";
      else if (para == "最近7天")
        return "7";
      else if (para == "最近30天")
        return "30";
    },
    load1(dt,recent_days) {
      var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option;
    option = {
      title: {
        text: ''
      },
      tooltip: {
        trigger: 'item',
        triggerOn: 'mousemove'
      },
      series: [
        {
          type: 'sankey',
          emphasis: {
            focus: 'adjacency'
          },
          nodeAlign: 'left',
          data: [],
          links: [],
          lineStyle: {
            color: 'source',
            curveness: 0.5
          }
        }
      ]
    }
    myChart.showLoading();
    // 请求数据
    this.request.post(this.ip + "/flow/path", {
      dt: dt,
      recent_days: recent_days
    }).then(res => {
      console.log("请求ip", this.ip + "/flow/path", "成功", res)
      var nodes = []
      var links = []
      res.forEach(obj => {
        var link = {}
        link.source = obj.source;
        link.target = obj.target;
        link.value = obj.path_count;
        links.push(link);

        nodes.push({ name: obj.source })
        nodes.push({ name: obj.target })
      });
      // 去重
      for (var i = 0; i < nodes.length - 1; i++) {
        for (var j = i + 1; j < nodes.length; j++) {
          if (nodes[i].name == nodes[j].name) {
            nodes.splice(j, 1);
            //因为数组长度减小1，所以直接 j++ 会漏掉一个元素，所以要 j--
            j--;
          }
        }
      }

      console.log(nodes);
      console.log(links);

      option.series[0].data = nodes;
      option.series[0].links = links;

      myChart.hideLoading();
      myChart.setOption(option);
    }).catch(err => {
      console.log("err_msg:", err)
      console.log("err,请求ip", this.ip + "/flow/path")
    })

    myChart.setOption(option);

    // option && myChart.setOption(option);
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

  },
  mounted() {
    this.load1(this.date1, this.getdays(this.radio1));
  }
}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
  