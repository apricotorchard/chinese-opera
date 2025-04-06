package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.constant.HttpStatus;
import com.example.springboot.domain.Shop;
import com.example.springboot.mapper.ShopMapper;
import com.example.springboot.service.IShopService;
import com.example.springboot.utils.CacheClient;
import com.example.springboot.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.example.springboot.common.constant.CacheConstants.CACHE_SHOP_KEY;
import static com.example.springboot.common.constant.CacheConstants.CACHE_SHOP_TTL;

@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {
    @Autowired
    private CacheClient cacheClient;
    @Override
    public ResponseResult queryById(Long id) {
        Shop shop = cacheClient
                .queryWithPassThrough(CACHE_SHOP_KEY, id, Shop.class, this::getById, CACHE_SHOP_TTL, TimeUnit.MINUTES);
        if(shop == null){
            return new ResponseResult<>(HttpStatus.ERROR,"店铺不存在！");
        }
        return new ResponseResult<>(HttpStatus.SUCCESS,"成功",shop);
    }
}
