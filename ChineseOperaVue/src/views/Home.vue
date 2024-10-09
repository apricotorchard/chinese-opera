<template>
  <div >
    <el-header style="padding:0;height: 100%;">
        <HomeHeader></HomeHeader>
        <!-- 分类列表 -->
        <div>
            <div class="header-channel">
                <div class="channel-imgContainer" @click="navigateToHotList">
                    <img src="@/assets/hot.png" alt="热门照片">
                    <span>热门</span>
                </div>
                
                <ul class="opera-tags">
                    <li v-for="(operaTag,index) in operaTags" :key="index" class="tag-item" @click="navigateToOperaTag(operaTag)">
                        {{ operaTag }}
                    </li>
                </ul>
            </div>
        </div>
    </el-header>
    <el-main style="padding: 0;margin-top:30px">
        <div class="recommend-container">
            <div class="carousel-container">
                <el-carousel trigger="click" style="height:370px">
                    <el-carousel-item style="height:370px" v-for="(opera,index) in carouselItems" :key="index" @click="navigateToOperaPlay(opera)">
                        <img :src="opera.operaPictureUrl" :alt="opera.operaName" class="carousel-img" title="热播榜">
                        <div class="carousel-operaName">
                            <h3>{{ opera.operaName }}</h3>
                        </div>
                    </el-carousel-item>
                </el-carousel>
            </div>
            
            <div class="right-operas" >
                <div class="singleOpera-container" v-for="(opera,index) in operas" :key="index" @click="navigateToOperaPlay(opera)">
                    <SingleOpera :opera="opera"></SingleOpera>
                </div>
                
            </div>
        </div>
    </el-main>
    
  </div>
</template>

<script>
import HomeHeader from "@/components/Home/Header.vue"
import SingleOpera from "@/components/Home/SingleOpera.vue"
export default {
    name:'Home',
    components:{
        HomeHeader,
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
            operaTags:['京剧', '秦腔', '曲剧', '晋剧', '黄梅戏', '评剧', '豫剧', '吕剧', '昆曲', '越剧', '潮剧', '川剧', '琼剧', '茂腔', '蒲剧', '越调', '赣剧', '湘剧'],
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
        navigateToOperaTag(operaTag){
            this.$router.push({
                name:'OperaTagShow',
                query:{operaTag:operaTag}
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
    justify-content: space-around;
    align-items: center;
    margin-top: 20px;
    padding: 0 60px;
    .channel-imgContainer{
        display: flex;
        flex-direction: column;
        // margin-right: 20px;
        // margin-left:50px
        span{
            text-align: center;
        }
    }
    .opera-tags{
        display: grid;
        grid-template-columns: repeat(9,1fr);
        grid-gap: 10px 40px;
        list-style: none;
        padding: 0;
        .tag-item{
            padding: 10px 25px;
            background-color: #f0f0f0;
            border-radius: 8px;
            text-align: center;
            cursor: pointer;
            transition: background-color 0.3s ease;
            &:hover{
                background-color: #ccc;
            }
            font-size: 12px;
            color: #333;
        }
    }
}

.recommend-container{
    padding: 0 60px;
    display: flex;
    justify-content: space-between;
    .carousel-container{
        width: 550px;
        height: 370px;
        .carousel-img{
            // height: 100%;
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .carousel-operaName{
            position: absolute;
            bottom: 20px;
            left: 20px;
            color: white;
            background-color: rgba(0, 0, 0, 0.5);
            // padding: 10px;
            border-radius: 5px;
        }
    }
    .right-operas{
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
        width: 750px;
    }
    
}
</style>