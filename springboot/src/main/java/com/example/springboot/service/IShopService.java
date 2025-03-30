package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.domain.Shop;
import com.example.springboot.utils.ResponseResult;

public interface IShopService extends IService<Shop> {
    ResponseResult queryById(Long id);
}
