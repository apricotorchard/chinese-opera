package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.domain.Opera;
import com.example.springboot.utils.ResponseResult;
import com.example.springboot.utils.TableDataInfo;


public interface OperaService {
    ResponseResult getOpera();

    ResponseResult getOperaListByCollectionId(int collectionId);

    Page<Opera> getOperaListByTag(String operaTag, int pageNum, int pageSize);

    ResponseResult addOpera(Opera opera);
}
