package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.domain.VoucherOrder;
import com.example.springboot.utils.ResponseResult;

public interface IVoucherOrderService extends IService<VoucherOrder> {
    ResponseResult seckillVoucher(Long voucherId);
}
