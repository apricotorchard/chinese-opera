package com.example.springboot.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("opera")
public class Opera {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String singer;
    private String tag;
    private String des;
    private String pictureUrl;
    private String playUrl;
    private Integer collectionId;
}
