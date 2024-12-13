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
// 获取用户评论
export function getCommentsByOperaId(operaId){
    return request({
        url:`/opera/comments/${operaId}`,
        method:'get'
    })
}

// 插入用户的评论

export function addComment(comment){
    return request({
        url:'/opera/comments/addcomment',
        method:'post',
        data:comment
    })
}

//查询合集信息
export function getCollectionInfo(){
    return request({
        url:'/opera/collect/getcollectinfo',
        method:'get'
    })
}

// 新增合集信息
export function addCollection(data){
    return request({
        url:'/opera/collect/addcollect',
        method:'post',
        data:data
    })
}

// 获取排行榜
export function getHotList(){
    return request({
        url:'/opera/gethotlist',
        method:'get'
    })
}
// 编辑分组信息



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