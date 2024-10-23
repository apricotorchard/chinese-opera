package com.example.springboot;

import com.example.springboot.domain.User;
import com.example.springboot.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> userList = userMapper.selectList(null);
        System.out.println(userList);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String psd1 = passwordEncoder.encode("123456");
        System.out.println(psd1);
        boolean result = passwordEncoder.matches("123456","$2a$10$FNQqAotf4vVwmyK1Dyp4OeTUCtKAmoYWuByvx6PRt5Aqsj7i11aDq");
        System.out.println(result);
    }

}
