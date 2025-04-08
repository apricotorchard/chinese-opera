package com.example.springboot.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

//1符号位 31时间戳 32 序列号
@Component
public class RedisIdWorker {
    private static final long BEGIN_TIMESTAMP = 1640995200L;
    private static final int COUNT_BITS = 32;
    private RedisTemplate redisTemplate;
    public RedisIdWorker(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    public long nextId(String keyPrefix){
        //1.生成时间戳
        LocalDateTime now = LocalDateTime.now();
        long nowSecond = now.toEpochSecond(ZoneOffset.UTC);
        long timestamp = nowSecond - BEGIN_TIMESTAMP;

        //2.生成序列号
        //2.1获取到当前日期，精确到天
        String date = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        //2.2自增长
        long count = redisTemplate.opsForValue().increment("icr:"+keyPrefix+":"+date);
        //3.拼接并返回
        return timestamp<<COUNT_BITS|count;
    }

}
