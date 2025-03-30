package com.example.springboot.controller;

import com.example.springboot.domain.Comment;
import com.example.springboot.service.CommentService;
import com.example.springboot.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/{operaId}")
    public ResponseResult getCommentsByOperaId(@PathVariable long operaId){
        return commentService.getCommentsByOperaId(operaId);
    }

    @PostMapping("/add-comment")
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
}
