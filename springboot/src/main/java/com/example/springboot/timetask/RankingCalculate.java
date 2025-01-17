package com.example.springboot.timetask;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.constant.Constants;
import com.example.springboot.domain.Opera;
import com.example.springboot.mapper.RankingMapper;
import com.example.springboot.service.AccessService;
import com.example.springboot.service.OperaService;
import com.example.springboot.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.springboot.common.constant.Constants.HOT_OPERAS;
import static com.example.springboot.common.constant.Constants.HOT_OPERAS_TTL;

@Component
@Slf4j
public class RankingCalculate {
    @Autowired
    OperaService operaService;

    @Autowired
    RankingMapper rankingMapper;

    @Autowired
    AccessService accessService;
    @Autowired
    RedisCache redisCache;


    @Scheduled(cron="0 0 */12 * * ?")
//    @Scheduled(cron="0 * * * * ?")
    @Transactional
    //每12小时根据用户行为数据计算出排行榜，写入到排行榜数据库当中。
    public void calculateRankings(){
        //1.更新数据库并返回最新的排行榜列表
        //先把数据库清空
        rankingMapper.delete(null);
        List<Opera> operaList = accessService.calculateRanking();
        //2.删除缓存
        redisCache.deleteObject(HOT_OPERAS);
        //3.缓存重建
        redisCache.setCacheList(HOT_OPERAS,operaList);
        redisCache.expire(HOT_OPERAS,HOT_OPERAS_TTL, TimeUnit.HOURS);
    }

}
