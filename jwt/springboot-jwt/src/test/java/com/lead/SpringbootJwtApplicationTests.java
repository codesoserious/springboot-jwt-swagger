package com.lead;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lead.dao.UserDao;
import com.lead.entity.User;
import com.lead.utils.SaltUtils;
import com.lead.utils.VerifyEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.HashMap;

@SpringBootTest(classes = SpringbootJwtApplication.class)
public class SpringbootJwtApplicationTests {


    @Resource
    private UserDao userDao;

    @Test
    public void contextLoads() {
        HashMap<String, Object> object = new HashMap<>();
        String salt = SaltUtils.getSalt(6);
        // 设置过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 120);

        System.out.println("生成的随机salt = " + salt);
        String sign =
                JWT.create()
                        .withHeader(object) // header
                        .withClaim("userId", 10) // payload
                        .withClaim("username", "li") // payload
                        .withExpiresAt(instance.getTime()) // 设置过期时间
                        .sign(Algorithm.HMAC256("u$AaIE")); // signature 签名
        System.out.println(sign);
    }

    @Test
    public void test2() {
        // 生成验证对象
        JWTVerifier u$AaIE1 = JWT.require(Algorithm.HMAC256("u$AaIE")).build();
        DecodedJWT verify =
                u$AaIE1.verify(
                        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTk5MTg3NTEsInVzZXJJZCI6MTAsInVzZXJuYW1lIjoibGkifQ.QQWtxU48uWix_k9iSEPGMSvKB9rLVVqTbOr3q_k3e74");
        System.out.println(verify.getClaim("userId").asInt());
        System.out.println(verify.getClaim("username").asString());
    }

    @Test
    public void test3() throws IllegalAccessException {
        User user = new User();
        VerifyEntity.verify(user);
    }

    @Test
    public void test4() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", "string");
        System.out.println(userDao.selectOne(userQueryWrapper));
    }
}
