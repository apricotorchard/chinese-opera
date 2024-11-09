package com.example.springboot.controller;

import com.example.springboot.common.constant.HttpStatus;
import com.example.springboot.utils.Base64;
import com.example.springboot.utils.ResponseResult;
import com.example.springboot.utils.RedisCache;
import com.google.code.kaptcha.Producer;
import com.example.springboot.common.constant.Constants;
import jakarta.annotation.Resource;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/user")
public class CaptchaContoller {

    @Resource(name="captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;

    @GetMapping("/captchaImage")
    public ResponseResult getCode(HttpServletResponse response) throws IOException
    {
        String uuid = UUID.randomUUID().toString();
        String verifyKey = Constants.CAPTCHA_CODE_KEY+uuid;
        String capText = captchaProducerMath.createText();
        String capStr = capText.substring(0, capText.lastIndexOf("@"));
        String code = capText.substring(capText.lastIndexOf("@") + 1);
        BufferedImage image = captchaProducerMath.createImage(capStr);
        redisCache.setCacheObject(verifyKey,code,Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        //轮转流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            return new ResponseResult<>(HttpStatus.ERROR,"验证码返回失败");
        }
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("uuid", uuid);
        hashMap.put("img",Base64.encode(os.toByteArray()));
        return new ResponseResult<>(HttpStatus.SUCCESS,"验证码返回成功",hashMap);
    }
}
