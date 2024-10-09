import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path:'/operaplay',
      name:'operaPlay',
      component: () => import('@/views/OperaPlay.vue'),
    },
    {
      path:'/hotlist',
      name:'HotList',
      component: () => import('@/views/HotList.vue')
    },
    {
      path:'/operatag',
      name:'OperaTagShow',
      component: () => import('@/views/OperaTagShow.vue')
    },
  ]
})

export default router
