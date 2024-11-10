package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.domain.Audio;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AudioMapper extends BaseMapper<Audio> {
}
