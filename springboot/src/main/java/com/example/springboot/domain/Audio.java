package com.example.springboot.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("audio")
public class Audio {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String audioUrl;
    private String tag;
}
