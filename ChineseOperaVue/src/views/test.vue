<template>
  <el-button type="primary" @click="caseAnalyseShowPopup = true">案例分析</el-button>
  <div v-if="AIDocumentReviewShowPopup" class="popup-overlay" @click.self="AIDocumentReviewShowPopup = false">
      <div class="popup-content">
        <el-button type="danger" class="case-analyse-close-button" @click="AIDocumentReviewShowPopup = false">关闭</el-button>
        <iframe
          src=" https://agent.qkos.cn/chat/zvCyVdn9dSdc9KBj"
          frameborder="0"
          width="100%"
          height="95%"
        >
        </iframe>
      </div>
  </div>
</template>

<script>
export default {
    name:'test',
    data() {
    return {
      caseAnalyseShowPopup: false, // 控制案例分析的弹窗显示与隐藏的标志
    };
  },
}
</script>
<!-- AI文书审核 -->
<style scoped>
.popup-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.popup-content {
  background-color: white;
  width: 80%;
  height: 85%;
  padding: 20px;
  position: relative;
}

.case-analyse-close-button{
  float:right;
}
</style>


