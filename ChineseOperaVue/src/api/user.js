import request from '@/utils/request.js'
export function getallusers(){
    return request({
        method:'get',
        url:'/usermanage/getallusers'
    })   
}

export function updateuserinfo(userinfo){
    return request({
        method:'post',
        url:'/usermanage/updateuserinfo',
        data:userinfo
    })
}