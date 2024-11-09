package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.constant.HttpStatus;
import com.example.springboot.domain.Opera;
import com.example.springboot.utils.ResponseResult;
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

    @Override
    public Page<Opera> getOperaListByTag(String operaTag, int pageNum, int pageSize) {
        Page<Opera> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Opera> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag", operaTag);


        return operaMapper.selectPage(page, queryWrapper);
//        QueryWrapper<Opera> queryWrapper
//        List<Opera> operaList = operaMapper.selectOperaListByTag(operaTag);

//        TableDataInfo tableDataInfo = new TableDataInfo();
//        tableDataInfo.setCode(HttpStatus.SUCCESS);
//        tableDataInfo.setMsg("查询成功");
//        tableDataInfo.setTotal(operaPageInfo.getTotal());
//        tableDataInfo.setRows(operaPageInfo.getList());
//        return new PageInfo<>(operaList);
    }
}
