<template>
    <div class="play-container">
      <el-header style="padding:0;">
        <Header :showBackground="false" :show-logo="false"></Header>
      </el-header>
      <el-main style="padding:0 60px;margin-top:30px">
          <div class="opera-info">
            <h1>{{ opera.name }}</h1>
            <span>
              {{ opera.tag }}
            </span>
            <span>演唱者：{{ opera.singer }}</span>
           
            
          </div>
        <div class="iframe-container">
          <video controls :src="opera.playUrl" width="70%" height="550px"></video>
          <AsideList :operaList = "operaList"></AsideList>
        </div>

        <div class="user-operate">
            <!-- 点赞 -->

            <!-- 收藏 -->
            <el-icon :size="20" class="star">
              <Star/>
            </el-icon>
        </div>
        <CommentSection :operaid="this.opera.id"></CommentSection>
      </el-main>
    </div>
  </template>
  
  <script>
  // import { ElIcon } from 'element-plus';
  import CommentSection from '@/components/OperaPlay/Comment.vue'
  import AsideList from '@/components/OperaPlay/AsideList.vue';
  import Header from '@/components/Home/Header.vue';
  import {getOperaByCollectionId} from '@/api/opera.js'
  export default {
    name: 'OperaPlay',
    components: {
      Header,
      AsideList,
      CommentSection
    },
    data() {
      return {
        opera:null,
        operaList:[],
      };
    },
    created(){
        this.opera = JSON.parse(this.$route.query.opera);
        //根据collectionId 从数据库当中查询。
        this.getOperaListByConditionId(this.opera.collectionId);
    },
    methods: {
      collectOpera() {
        // 收藏逻辑
      },
      // 根据collectid 获得属于相同组的戏曲
      getOperaListByConditionId(collectionId){
        getOperaByCollectionId(collectionId).then(res=>{
          this.operaList = res.data.data.filter(item => item.id!=this.opera.id);
        })
      }
    }
  };
  </script>
  
  <style lang="scss" scoped>
  .play-container {
    padding: 0;
  }
  .opera-info{
    
    h1{
      font-size: 25px;
    }
    span{
      width: 50px;
      @include no-wrap;
      &:nth-of-type(1){
        margin-right: 10px;
      }
    }
    
  }
  .star:hover{
    color: yellow;
  }
  .iframe-container{
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
  }
  </style>