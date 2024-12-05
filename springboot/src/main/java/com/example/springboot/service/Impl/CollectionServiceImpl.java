package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.constant.HttpStatus;
import com.example.springboot.domain.Collection;
import com.example.springboot.mapper.CollectionMapper;
import com.example.springboot.service.CollectionService;
import com.example.springboot.utils.ResponseResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {
    @Override
    public ResponseResult getCollectInfo() {
        LambdaQueryWrapper<Collection> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<Collection> collectionList = this.list(lambdaQueryWrapper);

        return new ResponseResult<>(HttpStatus.SUCCESS,"查询数据成功",collectionList);
    }

    @Override
    public ResponseResult addCollection(Collection collection) {
        boolean isSuccess = this.save(collection);
        if(isSuccess){
            return new ResponseResult<>(HttpStatus.SUCCESS,"新增合集成功");
        }
        return new ResponseResult<>(HttpStatus.ERROR,"新增合集失败");
    }
}
