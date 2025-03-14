package com.happy_hao.pdsds.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.WeakKeyException;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import com.happy_hao.pdsds.config.JwtConfig;

@Component
public class JwtUtil {

    private static long jwtExpiration = JwtConfig.getExpirationTime();
    private static SecretKey key = JwtConfig.getSecretKey(); // 保存密钥对象

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