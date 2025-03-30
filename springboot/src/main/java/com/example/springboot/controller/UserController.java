package com.example.springboot.controller;

import com.example.springboot.domain.DTO.LoginBody;
import com.example.springboot.domain.DTO.RegisterDto;
import com.example.springboot.domain.DTO.RoleDTO;
import com.example.springboot.domain.DTO.UpdateUserDTO;
import com.example.springboot.domain.User;
import com.example.springboot.service.LoginService;
import com.example.springboot.service.RoleService;
import com.example.springboot.service.UserService;
import com.example.springboot.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginBody loginBody){
        return loginService.login(loginBody.getUsername(),loginBody.getPassword(),loginBody.getCode(), loginBody.getUuid());
    }
    @PostMapping ("/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestBody RegisterDto registerDto){
        return loginService.register(registerDto);
    }
    @GetMapping
    public ResponseResult getUserInfo(){
        return userService.getUserInfo();
    }
    @GetMapping("/list")
    public ResponseResult getAllUsers(){
        return new ResponseResult(200,"success",userService.getAllUsersWithRoles());
    }
    @PutMapping ("/update")
    public ResponseResult updatePersonInfo(@RequestBody User user){
        return new ResponseResult(200,"success",userService.updatePersonInfo(user));
    }
    //TODO：重写更新个人信息界面，这个应该是管理用户的个人信息
    @PostMapping("/manage-update")
    public ResponseResult updateUserInfo(@RequestBody UpdateUserDTO userDTO){
        return new ResponseResult<>(200,"success",userService.updateManageUserInfo(userDTO));
    }
}
