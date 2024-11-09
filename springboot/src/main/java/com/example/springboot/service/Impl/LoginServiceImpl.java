package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springboot.common.constant.Constants;
import com.example.springboot.common.constant.HttpStatus;
import com.example.springboot.domain.DTO.RegisterDto;
import com.example.springboot.domain.LoginUser;
import com.example.springboot.utils.ResponseResult;
import com.example.springboot.domain.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.LoginService;
import com.example.springboot.utils.JwtUtil;
import com.example.springboot.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisCache redisCache;

    @Autowired
    UserMapper userMapper;
    @Override
    public ResponseResult login(String username,String password,String code,String uuid) {
        validateCaptcha(code, uuid);
        // 登录前置校验
        loginPreCheck(username, password);
        //1.封装Authentication对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        //2.通过AuthenticationManager的authenticate方法来进行用户认证
        Authentication authenticated = authenticationManager.authenticate(authenticationToken);

        //3.在Authentication中获取用户信息
        LoginUser loginUser = (LoginUser) authenticated.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        //4.认证通过生成token
        String jwt = JwtUtil.createJWT(userId);
        //5.用户信息存入redis
        redisCache.setCacheObject(Constants.LOGIN_USER_KEY + userId, loginUser);
        //6.把token返回给前端
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(Constants.TOKEN, jwt);
        return new ResponseResult(HttpStatus.SUCCESS, "登录成功", hashMap);
    }
    @Override
    public ResponseResult logout(){
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisCache.deleteObject(Constants.LOGIN_USER_KEY+userid);
        return new ResponseResult(HttpStatus.SUCCESS,"注销成功");
    }

    @Override
    public ResponseResult register(RegisterDto registerDto) {
        //1.验证用户名和密码是否有效，符合规范
        loginPreCheck(registerDto.getUsername(), registerDto.getPassword());

        //2.需要根据用户名查询数据库
        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<User>().eq(User::getUserName,registerDto.getUsername());
        User user = userMapper.selectOne(wrapper);
        //3.将用户名和密码插入到数据库当中，中间需要一步加密操作。
        if(user!=null){
            throw new RuntimeException("用户名已经被注册");
        }
        User newuser = new User();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        newuser.setUserName(registerDto.getUsername());
        newuser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        try{
            userMapper.insert(newuser);
        }catch (Exception e) {
            throw new RuntimeException("注册失败，请重试");
        }
        return new ResponseResult(HttpStatus.SUCCESS,"注册成功");
    }

    @Override
    public ResponseResult getUserInfo() {


        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //Todo 角色集合
        //TODO 权限集合
        //根据userid查询信息返回给前端
        return new ResponseResult<>(HttpStatus.SUCCESS,"返回用户信息",loginUser);
    }

    public void validateCaptcha(String code,String uuid){
        uuid = (uuid != null) ? uuid : "";
        String verifyKey = Constants.CAPTCHA_CODE_KEY+uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if(captcha==null){
            throw new RuntimeException("没找打对应的验证码");
        }
        if(!code.equalsIgnoreCase(captcha)){
            //我该如何把这种报错的异常传回到前端进行信息的显示？
            throw new RuntimeException("验证码不匹配");
        }
    }

    public void loginPreCheck(String username,String password){
        if(username==null||password==null){
            throw  new RuntimeException("用户名或密码错误");
        }
        if(password.length()<Constants.PASSWORD_MIN_LENGTH||password.length()>Constants.PASSWORD_MAX_LENGTH){
            throw new RuntimeException("密码长度需要在5到20之间");
        }
        if(username.length()<Constants.USERNAME_MIN_LENGTH||username.length()>Constants.USERNAME_MAX_LENGTH){
            throw new RuntimeException("用户名不符合规范");
        }
        //IP黑名单校验是怎么弄的？
    }


}


