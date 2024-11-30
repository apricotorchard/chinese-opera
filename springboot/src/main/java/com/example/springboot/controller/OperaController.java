package com.example.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.domain.Audio;
import com.example.springboot.domain.Comment;
import com.example.springboot.domain.Opera;
import com.example.springboot.utils.ResponseResult;
import com.example.springboot.service.CommentService;
import com.example.springboot.service.OperaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    //根据戏曲的类型进行查询，这个要做一个分页的操作。
    @GetMapping("/getlistbyoperatag")
    public Page<Opera> getOperaListByTag(@RequestParam String operaTag,
                                         @RequestParam int pageNum, @RequestParam int pageSize){
        //开启分页
        return operaService.getOperaListByTag(operaTag,pageNum,pageSize);
    }


    //获取戏曲下的所有评论
    @GetMapping("/comments/{operaId}")
    public ResponseResult getCommentsByOperaId(@PathVariable long operaId){
        return commentService.getCommentsByOperaId(operaId);
    }

    //插入评论
    @PostMapping("/comments/addcomment")
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @PostMapping("/addopera")
    public ResponseResult addOpera(@RequestBody Opera opera){
        return operaService.addOpera(opera);
    }

}

