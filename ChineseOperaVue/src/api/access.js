import request from '@/utils/request.js'
export function getHotList(){
    return request({
        url:'/access/hot',
        method:'get'
    })
}

export function getOperasFromUserAccess(){
    return request({
        url:'/access/history',
        method:'get'
    })
}

export function deleteByOperaIds(operaIds){
    return request({
        url:'/access',
        method:'delete',
        data:operaIds,
    });
}