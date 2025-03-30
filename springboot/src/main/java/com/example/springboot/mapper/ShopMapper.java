package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.domain.Shop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopMapper extends BaseMapper<Shop> {
}
