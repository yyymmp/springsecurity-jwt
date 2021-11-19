package com.security.jwt.demo.utils;

public interface Constants {
    interface Jwt {
        /**
         * 密钥
         */
        String KEY = "123123";
        /**
         * 过期时间
         */
        long EXPIRATION = 7200000;
        /**
         * 请求头
         */
        String TOKEN_HEAD = "Authorization";
    }
}
