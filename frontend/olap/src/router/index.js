import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    // 根目录
    path: '/',
    name: 'OLAP-System',
    redirect:'/data-report/outline',
  },
  {
    // 报表 
    path: '/data-report',
    name: 'Data-Report',
    component: () => import('../views/Data-Report.vue'),
    children: [
      {
        // 报表 / 概览
        path: 'outline',
        name: 'Outline',
        component: () => import('../views/Data-Report/Outline.vue'),
      },
      {
        // 报表 / 用户主题
        path: 'user',
        name: 'User',
        component: () => import('../views/Data-Report/User.vue'),
        redirect:'/data-report/user/change',
        children: [
          {
            path: 'change',
            name: 'UserChange',
            component: () => import('../views/Data-Report/User/UserChange.vue')
          },
          {
            path: 'save',
            name: 'UserSave',
            component: () => import('../views/Data-Report/User/UserSave.vue')
          },
          {
            path: 'newactive',
            name: 'UserNewActive',
            component: () => import('../views/Data-Report/User/UserNewActive.vue')
          },
          {
            path: 'actionanalysis',
            name: 'UserActionAnalysis',
            component: () => import('../views/Data-Report/User/UserActionAnalysis.vue')
          },
          {
            path: 'neworder',
            name: 'UserNewOrder',
            component: () => import('../views/Data-Report/User/UserNewOrder.vue')
          }
        ]
      } ,
      {
        // 报表 / 流量主题
        path: 'flow',
        name: 'Flow',
        redirect:'/data-report/flow/channel',
        children: [
          {
            path: 'channel',
            name: 'FlowChannel',
            component: () => import('../views/Data-Report/Flow/FlowChannel.vue')
          },
          { 
            path: 'path',
            name: 'Flowpath',
            component: () => import('../views/Data-Report/Flow/Flowpath.vue')
          }
        ]
      } 
    ]
  }
  // { 用户画像 
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
