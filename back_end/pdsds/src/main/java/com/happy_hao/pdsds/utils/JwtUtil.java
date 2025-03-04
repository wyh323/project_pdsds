package com.happy_hao.pdsds.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class JwtUtil {
    private final static byte[] jwtKeyBytes;
    private final static long jwtExpiration;

    // 获取令牌密钥和过期时间
    static {
        try (InputStream input = JwtUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            String jwtKeyString = properties.getProperty("jwt.key");
            jwtKeyBytes = jwtKeyString.getBytes(); // 将密钥字符串转换为字节数组
            jwtExpiration = Long.parseLong(properties.getProperty("jwt.expiration"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load JWT configuration", e);
        }
    }

    public static String genToken(Map<String, Object> claims) {
        // 使用密钥生成签名
        var key = Keys.hmacShaKeyFor(jwtKeyBytes);

        // 构建 JWT
        return Jwts.builder()
                .setClaims(claims) // 设置自定义声明
                .setIssuedAt(new Date()) // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration * 1000)) // 设置过期时间
                .setSubject("all") // 设置主题
                .signWith(key, SignatureAlgorithm.HS256) // 设置签名算法和密钥
                .compact(); // 构建 JWT 并返回紧凑的字符串表示
    }

    public static Map<String, Object> parseToken(String token) {
        // 使用密钥生成签名
        var key = Keys.hmacShaKeyFor(jwtKeyBytes);

        try {
            // 解析 JWT 并返回 Claims
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key) // 设置签名密钥
                    .build()
                    .parseClaimsJws(token) // 解析 JWT
                    .getBody(); // 获取 Claims

            return claims;
        } catch (SignatureException e) {
            throw new IllegalArgumentException("Invalid JWT signature", e);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JWT token", e);
        }
    }
}