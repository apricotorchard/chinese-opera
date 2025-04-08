package com.example.springboot.utils;

import cn.hutool.json.JSONUtil;
import com.example.springboot.common.constant.RabbitConstant;
import com.example.springboot.domain.VoucherOrder;
import com.example.springboot.service.ISeckillVoucherService;
import com.example.springboot.service.IVoucherOrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SeckillOrderConsumer {

    @Resource
    private IVoucherOrderService voucherOrderService;
    @Resource
    private ISeckillVoucherService seckillVoucherService;

    @RabbitListener(queues = RabbitConstant.SECKILL_ORDER_QUEUE)
    public void receiveOrder(VoucherOrder order){
        log.info("接收到存储订单信息的消息:{}", JSONUtil.toJsonStr(order));
        boolean success = seckillVoucherService.update()
                .setSql("stock=stock-1")
                .eq("voucher_id",order.getVoucherId())
                .gt("stock",0)
                .update();
        voucherOrderService.save(order);
        log.info("订单信息存储完成：{}",success);
    }
}



