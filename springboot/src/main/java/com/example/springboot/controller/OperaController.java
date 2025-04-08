package com.example.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.domain.Opera;
import com.example.springboot.service.OperaService;
import com.example.springboot.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/opera")
public class OperaController {
    @Autowired
    private OperaService operaService;

    @GetMapping("/list")
    public ResponseResult getOpera(@RequestParam int pageNum,@RequestParam int  pageSize){
        return operaService.getOpera(pageNum,pageSize);
    }
    @GetMapping("/{collectId}")
    public ResponseResult getOperaListByCollectionId(@PathVariable("collectId")Integer collectId){
        return operaService.getOperaListByCollectionId(collectId);
    }
    @GetMapping("/tag")
    public Page<Opera> getOperaListByTag(@RequestParam String operaTag,
                                         @RequestParam int pageNum, @RequestParam int pageSize){
        return operaService.getOperaListByTag(operaTag,pageNum,pageSize);
    }
    @PostMapping
    public ResponseResult addOpera(@RequestBody Opera opera){
        return operaService.addOpera(opera);
    }

    @PutMapping
    public ResponseResult updateOpera(@RequestBody Opera opera){
        return operaService.updateOpera(opera);
    }
}

