package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.domain.Comment;
import com.example.springboot.domain.ResponseResult;
import com.example.springboot.mapper.CommentMapper;
import org.springframework.stereotype.Service;


public interface CommentService{
    ResponseResult getCommentsByOperaId(long operaId);

    ResponseResult getReplies(Long parentId);
}
