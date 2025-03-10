import request from '@/utils/request'

export function getpermission(){
    return request({
        method:'get',
        url:'/menu/getpermessions'
    })
}

export function updateMenu(editForm){
    return request({
        method:'post',
        url:'/menu/updatepermessions',
        data:editForm
    })
}

export function deletemenuById(id) {
    return request({
        method: 'delete',  // 修改为 DELETE 方法
        url: `/menu/deletepermession/${id}`,  // ID 作为路径参数
    });
}
export function addMenu(menu){
    console.log(menu);
    return request({
        method:'post',
        url:'/menu/addpermession',
        data:menu
    })
}