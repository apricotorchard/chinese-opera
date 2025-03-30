<template>
  <div class="container">
    <el-menu
      default-active="1"
      class="el-menu-vertical"
      :collapse="isCollapse"
      @open="handleOpen"
      @close="handleClose"
      @select="handleMenuSelect"
    >
      <el-menu-item v-for="(menu, index) in filteredMenuList" :key="index" :index="menu.name.toString()">
        <template #title>
          <!-- <el-icon :component="menu.icon"></el-icon> -->
          <span>{{ menu.meta.title}}</span>
        </template>
        <!-- 渲染子菜单项 -->
       
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script lang="ts" setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'

// 获取当前用户的权限
const user = JSON.parse(localStorage.getItem('user'))
const router = useRouter() 
const defaultMenuItem = ref('1')

onMounted(() => {
  // 默认跳转到第一个菜单项对应的页面
  if (filteredMenuList.value.length > 0) {
    const defaultRoute = filteredMenuList.value[0].name.toString()
    router.push({ name: defaultRoute })
  }
})
// 动态获取权限后的菜单项
const filteredMenuList = computed(() => {
   // 获取路由实例
  const routes = router.getRoutes() // 获取所有路由配置
  const routerList = routes.filter(route=>{
    if (route.path.startsWith('/manage')){
      if(route.meta.permissions){
        const isInclude = user.permissions.includes(route.meta.permissions[0]);
        return isInclude;
      }
    }
  })
  return routerList;
})

// 控制菜单折叠状态
const isCollapse = ref(false)

const handleOpen = (key: string, keyPath: string[]) => {

}

const handleClose = (key: string, keyPath: string[]) => {

}

// 跳转到选中的菜单
const handleMenuSelect = (index: string) => {
  router.push({ name: index }) // 根据菜单项的index跳转到对应路由
}
</script>

<style lang="scss" scoped>
.el-menu-vertical {
  width: 100%;
  height: 100vh;
  min-height: 400px;
  padding: 0px;
}
.container {
  height: 100%;
}
</style>
