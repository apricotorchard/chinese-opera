import { defineStore } from "pinia";
export const useTrackStore = defineStore('track',{
    state:()=>({
        currentTag:'京剧',
        currentTrackId:1,
        isPlaying:false,
        isfinishGetData:false,
        audioList:[]
    }),
    actions:{
        setCurrentTrackId(trackId){
            this.currentTrackId = trackId;
        },
        setCurrentTag(trackTag){
            this.currentTag = trackTag;
        },
        clearAudioList(){
            this.audioList = [];
        },
        pauseStoreTrack(){
            this.isPlaying = false;
        },
        playStoreTrack(){
            this.isPlaying = true;
        },
        addAudioList(audioList) {
            this.audioList = [...this.audioList, ...audioList];
            this.isfinishGetData = true;
            console.log("数据执行完成");
        }
    }
})