package com.security.jwt.demo.utils;

import io.jsonwebtoken.*;

import java.util.Date;

/**
 * @author jlz
 * @className: JwtTokenUtils
 * @date 2021/11/19 11:14
 * @description todo
 **/
public class JwtTokenUtils {
    private static final long serialVersionUID = -3369436201465044824L;

    /**
     * 生成token
     *
     * @param username
     * @param id
     * @return
     */
    public static String createToken(String username, long id) {
        return Jwts.builder().setSubject(username)
                .claim("id", id)
                .setExpiration(new Date(System.currentTimeMillis() + Constants.Jwt.EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, Constants.Jwt.KEY).compressWith(CompressionCodecs.GZIP).compact();
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public static String getUserName(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Constants.Jwt.KEY).parseClaimsJws(token);
        return claimsJws.getBody().getSubject();
    }

    /**
     * 获取用户id
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Constants.Jwt.KEY).parseClaimsJws(token);
        return String.valueOf(claimsJws.getBody().get("id"));
    }


}
