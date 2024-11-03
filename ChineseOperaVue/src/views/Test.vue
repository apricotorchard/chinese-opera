<template>
    <div class="time-box"> 
      <el-date-picker 
        v-model="choidedYear" 
        type="year" 
        prefix-icon="CaretBottom" 
        placeholder="请选择时间"
        popper-class="popperClass" 
        />
    </div>
</template>
  
  <script>

  
  </script>

  <style lang="scss" >

  .popperClass {
  
    //图标样式
    .el-icon {
      color: rgb(73, 68, 143);
    }
  
    //头部样式
    .el-date-picker__header-label {
      color: #fff;
      font-size: 18px;
    }
  
    // 星期样式
    .el-date-table th {
      color: #fff;
    }
    // 时间选择器层样式
    .el-picker-panel {
      z-index: 2007;
      color: #fff;
      background: rgba(30, 84, 128, 0.8);
      border: 1px solid rgba(29, 128, 218, 1);
    }
  }
  // 输入框的样式在这里改就行
  .time-box {
    .el-input__wrapper {
      background-color: transparent;
      border-radius: 0px;
      box-shadow: none;
    }
  
    .el-input__inner {
      color: #ffffff;
    }
  
    .el-date-editor.el-input,
    .el-date-editor.el-input__wrapper {
      width: 120px;
    }
  
    .el-input__wrapper:hover {
      box-shadow: none;
    }
  
    .el-input {
      --el-input-focus-border-color: transparent;
      --el-input-border: none;
    }
  
    .el-popper.is-light {
      background: #255783;
      border: 1px solid #106c94;
    }
  }
  // 弹出框popper层样式
  .el-picker__popper.el-popper {
    background: rgba(30, 84, 128, 0.8);
    border: 1px solid rgba(29, 128, 218, 1);
    box-shadow: rgba(30, 84, 128, 0.8);
  }
  // 弹出框外部尖三角样式
  .el-popper.is-light .el-popper__arrow::before {
    border: 1px solid rgba(29, 128, 218, 1);
    background: rgba(29, 128, 218, 1);
  }
  </style>
  
  