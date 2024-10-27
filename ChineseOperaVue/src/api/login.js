import request from '@/utils/request'

export function login(username,password,code,uuid){
    const data = {
        username,password,code,uuid
    }
    return request({
        url:'user/login',
        headers:{
            isToken:false,
            repeatSubmit:false
        },
        method:'post',
        data:data
    })
}



// 获取验证码
export function getCodeImg() {
    return request({
      url: 'user/captchaImage',
      headers: {
        isToken: false
      },
      method: 'get',
      timeout: 20000
    })
}

// 退出登录的操作

export function logout(){
  return request({
    url:'user/logout',
    method:'post',
  })
}

//用户注册的操作

export function register(data){
  return request({
    url:'user/register',
    method:'post',
    data:data
  })
}