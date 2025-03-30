package com.example.springboot.service;


import com.example.springboot.domain.DTO.RegisterDto;
import com.example.springboot.utils.ResponseResult;

public interface LoginService {
        ResponseResult login(String username,String password,String code,String uuid);
        ResponseResult logout();

        ResponseResult register(RegisterDto registerDto);


}
