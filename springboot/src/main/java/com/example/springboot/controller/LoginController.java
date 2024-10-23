package com.example.springboot.controller;

import com.example.springboot.domain.ResponseResult;
import com.example.springboot.domain.User;
import com.example.springboot.domain.model.LoginBody;
import com.example.springboot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")

    public ResponseResult login(@RequestBody LoginBody loginBody){
        return loginService.login(loginBody.getUsername(),loginBody.getPassword(),loginBody.getCode(), loginBody.getUuid());
    }
}
