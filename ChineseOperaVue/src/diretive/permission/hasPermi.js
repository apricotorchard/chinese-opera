import useUserStore from '@/stores/userStore.js'

export default{
    mounted(el,binding,vnode){
        const {value} = binding
        const all_permission = "*:*:*"
        const userStore = useUserStore();
        const permissions = userStore.permissions
        
        if(value && value instanceof Array && value.length>0){
            const permissionsFlag = value
            const hasPermissions = permissions.some(permission=>{
                return all_permission === permission||permissionsFlag.includes(permission)
            })
            if (!hasPermissions) {
                el.parentNode && el.parentNode.removeChild(el)
              }
        }
        else{
            throw new Error('请设置操作权限标签')
        }
    }
}