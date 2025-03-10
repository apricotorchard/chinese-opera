package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.domain.Opera;
import com.example.springboot.domain.Rank;
import com.example.springboot.domain.UserAccess;
import com.example.springboot.mapper.OperaMapper;
import com.example.springboot.mapper.RankingMapper;
import com.example.springboot.mapper.UserAccessMapper;
import com.example.springboot.service.AccessService;
import com.example.springboot.service.OperaService;
import com.example.springboot.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.example.springboot.common.constant.Constants.HOT_OPERAS;
import static com.example.springboot.common.constant.Constants.HOT_OPERAS_TTL;

@Service
public class AccessServiceImpl extends ServiceImpl<UserAccessMapper, UserAccess> implements AccessService{

    @Autowired
    RedisCache redisCache;
    @Autowired
    UserAccessMapper userAccessMapper;
    @Autowired
    OperaMapper operaMapper;

    @Autowired
    OperaService operaService;

    @Autowired
    RankingMapper rankingMapper;

    @Override
    public List<Opera> getHotListByUserAccess() {
        //1.首先先查询缓存中有没有需要的排行榜数据
        List<Opera> rankingData = redisCache.getCacheList(HOT_OPERAS);
        //2.如果redis当中没有数据，查询数据库，并写入到缓存当中
        if(rankingData==null||rankingData.isEmpty()){
            rankingData = getRankingDataFromDataBase();
            redisCache.setCacheList(HOT_OPERAS,rankingData);

            redisCache.expire(HOT_OPERAS,HOT_OPERAS_TTL,TimeUnit.HOURS);
        }
        return rankingData;
    }
    //具体的获得排行榜列表算法的地方。
    private List<Opera> getRankingDataFromDataBase() {
        //1.从排行榜数据库当中查找
        List<Rank> ranks = rankingMapper.selectList(new QueryWrapper<>());
        List<Opera> rankingData = new ArrayList<>();
        //2.如果数据库当中也没数据，就生成一份数据
        if(ranks==null|| ranks.isEmpty()){
            rankingData = calculateRanking();
        }
        else{
            for (Rank rank : ranks) {
                Opera opera = operaMapper.selectById(rank.getOperaId());
                rankingData.add(opera);
            }
        }
        return rankingData;
    }
    @Override
    public List<Opera> calculateRanking(){
        //这是一个比较耗时的操作
        //1.统计每个opera的访问次数，从大到小排序，取出前10个
        List<Rank> rankingList = userAccessMapper.getRankingOperaIdList();
        //2.根据opera_id去查询戏曲的详细信息返回
        List<Opera> rankingData = new ArrayList<>();

        for (Rank rank: rankingList) {
            Opera opera = operaMapper.selectById(rank.getOperaId());
            rankingData.add(opera);
            //3.写入到排行榜数据库当中
            rankingMapper.insert(rank);
        }
        return rankingData;
    }

    @Override
    public List<Opera> getOperasByUserId(long userId) {
        LambdaQueryWrapper<UserAccess> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserAccess::getUserId,userId);
        List<UserAccess> userAccesses = userAccessMapper.selectList(lambdaQueryWrapper);
        List<Opera> operaList = userAccesses.stream()
                .map((userAccess -> operaMapper.selectById(userAccess.getOperaId())))
                .collect(Collectors.toList());
        return operaList;
    }

    @Override
    public boolean deleteOperasByIds(long userId, List<Integer> ids) {
        //根据用户id和戏曲ids删除数据。
        if (ids == null || ids.isEmpty()) {
            return false; // 参数校验，防止 SQL 语句执行错误
        }
        return this.remove(new LambdaQueryWrapper<UserAccess>()
                .eq(UserAccess::getUserId, userId)
                .in(UserAccess::getOperaId, ids)
        );
//        return false;
    }

}
