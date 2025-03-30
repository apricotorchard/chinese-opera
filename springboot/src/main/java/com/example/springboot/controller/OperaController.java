package com.example.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.constant.HttpStatus;
import com.example.springboot.domain.Audio;
import com.example.springboot.domain.Collection;
import com.example.springboot.domain.Comment;
import com.example.springboot.domain.Opera;
import com.example.springboot.service.AccessService;
import com.example.springboot.service.CollectionService;
import com.example.springboot.service.Impl.LoginServiceImpl;
import com.example.springboot.utils.ResponseResult;
import com.example.springboot.service.CommentService;
import com.example.springboot.service.OperaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.util.ObjectUtils;
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

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private AccessService accessService;

    @GetMapping
    public ResponseResult getOpera(@RequestParam int pageNum,@RequestParam int  pageSize){
        return operaService.getOpera(pageNum,pageSize);
    }

//    @PostMapping("/addopera")
//    public ResponseResult addOpera(@RequestBody Opera opera){
//        return operaService.addOpera(opera);
//    }
//    @PreAuthorize("hasAnyAuthority('system:test:list')")
    @GetMapping("/getoperalistbycollectid")
    public ResponseResult getOperaListByCollectionId(@RequestParam Integer collectionid){
        return operaService.getOperaListByCollectionId(collectionid);
    }

    @PostMapping("/addopera")
    public ResponseResult addOpera(@RequestBody Opera opera){
        return operaService.addOpera(opera);
    }


    @PostMapping("/updateopera")
    public ResponseResult updateOpera(@RequestBody Opera opera){
        return operaService.updateOpera(opera);
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




    @GetMapping("/collect/getcollectinfo")
    public ResponseResult getCollectInfo(){
        return collectionService.getCollectInfo();
    }

    @PostMapping("/collect/addcollect")
    public ResponseResult addCollection(@RequestBody Collection collection){
        return collectionService.addCollection(collection);
    }

    @GetMapping("/gethotlist")
    public ResponseResult getHotList(){
        List<Opera> hotListByUserAccess = accessService.getHotListByUserAccess();
        return new ResponseResult<>(HttpStatus.SUCCESS,hotListByUserAccess);
    }

    //查询用户的行为信息。
    @GetMapping("/getoperasbyuserid")
    public ResponseResult getOperasByUserId(){
        long userId = LoginServiceImpl.getCurrentUserId();
        List<Opera> operas = accessService.getOperasByUserId(userId);
        return new ResponseResult(HttpStatus.SUCCESS,operas);
    }
    @PostMapping("/deletebyids")
    public ResponseResult deleteOperasByIds(@RequestBody List<Integer> ids){
        long userId = LoginServiceImpl.getCurrentUserId();
        boolean isSuccess = accessService.deleteOperasByIds(userId,ids);
        if(isSuccess){
            return new ResponseResult(HttpStatus.SUCCESS,isSuccess);
        }
        return new ResponseResult(HttpStatus.ERROR,isSuccess);
    }
}

