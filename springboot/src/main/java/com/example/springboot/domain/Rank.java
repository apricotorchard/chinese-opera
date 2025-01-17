package com.example.springboot.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("ranking")
@AllArgsConstructor
@NoArgsConstructor
public class Rank {
    @TableId(type = IdType.AUTO)
    private Integer operaId;
    private Integer accessCount;
}
