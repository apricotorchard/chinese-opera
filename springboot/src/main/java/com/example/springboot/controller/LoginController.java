package com.example.springboot.controller;

import com.example.springboot.domain.DTO.RegisterDto;
import com.example.springboot.domain.ResponseResult;
import com.example.springboot.domain.DTO.LoginBody;
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

    @PostMapping ("/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestBody RegisterDto registerDto){
        return loginService.register(registerDto);
    }
}
