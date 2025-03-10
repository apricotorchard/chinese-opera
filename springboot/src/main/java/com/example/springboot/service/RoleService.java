package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.domain.DTO.RoleDTO;
import com.example.springboot.domain.Role;

import java.util.List;

public interface RoleService extends IService<Role> {
    List<Role> getRolesInfo();

    int saveRoleInfo(RoleDTO role);

    int updateRoleInfo(RoleDTO role);

    int removeRoleAndMenuById(long id);
}
