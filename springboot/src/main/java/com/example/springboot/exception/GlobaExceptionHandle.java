package com.example.springboot.exception;


import com.example.springboot.common.constant.HttpStatus;
import com.example.springboot.utils.ResponseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.swing.*;

@RestControllerAdvice
public class GlobaExceptionHandle {

    //Spring Security 会抛出 AccessDeniedException
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult<String> handleAccessDenied(AccessDeniedException ex) {
        return new ResponseResult<>(HttpStatus.ERROR,"您没有权限访问此资源");
    }

}
