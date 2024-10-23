import axios from 'axios'


// 封装axios请求
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'

const request = axios.create({
    baseURL:'http://localhost:8080',
})
// 1.请求拦截器


// 2.响应拦截器

export default request