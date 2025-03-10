package com.example.springboot.controller;

import com.example.springboot.domain.DTO.UpdateUserDTO;
import com.example.springboot.service.MenuService;
import com.example.springboot.service.UserService;
import com.example.springboot.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usermanage")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    MenuService menuService;

    @GetMapping("/getallusers")
    public ResponseResult getAllUsers(){
        return new ResponseResult(200,"success",userService.getAllUsersWithRoles());
    }

    @PostMapping("/updateuserinfo")
    public ResponseResult updateUserInfo(@RequestBody UpdateUserDTO userDTO){
        return new ResponseResult<>(200,"success",userService.updateManageUserInfo(userDTO));
    }
    @GetMapping("/getpermessions")
    public ResponseResult getPermessions(){
        return new ResponseResult(200,"success",menuService.list());
    }
    @GetMapping("/getroleandpermiss")
    public ResponseResult getRoleAndPermiss(){
        return new ResponseResult(200,"success",null);
    }
}
