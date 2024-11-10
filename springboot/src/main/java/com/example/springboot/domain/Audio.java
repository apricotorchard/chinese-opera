package com.example.springboot.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("opera_audio_info")
public class Audio {

    @TableId
    private int id;
    private String name;
    private String audioUrl;
    private String tag;
}
