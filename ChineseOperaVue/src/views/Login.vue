<template> 
    <div ref="vantaBg" class="vanta-container"></div>
    <div class="lr-container">
        <div class="content_body">
        <div class="content_header">
            <a :class="isActive?['isActive']:[]" @click="lr_change">登录</a>
            <a :class="isActive?[]:['isActive']" @click="lr_change">注册</a>
        </div>
        <!-- 登录 -->
        <div class="login_content" v-if="isActive">
            <form>
                <div class="uname">
                    <input type="text" class="ipt" placeholder="账号"  v-model="username" />
                </div>
                <div class="pwd">
                    <input type="password" class="ipt" placeholder="密码"  v-model="password" />
                </div>
                
                <div @click="refreshCode" class="identify-code">
                    <input type="text" class="ipt code-input" placeholder="请输入验证码" v-model="userInputCode" @keyup.enter="login">
                    <VerificationCode :identifyCode="identifyCode" :refreshCode="refreshCode"></VerificationCode>
                </div>
                <div class="rpwd">
                    <input type="checkbox" v-model="rememberMe"/>
                    <span>记住密码</span>
                </div>
            </form>
            <button class="login_btn" @click="login">登录</button>
        </div>
        <!-- 注册 -->
        <div class="register_content" v-else>
            <form action="">
            <div class="uname">
                <input type="text" placeholder="请输入需要注册的账号" class="ipt" v-model="username" />
            </div>
            <div class="psw">
                <input type="password" placeholder="请输入密码" class="ipt" v-model="password" />
            </div>
            
            </form>
            <button class="register_btn" @click="register">注册</button>
        </div>

        
    </div>
    </div>
    
</template>

<script>
import * as THREE from 'three'; 
import BIRDS from 'vanta/dist/vanta.birds.min.js'; 
import VerificationCode from '@/components/Login/identify.vue'
export default {
    components:{
        VerificationCode
    },
    data() {
        return {
            antaEffect: null,
            isActive: true,
            username: '',
            password: '',
            code:'',
            uuid:'',
            rememberMe:'',
            identifyCode:'',
            userInputCode:''
        };
    },
    methods: {
        lr_change() {
            this.isActive = !this.isActive
        },
        login(){
            // 需要对输入的账号和密码校验
            // 勾选了需要记住密码需要在cookie中设置记住用户名和密码
            // 调用userStore 的action的登陆方法
            // 在后端实现验证码登录的功能
            window.alert("登录")
            console.log(this.username+this.password+" "+this.rememberMe+this.userInputCode);
        },
        register() {
            
        }, 
        refreshCode(newIdentifyCode){
            this.identifyCode = newIdentifyCode
        }
    },
    mounted() {
        try {
        this.vantaEffect = BIRDS({
            el: this.$refs.vantaBg, 
            THREE: THREE, 
            mouseControls: true,
            touchControls: true,
            gyroControls: false,
            minHeight: 200.00,
            minWidth: 200.00,
            scale: 1.00,
            scaleMobile: 1.00,
            backgroundColor:0xffffff,
            color1: 0x9f2b2b,
            color2: 0x3e793c,
            birdSize: 0.50,
            wingSpan: 23.00,
            separation: 27.00,
            alignment: 37.00,
            cohesion: 55.00
        });
        } catch (error) {
        console.error("[vanta.js] birds init error: ", error);
        }
    },
    beforeUnmount() {
        if (this.vantaEffect) this.vantaEffect.destroy(); 
    }
    };
</script>

<style scoped lang="scss">
.vanta-container {
    width: 100%;
    height: 100vh; 
    position: absolute;
    z-index: 1;
}
.lr-container{
    width: 100%;
    height: 100vh; 
    position: relative;
    
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 2;
    
}
.content_body {
    border: 1px solid #eee;
    box-shadow: 2px 3px 5px #eee;
    border-radius: 10px;
    color: #fff;
    width: 20%;
    background: rgba($color: #000000, $alpha: 0.2);
    .content_header{
        margin: 20px;
        a {
            
            font-size: 20px;
            margin: 0 10px;
            cursor: pointer;
        }
        .isActive {
            color: #fea443;
            font-weight: 400;
            border-bottom: 3px solid #fea443;
        }      
    }
    .login_content,.register_content{
        text-align: center;
        .ipt {
            width: 80%;
            height: 60px;
            border: none;
            border-radius: 10px;
            padding: 0 20px;
            margin: 10px 0;
            font-size: 12px;
            &:focus{
                outline: none;
            }
        }
        .login_btn,.register_btn{
            width: 80%;
            height: 50px;
            color: #fff;
            margin: 10px 0;
            border: none;
            font-size: 14px;
            font-weight: 500;
            border-radius: 5px;
            background-color: rgba($color: #000000, $alpha: 0.2);
            cursor: pointer;
            &:hover{
                background-color: #fea443;
            }
        }
        .rpwd{
            display: flex;
            font-size: 12px;
            margin-left: 10%;
        }
    }
    .identify-code{
        @include flex-center;
        .code-input{
            height: 40px;
            width: 40%;
        }
    }
    
}
</style>
  