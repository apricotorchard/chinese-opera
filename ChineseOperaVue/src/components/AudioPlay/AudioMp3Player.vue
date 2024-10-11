<template>
  <div class="audio-player">
    <h2 v-if="currentTrack">{{ currentTrack.operaName }}</h2>
    <p v-if="currentTrack">{{ currentTrack.operaSinger }} - {{ currentTrack.operaTag }}</p>
    <div class="controls" v-if="currentTrack">
        <button @click="togglePlay">{{ isPlaying ? '暂停' : '播放' }}</button>
        <button @click="stopTrack">重新播放</button>
        <button @click="prevTrack">上一首</button>
        <button @click="nextTrack">下一首</button>
    </div>

    <div class="progress" v-if="currentTrack">
        <input
          type="range"
          min="0"
          :max="duration"
          v-model="seek"
          @input="seekTrack"
          step="0.01"
        />
        <span>{{ formatTime(seek) }} / {{ formatTime(duration) }}</span>
    </div>
  </div>
</template>

<script>
import { Howl } from 'howler';
import audio1 from '@/assets/audio1.mp3'
export default {
    // props:{
    //     operaInfo:{

    //     }
    // },
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
        };
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
            this.playTrack();
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
 .audio-player {
    background-color: #f9f9f9;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    width: 300px;
    margin: 0 auto;
    text-align: center;
  }
  
  h2 {
    font-size: 18px;
    margin-bottom: 10px;
  }
  
  .controls {
    margin: 10px 0;
  }
  
  .controls button {
    margin-right: 5px;
    padding: 5px 10px;
    background-color: #007bff;
    color: white;
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