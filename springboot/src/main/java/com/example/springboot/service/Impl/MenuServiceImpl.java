package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.domain.DTO.UpdatePermissionDTO;
import com.example.springboot.domain.Menu;
import com.example.springboot.mapper.MenuMapper;
import com.example.springboot.service.MenuService;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Override
    public boolean updatePermessions(UpdatePermissionDTO updatePermissionDTO) {
        int permessionId = updatePermissionDTO.getId();
        Menu menu = this.getById(permessionId);
        menu.setName(updatePermissionDTO.getName());
//        menu.setStatus(updatePermissionDTO.getStatus());
        if (this.updateById(menu)) {
            return true;
        }
        return false;
    }
}
