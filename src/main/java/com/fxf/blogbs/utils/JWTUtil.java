package com.fxf.blogbs.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtil {
    private static final String SIGN = "!@D#F3D!fa%";

    /**
     * 生成Token header.payload.sing
     * @return
     */
    public static String getToken(Map<String,String> map){


        Calendar instance = Calendar.getInstance();
        //设置Token令牌过期时间
        instance.add(Calendar.DATE,5);

        //创建jwt.builder
        JWTCreator.Builder builder = JWT.create();

        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });

        String token = builder.withExpiresAt(instance.getTime())//指定令牌的过期时间
                                .withIssuer("auth0")
                                .sign(Algorithm.HMAC256(SIGN));//签名
        System.out.println("生成的token："+token);
        return token;
    }

    /**
     * 验证token合法性
     * @return
     */

    public static boolean verify(String token){
        try {
            DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).withIssuer("auth0").build().verify(token);
            System.out.println("认证通过：");
            System.out.println("username: " + verify.getClaim("username").asString());
            System.out.println("过期时间：      " + verify.getExpiresAt());
            return true;

        }catch (Exception e){
            return false;
        }



    }

    /**
     * 获取token信息方法
     */

    private static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return verify;
    }
}
