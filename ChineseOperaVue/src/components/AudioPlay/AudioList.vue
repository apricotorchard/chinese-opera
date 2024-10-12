<template>
    <div class="opera-list flex-col">
      <template v-if="operaInfoList.length>0">
        <div class="list-item list-header">
          <span class="list-num">戏曲ID</span>
          <span class="list-name">戏曲</span>
          <span class="list-singer">演唱者</span>
          <span class="list-time">时长</span>
          <span class="list-operation"></span>
        </div>
        <div class="list-content" @scroll="listScroll($event)">
          <!-- 双击实现 -->
          <div v-for="(opera,index) in displayedOperas" :key="index" class="list-item" @dblclick="selectOpera(opera,index,$evebt)">
            <span class="list-num">{{ opera.operaId }}</span>
            <span class="list-name">{{ opera.operaName }}</span>
            <span class="list-singer">{{ opera.operaSinger }}</span>
            <span class="list-time">
              {{ opera.operaDuration }}
            </span>
            <div class="icon-container">
                <el-icon class="action-icon"><VideoPlay /></el-icon>
                <el-icon class="action-icon"><Delete /></el-icon>
            </div>
            
          </div>
  
        </div>
      </template>
      <!-- 如果没有显示列表空空如也 -->
    </div>
</template>
  
  <script>
  import audio1 from '@/assets/audio1.mp3'
  const THRESHOLD = 100
  // 戏曲的不变的信息可以直接从后端获取，对于容易变化到的数据，需要放在vuex里面
  export default {
    data(){
      return{
        displayedOperas:[],
        lockUp:false,
        pageSize:10,
        currentPage:1,
        operaInfoList:[{
                  operaId:1,
                  operaName:'《富春令》',
                  operaSinger:'李淑芳',
                  operaTag: '越剧',
                  operaDuration:'20min',
                  operaAudioUrl:  audio1,   
        },
        {
                  operaId:2,
                  operaName:'《千里寻夫》又名《北国认父》王帅军 杨少彭 杨乃彭 施昊 马杰',
                  operaSinger:'王帅军 杨少彭 杨乃彭 施昊 马杰',
                  operaTag: '越剧',
                  operaDuration:'20min',
                  operaAudioUrl:  audio1, 
        },{
                  operaId:3,
                  operaName:'《富春令》',
                  operaSinger:'李淑芳',
                  operaTag: '越剧',
                  operaDuration:'20min',
                  operaAudioUrl:  audio1,
        },
        {
                  operaId:4,
                  operaName:'《富春令》',
                  operaSinger:'李淑芳',
                  operaTag: '越剧',
                  operaDuration:'20min',
                  operaAudioUrl:  audio1,
        },
        {
                  operaId:5,
                  operaName:'《富春令》',
                  operaSinger:'李淑芳',
                  operaTag: '越剧',
                  operaDuration:'20min',
                  operaAudioUrl:  audio1,
        },
        {
                  operaId:6,
                  operaName:'《富春令》',
                  operaSinger:'李淑芳',
                  operaTag: '越剧',
                  operaDuration:'20min',
                  operaAudioUrl:  audio1,
        },
        {
                  operaId:7,
                  operaName:'《富春令》',
                  operaSinger:'李淑芳',
                  operaTag: '越剧',
                  operaDuration:'20min',
                  operaAudioUrl:  audio1,
        },
        {
                  operaId:8,
                  operaName:'《富春令》',
                  operaSinger:'李淑芳',
                  operaTag: '越剧',
                  operaDuration:'20min',
                  operaAudioUrl:  audio1,
        },
        {
                  operaId:9,
                  operaName:'《富春令》',
                  operaSinger:'李淑芳',
                  operaTag: '越剧',
                  operaDuration:'20min',
                  operaAudioUrl:  audio1,
        },
        {
                  operaId:10,
                  operaName:'《富春令》',
                  operaSinger:'李淑芳',
                  operaTag: '越剧',
                  operaDuration:'20min',
                  operaAudioUrl:  audio1,
        },
        {
                  operaId:11,
                  operaName:'《富春令》',
                  operaSinger:'李淑芳',
                  operaTag: '越剧',
                  operaDuration:'20min',
                  operaAudioUrl:  audio1,
        },
        {
                  operaId:12,
                  operaName:'《富春令》',
                  operaSinger:'李淑芳',
                  operaTag: '越剧',
                  operaDuration:'20min',
                  operaAudioUrl:  audio1,
        },
        {
                  operaId:13,
                  operaName:'《富春令》',
                  operaSinger:'李淑芳',
                  operaTag: '越剧',
                  operaDuration:'20min',
                  operaAudioUrl:  audio1,
        },
        {
                  operaId:14,
                  operaName:'《富春令》',
                  operaSinger:'李淑芳',
                  operaTag: '越剧',
                  operaDuration:'20min',
                  operaAudioUrl:  audio1,
        },
        {
                  operaId:15,
                  operaName:'《富春令》',
                  operaSinger:'李淑芳',
                  operaTag: '越剧',
                  operaDuration:'20min',
                  operaAudioUrl:  audio1,
        },
        {
                  operaId:16,
                  operaName:'《富春令》',
                  operaSinger:'李淑芳',
                  operaTag: '越剧',
                  operaDuration:'20min',
                  operaAudioUrl:  audio1,
        },
        {
                  operaId:17,
                  operaName:'《富春令》',
                  operaSinger:'李淑芳',
                  operaTag: '越剧',
                  operaDuration:'20min',
                  operaAudioUrl:  audio1,
        },
      ],
      }
    },
    mounted(){
        this.loadMore();
    },
    methods:{
      //滚动事件
        listScroll(e){
            const { scrollTop, scrollHeight, offsetHeight } = e.target;
            if (scrollTop + offsetHeight >= scrollHeight - THRESHOLD && !this.lockUp) {
                this.lockUp = true; // 锁定滚动加载
                this.loadMore(); // 加载更多数据
            }
        },
      loadMore(){
            const startIndex = this.pageSize * (this.currentPage - 1);
            const endIndex = startIndex + this.pageSize;
            const newItems = this.operaInfoList.slice(startIndex, endIndex);
            if (newItems.length > 0) {
                this.displayedOperas = [...this.displayedOperas, ...newItems];
                this.currentPage += 1;
            }
            this.lockUp = false; // 解锁滚动加载
        },
    }
  }
  </script>
  
  <style lang="scss" scoped>
  .opera-list {
    width: 100%;
    height: 80vh;
    padding: 10px;
    .list-content{
       
        overflow-x: hidden;
        overflow-y: auto;
    }
    .list-item {
        color: #fff;
        @include flex-center();
        padding: 15px;
        border-bottom: 1px solid rgba(255, 255, 255, 0.2);
        transition: background-color 0.3s ease;
        &>span{
            text-align: center;
            @include no-wrap;
        }
        &:hover {
            background-color: rgba(255, 255, 255, 0.1);
        }

        /* 鼠标悬停时，显示播放和删除按钮 */
        &:hover .action-icon {
            display: inline-block;
        }
        .icon-container{
            display: flex;
            justify-content: space-around;
            cursor: pointer;
            font-size: 24px; /* 调整图标大小 */
        }
    }
    .list-num,.list-time, .icon-container,.list-operation{
      flex-basis: 10%;
    }
    .list-name, .list-singer{
        flex-basis: 30%;
    }
    .list-header{
        // border-bottom: 1px solid $list_head_line_color;
        color: $text_color_active;
        font-weight: bold;
        padding-bottom: 20px;
    }

    /* 默认隐藏播放和删除按钮 */
    .action-icon {
      display: none;
      cursor: pointer;
    }
  }
  
  
  </style>