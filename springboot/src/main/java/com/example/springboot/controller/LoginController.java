package com.example.springboot.controller;

import com.example.springboot.domain.ResponseResult;
import com.example.springboot.domain.model.LoginBody;
import com.example.springboot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginBody loginBody){
        return loginService.login(loginBody.getUsername(),loginBody.getPassword(),loginBody.getCode(), loginBody.getUuid());
    }

    @GetMapping("/test")
    public String hello(){
        System.out.println("111");
        return "hello";
    }

    @PostMapping ("/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
