import request from '@/utils/request.js'
//查询合集信息
export function getCollectionInfo(){
    return request({
        url:'/collect/getcollectinfo',
        method:'get'
    })
}

// 新增合集信息
export function addCollection(data){
    return request({
        url:'/collect/addcollect',
        method:'post',
        data:data
    })
}