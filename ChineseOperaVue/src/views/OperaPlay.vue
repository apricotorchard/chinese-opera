<template>
    <div class="play-container">
      <el-header style="padding:0;margin-top:30px">
        <h1 style="margin-top:30px">{{ opera.operaName }}</h1>
        <div class="opera-info">
          <span class="tag_span">
            {{ opera.operaTag }}
          </span>
          <span>演唱者：{{ opera.operaSinger }}</span>
          <el-icon :size="20" color="#409efc">
            <Star/>
          </el-icon>
        </div>
      </el-header>
      <el-main style="padding:0;margin-top:30px">
        <div class="iframe-container" style="margin-bottom:20px">
          <iframe :src="opera.operaPlayUrl" frameborder="0" allowfullscreen width="100%" height="500px"></iframe>
        </div>
        <commentSection></commentSection>
      </el-main>
    </div>
  </template>
  
  <script>
  import { ElIcon } from 'element-plus';
  import CommentSection from '@/components/OperaPlay/comment.vue';
  
  export default {
    name: 'OperaPlay',
    components: {
      ElIcon,
      CommentSection
    },
    data() {
      return {
        opera:null
      };
    },
    created(){
        this.opera = JSON.parse(this.$route.query.opera);
    },
    methods: {
      collectOpera() {
        // 收藏逻辑
      }
    }
  };
  </script>
  
  <style lang="scss" scoped>
  .play-container {
    padding: 0 60px;

  }
  .opera-info{
    margin-left: 20px;
    display: flex;
    align-items: center;
    span{
        margin-right: 20px;
        
    }
  }
  </style>