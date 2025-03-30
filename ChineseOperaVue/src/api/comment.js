import request from '@/utils/request.js'
// 获取用户评论
export function getCommentsByOperaId(operaId){
    return request({
        url:`/comments/${operaId}`,
        method:'get'
    })
}

// 插入用户的评论

export function addComment(comment){
    return request({
        url:'/comments/addcomment',
        method:'post',
        data:comment
    })
}