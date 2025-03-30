package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.domain.Shop;
import com.example.springboot.mapper.ShopMapper;
import com.example.springboot.service.IShopService;
import com.example.springboot.utils.ResponseResult;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {
    @Override
    public ResponseResult queryById(Long id) {
        return null;
    }
}
