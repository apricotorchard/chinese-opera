import request from '@/utils/request.js'
export function getallusers(){
    return request({
        method:'get',
        url:'/user/list'
    })   
}
//TODO:重写这个更新个人信息界面
export function updateuserinfo(userinfo){
    return request({
        method:'put',
        url:'/user/manage-update',
        data:userinfo
    })
}

export function updatePerson(user){
    return request({
        method:'post',
        url:'/user/update',
        data:user
    })
}

//获取用户信息
export function getInfo(){
    return request({
      'url':'/user',
      'method':'get'
    })
  }