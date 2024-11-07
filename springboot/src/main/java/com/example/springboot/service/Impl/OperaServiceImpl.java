package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springboot.common.constant.HttpStatus;
import com.example.springboot.domain.Opera;
import com.example.springboot.domain.ResponseResult;
import com.example.springboot.mapper.OperaMapper;
import com.example.springboot.service.OperaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperaServiceImpl implements OperaService {

    @Autowired
    OperaMapper operaMapper;
    @Override
    public ResponseResult getOpera() {
        LambdaQueryWrapper<Opera> queryWrapper = new LambdaQueryWrapper<>();
        List<Opera> operaList = operaMapper.selectList(queryWrapper);

        return new ResponseResult<>(HttpStatus.SUCCESS,"成功",operaList);
    }

    @Override
    public ResponseResult getOperaListByCollectionId(int collectionId) {
        LambdaQueryWrapper<Opera> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Opera::getCollectionId,collectionId);
        List<Opera> operaList = operaMapper.selectList(lambdaQueryWrapper);
        return new ResponseResult<>(HttpStatus.SUCCESS,"成功",operaList);
    }
}
