<template>
    <div class="header">
        <div>
            <img class="logo" src="../assets/logo.png" mode="aspectFit" />
            <b style="font-size: large;">联机数据分析平台</b>
        </div>
        <div class="right-h">
            <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="select" router>
                <el-menu-item index="/data-report/outline">数据报表</el-menu-item>
                <!-- <el-submenu index="2">
                <template slot="title">我的工作台</template>
                <el-menu-item index="2-1">选项1</el-menu-item>
                <el-menu-item index="2-2">选项2</el-menu-item>
                <el-menu-item index="2-3">选项3</el-menu-item>
                <el-submenu index="2-4">
                    <template slot="title">选项4</template>
                    <el-menu-item index="2-4-1">选项1</el-menu-item>
                    <el-menu-item index="2-4-2">选项2</el-menu-item>
                    <el-menu-item index="2-4-3">选项3</el-menu-item>
                </el-submenu>
            </el-submenu> -->
                <el-menu-item index="/data-report/emo">情感分析</el-menu-item>
                <el-menu-item index="/data-report/analysis">联机分析</el-menu-item>
            </el-menu>
            <div>
                <el-dropdown trigger="hover" placement="bottom">
                    <i class="el-icon-user" style="margin-right: 15px"></i>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>个人中心</el-dropdown-item>
                        <el-dropdown-item>退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
                <span>猪猪侠</span>
            </div>
        </div>
    </div>
</template>
  
<script>
export default {
    name: 'Header',
    props: {
    },
    data() {
        return {
            activeIndex: '/data-report/outline', // 当前激活的菜单栏
        };
    },
    methods: {
        select(index, indexPath) {
            // console.log(this.$route)
            // console.log(index, indexPath);
            this.activeIndex = index
        }
    },
    mounted() {
        // console.log(this.$route)
        this.activeIndex = this.$route.fullPath
    },

    watch: {
        $route(to, from) {
            console.log(from.path);//从哪来
            console.log(to.path);//到哪去
            if(to.path.split('/').length>3)
                this.activeIndex = "/data-report/outline"
            else
                this.activeIndex = to.path
        }
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
  