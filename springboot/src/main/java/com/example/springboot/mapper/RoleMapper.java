package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.domain.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select menu_id from sys_role_menu where role_id = #{roleid}")
    List<Long> getRolemenu(long roleid);
    @Delete("delete from sys_role_menu where role_id = #{roleid}")
    void deleteRoleMenu(Long roleid);

    void insertRoleMenu(Long userid, List<Long> menus);
}
