<template>
    <div class="play-container">
      <el-header style="padding:0;">
        <Header :showBackground="false" :show-logo="false"></Header>
      </el-header>
      <el-main style="padding:0 60px;margin-top:30px">
          <div class="opera-info">
            <div class="first-line">
              <h1>{{ opera.operaName }}</h1>
              <el-icon :size="20" color="#409efc" class="star">
                <Star/>
              </el-icon>
            </div>
            <span>
              {{ opera.operaTag }}
            </span>
            <span>演唱者：{{ opera.operaSinger }}</span>
            
          </div>
        <div class="iframe-container" style="margin-bottom:20px">
          <iframe :src="opera.operaPlayUrl" frameborder="0" allowfullscreen width="100%" height="500px"></iframe>
        </div>
        <commentSection></commentSection>
      </el-main>
    </div>
  </template>
  
  <script>
  // import { ElIcon } from 'element-plus';
  import CommentSection from '@/components/OperaPlay/comment.vue';
  import Header from '@/components/Home/Header.vue';
  export default {
    name: 'OperaPlay',
    components: {
      Header,
      // ElIcon,
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
    padding: 0;
  }
  .opera-info{
    .first-line{
      display: flex;
      align-items: center;
      h1{
        font-size: 25px;
      }
    }
    span:nth-of-type(1){
      display: inline-block;
      width: 50px;
      margin-left: 10px;
      @include no-wrap;
    }
    span:nth-of-type(2){
      display: inline-block;
      width: 500px;
      @include no-wrap;
    }
  }
  .star:hover{
    color: yellow;
  }
  </style>