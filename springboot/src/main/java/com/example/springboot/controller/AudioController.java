package com.example.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.domain.Audio;
import com.example.springboot.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/audio")
public class AudioController {
    @Autowired
    AudioService audioService;

    @GetMapping("/getaudiobytag")
    public Page<Audio> getAudioListByTag(@RequestParam String tag, @RequestParam int pageNum, @RequestParam int pageSize){
        return audioService.getAudioListByTag(tag,pageNum,pageSize);
    }
}
