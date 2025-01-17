package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.domain.Rank;
import com.example.springboot.domain.UserAccess;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserAccessMapper extends BaseMapper<UserAccess> {
    List<Rank> getRankingOperaIdList();
}
