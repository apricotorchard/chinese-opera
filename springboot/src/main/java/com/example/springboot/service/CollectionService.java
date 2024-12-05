package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.domain.Collection;
import com.example.springboot.utils.ResponseResult;

public interface CollectionService extends IService<Collection> {
    ResponseResult getCollectInfo();

    ResponseResult addCollection(Collection collection);
}
