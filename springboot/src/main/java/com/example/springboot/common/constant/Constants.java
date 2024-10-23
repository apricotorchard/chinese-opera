package com.example.springboot.common.constant;

public class Constants {
    public static final String TOKEN="token";


    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;
    /**
     * 用户名长度限制
     */

    public static final int USERNAME_MIN_LENGTH = 2;
    public static final int USERNAME_MAX_LENGTH = 20;
    /**
     * 密码长度限制
      */
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 20;

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";
}
