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
    private AccessService accessService;

    @GetMapping
    public ResponseResult getOpera(@RequestParam int pageNum,@RequestParam int  pageSize){
        return operaService.getOpera(pageNum,pageSize);
    }

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

    @GetMapping("/getlistbyoperatag")
    public Page<Opera> getOperaListByTag(@RequestParam String operaTag,
                                         @RequestParam int pageNum, @RequestParam int pageSize){
        return operaService.getOperaListByTag(operaTag,pageNum,pageSize);
    }

    @GetMapping("/gethotlist")
    public ResponseResult getHotList(){
        List<Opera> hotListByUserAccess = accessService.getHotListByUserAccess();
        return new ResponseResult<>(HttpStatus.SUCCESS,hotListByUserAccess);
    }

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

