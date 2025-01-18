import axios from 'axios'
import {getToken} from '@/utils/auth'

// 封装axios请求
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'


const service = axios.create({
    baseURL:'8.130.36.156:8091',
})


// 1.请求拦截器
service.interceptors.request.use(config=>{
    const isToken = (config.headers || {}).isToken === false
    if(getToken() && !isToken){
        config.headers['Authorization'] = getToken()
    }
    return config;
})

// 2.响应拦截器

export default service