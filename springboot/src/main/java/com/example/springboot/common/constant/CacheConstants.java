package com.example.springboot.common.constant;

public class CacheConstants {
    public static final String TOKEN="token";
    public static final String LOGIN_USER_KEY = "login:";
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";
    public static final String HOT_OPERAS = "cache:hotoperas:";
    public static final String OPERA_COMMENT = "cache:comment:";
    public static final Long HOT_OPERAS_TTL = 6L;
    public static final Long OPERA_COMMENT_TTL = 30L;
    public static final Integer CAPTCHA_EXPIRATION = 2;
}
