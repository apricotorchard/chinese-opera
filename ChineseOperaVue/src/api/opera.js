import request from '@/utils/request.js'

// 动态获得服务器上的戏曲视频播放地址
export function getOperaPlayUrl(opera){
    const operaPathMap= {
        "京剧": "jingju",
        "黄梅戏": "huangmeixi",
        "豫剧": "yuju",
    };
    
    const serverAddress = "http://8.130.36.156:8080/chineseopera/";
    const path = operaPathMap[opera.tag];
    const dir = "video/"
    const suffix = ".mp4";
    const playUrl = `${serverAddress}${dir}${path}/c${opera.collectionId}/${opera.id}${suffix}`;
    return playUrl;
}
export function getAlloperaInfo(params){
    return request({
        url:'/opera',
        method:'get',
        params:params
    })
}

export function addOpera(data){
    return request({
        url:'/opera/addopera',
        method:'post',
        data:data
    })
}

export function updateOpera(data){
    return request({
        url:'/opera/updateopera',
        method:'post',
        data:data
    })
}

export function getOperaByCollectionId(collectionId){
    return request({
        url:`/opera/getoperalistbycollectid?collectionid=${collectionId}`,
        method:'get'
    })
}


export function getOperaListByTag(params){
    return request({
        url:'/opera/getlistbyoperatag',
        method:'get',
        params:params
    })
}

// 获取排行榜
export function getHotList(){
    return request({
        url:'/opera/gethotlist',
        method:'get'
    })
}
// 根据userId来获取戏曲的数据
export function getOperasFromUserAccess(){
    return request({
        url:'/opera/getoperasbyuserid',
        method:'get'
    })
}

export function deleteByOperaIds(operaIds){
    return request({
        url:'opera/deletebyids',
        method:'post',
        data:operaIds,
    });
}

//递归地去找  回复的是谁的评论,感觉这样效率太低。
// export function findCommentById(comments, targetId) {
//     for (const comment of comments) {
//         if (comment.id === targetId) {
//             return comment;
//         }
//         if (comment.children && comment.children.length) {
//             const found = findCommentById(comment.children, targetId);
//             if (found) return found;
//         }
//     }
//     return null; // 如果没有找到，返回 null
// }