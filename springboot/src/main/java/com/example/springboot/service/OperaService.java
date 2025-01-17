package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.domain.Opera;
import com.example.springboot.mapper.OperaMapper;
import com.example.springboot.utils.ResponseResult;
import com.example.springboot.utils.TableDataInfo;
import org.springframework.web.bind.annotation.RequestParam;


public interface OperaService extends IService<Opera> {
    ResponseResult getOpera(int pageNum, int  pageSize);

    ResponseResult getOperaListByCollectionId(Integer collectionId);

    Page<Opera> getOperaListByTag(String operaTag, int pageNum, int pageSize);

    ResponseResult addOpera(Opera opera);


    ResponseResult updateOpera(Opera opera);

//    ResponseResult getHotList();
}
