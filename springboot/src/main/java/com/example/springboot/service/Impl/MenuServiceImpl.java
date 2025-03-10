package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.domain.Menu;
import com.example.springboot.mapper.MenuMapper;
import com.example.springboot.service.MenuService;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
}
