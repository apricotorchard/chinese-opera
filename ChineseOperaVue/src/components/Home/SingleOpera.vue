<template>
    <!-- {width:width+'px',height:height+'px'} -->
    <div class="opera-card" :style="style">
        <img class="opera-img" :src="opera.operaPictureUrl" :alt="opera.operaName">
        <div class="opera-info">
            <span class="opera-name">{{ opera.operaName }}</span>
            <div class="opera-details">
                <span class="opera-singer">{{ opera.operaSinger }}</span>
                <span class="opera-tag">{{ opera.operaTag }}</span>
            </div>
            
        </div>
    </div>
</template>

<script>

export default {
    props:{
        opera:{
            type:Object,
            required:true //表示父组件在向子组件传递的时候，必须传递一个名为opera的属性值给子组件。
        },
        width:{
            type:Number,
            default:'200'
        },
        height:{
            type:Number,
            default:'200'
        },
        style:{
            type:Object,
            default:()=>({})
        }
    },
    name:'SingleOpera',
}
</script>

<style lang = "scss" scoped>
.opera-card{
    // width: 15vw;
    color: #6A3D3D;
    .opera-img{
        width: 100%;
        height: auto;
        object-fit: cover;
        border-radius: 12px;
        cursor: pointer;
        &:hover{
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
        }
    }
    .opera-info{
        // margin-top: 5px;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        
        .opera-name{
           display: inline-block;
            font-size: 16px;
            font-weight: bold;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .opera-details{
            display: flex;
            justify-content: space-between;
            align-items: center;
            .opera-singer,.opera-tag{
            font-size: 14px;
            margin-left: 10px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .opera-singer{
            max-width: 80%;
        }

        }
        
    }
}
</style>