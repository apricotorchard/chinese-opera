<template>
  <div class="audio-player">
    <div>
        <el-image class="slow-rotate" style="width: 200px; height: 200px" :src=url fit="cover">
        </el-image>
    </div>
    <h2 v-if="currentTrack">{{ currentTrack.name }}</h2>
  
    <p v-if="currentTrack">{{ currentTrack.tag }}</p>
    <div class="controls" v-if="currentTrack">
        <el-icon :size="30" @click="prevTrack"><CaretLeft /></el-icon>
        <el-icon :size="30" @click="togglePlay">
          <VideoPlay v-if="!trackStore.isPlaying"/>
          <VideoPause v-if="trackStore.isPlaying"/>
        </el-icon>
        <el-icon :size="30" @click="nextTrack"><CaretRight /></el-icon>
    </div>
    <span>{{ formatTime(seek) }} / {{ formatTime(duration) }}</span>
    <div class="progress" v-if="currentTrack">
        <input
          type="range"
          min="0"
          :max="duration"
          v-model="seek"
          @input="seekTrack"
          step="0.01"
        />
    </div>
  </div>
</template>

<script>
import { Howl } from 'howler';
import { useTrackStore } from '@/stores/trackStore'; 
export default {
    data(){
        return{
            currentTrack:null,
            sound:null,
            seek:0,
            duration:0,
            url:'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
            trackStore: useTrackStore(),
        };
    },
    watch: {
      'trackStore.currentTrackId': function (newTrackId) {
        if (this.trackStore.isfinishGetData) {
          const newTrack = this.trackStore.audioList[newTrackId];
          if (newTrack) {
            this.loadTrack(newTrack);
            this.playTrack();
          }
        }
      },

      // 监听 isPlaying 变化
      'trackStore.isPlaying': function (isPlaying) {
        isPlaying ? this.playTrack() : this.pauseTrack();
      },

      // 监听 isfinishGetData，加载完成时进行初始化
      'trackStore.isfinishGetData': function (newVal) {
        console.log("当前列表数据加载完毕");
        if (newVal) {
          this.currentTrack = this.trackStore.audioList[this.trackStore.currentTrackId];
          console.log(this.currentTrack);
          this.loadTrack(this.currentTrack);
        }
        immediate: true // 确保组件初始化时立即执行一次
      }
    },
    // activated(){
    //   this.trackStore.isfinishGetData = false; //先执行这个，然后audioList组件 会重新将this.trackStore.isfinishGetData赋值为false
    // },
    deactivated() {
    // 组件被切换到其他页面时可以暂停播放或处理其他逻辑
        if (this.sound) {
            this.trackStore.isPlaying = false;
            this.trackStore.isfinishGetData = false;
            this.sound.unload();
        }
    },
    methods:{
        loadTrack(track){
            if(this.sound){
                this.sound.unload();
            }
            this.currentTrack = track;
            
            this.sound = new Howl({
              src:[track.audioUrl],
              html5:true,
              onload:()=>{
                  this.duration = this.sound.duration();
              },
              onend:()=>{
                this.trackStore.isPlaying = false;
            }
          });
        },
        playTrack() {
            this.sound.play();
            this.trackStore.isPlaying = true;
            this.updateSeek();
        },
        pauseTrack() {
            if(this.sound){
              this.sound.pause();
              this.trackStore.isPlaying = false;
            } 
        },
        togglePlay() {
            this.trackStore.isPlaying ? this.pauseTrack() : this.playTrack();
        },
        prevTrack() {
            const currentIndex = this.trackStore.audioList.findIndex(
            (track) => track.id === this.currentTrack.id
            );
            if (currentIndex > 0) {
              this.trackStore.currentTrackId = currentIndex-1;
            }
        },
        nextTrack() {
          const currentIndex = this.trackStore.audioList.findIndex(
          (track) => track.id === this.currentTrack.id
          );
        
          if (currentIndex < this.trackStore.audioList.length - 1) {
            this.trackStore.currentTrackId = currentIndex+1;
          }
        },
        updateSeek() {
            if (this.sound && this.trackStore.isPlaying) {
            this.seek = this.sound.seek();
            requestAnimationFrame(this.updateSeek);
            }
        },
        seekTrack(event) {
            if (this.sound) {
            this.sound.seek(event.target.value);
            }
        },
        formatTime(seconds) {
            const minutes = Math.floor(seconds / 60) || 0;
            const secs = Math.floor(seconds - minutes * 60) || 0;
            return `${minutes}:${secs < 10 ? '0' : ''}${secs}`;
        },
    }
    
}
</script>

<style scoped lang="scss">
.slow-rotate {
  border-radius: 50%;
  transform-origin: center;
  animation: rotate 9s linear infinite;
}
.slow-rotate:hover{
  animation-play-state: paused;
}
 .audio-player {
    width: 25vw;
    height: 80vh;
    text-align: center;
    display: flex;
    flex-direction: column;
    justify-content: center;
  }
  h2,p{
    @include no-wrap;
  }
  h2 {
    margin-bottom: 10px;
  }
  
  .controls {
    display: flex;
    justify-content: space-around;
    margin: 10px 0;
  }
 
  .progress {
    margin-top: 15px;
  }
  
  .progress input[type="range"] {
    width: 100%;
  }
  
 

</style>
