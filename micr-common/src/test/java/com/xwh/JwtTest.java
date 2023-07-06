package com.xwh;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 作者:陈方银
 * 时间:2023/7/6
 */

public class JwtTest {

    // 生成uuid
    String key = "c886db79dc174d13b3c3e4c32e6e0d72";
    // 创建secretKey
    SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    @Test
    public void testCreate() {


        Map<String, Object> map = new HashMap<>();
        map.put("userId", "1000");
        map.put("name", "张三");
        map.put("role", "老板");
        // 创建JWT
        Date curDate = new Date();
        String jwt = Jwts.builder()
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .setExpiration(DateUtils.addMinutes(curDate, 10))
                .setIssuedAt(curDate)
                .setId(UUID.randomUUID().toString().replace("-", ""))
                .addClaims(map)
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void testReadJwt() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODg2MTM1MDMsImlhdCI6MTY4ODYxMjkwMywianRpIjoiYzFkZjZmY2Q4NDI3NGJiZWFlY2I1MTQwZGI2ODlkNDQiLCJyb2xlIjoi6ICB5p2_IiwibmFtZSI6IuW8oOS4iSIsInVzZXJJZCI6IjEwMDAifQ.gKYBlCsuzuQNSoVowXikpcQtiYLIOFHMCRCVc9S9G6E";
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(jwt);
        Claims claims = claimsJws.getBody();
        String userId = claims.get("userId", String.class);
        System.out.println(userId);
    }
}
