import request from '@/utils/request.js'

export function addVideo(fileInfo){
    return request({
        url:'/opera/addopera',
        method:'post',
        data:fileInfo
    })
}
