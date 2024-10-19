<template>
  <div @click="refreshCode">
    <VerificationCode :identifyCode="identifyCode"></VerificationCode>
  </div>
</template>

<script>
import VerificationCode from '@/components/Login/identify.vue'
export default {
    components:{
        VerificationCode
    },
    data(){
        
    }
}
</script>

<style>

</style>