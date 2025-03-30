package com.example.springboot.controller;

import com.example.springboot.domain.Collection;
import com.example.springboot.service.CollectionService;
import com.example.springboot.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collect")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @GetMapping("/getcollectinfo")
    public ResponseResult getCollectInfo(){
        return collectionService.getCollectInfo();
    }

    @PostMapping("/addcollect")
    public ResponseResult addCollection(@RequestBody Collection collection){
        return collectionService.addCollection(collection);
    }
}
