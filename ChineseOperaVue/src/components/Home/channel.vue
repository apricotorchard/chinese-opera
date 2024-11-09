<template>
    <ul class="opera-tags">
        <li v-for="(operaTag,index) in operaTags" :key="index" class="tag-item" @click="navigateToOperaTag(operaTag)">
            {{ operaTag }}
        </li>
    </ul>
</template>

<script>
export default {
    name:'Channel',
    data(){
        return {
            operaTags:['京剧', '秦腔', '曲剧', '晋剧', '黄梅戏', '评剧', '豫剧', '吕剧', '昆曲', '越剧', '潮剧', '川剧', '琼剧', '茂腔', '蒲剧', '越调', '赣剧', '湘剧'],
        }
    },
    methods:{
        navigateToOperaTag(operaTag){
            this.$router.push({
                name:'OperaTagShow',
                query:{operaTag:operaTag}
            })
        },
    }
}
</script>

<style lang="scss" scoped>
 .opera-tags{
        margin-left: 30px;
        display: grid;
        grid-template-columns: repeat(9,1fr);
        grid-gap: 10px 40px;
        list-style: none;
        .tag-item{
            padding: 10px 20px;
            background-color: #ddd;
            border-radius: 8px;
            text-align: center;
            cursor: pointer;
            transition: background-color 0.3s ease;
            &:hover{
                background-color: #ccc;
            }
            font-size: 12px;
            @include no-wrap;
        }
    }

</style>