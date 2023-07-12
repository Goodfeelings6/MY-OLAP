<template>
    <div style="display: flex;align-items: center;justify-content: center;flex-direction: column;">
        <!-- 商品推荐模块 -->
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm"
            style="width: 50%;margin-left: 20px; margin-right: auto;">
            <el-form-item prop="scope" label="类别">
                <el-select v-model="ruleForm.type" placeholder="请选择ID类别">
                    <el-option label="用户ID" value="1"></el-option>
                    <el-option label="商品ID" value="2"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="ID" prop="id">
                <el-input type="number" v-model="ruleForm.id" placeholder="请输入用户ID:1~3000 或 商品ID:1~35"></el-input>
            </el-form-item>

            <el-form-item label="推荐数量" prop="num" type="">
                <el-input type="number" v-model="ruleForm.num" placeholder='请输入推荐数量：1~35'></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <el-button type="danger " @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>

        <!-- tip -->
        <div v-if="tip">
            <el-tag type="success" style="font-size: 17px;height: 40px;line-height: 40px;" size="medium">
                {{ tip }}
            </el-tag>
        </div>

        <!-- 表格 -->
        <div style="width: 90%;margin-top: 20px;">
            <el-table :data="currentPage_tableData" style="width: 100%" v-loading="loading" @sort-change="table_sort">
                <el-table-column prop="id" label="推荐商品ID" sortable width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="name" label="推荐商品名称" width="800" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="degree" label="推荐程度" sortable header-align="center" align="center">
                </el-table-column>
            </el-table>
        </div>

        <!-- 分页 -->
        <div class="block" style="margin-top: 20px;">
            <!-- <span class="demonstration">完整功能</span> -->
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
                :page-sizes="[3, 5, 10, 15]" :page-size="page_size" layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
        </div>

        <!-- pie -->
        <el-card style="margin-top: 40px;" v-show="total != 0">
            <div class="fc">
                <div style="width: 10px;"><b>商品ID</b></div>
                <div class="fc" style="flex-direction: column;">
                    <div id="main1" style="width: 1000px;height:400px;"></div>
                    <div><b>推荐指数</b></div>
                </div>
            </div>
        </el-card>
    </div>
</template>
  
<script>
import * as echarts from 'echarts';
export default {
    name: 'ShopRecommend',
    props: {
    },
    data() {
        // 校验id函数
        var validateID = (rule, value, callback) => {
            if (!value) {
                if (this.ruleForm.type === '1')
                    return callback(new Error('用户ID不能为空'));
                else if (this.ruleForm.type === '2')
                    return callback(new Error('用户ID不能为空'));
            }
            setTimeout(() => {
                // console.log(this.ruleForm.type);
                if (this.ruleForm.type === '1') {
                    if (value > 0 && value <= 3000)
                        callback();
                    else
                        callback(new Error('用户ID只能为在1 ~ 3000的整数'));
                } else if (this.ruleForm.type === '2') {
                    if (value > 0 && value <= 35)
                        callback();
                    else
                        callback(new Error('商品ID只能为在1 ~ 35的整数'));
                }
            }, 200);
        };
        // 校验num函数
        var validateNum = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('推荐数量不能为空'));
            }
            setTimeout(() => { // 延迟一定时间再返回校验结果
                if (value > 0 && value <= 35)
                    callback();
                else
                    callback(new Error('推荐数量只能为在1 ~ 35的整数'));
            }, 200);
        };
        return {
            // 表单
            ruleForm: {
                type: "1",
                id: '',
                num: '5'
            },
            // 校验规则
            rules: {
                id: [ // 这里的id 字段 对应 prop='id' 的输入框
                    { validator: validateID, trigger: 'blur' }
                ],
                num: [
                    { validator: validateNum, trigger: 'blur' }
                ]
            },
            // tip
            tip: '',
            // 表格所有数据
            tableData: [
                {
                    id: '1',
                    name: 'oppo',
                    degree: 0.5
                }],
            // 表格当前分页数据
            currentPage_tableData: [],
            user_id_rec: [], // 用户用户id推荐表
            shop_id_rec: [], // 根据商品id推荐表
            num: 5, // 默认推荐数量

            loading: false,
            currentPage: 1,
            page_size: 5,
            total: 0

        };
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
        // 分页
        handleSizeChange(val) {
            // console.log(`每页 ${val} 条`);
            this.page_size = val;
            this.currentPage_tableData = this.tableData.slice((this.currentPage - 1) * val, this.currentPage * val)
            // 刷新饼图
            this.load_pie()
        },
        // 页跳转
        handleCurrentChange(val) {
            // console.log(`当前页: ${val}`);
            this.currentPage_tableData = this.tableData.slice((val - 1) * this.page_size, val * this.page_size)
            // 刷新饼图
            this.load_pie()
        },
        // 从txt文件读取
        getFile(path) {
            let xhr = new XMLHttpRequest();
            xhr.open('GET', path, false);// 文件路径
            xhr.overrideMimeType("text/html;charset=utf-8");// 默认为utf-8
            xhr.send(null);
            // 获取文件信息(得到的数据是String)
            //  console.log(xhr.responseText);
            return xhr.responseText
        },
        // 根据 类别type 和 id值 加载数据, num:推荐条数
        load_data(type, id, num = 5) {
            if (type == 1) {//用户id
                var recommend = this.user_id_rec[id - 1].recommend;
                var data = [];
                for (let i = 0; i < num; ++i) {
                    var tmp = {
                        id: recommend[i].shop_id,
                        name: recommend[i].shop_name,
                        degree: ((num - i) / num).toFixed(2)
                    };
                    data.push(tmp);
                }
                this.tableData = data;
            } else { //商品id
                var recommend = this.shop_id_rec[id - 1].recommend;
                var data = [];
                for (let i = 0; i < num; ++i) {
                    var tmp = {
                        id: recommend[i].shop_id,
                        name: recommend[i].shop_name,
                        degree: ((num - i) / num).toFixed(2)
                    };
                    data.push(tmp);
                }
                this.tableData = data;
            }
            // 刷新分页数据
            this.handleCurrentChange(this.currentPage)
        },
        // 自定义排序( order=true 则降序)
        mySort(arr = [], prop, order = true) {
            if (arr.length < 2) return arr
            let temp = [...arr]
            for (let i = 0; i < temp.length - 1; i++) {
                let [v, pos] = [temp[i][prop], i]
                for (let j = i + 1; j < temp.length; j++) {
                    if (Number(v) > Number(temp[j][prop])) {
                        v = temp[j][prop]
                        pos = j
                    }
                }
                [temp[i], temp[pos]] = [temp[pos], temp[i]]
            }
            return order ? temp : temp.reverse();
        },
        // 表格排序改动监听
        table_sort(p) {
            // console.log("排序", p);

            if (p.order === "ascending") { // 升序
                this.tableData = this.mySort(this.tableData, p.prop, true)
            }
            else { // 降序
                this.tableData = this.mySort(this.tableData, p.prop, false)
            }
            // console.log(this.tableData);
            // 刷新分页数据
            this.handleCurrentChange(this.currentPage)
        },
        // 表单提交
        submitForm(formName) {
            // 校验合法性
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    // alert('submit!');
                    let d = this.ruleForm;
                    // tip
                    if (d.type == 1)
                        this.tip = "成功对ID为 " + d.id + " 的用户推荐其可能感兴趣商品";
                    else
                        this.tip = "成功对ID为 " + d.id + " 的商品推荐与其关联度高的商品";

                    // 根据表单加载数据
                    this.loading = true;
                    this.load_data(d.type, d.id, d.num);
                    // 刷新饼图
                    this.load_pie()
                    setTimeout(
                        () => {
                            this.total = eval(d.num);
                            this.loading = false;
                        }, 500
                    )
                } else {
                    console.log('error submit!!');
                    alert('提交数据错误!');
                    return false;
                }
            });
        },
        // 重置
        resetForm(formName) {
            this.$refs[formName].resetFields();
            this.tableData = [];
            this.currentPage_tableData = [];
            this.total = 0;
            this.tip = '';
        },
        // 饼图加载
        load_pie() {
            var chartDom = document.getElementById('main1');
            var myChart = echarts.init(chartDom);
            var option;

            option = {
                color: "#00CED1",
                title: {
                    text: '商品推荐',
                    textStyle: {
                        //文字颜色
                        color: '#FF6347',
                        //字体风格,'normal','italic','oblique'
                        fontStyle: 'normal',
                        //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
                        fontWeight: 'bold',
                        //字体系列
                        fontFamily: 'sans-serif',
                        //字体大小
                        fontSize: 25
                    }
                },
                tooltip: {
                    trigger: 'item',
                    formatter: function (param) {
                        // console.log(param);
                        if (param.data) {
                            return param.marker + "推荐商品ID : " + param.data.id
                                + " <br/>  推荐商品名称 : " + param.data.name
                                + " <br/>  推荐指数 : " + param.value
                        } else {
                            return null
                        }
                    },
                    axisPointer: {
                        type: 'shadow'
                    }

                },
                legend: {},
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'value',
                    boundaryGap: [0, 0.01]
                },
                yAxis: {
                    type: 'category',
                    data: []
                },
                series: [
                    {
                        name: '推荐商品指数',
                        type: 'bar',
                        data: []
                    }
                ]
            };
            option.yAxis.data = this.collect(this.currentPage_tableData, 'id').reverse()
            // 推荐指数整合
            var data1 = []
            for (let i = 0; i < this.currentPage_tableData.length; ++i) {
                var tmp = {
                    value: this.currentPage_tableData[i].degree,
                    name: this.currentPage_tableData[i].name,
                    id: this.currentPage_tableData[i].id
                }
                data1.push(tmp)
            }
            option.series[0].data = data1.reverse()
            // console.log(data1)
            myChart.setOption(option);
        }
    },
    created() {
        this.user_id_rec = eval('(' + this.getFile('static/user_id_rec.txt') + ')');
        this.shop_id_rec = eval('(' + this.getFile('static/shop_id_rec.txt') + ')');
        // console.log(this.user_id_rec);
        // console.log(this.shop_id_rec);
    },
    mounted() {
        // this.total = this.num
        // this.load_data(1, 1, this.num)
        // this.load_pie()
    }
}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.header {
    display: flex;
    flex-direction: row;
}

.logo {
    height: 20px;
    width: 20px;
    position: relative;
    top: 4px;
    margin-right: 5px;
}

.right-h {
    width: calc(100% - 300px);
    margin-left: 80px;
    display: flex;
    justify-content: space-between;
}
</style>
  