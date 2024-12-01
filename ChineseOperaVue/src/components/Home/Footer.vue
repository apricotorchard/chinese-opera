<!-- 底部需要显示的字体 -->
<template>
  <el-row class="footer">
    <el-col :span="4">
    </el-col>

    <el-col :span="16">
      <el-row style="height:100%;display: flex;align-items: center;">
        <el-col :span="5"
                class="footer-logo">
          <img class="footer-img"
               :src="sungif"
               width="50px"
               />
          <span class="footer-title">{{footerTitle}}</span>
        </el-col>
        <el-col :span="7">
          <el-row>
            <el-col :span="12"
                    class="footer-word">QQ:2016410498</el-col>
            <el-col :span="12"
                    class="footer-word">微信:19553500806</el-col>
          </el-row>
        </el-col>
        <el-col :span="12">
          <div class="divider"></div>
        </el-col>
      </el-row>
    </el-col>

    <el-col :span="4">
    </el-col>
  </el-row>
</template>
<script>
import sunGif from '@/assets/sun.gif';
export default {
  data: function () {
    return {
      footerTitle: '王广安',
      sungif:sunGif
    }
  }
}
</script>
<style scoped>
.footer {
  background-color: #212121;
  position: fixed;
  bottom: 0px;
  width: 100%;
}
.footer-logo {
  display: flex;
  align-items: center;
}
.footer-img {
  margin-right: 24px;
}
.footer-title {
  color: #fff;
  font-size: 20px;
}
.footer-word {
  color: #fff;
  font-size: 14px;
}

</style>
