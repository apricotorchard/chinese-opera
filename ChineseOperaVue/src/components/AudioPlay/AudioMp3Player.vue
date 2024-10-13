<template>
  <div class="audio-player">
    <p>{{currentTrackId}}</p>
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
            <component :is="isPlaying ? 'VideoPause' : 'VideoPlay'" />
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
import audio1 from '@/assets/audio1.mp3';
import {useTrackStore} from '@/stores/trackStore.js';
import {mapState,mapActions} from 'pinia';
export default {
    data(){
        return{
            operaInfoList:[{
                operaId:1,
                operaName:'《富春令》',
                operaSinger:'李淑芳',
                operaTag: '越剧',
                operaAudioUrl:  audio1,
            },],
            currentTrack:null,
            sound:null,
            isPlaying:false,
            seek:0,
            duration:0,
            url:'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
        };
    },
    computed: {
      ...mapState(useTrackStore, {
        currentTrackId:state=>state.currentTrackId
      })
    },
    mounted(){
        this.loadTrack(this.operaInfoList[0]);
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
                    this.isPlaying = false;
                }
            });
        },
        playTrack() {
            this.sound.play();
            this.isPlaying = true;
            this.updateSeek();
        },
        pauseTrack() {
            this.sound.pause();
            this.isPlaying = false;
        },
        togglePlay() {
            this.isPlaying ? this.pauseTrack() : this.playTrack();
        },
        stopTrack() {
            this.sound.stop();
            this.isPlaying = false;
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
            if (this.sound && this.isPlaying) {
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

<style scoped>
.slow-rotate {
  border-radius: 50%;
  transform-origin: center;
  animation: rotate 9s linear infinite;
}
.slow-rotate:hover{
  animation-play-state: paused;
}
 .audio-player {
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    width: 30vw;
    height: 80vh;
    text-align: center;
    display: flex;
    flex-direction: column;
    justify-content: center;
  }
  
  h2 {
    font-size: 18px;
    margin-bottom: 10px;
  }
  
  .controls {
    display: flex;
    justify-content: space-around;
    margin: 10px 0;
  }
  
  .controls button {
    margin-right: 5px;
    padding: 5px 10px;
    /* background-color: #007bff; */
    /* color: white; */
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  .controls button:hover {
    background-color: #0056b3;
  }
  
  .progress {
    margin-top: 15px;
  }
  
  .progress input[type="range"] {
    width: 100%;
  }
  
  span {
    font-size: 14px;
  }

</style>