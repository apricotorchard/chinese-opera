<template>
    
    <div class="aside-container">
        <!-- 如果说有视频合集 -->
        <div v-if="operaList.length!=0" class="opera-list">
            <div class="list-header"><span>视频选集：</span></div>
            <div class="list-container">
                <div v-for="(opera,index) in operaList" :key="index" class="list-item">
                    <span>{{ opera.name }}</span>
                </div>
            </div>
            <div v-for="(opera,index) in operaList.slice(0,2)" :key="index" class="opera-info">
                <div class="img-container">
                    <img :src="opera.pictureUrl">
                </div>
                <div class="word-container">
                    <p>名称：{{ opera.name }}</p>
                    <span>演唱者：{{ opera.singer}}</span>
                </div>
            </div>
        </div>

        <div v-else class="opera-container">
            <div v-for="(opera,index) in operaList.slice(0,5)" :key="index" class="opera-info">
                <div class="img-container">
                    <img :src="opera.pictureUrl">
                </div>
                <div class="word-container">
                    <p>名称：{{ opera.name }}</p>
                    <span>演唱者：{{ opera.singer}}</span>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    props:{
        operaList:{
            type:Object,
            requeire:true
        }
    },
    data(){
        return{

        }
    },
    method:{
     
    }
}
</script>

<style scoped lang="scss">
.aside-container{
    width: 29%;
    height: 100%;
    margin-top: 5px;
    .opera-list{
        .list-header{
            @include graystyle;
            font-size: 20px;
            line-height: 2;
            margin-bottom: 10px;
            padding: 0px 10px
        }
        .list-container{
            @include graystyle;
            height: 35vh;
            padding: 5px 10px;
            overflow-y: auto;
            .list-item{
                padding: 5px 5px;
                font: 15px;
                border-radius: 5px;
                &:hover{
                    background-color: white;
                    color: #7FCBF8;
                }
            }
        }
    }
    .opera-container{
        display: flex;
        flex-direction: column;
    }

    .opera-info{
            flex:1;
            display: flex;
            padding: 5px 0px;
            border-bottom: 1px solid #ddd;
            align-items:flex-start;
            .img-container{
                height: 100px;
                img{
                    height: 100px;
                    border-radius: 5px;
                }
            }
            .word-container{
                display: flex;
                flex-direction: column;
                padding: 0px 10px;
                p{
                                     /* 设置容器宽度，根据需要调整 */
                    display: -webkit-box;          /* Flex盒模型 */
                    -webkit-box-orient: vertical;  /* 垂直排列 */
                    -webkit-line-clamp: 2;         /* 限制为两行 */
                    overflow: hidden;              /* 超出部分隐藏 */
                    text-overflow: ellipsis;       /* 超出部分显示省略号 */
                }
                span{
                    margin-bottom: 5px;
                    font-size: 12px;
                    color:rgb(164, 163, 163)
                }
            }
        }
   
   
}
</style>