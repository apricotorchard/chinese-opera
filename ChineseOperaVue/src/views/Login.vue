<template> 
    <div ref="vantaBg" class="vanta-container"></div>
    <div class="lr-container">
        <!-- <LoginRegister></LoginRegister> -->
        <div class="content_body">
        <div class="content_header">
            <a :class="isActive?['isActive']:[]" @click="login">登录</a>
            <a :class="isActive?[]:['isActive']" @click="register">注册</a>
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
                
            </form>
            <button class="login_btn" @click="GetLogin">登录</button>
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
            <button class="register_btn" @click="goRegister">注册</button>
        </div>
    </div>
    </div>
    
</template>

<script>
import * as THREE from 'three'; 
import BIRDS from 'vanta/dist/vanta.birds.min.js'; 
export default {
    data() {
        return {
            antaEffect: null,
            isActive: true,
            username: '',
            password: '',
        };
    },
    methods: {
        login() {
            this.isActive = !this.isActive
        },
        register() {
            this.isActive = !this.isActive
        }, 
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
            margin: 20px 0;
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
    }
}
</style>
  