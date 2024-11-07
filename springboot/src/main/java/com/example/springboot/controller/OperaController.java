package com.example.springboot.controller;

import com.example.springboot.domain.Comment;
import com.example.springboot.domain.Opera;
import com.example.springboot.domain.ResponseResult;
import com.example.springboot.service.CommentService;
import com.example.springboot.service.OperaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opera")
public class OperaController {
    // TODO: 2024/11/3  先暂时写一个查询全部的操作，然后等数据量多的时候更换成分页查询
    @Autowired
    private OperaService operaService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseResult getOpera(){
        return operaService.getOpera();
    }

    @GetMapping("/getoperalistbycollectid")
    public ResponseResult getOperaListByCollectionId(@RequestParam int collectionid){
        return operaService.getOperaListByCollectionId(collectionid);
    }
    //获取戏曲下的所有评论
    @GetMapping("/comments/{operaId}")
    public ResponseResult getCommentsByOperaId(@PathVariable long operaId){
        return commentService.getCommentsByOperaId(operaId);
    }

    //获取某评论的所有回复
    @GetMapping("/replies/{parentId}")
    public ResponseResult getReplies(@PathVariable Long parentId){
        return commentService.getReplies(parentId);
    }

}
