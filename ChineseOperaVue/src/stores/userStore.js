// src/stores/userStore.js
import { defineStore } from 'pinia';
import {login,logout} from '@/api/login'
import {getToken,setToken,removeToken} from '@/utils/auth'


export const useUserStore = defineStore('user', {
  state: () => ({
    userId: null, // 用户ID
    userName: '', // 用户名
    nickName:'',
    token:getToken(),
    avatar:'',
    roles:[],
    permissions:[]
  }),
  actions: {
   login(userInfo){
    const username = userInfo.username.trim()
    const password = userInfo.password
    const code = userInfo.code
    const uuid = userInfo.uuid
    return new Promise((resolve,reject)=>{
      login(username,password,code,uuid).then(res=>{
        console.log(res.data.data)
        setToken(res.data.data.token)
        this.token = res.data.data.token
        // this.avatar = res.data.data.avatar
        // this.userId = res.data.data.userId
        // this.nickName = res.data.data.nickName
        resolve()
      }).catch(error=>{
        reject(error)
      })
    })
   },
   logOut(){
    return new Promise((resolve,reject)=>{
      logout(this.token).then(()=>{
        this.token = '';
        this.roles = [];
        this.permissions = [];
        removeToken()
        resolve()
      }).catch(error=>{
        reject(error)
      })
    })
   },
   
  },
});
export default useUserStore
