<template>
  <div class="audio-container">
    <Header :showBackground="false" :showLogo="false"></Header>
    <div class="channel-container">
      <Channel></Channel>
    </div>
    

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
export default {
  components:{
    AudioMp3Player,
    AudioList,
    Header,
    Channel
  },
  data(){
    return{
      // operaTags:['京剧', '秦腔', '曲剧', '晋剧', '黄梅戏', '评剧', '豫剧', '吕剧', '昆曲', '越剧', '潮剧', '川剧', '琼剧', '茂腔', '蒲剧', '越调', '赣剧', '湘剧'],
    }
  },
  methods:{
    // 获取播放状态 type
   
  }
}
</script>

<style lang="scss" scoped>
.audio-container{
  background: rgba(0, 0, 0, 0.5);
}
.channel-container{
  margin-top: 20px;
}
.content{
  @include flex-center();
  margin-top: 10px;
  
}
</style>