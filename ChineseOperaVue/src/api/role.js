import request from '@/utils/request.js'
export function getroles(){
    return request({
        method:'get',
        url:'/role/list'
    })
}

export function addrole(newRole){
    return request({
        method:'post',
        url:'/role/add',
        data:newRole
    })
}
export function deleteRoleInfo(id){
    return request({
        method:'delete',
        url:`/role/delete/${id}`
    })
}

export function updateRole(roles){
    return request({
        method:'post',
        url:'/role/update',
        data:roles
    })
}