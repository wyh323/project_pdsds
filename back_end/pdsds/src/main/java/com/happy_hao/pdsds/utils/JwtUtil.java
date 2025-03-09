package com.happy_hao.pdsds.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.WeakKeyException;

import java.io.InputStream;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static final String jwtKey;
    private static final long jwtExpiration;
    private static final byte[] keyBytes; // 保存密钥字节数组
    private static final SecretKey key; // 保存密钥对象

    // 获取令牌密钥和过期时间
    static {
        try (InputStream input = JwtUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            jwtKey = properties.getProperty("jwt.secretKey");
            keyBytes = jwtKey.getBytes(); // 初始化密钥字节数组
            key = Keys.hmacShaKeyFor(keyBytes); // 初始化密钥对象
            jwtExpiration = Long.parseLong(properties.getProperty("jwt.expirationTime"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load JWT configuration", e);
        }
    }

    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .claims().empty().add(claims).and()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration)) // 使用静态字段
                .signWith(key) // 使用静态密钥对象
                .compact();
    }

    public static Claims extractClaims(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(key) // 使用静态密钥对象
                    .build()
                    .parseSignedClaims(token);
            // 获取 Claims
            Claims claims = claimsJws.getPayload();

            // 验证过期时间
            Instant expiration = Instant.ofEpochSecond(claims.getExpiration().getTime() / 1000L);
            if (Instant.now().isAfter(expiration)) {
                throw new RuntimeException("JWT token has expired");
            }

            return claims;
        } catch (SignatureException e) {
            throw new RuntimeException("Invalid JWT signature", e);
        } catch (WeakKeyException e) {
            throw new RuntimeException("Weak key used for JWT verification", e);
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }

}