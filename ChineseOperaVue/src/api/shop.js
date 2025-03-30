import request from '@/utils/request.js'
export function getShopList(typeId,current){
    return request({
        url:`/shop/of/type?typeId=${typeId}&current=${current}`,
        method:'get',
    })
}