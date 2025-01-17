package com.example.springboot.service;

import com.example.springboot.domain.Opera;

import java.util.List;

public interface AccessService {
    //1.根据用户行为获得热榜
    List<Opera> getHotListByUserAccess();
    //2.计算排行榜
    List<Opera> calculateRanking();
}
