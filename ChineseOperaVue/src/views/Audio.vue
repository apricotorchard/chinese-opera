<template>
  <div class="audio-container">
    <Header :showBackground="false" :showLogo="false"></Header>
    <Channel></Channel>

    <div class="content">
      <AudioList></AudioList>
      <AudioMp3Player></AudioMp3Player>
    </div>
  </div>
</template>

<script>
import AudioMp3Player from '@/components/AudioPlay/AudioMp3Player.vue'
// 戏曲的不变的信息可以直接从后端获取，对于容易变化到的数据，需要放在vuex里面
import AudioList from '@/components/AudioPlay/AudioList.vue'
import Header from '@/components/Home/Header.vue'
import Channel from '@/components/Home/channel.vue'
import audio1 from '@/assets/audio1.mp3'
export default {
  components:{
    AudioMp3Player,
    AudioList,
    Header,
    Channel
  },
  data(){
    return{
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
                operaName:'《富春令》',
                operaSinger:'李淑芳',
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
    ],
    operaTags:['京剧', '秦腔', '曲剧', '晋剧', '黄梅戏', '评剧', '豫剧', '吕剧', '昆曲', '越剧', '潮剧', '川剧', '琼剧', '茂腔', '蒲剧', '越调', '赣剧', '湘剧'],
    }
  },
  methods:{
    // 获取播放状态 type
   
  }
}
</script>

<style lang="scss" scoped>
.audio-container{
  
}
.content{
  @include flex-center();
  background: rgba(0, 0, 0, 0.5);
}
</style>