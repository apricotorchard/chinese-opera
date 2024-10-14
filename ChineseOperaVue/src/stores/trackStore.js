import { defineStore } from "pinia";
export const useTrackStore = defineStore('track',{
    state:()=>({
        currentTrackId:1,
        isPlaying:false
    }),
    actions:{
        setCurrentTrackId(trackId){
            this.currentTrackId = trackId;
        },
        pauseStoreTrack(){
            this.isPlaying = false;
        },
        playStoreTrack(){
            this.isPlaying = true;
        }
    }
})