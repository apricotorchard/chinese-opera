import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'

// 公共路由
const constantRoutes = [
  {
    path: '/',
    name: 'Login',
    component: () => import('@/views/Login.vue')
  },
  {
    path: '/home',
    name: 'home',
    component: Home
  },
  {
    path: '/operaplay',
    name: 'operaPlay',
    component: () => import('@/views/OperaPlay.vue'),
  },
  {
    path: '/hotlist',
    name: 'HotList',
    component: () => import('@/views/HotList.vue')
  },
  {
    path: '/operatag',
    name: 'OperaTagShow',
    component: () => import('@/views/OperaTagShow.vue')
  },
  {
    path: '/audio',
    name: 'Audio',
    component: () => import('@/views/Audio.vue')
  },
  {
    path: '/history',
    name: 'History',
    component: () => import('@/views/History.vue')
  },
  {
    path: '/test',
    name: 'Test',
    component: () => import('@/views/test.vue')
  },
]

// 动态路由，需要根据权限加载
const dynamicRoutes = [
  {
    path: '/manage',
    name: 'ManageData',
    component: () => import('@/views/ManageData.vue'),
    children: [
      {
        path: '/manageUploadaudiofile',
        name: 'UploadAudioFile',
        component: () => import('@/components/ManageData/UploadFile/UploadAudio.vue'),
        meta: { title:'上传音频',permissions: ['manage:manageUploadaudiofile'] }
      },
      {
        path: '/manageUploadvideofile',
        name: 'UploadVideoFile',
        component: () => import('@/components/ManageData/UploadFile/UploadVideo.vue'),
        meta: { title:'上传视频',permissions: ['manage:manageUploadvideofile'] }
      },
      {
        path: '/managevideo',
        name: 'ManageVideo',
        component: () => import('@/components/ManageData/ManageVideo/ManageVideo.vue'),
        meta: { title:'视频管理',permissions: ['manage:managevideo'] }
      },
      {
        path: '/manageCollection',
        name: 'ManageCollection',
        component: () => import('@/components/ManageData/ManageCollection/ManageCollection.vue'),
        meta: { title:'合集管理',permissions: ['manage:managecollection'] }
      },
      {
        path: '/manageuser',
        name: 'ManageUser',
        component: () => import('@/components/ManageData/ManagePermission/ManageUser.vue'),
        meta: { title:'用户管理',permissions: ['manage:manageuser'] }
      },
      {
        path: '/managerole',
        name: 'ManageRole',
        component: () => import('@/components/ManageData/ManagePermission/ManageRole.vue'),
        meta: { title:'角色管理',permissions: ['manage:managerole'] }
      },
      {
        path: '/managemenu',
        name: 'ManageMenu',
        component: () => import('@/components/ManageData/ManagePermission/ManageMenu.vue'),
        meta: { title:'菜单管理',permissions: ['manage:managemenu'] }
      }
    ]
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [...constantRoutes, ...dynamicRoutes],
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { top: 0 }
    }
  },
})

// 白名单路由，无需权限控制
const whiteList = ['/','/manage','/history'] // 登录页等公共路由

// 路由守卫，检查权限
router.beforeEach((to, from, next) => {
  const user = JSON.parse(localStorage.getItem('user'));

  // 1. 检查是否是白名单路由，无需权限验证
  if (whiteList.indexOf(to.path) !== -1) {
    next();
  }
  // 2. 检查是否是需要权限控制的路由（例如 `/manage` 下的路由）
  else if (to.path.startsWith('/manage')) {
    if (user && user.token) {
      // 如果用户已登录，检查是否有权限访问
      if (user.roles === 'admin' || user.permissions.includes(to.meta.permissions[0])) {
        next(); // 权限通过，放行
      } else {
        next('/'); // 权限不足，跳转到登录页
      }
    } else {
      next('/'); // 未登录，跳转到登录页
    }
  }
  // 3. 普通路由，未登录则跳转到登录页
  else if (!user || !user.token) {
    next('/'); // 未登录，跳转到登录页
  } else {
    next(); // 用户已登录，放行
  }
})

export default router
