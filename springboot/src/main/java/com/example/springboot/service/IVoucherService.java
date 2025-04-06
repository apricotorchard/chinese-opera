package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.domain.Voucher;
import com.example.springboot.utils.ResponseResult;

public interface IVoucherService extends IService<Voucher> {
    ResponseResult queryVoucherOfShop(Long shopId);

    void addSeckillVoucher(Voucher voucher);
}
