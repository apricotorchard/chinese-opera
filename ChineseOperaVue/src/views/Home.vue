<template>
  <div >
    <el-header style="padding:0;height: auto;">
        <HomeHeader></HomeHeader>
    </el-header>
    <el-main style="padding: 0 60px;height: 80vh;">
         <!-- 分类列表 -->
        <div>
            <div class="header-channel">
                <div class="imgContainer">
                    <div class="mp3-imgContainer" @click="navigateToAudio">
                        <img src="@/assets/hot.png" alt="热门照片">
                        <span>MP3</span>
                    </div>
                    <div class="hot-imgContainer" @click="navigateToHotList">
                        <img src="@/assets/hot.png" alt="热门照片">
                        <span>热门</span>
                    </div>
                </div>
                
                <Channel></Channel>
                <!-- <ul class="opera-tags">
                    <li v-for="(operaTag,index) in operaTags" :key="index" class="tag-item" @click="navigateToOperaTag(operaTag)">
                        {{ operaTag }}
                    </li>
                </ul> -->
            </div>
        </div>
        <div class="recommend-container">
            <div class="carousel-container">
                <el-carousel  height="50vh">
                    <el-carousel-item  v-for="(opera,index) in carouselItems" :key="index" @click="navigateToOperaPlay(opera)">
                        <img :src="opera.operaPictureUrl" :alt="opera.operaName" class="carousel-img  cover-img" title="热播榜">
                        <div class="carousel-operaName">
                            <h3>{{ opera.operaName }}</h3>
                        </div>
                    </el-carousel-item>
                </el-carousel>
            </div>
            
            <div class="right-operas" >
                <div class="singleOpera-container" :style="{width:'200px',height:'200px'}" v-for="(opera,index) in operas" :key="index" @click="navigateToOperaPlay(opera)">
                    <SingleOpera :opera="opera"></SingleOpera>
                </div>
                
            </div>
        </div>
    </el-main>
    
  </div>
</template>

<script>
import HomeHeader from "@/components/Home/Header.vue"
import Channel from "@/components/Home/channel.vue"
import SingleOpera from "@/components/Home/SingleOpera.vue"
export default {
    name:'Home',
    components:{
        HomeHeader,
        Channel,
        SingleOpera
    },
    data(){
        return{
            operaInfoList:[
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
           
        }
    },
    methods:{
        navigateToOperaPlay(opera){
            this.$router.push({
                name: 'operaPlay',
                query: { opera: JSON.stringify(opera) } 
            })
        },
        navigateToHotList(){
            this.$router.push({
                name:'HotList'
            })
        },
       
        navigateToAudio(){
            this.$router.push({
                name:'Audio',
            })
        }
    },
    computed:{
        carouselItems(){
            return this.operaInfoList.slice(0,4);
        },
        operas(){
            return this.operaInfoList.slice(4,10);
        }
    }
}
</script>

<style lang="scss" scoped>

.header-channel{
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
    padding: 0 60px;
    .imgContainer{
        display: flex;
        width: 120px;
        justify-content: space-between;
        .mp3-imgContainer,.hot-imgContainer{
        display: flex;
        flex-direction: column;
        span{
            text-align: center;
        }
        &>img{
            height: 50px;
        }
    }
    }
}

.recommend-container{
    margin-top: 20px;
    display: flex;
    justify-content: center;
    .carousel-container{

        width: 30vw;
        position: relative;
        margin-left: 20px;
        margin-top: 20px;
        .carousel-img{
            border-radius: 10px; // 设置圆角
        }
        .carousel-operaName{
            position: absolute;
            bottom: 20px;
            left: 20px;
            color: white;
            background-color: rgba(0, 0, 0, 0.5);
            border-radius: 5px;
        }
    }
    .right-operas{
        margin-left: 50px;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
        white-space: nowrap;
        width: 50vw;
        .singleOpera-container{
            margin-top: 20px;
            min-width: 15vw;
        }
    }
}
</style>