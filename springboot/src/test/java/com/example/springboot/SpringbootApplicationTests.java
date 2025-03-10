package com.example.springboot;

import ch.qos.logback.classic.LoggerContext;
import com.example.springboot.domain.User;
import com.example.springboot.mapper.MenuMapper;
import com.example.springboot.mapper.UserMapper;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;


@SpringBootTest
class SpringbootApplicationTests {

//    @Autowired
//    UserMapper userMapper;
//
//    @Autowired
//    MenuMapper menuMapper;

    public static final Logger LOGGER = LoggerFactory.getLogger(SpringbootApplicationTests.class);
//    @Test
//    void contextLoads() {
//        List<User> userList = userMapper.selectList(null);
//        System.out.println(userList);
//
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String psd1 = passwordEncoder.encode("123456");
//        System.out.println(psd1);
//        boolean result = passwordEncoder.matches("123456","$2a$10$FNQqAotf4vVwmyK1Dyp4OeTUCtKAmoYWuByvx6PRt5Aqsj7i11aDq");
//        System.out.println(result);
//    }
//
//
//    @Test
//    void testmenu(){
//        List<String> permissions = menuMapper.selectPermsByUserId(1L);
//        for (String permission : permissions) {
//            System.out.println(permission);
//        }
//    }

    @Test
    void testlog(){
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        System.out.println("Logback configuration loaded: " + loggerContext.getStatusManager().getCopyOfStatusList());

        LOGGER.error("error");
        LOGGER.warn("jhdqwjhdqjhdq");
        LOGGER.info("info");
        LOGGER.debug("DEBUG");
        LOGGER.trace("tracd");
    }
    @Autowired
    UserMapper userMapper;
    @Test
    void test(){
        List<User> allUser = userMapper.getAllUsersWithRoles();
        System.out.println();
    }
}
