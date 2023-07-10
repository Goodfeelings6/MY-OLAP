import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css';
import request from './utils/request';
import { ip } from './utils/config.js';
Vue.config.productionTip = false

Vue.use(ElementUI);
// 全局方法挂载
Vue.prototype.request=request
Vue.prototype.ip = ip
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
