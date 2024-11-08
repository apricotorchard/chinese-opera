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