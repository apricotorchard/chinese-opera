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
          path:'/manageUploadaudiofile',
          name:'UploadAudioFile',
          component: () => import('@/components/ManageData/UploadFile/UploadAudio.vue')
        },
        {
          path:'/manageUploadvideofile',
          name:'UploadVideoFile',
          component: () => import('@/components/ManageData/UploadFile/UploadVideo.vue')
        },
        {
          path:'/managevideo',
          name:'ManageVideo',
          component: () => import('@/components/ManageData/ManageVideo/ManageVideo.vue')
        },
        {
          path:'/manageCollection',
          name:'ManageCollection',
          component: () => import('@/components/ManageData/ManageCollection/ManageCollection.vue')
        },
      ]
    },
  ]
})

//添加一个路由守卫：检查用户是否有权限访问某个路由
router.beforeEach((to,from,next)=>{
  const user = JSON.parse(localStorage.getItem('user'));
  
  // 如果是需要鉴权的
  if(to.path.startsWith('/manage')){
    if(user.token && user.roles=="1"){
      next();
    }else{
      next('/');
    }
  }
  else{
      next();
  }
  
})
export default router
