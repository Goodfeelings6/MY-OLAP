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
        component: () => import('../views/Data-Report/Flow.vue'),
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
      },
      {
        // 报表 / 商品主题
        path: 'shop',
        name: 'Shop',
        component: () => import('../views/Data-Report/Shop.vue'),
        redirect:'/data-report/shop/BrandRebuy',
        children: [
          {
            path: 'BrandRebuy',
            name: 'ShopBrandRebuy',
            component: () => import('../views/Data-Report/Shop/ShopBrandRebuy.vue')
          },
          {
            path: 'BrandAddOrder',
            name: 'ShopBrandAddOrder',
            component: () => import('../views/Data-Report/Shop/ShopBrandAddOrder.vue')
          },
          { 
            path: 'BrandReduceOrder',
            name: 'ShopBrandReduceOrder',
            component: () => import('../views/Data-Report/Shop/ShopBrandReduceOrder.vue')
          },
          {
            path: 'KindAddOrder',
            name: 'ShopKindAddOrder',
            component: () => import('../views/Data-Report/Shop/ShopKindAddOrder.vue')
          },
          { 
            path: 'KindReduceOrder',
            name: 'ShopKindReduceOrder',
            component: () => import('../views/Data-Report/Shop/ShopKindReduceOrder.vue')
          },
          { 
            path: 'Province',
            name: 'ShopProvince',
            component: () => import('../views/Data-Report/Shop/ShopProvince.vue')
          }
        ]
      },
      {
        // 报表 / 优惠劵主题
        path: 'coupon',
        name: 'Coupon',
        component: () => import('../views/Data-Report/Coupon.vue'),
        redirect:'/data-report/coupon/Discount',
        children: [
          {
            path: 'Discount',
            name: 'CouponDiscount',
            component: () => import('../views/Data-Report/Coupon/CouponDiscount.vue')
          }
        ]
      } ,
      {
        // 报表 / 活动主题
        path: 'activity',
        name: 'Activity',
        component: () => import('../views/Data-Report/Activity.vue'),
        redirect:'/data-report/activity/Discount',
        children: [
          {
            path: 'Discount',
            name: 'ActivityDiscount',
            component: () => import('../views/Data-Report/Activity/ActivityDiscount.vue')
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
