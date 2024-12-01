<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column type="selection" width="55" />
    <el-table-column property="name" label="名称" width="120"/>
    <el-table-column property="singer" label="演唱者" width="120" show-overflow-tooltip/>
    <el-table-column property="des" label="描述" width="240" show-overflow-tooltip/>
    <el-table-column property="tag" label="类别" />
    <el-table-column property="tag" label="分组" />
    <!-- 操作 -->
    <el-table-column  label="操作">
        <el-button link type="primary" size="small" @click="handleDeleteClick">
          删除
        </el-button>
        <el-button link type="primary" size="small">
            编辑
        </el-button>
    </el-table-column>
  </el-table>
</template>

<script>
export default {

}
</script>

<style>

</style>