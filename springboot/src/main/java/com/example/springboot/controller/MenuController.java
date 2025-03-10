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
    @GetMapping("/getpermessions")
    public ResponseResult getPermessions(){
        return new ResponseResult(200,"success",menuService.list());
    }

    @PostMapping("/updatepermessions")
    public ResponseResult updatePermessions(@RequestBody UpdatePermissionDTO updatePermissionDTO){
        return new ResponseResult(200,"success",menuService.updatePermessions(updatePermissionDTO));
    }
    //TODO:删除权限的时候，也要把role_perssion里面的权限删除掉
    @DeleteMapping("/deletepermession/{id}")
    public ResponseResult deletePermession(@PathVariable Integer id){
        return new ResponseResult(200,"success",menuService.removeById(id));
    }

    @PostMapping("/addpermession")

    public ResponseResult addPermession(@RequestBody Menu menu) {
        return new ResponseResult(200, "success", menuService.save(menu));
    }

}
