package com.example.springboot.service;

import com.example.springboot.domain.ResponseResult;

public interface OperaService {
    ResponseResult getOpera();

    ResponseResult getOperaListByCollectionId(int collectionId);
}
