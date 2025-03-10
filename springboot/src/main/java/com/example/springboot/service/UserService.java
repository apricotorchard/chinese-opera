package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.domain.DTO.UpdateUserDTO;
import com.example.springboot.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService extends IService<User> {
    List<User> getAllUsersWithRoles();

    boolean updateManageUserInfo(UpdateUserDTO userDTO);
}
