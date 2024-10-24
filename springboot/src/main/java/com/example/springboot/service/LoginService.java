package com.example.springboot.service;


import com.example.springboot.domain.ResponseResult;
import com.example.springboot.domain.User;

public interface LoginService {
        ResponseResult login(String username,String password,String code,String uuid);
        public ResponseResult logout();
}
