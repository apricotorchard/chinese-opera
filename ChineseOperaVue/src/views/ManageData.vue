<template>
    <el-container class="container">
        <!-- 侧边栏 -->
        <el-aside style="width:200px">
                <ManageAside/>
        </el-aside>
        
        <el-container style="padding:0 60px">
            <el-header style="padding:0px;height: 5vh;">
                <ManageHeader/>
            </el-header>
            <el-main style="padding:0px;">
                <ManageMain/>
            </el-main>
        </el-container>


    </el-container>
  
  
</template>

<script setup>
    import ManageHeader from '@/components/ManageData/ManageHeader.vue'
    import ManageAside from '@/components/ManageData/ManageAside.vue'
    import ManageMain from '@/components/ManageData/ManageMain.vue'
</script>

<style scoped lang="scss">
</style>