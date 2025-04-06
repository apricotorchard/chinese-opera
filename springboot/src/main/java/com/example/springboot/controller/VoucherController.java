package com.example.springboot.controller;

import com.example.springboot.common.constant.HttpStatus;
import com.example.springboot.domain.Voucher;
import com.example.springboot.service.IVoucherService;
import com.example.springboot.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    IVoucherService voucherService;

    /**
     * 查询店铺的优惠卷列表
     * @param shopId
     * @return
     */
    @GetMapping("/list/{shopId}")
    public ResponseResult queryVoucherOfShop(@PathVariable("shopId") Long shopId) {
        return voucherService.queryVoucherOfShop(shopId);
    }

    /**
     * 保存普通的优惠卷
     * @param voucher
     * @return
     */
    @PostMapping
    public ResponseResult addVoucher(@RequestBody Voucher voucher) {
        voucherService.save(voucher);
        return new ResponseResult<>(HttpStatus.SUCCESS,"成功",voucher.getId());
    }

    /**
     * 新增秒杀卷
     * @param voucher
     * @return
     */
    @PostMapping("seckill")
    public ResponseResult addSeckillVoucher(@RequestBody Voucher voucher) {
        voucherService.addSeckillVoucher(voucher);
        return new ResponseResult(HttpStatus.SUCCESS,"成功",voucher.getId());
    }
}
