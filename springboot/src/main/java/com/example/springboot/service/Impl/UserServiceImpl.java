package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.domain.DTO.UpdateUserDTO;
import com.example.springboot.domain.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.mapper.UserRoleMapper;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<User> getAllUsersWithRoles() {
        return userMapper.getAllUsersWithRoles();
    }

    @Override
    public boolean updateManageUserInfo(UpdateUserDTO userDTO) {
        //1.应该根据userid设置用户的状态
        User user = userMapper.selectById(userDTO.getId());
        user.setStatus(userDTO.getStatus());
        int count = userMapper.updateById(user);
        //2.根据uid查询user_role，把user的role改为userDTO的role
        long userId = userDTO.getId();
        List<Integer> roleIds = userDTO.getRoleIds();
        userRoleMapper.deleteByUserId(userId); // 先删除旧数据
        if (!roleIds.isEmpty()) {
            userRoleMapper.insertUserRoles(userId, roleIds); // 插入新数据
        }
        return true;
    }
}
