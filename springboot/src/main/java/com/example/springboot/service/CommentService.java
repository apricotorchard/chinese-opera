package com.example.springboot.service;

import com.example.springboot.domain.Comment;
import com.example.springboot.utils.ResponseResult;


public interface CommentService{
    ResponseResult getCommentsByOperaId(long operaId);

    ResponseResult addComment(Comment comment);
}
