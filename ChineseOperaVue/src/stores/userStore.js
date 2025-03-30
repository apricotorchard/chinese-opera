// src/stores/userStore.js
import { defineStore } from 'pinia';
import {login,logout,getInfo} from '@/api/login'
import {getToken,setToken,removeToken} from '@/utils/auth'

// pinia中的数据 在页面刷新的时候会丢失，状态会被重置，所以需要被持久化在本地。
export const useUserStore = defineStore('user', {
  state: () => ({
    userId: null, // 用户ID
    userName: '', // 用户名
    nickName:'',
    token:getToken(),
    avatar:'',
    roles:'',
    permissions:[]
  }),
  actions: {
   login(userInfo){
    const username = userInfo.username.trim()
    const password = userInfo.password
    const code = userInfo.code
    const uuid = userInfo.uuid
    localStorage.removeItem('user');
    return new Promise((resolve,reject)=>{
      login(username,password,code,uuid).then(res=>{
        setToken(res.data.data.token)
        this.token = res.data.data.token
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
  // 获取用户的详细信息。
  getUserInfo(){
    return new Promise((resolve,reject)=>{
        getInfo().then(res=>{
            // 这里可以添加一些用户权限的东西
            console.log(res);
            this.userId = res.data.data.user.id;
            this.userName  = res.data.data.user.userName;
            this.nickName = res.data.data.user.nickName;
            this.avatar = res.data.data.user.avatar;
            this.roles = res.data.data.user.type==0?'super_admin':'admin';
            this.permissions = res.data.data.userPermissions;
            resolve(res);
        }).catch(error=>{reject(error)})
    })
  },
  },
  persist: {
    enabled: true, // 启用持久化
    strategies: localStorage
  }
});
export default useUserStore
