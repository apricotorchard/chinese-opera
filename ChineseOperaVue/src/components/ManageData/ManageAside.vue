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
        <el-sub-menu index="1">
            <template #title>
              <el-icon><location /></el-icon>
              <span>数据中心</span>
            </template>
            <el-menu-item-group title="视频数据">
            </el-menu-item-group>
            <el-menu-item-group title="音频数据">
            </el-menu-item-group>
            <el-menu-item-group title="用户数据">

            </el-menu-item-group>
        </el-sub-menu>
        <el-menu-item index="UploadAudioFile">
            <el-icon><icon-menu /></el-icon>
            <template #title>上传音频</template>
        </el-menu-item>
        <el-menu-item index="UploadVideoFile">
            <el-icon><document /></el-icon>
            <template #title>上传视频</template>
        </el-menu-item>
        <el-menu-item index="ManageVideo">
            <el-icon><setting /></el-icon>
            <template #title>视频管理</template>
        </el-menu-item>
        <el-menu-item index="6">
            <el-icon><setting /></el-icon>
            <template #title>音频管理</template>
        </el-menu-item>
        <el-menu-item index="5">
            <el-icon><setting /></el-icon>
            <template #title>用户管理</template>
        </el-menu-item>
        
        </el-menu>
    </div>
    
</template>
  
<script lang="ts" setup>
  import { ref } from 'vue'
  import {
    Document,
    Menu as IconMenu,
    Location,
    Setting,
  } from '@element-plus/icons-vue'
  import { useRouter } from 'vue-router'
    const router = useRouter()
  const isCollapse = ref(false)
  const handleOpen = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
  }
  const handleClose = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
  }
  const handleMenuSelect = (index) =>{
    router.push({name:index});
  }
</script>
  
<style lang="scss" scoped>
  .el-menu-vertical {
    width: 100%;
    height:100vh;
    min-height: 400px;
    padding:0px;
  }
  .container{
    height: 100%;
    
  }
</style>