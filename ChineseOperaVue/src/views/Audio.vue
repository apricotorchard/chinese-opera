<template>
  <div class="audio-container">
    <Header :showBackground="false" :showLogo="false"></Header>
    <div class="channel-container">
      <Channel :isAudio=true></Channel>
    </div>
    <div class="content">
      <keep-alive>
        <AudioMp3Player></AudioMp3Player>
      </keep-alive>
      <AudioList></AudioList>
      
    </div>
  </div>
</template>

<script>
import AudioMp3Player from '@/components/AudioPlay/AudioMp3Player.vue'
// 戏曲的不变的信息可以直接从后端获取，对于容易变化到的数据，需要放在vuex里面
import AudioList from '@/components/AudioPlay/AudioList.vue'
import Header from '@/components/Home/Header.vue'
import Channel from '@/components/Home/ChannelVue.vue'
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
  background:$bg_color;
}
.channel-container{
  margin-top: 20px;
}
.content{
  @include flex-center();
  margin-top: 10px; 
}
</style>