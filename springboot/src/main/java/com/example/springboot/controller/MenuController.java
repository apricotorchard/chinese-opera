package com.example.springboot.controller;

import com.example.springboot.domain.DTO.UpdatePermissionDTO;
import com.example.springboot.domain.Menu;
import com.example.springboot.service.MenuService;
import com.example.springboot.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;
    //查询所有的权限
    @GetMapping("/getpermessions")
    public ResponseResult getPermessions(){
        return new ResponseResult(200,"success",menuService.list());
    }
    //修改权限
    @PostMapping("/updatepermessions")
    public ResponseResult updatePermessions(@RequestBody UpdatePermissionDTO updatePermissionDTO){
        return new ResponseResult(200,"success",menuService.updatePermessions(updatePermissionDTO));
    }
    //删除权限
    @DeleteMapping("/deletepermession/{id}")
    public ResponseResult deletePermession(@PathVariable Integer id){
        return new ResponseResult(200,"success",menuService.removeById(id));
    }
    //添加权限
    @PostMapping("/addpermession")
    public ResponseResult addPermession(@RequestBody Menu menu) {
        return new ResponseResult(200, "success", menuService.save(menu));
    }

}
