import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path:'/',
      name:'Login',
      component: () => import('@/views/Login.vue')
    },
    {
      path: '/home',
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
    {
      path:'/audio',
      name:'Audio',
      component: () => import('@/views/Audio.vue')
    },
    {
      path:'/manage',
      name:'ManageData',
      component: () => import('@/views/ManageData.vue'),
      children:[
        {
          path:'/uploadfile',
          name:'UploadFile',
          component: () => import('@/components/ManageData/UploadFile/UploadAudio.vue')
        },
      ]
    },
   
    {
      path:'/test',
      name:'test',
      component: () => import('@/views/Test.vue')
    },
  ]
})

export default router
