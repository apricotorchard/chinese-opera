package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.constant.Constants;
import com.example.springboot.common.constant.HttpStatus;
import com.example.springboot.domain.Collection;
import com.example.springboot.domain.Opera;
import com.example.springboot.mapper.CollectionMapper;
import com.example.springboot.utils.RedisCache;
import com.example.springboot.utils.ResponseResult;
import com.example.springboot.mapper.OperaMapper;
import com.example.springboot.service.OperaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OperaServiceImpl extends ServiceImpl<OperaMapper,Opera> implements OperaService {

    @Autowired
    OperaMapper operaMapper;

    @Autowired
    CollectionMapper collectionMapper;

    @Autowired
    RedisCache redisCache;
    @Override
    public ResponseResult getOpera(int pageNum,int pageSize) {
        Page<Opera> page= new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<Opera> queryWrapper = new LambdaQueryWrapper<>();
        Page<Opera> operaPage = operaMapper.selectPage(page, queryWrapper);
        List<Opera> operaList = operaPage.getRecords();
        for (Opera opera : operaList) {
            Integer collectionId = opera.getCollectionId();
            String collectionName;
            if(Objects.isNull(collectionId)){
                collectionName = "未分组";
            }else{
                Collection collection = collectionMapper.selectById(collectionId); // 你需要定义一个 collectionMapper 用来查询 collection
                collectionName = collection.getTitle();
            }
            opera.setCollectName(collectionName); // 将 collection 信息设到 opera 中
        }
        return new ResponseResult<>(HttpStatus.SUCCESS,"戏曲数据加载成功",operaPage);
    }

    @Override
    public ResponseResult getOperaListByCollectionId(Integer collectionId) {
        LambdaQueryWrapper<Opera> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Opera::getCollectionId,collectionId);
        List<Opera> operaList = operaMapper.selectList(lambdaQueryWrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("operaList",operaList);
        //如果operaList的长度大于1，说明有合集
        if(operaList.size()>1){
            LambdaQueryWrapper<Collection> collectionLambdaQueryWrapper = new LambdaQueryWrapper<>();
            collectionLambdaQueryWrapper.eq(Collection::getCollectionId,collectionId);
            Collection collection = collectionMapper.selectOne(collectionLambdaQueryWrapper);
            map.put("collection",collection);
        }else{
            map.put("collection","");
        }
        return new ResponseResult<>(HttpStatus.SUCCESS,"成功",map);
    }

    @Override
    public Page<Opera> getOperaListByTag(String operaTag, int pageNum, int pageSize) {
        Page<Opera> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Opera> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag", operaTag);
        return operaMapper.selectPage(page, queryWrapper);
    }

    @Override
    public ResponseResult addOpera(Opera opera) {
        boolean isSuccess = this.save(opera);
        if(isSuccess){
            return new ResponseResult<>(HttpStatus.SUCCESS,"新增成功");
        }
        return new ResponseResult<>(HttpStatus.ERROR,"失败");
    }

    @Override
    public ResponseResult updateOpera(Opera opera) {

        boolean isSuccess = this.updateById(opera);
        if(isSuccess){
            return new ResponseResult<>(HttpStatus.SUCCESS,"更新成功");
        }
        return new ResponseResult<>(HttpStatus.ERROR,"失败");

    }

    @Override
    public ResponseResult getHotList() {
        List<Opera> hotLists = redisCache.getCacheObject(Constants.HOT_OPERAS);
        return new ResponseResult<>(HttpStatus.SUCCESS,"成功",hotLists);
    }


}
