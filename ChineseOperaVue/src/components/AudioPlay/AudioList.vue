<template>
    <div class="opera-list flex-col">
        <div class="list-item list-header">
          <span class="list-name">戏曲名称</span>
          <span class="list-operation"></span>
        </div>
        <div class="list-content" @scroll="listScroll($event)">
          <!-- 双击实现 -->
          <div v-for="(audio,index) in track.audioList" :key="index" class="list-item" @dblclick="selectAudio(audio)">
            <span class="list-name">{{ audio.name }}</span>

            <div class="icon-container">
                <el-icon class="action-icon" :size="30">
                    <VideoPause v-if="track.isPlaying"  @click="cancelAudio(audio)"/>
                    <VideoPlay v-if="!track.isPlaying" @click="selectAudio(audio)"/>
                </el-icon>
                
                <el-icon class="action-icon"><Delete /></el-icon>
            </div>
            
          </div>
  
        </div>
      <!-- </template> -->
      <!-- 如果没有显示列表空空如也 -->
    </div>
</template>
  
<script>
  import {useTrackStore} from '@/stores/trackStore';
  import {getAudioListByTag,getAudioPlayUrl} from '@/api/audio.js';
  const THRESHOLD = 100
  // 戏曲的不变的信息可以直接从后端获取，对于容易变化到的数据，需要放在vuex里面
  export default {
    // props:{
    //   audioTag:{
    //     type:String,
    //     required:true
    //   }
    // },
    data(){
      return{
        displayedAudios:[],
        lockUp:false,
        pageNum:1,
        pageSize:10,
        tag:'京剧',
        track:useTrackStore()
      }
    },
    created(){
        
    },
    mounted(){


      this.getAudioList();
    },
    methods:{
        selectAudio(audio){
            // 1.先把当前的播放的戏曲id更换
            // 2.更换播放状态
            this.track.isPlaying = true;
            this.track.setCurrentTrackId(audio.id-1);
        },
        cancelAudio(audio){
            this.track.isPlaying = false;
            this.track.setCurrentTrackId(audio.id-1);
        },
      //滚动事件
        listScroll(e){
            const { scrollTop, scrollHeight, offsetHeight } = e.target;
            // if (scrollTop + offsetHeight >= scrollHeight - THRESHOLD && !this.lockUp) {
            //     this.lockUp = true; // 锁定滚动加载
            //     this.loadMore(); // 加载更多数据
            // }
        },
        loadMore(){
                this.pageNum+=1;
                // 向后端发送请求加载更多的数据
                this.getAudioList();
                this.lockUp = false; // 解锁滚动加载
        },
        getAudioList(){
            const params={
              pageNum:this.pageNum,
              pageSize:this.pageSize,
              tag:this.tag,
            }
            getAudioListByTag(params).then(res=>{

              const audioList = res.data.records.map(item=>{
                item.audioUrl = getAudioPlayUrl(item)
                return item;
              })
              console.log("audiolist 数据开始执行......")
              this.track.addAudioList(audioList);
          })
        }
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
        color: $text-color;
        @include flex-center();
        padding: 15px;
        border-bottom: 1px solid rgba(43, 41, 41, 0.2);
        transition: background-color 0.3s ease;
        &>span{
            text-align: center;
            @include no-wrap;
        }
        &:hover {
            background-color: #f0f0f0;
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
        color: $text_color;
        font-weight: bold;
        padding-bottom: 20px;
    }
    .action-icon {
      display: none;
      cursor: pointer;
    }
  }
  
  
  </style>