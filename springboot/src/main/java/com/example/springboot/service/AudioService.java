package com.example.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.domain.Audio;
import com.example.springboot.utils.ResponseResult;

import java.util.List;

public interface AudioService {
    Page<Audio> getAudioListByTag(String tag, int pageNum, int pageSize);

    ResponseResult addAudio(Audio audio);

    ResponseResult addAudioBatch(List<Audio> audioList);
}
