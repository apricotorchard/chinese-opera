<template>
    <div class="opera-card">
        <img class="opera-img" :src="opera.operaPictureUrl" :alt="opera.operaName">
        <div class="opera-info">
            <span class="opera-name">{{ opera.operaName }}</span>
            <span class="opera-singer">{{ opera.operaSinger }}</span>
            <span class="opera-tag">{{ opera.operaTag }}</span>
        </div>
    </div>
</template>

<script>

export default {
    props:{
        opera:{
            type:Object,
            required:true //表示父组件在向子组件传递的时候，必须传递一个名为opera的属性值给子组件。
        }
    },
    name:'SingleOpera',
}
</script>

<style lang = "scss" scoped>
.opera-card{
    width: 200px;
    margin: 0 10px 10px 10px;
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
        margin-top: 5px;
        .opera-name{
            font-size: 16px;
            font-weight: bold;
        }
        .opera-singer,.opera-tag{
            font-size: 12px;
            margin-right: 5px;
        }
    }
}
</style>