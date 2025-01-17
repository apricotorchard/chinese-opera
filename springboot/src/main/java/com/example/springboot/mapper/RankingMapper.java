package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.domain.Rank;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RankingMapper extends BaseMapper<Rank> {
}
