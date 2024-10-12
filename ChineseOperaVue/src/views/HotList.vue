<template> 
    <div class="bg">
      <div class="container">
        <h1 class="title">热播榜</h1>
        <div class="hotList-container">
          <div
            v-for="(opera, index) in HotList"
            :key="index"
            class="hot-item"
            @click="navigateToOperaPlay(opera)"
          >
            <span class="index" :class="{ first: index === 0, second: index === 1, third: index === 2, other: index >= 3 }">{{ index + 1 }}</span>
            <img :src="opera.operaPictureUrl" alt="opera image" class="image" />
            <div class="info">
              <div class="title">{{ opera.operaName }}</div>
              <div class="singer">演出: {{ opera.operaSinger }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  
  <script>
  export default {
    data() {
      return {
       
        HotList:[
                {
                    operaId:1,
                    operaName:'《富春令》',
                    operaSinger:'李淑芳',
                    operaTag:'越剧',
                    operaTime:'2022/6/16',
                    operaPictureUrl:'http://xiqu.99uz.com/uploads/allimg/220616/d4fbdd87b02f36a2.gif',
                    operaPlayUrl:'https://player.youku.com/embed/XNDE0NTU2NzAzMg==?client_id=23d6a7035d8bd579&amp;autoplay=true',
                },
                {
                    operaId:1,
                    operaName:'《千里寻夫》又名《北国认父》',
                    operaSinger:'王帅军 杨少彭 杨乃彭 施昊 马杰',
                    operaTag:'越剧',
                    operaTime:'2022/6/16',
                    operaPictureUrl:'http://xiqu.99uz.com/uploads/allimg/220616/d4fbdd87b02f36a2.gif',
                    operaPlayUrl:'https://player.youku.com/embed/XNDE0NTU2NzAzMg==?client_id=23d6a7035d8bd579&amp;autoplay=true',
                },
                {
                    operaId:1,
                    operaName:'《富春令》',
                    operaSinger:'李淑芳',
                    operaTag:'越剧',
                    operaTime:'2022/6/16',
                    operaPictureUrl:'http://xiqu.99uz.com/uploads/allimg/220616/d4fbdd87b02f36a2.gif',
                    operaPlayUrl:'https://player.youku.com/embed/XNDE0NTU2NzAzMg==?client_id=23d6a7035d8bd579&amp;autoplay=true',
                },
                {
                    operaId:1,
                    operaName:'《富春令》',
                    operaSinger:'李淑芳',
                    operaTag:'越剧',
                    operaTime:'2022/6/16',
                    operaPictureUrl:'http://xiqu.99uz.com/uploads/allimg/220616/d4fbdd87b02f36a2.gif',
                    operaPlayUrl:'https://player.youku.com/embed/XNDE0NTU2NzAzMg==?client_id=23d6a7035d8bd579&amp;autoplay=true',
                },
                {
                    operaId:1,
                    operaName:'《富春令》',
                    operaSinger:'李淑芳',
                    operaTag:'越剧',
                    operaTime:'2022/6/16',
                    operaPictureUrl:'http://xiqu.99uz.com/uploads/allimg/220616/d4fbdd87b02f36a2.gif',
                    operaPlayUrl:'https://player.youku.com/embed/XNDE0NTU2NzAzMg==?client_id=23d6a7035d8bd579&amp;autoplay=true',
                },
                {
                    operaId:1,
                    operaName:'《富春令》',
                    operaSinger:'李淑芳',
                    operaTag:'越剧',
                    operaTime:'2022/6/16',
                    operaPictureUrl:'http://xiqu.99uz.com/uploads/allimg/220616/d4fbdd87b02f36a2.gif',
                    operaPlayUrl:'https://player.youku.com/embed/XNDE0NTU2NzAzMg==?client_id=23d6a7035d8bd579&amp;autoplay=true',
                },
                {
                    operaId:1,
                    operaName:'《富春令》',
                    operaSinger:'李淑芳',
                    operaTag:'越剧',
                    operaTime:'2022/6/16',
                    operaPictureUrl:'http://xiqu.99uz.com/uploads/allimg/220616/d4fbdd87b02f36a2.gif',
                    operaPlayUrl:'https://player.youku.com/embed/XNDE0NTU2NzAzMg==?client_id=23d6a7035d8bd579&amp;autoplay=true',
                },
                {
                    operaId:1,
                    operaName:'《富春令》',
                    operaSinger:'李淑芳',
                    operaTag:'越剧',
                    operaTime:'2022/6/16',
                    operaPictureUrl:'http://xiqu.99uz.com/uploads/allimg/220616/d4fbdd87b02f36a2.gif',
                    operaPlayUrl:'https://player.youku.com/embed/XNDE0NTU2NzAzMg==?client_id=23d6a7035d8bd579&amp;autoplay=true',
                },
                {
                    operaId:1,
                    operaName:'《富春令》',
                    operaSinger:'李淑芳',
                    operaTag:'越剧',
                    operaTime:'2022/6/16',
                    operaPictureUrl:'http://xiqu.99uz.com/uploads/allimg/220616/d4fbdd87b02f36a2.gif',
                    operaPlayUrl:'https://player.youku.com/embed/XNDE0NTU2NzAzMg==?client_id=23d6a7035d8bd579&amp;autoplay=true',
                },
                {
                    operaId:1,
                    operaName:'《富春令》',
                    operaSinger:'李淑芳',
                    operaTag:'越剧',
                    operaTime:'2022/6/16',
                    operaPictureUrl:'http://xiqu.99uz.com/uploads/allimg/220616/d4fbdd87b02f36a2.gif',
                    operaPlayUrl:'https://player.youku.com/embed/XNDE0NTU2NzAzMg==?client_id=23d6a7035d8bd579&amp;autoplay=true',
                }
        ],
      };
    },
    methods:{
      navigateToOperaPlay(opera){
          this.$router.push({
              name: 'operaPlay',
              query: { opera: JSON.stringify(opera) } 
          })
      },
    }
  };
  </script>
  
<style lang="scss" scoped>
// @import '@/styles/mixin.scss';
.bg {
  background-image: url('@/assets/R (1).jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  background-attachment: fixed;
  margin: 0;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;

  .title {
    font-family: 'Arial', sans-serif;
    font-size: 2.5rem;
    font-weight: bold;
    // color: #333;
    color: #6A3D3D; /* 柔和的深玫瑰色，与花的背景相得益彰 */
    margin-bottom: 20px;
  }

  .hotList-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
  }

  .hot-item {
    display: flex;
    align-items: center;
    justify-content: end;
    width: 48%;
    padding: 10px;
    
    background-color: rgba(255, 255, 255, 0.8);
    transition: background-color 0.3s ease, transform 0.3s ease;

    &:hover {
      background-color: rgba(255, 255, 255, 0.9);
      transform: scale(1.02); /* 悬停时放大效果 */
    }

    .index {
      font-size: 2rem;
      margin-right: 40px;
      font-weight: bold;
      color: #B85C5C; /* 暖色调红，与背景图协调 */
      &.first {
        color: gold;
      }
      &.second {
        color: silver;
      }
      &.third {
        color: #cd7f32; /* 铜色 */
      }
      &.other {
        color: #B85C5C;
      }
    }

    .image {
      width: 150px;
      margin-right: 20px;
    }

    .info {
      text-align: center;
      margin-right: 50px;
      width: 150px;

      .title {
        font-family: 'Arial', sans-serif;
        font-weight: bold;
        font-size: 1.1rem;
        margin-bottom: 5px;
        @include no-wrap;
        // white-space: nowrap;
        // overflow: hidden;
        // text-overflow: ellipsis;
        color: #B85C5C; /* 暖色调红，与背景图协调 */
      }

      .singer {
        font-size: 0.7rem;
        // color: #666;
        color: #8C4F4F; /* 低对比度的红色，舒适的视觉体验 */
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
      }
    }
  }
}

</style>
  

