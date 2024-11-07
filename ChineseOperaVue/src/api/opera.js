import request from '@/utils/request.js'

// 动态获得服务器上的播放地址
export function getOperaPlayUrl(opera){
    const operaPathMap= {
        "京剧": "jingju",
        "黄梅戏": "huangmeixi",
        "豫剧": "yuju",
    };
    
    const serverAddress = "http://8.130.36.156:8080/chineseopera/";
    const path = operaPathMap[opera.tag];
    const suffix = ".mp4";
    const playUrl = `${serverAddress}${path}/c${opera.collectionId}/${opera.id}${suffix}`;
    return playUrl;
}
export function getAlloperaInfo(){
    return request({
        url:'/opera',
        method:'get'
    })
}

export function getOperaByCollectionId(collectionId){
    return request({
        url:`/opera/getoperalistbycollectid?collectionid=${collectionId}`,
        method:'get'
    })
}


export function getCommentsByOperaId(operaId){
    return request({
        url:`/opera/comments/${operaId}`,
        method:'get'
    })
}