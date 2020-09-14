package com.lead.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.lead.execption.CheckException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类封装
 *
 * @author mac
 */
public class JwtUtils {

    public static Map<String, Object> changeMap(String username, Integer userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("username", username);
        return map;
    }

    /**
     * 生成Token方法1
     *
     * @param id      编号
     * @param days    过期时间以天为单位
     * @param issuer  该JWT的签发者，是否使用是可选的
     * @param subject 该JWT所面向的用户，是否使用是可选的；
     * @param sign    签名(默认为用户的密码 ,默认加密HMAC256)
     * @return token String
     */
    public static String generateToken(String id, Integer days, String issuer, String subject, String sign) {
        //设置过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, days);
        return JWT.create().withExpiresAt(calendar.getTime()).withIssuer(issuer).withSubject(subject).withJWTId(id).sign(Algorithm.HMAC256(sign));
    }

    /**
     * 生成Token方法2(2)
     *
     * @param id   编号
     * @param days 过期时间以天为单位
     * @param sign 签名(默认为用户的密码 ,默认加密HMAC256)
     * @return token String
     */
    public static String generateToken(String id, Integer days, String sign) {
        //设置过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        return JWT.create().withExpiresAt(calendar.getTime()).withJWTId(id).sign(Algorithm.HMAC256(sign));
    }

    /**
     * 验证token
     *
     * @param token
     */
    public static void verify(String token, String sign) {
        try {
            JWT.require(Algorithm.HMAC256(sign)).build().verify(token);
        } catch (SignatureVerificationException e) {
            throw new CheckException("签名不一致，请从新登陆");
        } catch (TokenExpiredException e) {
            throw new CheckException("令牌已经失效，请从新登陆");
        } catch (AlgorithmMismatchException e) {
            throw new CheckException("令牌不正确，请从新登陆");
        } catch (InvalidClaimException e) {
            throw new CheckException("失效的信息，请从新登陆");
        }

    }
}
