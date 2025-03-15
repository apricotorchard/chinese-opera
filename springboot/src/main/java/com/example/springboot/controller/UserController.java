package com.example.springboot.controller;

import com.example.springboot.domain.DTO.RoleDTO;
import com.example.springboot.domain.DTO.UpdateUserDTO;
import com.example.springboot.domain.User;
import com.example.springboot.service.MenuService;
import com.example.springboot.service.RoleService;
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
    RoleService roleService;

    @GetMapping("/getallusers")
    public ResponseResult getAllUsers(){
        return new ResponseResult(200,"success",userService.getAllUsersWithRoles());
    }

    @PostMapping("/updateuserinfo")
    public ResponseResult updateUserInfo(@RequestBody UpdateUserDTO userDTO){
        return new ResponseResult<>(200,"success",userService.updateManageUserInfo(userDTO));
    }


    @GetMapping("/getroleandpermiss")
    public ResponseResult getRoleAndPermiss(){
        return new ResponseResult(200,"success",null);
    }

    @GetMapping("/getroles")
    public ResponseResult getRoles(){
        return new ResponseResult(200,"success",roleService.getRolesInfo());
    }

    @DeleteMapping("/deleterole/{id}")
    public ResponseResult deleteRole(@PathVariable Integer id){
        return new ResponseResult(200,"success",roleService.removeRoleAndMenuById(id));
    }


    @PostMapping("/addrole")
    public ResponseResult addRole(@RequestBody RoleDTO role){
        return new ResponseResult(200,"success",roleService.saveRoleInfo(role));
    }

    @PostMapping("/updateroleandmenu")
    public ResponseResult updateRole(@RequestBody RoleDTO role){
        return new ResponseResult(200,"success",roleService.updateRoleInfo(role));
    }


    @PostMapping("/updatepersoninfo")
    public ResponseResult updatePersonInfo(@RequestBody User user){
        return new ResponseResult(200,"success",userService.updatePersonInfo(user));
    }
}
