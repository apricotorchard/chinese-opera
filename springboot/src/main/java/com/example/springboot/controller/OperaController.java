package com.example.springboot.controller;

import com.example.springboot.domain.Opera;
import com.example.springboot.domain.ResponseResult;
import com.example.springboot.service.OperaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/opera")
public class OperaController {
    // TODO: 2024/11/3  先暂时写一个查询全部的操作，然后等数据量多的时候更换成分页查询
    @Autowired
    OperaService operaService;
    @GetMapping
    public ResponseResult getOpera(){
        return operaService.getOpera();
    }

}
