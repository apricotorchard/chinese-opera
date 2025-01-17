package com.example.springboot.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("access_logs")
public class UserAccess {
    private Integer id;
    private Integer userId;
    private Integer operaId;
}
