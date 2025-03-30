package com.example.springboot.controller;

import com.example.springboot.domain.DTO.RoleDTO;
import com.example.springboot.service.RoleService;
import com.example.springboot.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/list")
    public ResponseResult getRoles(){
        return new ResponseResult(200,"success",roleService.getRolesInfo());
    }
    @PostMapping("/add")
    public ResponseResult addRole(@RequestBody RoleDTO role){
        return new ResponseResult(200,"success",roleService.saveRoleInfo(role));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteRole(@PathVariable Integer id){
        return new ResponseResult(200,"success",roleService.removeRoleAndMenuById(id));
    }
    @PostMapping("/updaterole")
    public ResponseResult updateRole(@RequestBody RoleDTO role){
        return new ResponseResult(200,"success",roleService.updateRoleInfo(role));
    }
}
