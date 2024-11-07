package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.constant.HttpStatus;
import com.example.springboot.domain.Comment;
import com.example.springboot.domain.ResponseResult;
import com.example.springboot.domain.User;
import com.example.springboot.mapper.CommentMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public ResponseResult getCommentsByOperaId(long operaId) {
        List<Comment> commentList = commentMapper.selectByOperaId(operaId);
        for(Comment comment:commentList){
            User user = userMapper.selectById(comment.getUserId());
            comment.setUser(user);
        }
        //将评论按照parentId进行分组
        Map<Long,List<Comment>> groupedComments = commentList.stream().collect(Collectors.groupingBy(comment-> comment.getParentId() == null ? -1L : comment.getParentId()));

        List<Comment> rootComments = buildCommentsTree(groupedComments,-1L);

        return new ResponseResult<>(HttpStatus.SUCCESS,"获取戏曲评论成功",rootComments);
    }


    private List<Comment> buildCommentsTree(Map<Long, List<Comment>> groupedComments, Long parentId) {
        List<Comment> commentList = groupedComments.get(parentId);
        if(commentList == null){
            return new ArrayList<>();
        }
        for(Comment comment: commentList){
            List<Comment> children = buildCommentsTree(groupedComments, comment.getId());
            comment.setChildren(children);
        }
        return commentList;
    }

    @Override
    public ResponseResult getReplies(Long parentId) {
        return null;
    }
}
