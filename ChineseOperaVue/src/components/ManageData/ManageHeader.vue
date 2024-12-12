<template>
    <div class="container">
        <!-- <HomeHeader :showBackground="false" :show-logo="false"></HomeHeader> -->
    </div>
</template>

<script>
import HomeHeader from "@/components/Home/Header.vue"

export default {
    name:'ManageHeader',
    components:{
        HomeHeader
    }
}
</script>

<style lang="scss" scoped>
.container{
    width: 100%;
    height: 5vh;
}
</style>