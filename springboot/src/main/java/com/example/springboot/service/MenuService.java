package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.domain.DTO.UpdatePermissionDTO;
import com.example.springboot.domain.Menu;

public interface MenuService extends IService<Menu> {
    boolean updatePermessions(UpdatePermissionDTO updatePermissionDTO);
}
