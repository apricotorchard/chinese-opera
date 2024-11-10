package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.domain.Audio;
import com.example.springboot.domain.Opera;
import com.example.springboot.mapper.AudioMapper;
import com.example.springboot.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AudioServiceImpl implements AudioService {

    @Autowired
    private AudioMapper audioMapper;
    @Override

    public Page<Audio> getAudioListByTag(String tag, int pageNum, int pageSize) {
        Page<Audio> page = new Page<>(pageNum, pageSize); // 创建分页对象
        QueryWrapper<Audio> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag", tag); // 设置查询条件
        return audioMapper.selectPage(page, queryWrapper); // 返回分页结果
    }

}
