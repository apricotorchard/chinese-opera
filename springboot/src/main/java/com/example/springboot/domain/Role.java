package com.example.springboot.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("sys_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Long id;
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String dataScope;
    private String status;
    private String delFlag;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private String remark;
}
