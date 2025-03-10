<template>
  <div>
    <h1>角色管理</h1>
    <el-card>
      <el-table :data="roles" border style="width: 100%">
        <el-table-column prop="name" label="角色名称"></el-table-column>
        <el-table-column prop="permissions" label="权限">
          <template #default="{ row }">
            {{ row.permissions.join(", ") }}
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="{ row }">
            <el-button size="small" @click="editRole(row)">编辑</el-button>
            <el-button size="small" type="danger" @click="deleteRole(row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="primary" style="margin-top: 10px" @click="openRoleDialog">新增角色</el-button>
    </el-card>

    <!-- 角色编辑弹窗 -->
    <el-dialog v-model="dialogVisible" title="角色管理">
      <el-form :model="roleForm">
        <el-form-item label="角色名称">
          <el-input v-model="roleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="权限">
          <el-select v-model="roleForm.permissions" multiple placeholder="选择权限">
            <el-option v-for="perm in allPermissions" :key="perm" :label="perm" :value="perm" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveRole">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "ManagerRole",
  data() {
    return {
      roles: [
        { id: 1, name: "管理员", permissions: ["查看用户", "管理系统"] },
        { id: 2, name: "编辑", permissions: ["编辑内容"] },
        { id: 3, name: "普通用户", permissions: ["浏览内容"] },
      ],
      allPermissions: ["查看用户", "管理系统", "编辑内容", "浏览内容"],
      dialogVisible: false,
      roleForm: {
        id: null,
        name: "",
        permissions: [],
      },
    };
  },
  methods: {
    editRole(role) {
      this.roleForm = { ...role };
      this.dialogVisible = true;
    },
    deleteRole(id) {
      this.roles = this.roles.filter((role) => role.id !== id);
      this.$message.success("角色删除成功");
    },
    openRoleDialog() {
      this.roleForm = { id: null, name: "", permissions: [] };
      this.dialogVisible = true;
    },
    saveRole() {
      if (this.roleForm.id) {
        const index = this.roles.findIndex((r) => r.id === this.roleForm.id);
        this.roles[index] = { ...this.roleForm };
      } else {
        this.roles.push({
          id: Date.now(),
          name: this.roleForm.name,
          permissions: [...this.roleForm.permissions],
        });
      }
      this.dialogVisible = false;
      this.$message.success("角色保存成功");
    },
  },
};
</script>

<style scoped>
.el-card {
  padding: 20px;
}
</style>
