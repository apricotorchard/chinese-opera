package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.domain.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    @Delete("DELETE FROM sys_user_role WHERE user_id = #{userId}")
    void deleteByUserId(@Param("userId") long userId);

    void insertUserRoles(@Param("userId") long userId, @Param("roleIds") List<Integer> roleIds);


}
