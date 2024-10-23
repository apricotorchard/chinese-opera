<template>
  <div @click="refreshCode">
    <el-button @click="test">点这里</el-button>
  </div>
</template>

<script>
import VerificationCode from '@/components/Login/identify.vue';
import request from '@/utils/request';
export default {
    components:{
        VerificationCode
    },
    data(){
        
    },
    methods:{
      test(){
        // headers:{
        //         isToken:false,
        //         repeatSubmit:false
        //     },
        return request({
            url:'test',
            method:'get',
            
        })
      }
    }
}
</script>

<style>

</style>