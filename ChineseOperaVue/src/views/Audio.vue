<template>
  
  <div class="opera-list">
    <template v-if="operaInfoList.length>0">
      <div class="list-item list-header">
        <span class="list-name">戏曲</span>
        <span class="list-singer">演唱者</span>
        <span class="list-time">时长</span>
      </div>
      <div class="list-content">
        <!-- 双击实现 -->
        <div v-for="(opera,index) in operaInfoList" :key="index" class="list-item" @dblclick="selectOpera(opera,index,$evebt)">
          <span class="list-num">{{ opera.operaId }}</span>
          <div class="list-name">
            <span>{{ opera.operaName }}</span>
            <opera-icon class="hover"  :size="40" @click.stop="selectItem(opera,index)"></opera-icon>
          </div>
          <span class="list-singer">{{ opera.operaSinger }}</span>
          <span class="list-time">
            {{ opera.operaDuration }}
            <opera-icon class="hover" type="delete-mini" :size="40" @click.stop="deleteItem(index)"></opera-icon>
          </span>
        </div>

      </div>
    </template>
    <!-- 如果没有显示列表空空如也 -->
  </div>
  <!-- <AudioMp3Player></AudioMp3Player> -->
</template>

<script>
import AudioMp3Player from '@/components/AudioPlay/AudioMp3Player.vue'
import audio1 from '@/assets/audio1.mp3'
// 戏曲的不变的信息可以直接从后端获取，对于容易变化到的数据，需要放在vuex里面
export default {
  components:{
    AudioMp3Player
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
                operaId:1,
                operaName:'《富春令》',
                operaSinger:'李淑芳',
                operaTag: '越剧',
                operaDuration:'20min',
                operaAudioUrl:  audio1,
                
      }
    ],
    }
  },
  methods:{
    // 获取播放状态 type
   
  }
}
</script>

<style lang="scss" scoped>
.opera-list{
  height: 100%;
  .list-header{
    border-bottom: 1px solid rgba(255, 255, 255, 0.8);
    color: #fff;
    .list-name {
      padding-left: 40px;
    }
  }
}

</style>