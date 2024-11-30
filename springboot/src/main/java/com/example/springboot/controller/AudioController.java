package com.example.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.domain.Audio;
import com.example.springboot.service.AudioService;
import com.example.springboot.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audio")
public class AudioController {
    @Autowired
    AudioService audioService;

    @GetMapping("/getaudiobytag")
    public Page<Audio> getAudioListByTag(@RequestParam String tag, @RequestParam int pageNum, @RequestParam int pageSize){
        return audioService.getAudioListByTag(tag,pageNum,pageSize);
    }

    @PostMapping("/addaudio")
    public ResponseResult addAudio(@RequestBody Audio audio){
        return audioService.addAudio(audio);
    }

    @PostMapping("/addaudiobatch")
    public ResponseResult addAudioBatch(@RequestBody  List<Audio> audioList){
        return audioService.addAudioBatch(audioList);
    }
}
