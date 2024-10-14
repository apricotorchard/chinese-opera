<template>
  <div class="audio-container">
    <Header :showBackground="false" :showLogo="false"></Header>
    <div class="channel-container">
      <Channel></Channel>
    </div>
    <div class="content">
      <AudioList></AudioList>
      <keep-alive>
        <AudioMp3Player></AudioMp3Player>
      </keep-alive>
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
    }
  },
  methods:{
  }
}
</script>

<style lang="scss" scoped>
.audio-container{
  background: rgba(8, 160, 104, 0.651);
}
.channel-container{
  margin-top: 20px;
}
.content{
  @include flex-center();
  margin-top: 10px;
  
}
</style>