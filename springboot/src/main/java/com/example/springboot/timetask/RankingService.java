package com.example.springboot.timetask;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.constant.Constants;
import com.example.springboot.domain.Opera;
import com.example.springboot.service.OperaService;
import com.example.springboot.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class RankingService {
    @Autowired
    OperaService operaService;

    @Autowired
    RedisCache redisCache;


    @Scheduled(cron="0 * * * * ?")
    public void calculateRankings(){
        //1.应该根据用户的各种行为计算出排行榜中的数据
        Page<Opera> hotLists = operaService.getOperaListByTag("京剧",1,10);

        List<Opera> hot = hotLists.getRecords();

        //同时应该写入日志系统里面

//        log.info(String.valueOf(hot));

        //3.将计算得到的排行榜中的数据写入到redis当中
        redisCache.setCacheObject(Constants.HOT_OPERAS,hot);

        //4.这会不会设计到多线程问题？
    }


}
