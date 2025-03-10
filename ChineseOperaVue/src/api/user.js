import request from '@/utils/request.js'
export function getallusers(){
    return request({
        method:'get',
        url:'/usermanage/getallusers'
    })   
}

export function updateuserinfo(userinfo){
    return request({
        method:'post',
        url:'/usermanage/updateuserinfo',
        data:userinfo
    })
}

export function getroles(){
    return request({
        method:'get',
        url:'/usermanage/getroles'
    })
}

export function addrole(newRole){
    return request({
        method:'post',
        url:'/usermanage/addrole',
        data:newRole
    })
}
export function deleteRoleInfo(id){
    return request({
        method:'delete',
        url:`/usermanage/deleterole/${id}`
    })
}

export function updateRole(roles){
    return request({
        method:'post',
        url:'/usermanage/updateroleandmenu',
        data:roles
    })
}