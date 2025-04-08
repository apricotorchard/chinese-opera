import request from '@/utils/request.js'

//新增优惠卷

//查找所有的优惠卷
export function getVoucherList(shopId){
    return request({
        url:`/voucher/list/${shopId}`,
        method:'get',
    })
}

//抢购优惠卷
export function seckillVoucher(voucherId){
    return request({
        url:`/voucher-order/seckill/${voucherId}`,
        method:'post'
    })
}