import { defineStore } from 'pinia';
import audio1 from '@/assets/audio1.mp3'
import audio2 from '@/assets/audio2.mp3'
export const useOperaListStore = defineStore('operaList', {
  state: () => ({
    operaInfoList: [
      {
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
              operaAudioUrl:  audio2, 
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
    ], // 存储所有的戏曲列表
  }),
  actions: {
    setOperaList(list) {
      this.operaInfoList = list;
    },
    addOpera(opera) {
      this.operaInfoList.push(opera);
    },
  },
});