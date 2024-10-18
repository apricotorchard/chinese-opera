<template>
  <div class="audio-player">
    <div>
        <el-image class="slow-rotate" style="width: 200px; height: 200px" :src=url fit="cover">
        </el-image>
    </div>
    <h2 v-if="currentTrack">{{ currentTrack.operaName }}</h2>
    
    <p v-if="currentTrack">{{ currentTrack.operaSinger }}</p>
    <p v-if="currentTrack">{{ currentTrack.operaTag }}</p>
    <div class="controls" v-if="currentTrack">
        <el-icon :size="30" @click="prevTrack"><CaretLeft /></el-icon>
        <el-icon :size="30" @click="togglePlay">
          <VideoPlay v-if="!isPlaying"/>
          <VideoPause v-if="isPlaying"/>
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
import { useOperaListStore } from '@/stores/operaListStore';
// 有一个bug，就是会重复播放两次。
export default {
    data(){
        return{
            currentTrack:null,
            sound:null,
            seek:0,
            duration:0,
            url:'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
        };
    },
    computed: {
      trackStore() {
        return useTrackStore();
      },
      operaListStore() {
        return useOperaListStore();
      },
      currentTrackId() {
        return this.trackStore.currentTrackId;
      },
      isPlaying(){
        return this.trackStore.isPlaying;
      }
    },
    watch: {
      currentTrackId(newTrackId) {
        console.log("currentTreackID发生变化....");
        const newTrack = this.operaListStore.operaInfoList[newTrackId-1];
        if (newTrack) {
          this.loadTrack(newTrack);
          this.playTrack();
        }
      },
      isPlaying(newTrackId){
        newTrackId ? this.playTrack():this.pauseTrack() ;
      }
   },
    mounted(){
      this.currentTrack = this.operaListStore.operaInfoList[this.trackStore.currentTrackId-1];
      this.loadTrack(this.currentTrack);
    },
    deactivated() {
    // 组件被切换到其他页面时可以暂停播放或处理其他逻辑
        if (this.sound) {
            this.trackStore.isPlaying = false;
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
              src:[track.operaAudioUrl],
              html5:true,
              onload:()=>{
                  this.duration = this.sound.duration();
              },
              onend:()=>{
                this.trackStore.isPlaying = false;
            }
          });
          console.log(this.sound);
        },
        playTrack() {
            this.sound.play();
            this.trackStore.isPlaying = true;
            this.updateSeek();
        },
        pauseTrack() {
            this.sound.pause();
            this.trackStore.isPlaying = false;
        },
        togglePlay() {
            this.trackStore.isPlaying ? this.pauseTrack() : this.playTrack();
        },
        stopTrack() {
            this.sound.stop();
            this.trackStore.isPlaying = false;
            this.seek = 0;
        },
        prevTrack() {
            const currentIndex = this.operaInfoList.findIndex(
            (track) => track.operaId === this.currentTrack.operaId
            );
            if (currentIndex > 0) {
            this.loadTrack(this.operaInfoList[currentIndex - 1]);
            }
        },
        nextTrack() {
            const currentIndex = this.operaInfoList.findIndex(
            (track) => track.operaId === this.currentTrack.operaId
            );
            if (currentIndex < this.operaInfoList.length - 1) {
            this.loadTrack(this.operaInfoList[currentIndex + 1]);
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