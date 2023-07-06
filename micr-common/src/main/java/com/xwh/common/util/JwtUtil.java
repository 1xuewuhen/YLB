package com.xwh.common.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

/**
 * 作者:陈方银
 * 时间:2023/7/6
 */
public class JwtUtil {


    private static SecretKey secretKey = null;
    private static String time = "";
    private static String issuer = "";

    /**
     *     // 生成uuid
     *     String key = "c886db79dc174d13b3c3e4c32e6e0d72";
     *     // 创建secretKey
     *     SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
     */
    static {
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("JwtConfig.properties");
        try {
            Properties properties = new Properties();
            properties.load(stream);
            String key = properties.getProperty("jwt.key", "f24e38539d0d4386ab254e3310ffb508");
            time = properties.getProperty("jwt.expTime", "20");
            secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
            issuer = properties.getProperty("jwt.issuer","血狱神教");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 创建jwt
    public static String createJwt(Map<String, Object> map) throws Exception {
        Date curDate = new Date();
        return Jwts.builder()
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .setExpiration(DateUtils.addMinutes(curDate, Integer.parseInt(time)))
                .setIssuedAt(curDate)
                .setIssuer(issuer)
                .setId(UUID.randomUUID().toString().replaceAll("-", ""))
                .addClaims(map)
                .compact();
    }


}
