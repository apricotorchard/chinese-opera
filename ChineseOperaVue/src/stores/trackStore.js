import { defineStore } from "pinia";
export const useTrackStore = defineStore('track',{
    state:()=>({
        currentTrackId:null,
        isPlaying:false
    }),
    actions:{
        playTrack(trackId){
            this.currentTrackId = trackId;
            this.isPlaying = true;
        },
        pauseTrack(){
            this.isPlaying = false;
        }
    }
})