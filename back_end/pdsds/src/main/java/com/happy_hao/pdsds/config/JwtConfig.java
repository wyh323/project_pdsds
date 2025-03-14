package com.happy_hao.pdsds.config;

import java.io.InputStream;
import java.util.Properties;
import javax.crypto.SecretKey;

import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.security.Keys;

@Configuration
public class JwtConfig {

    public static SecretKey getSecretKey() {
        try (InputStream input = JwtConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            return Keys.hmacShaKeyFor(properties.getProperty("jwt.secretKey").getBytes());

        } catch (Exception e) {
            throw new RuntimeException("Failed to load secretKey configuration", e);
        }
    }

    public static long getExpirationTime() {
        try (InputStream input = JwtConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            return Long.parseLong(properties.getProperty("jwt.expirationTime"));

        } catch (Exception e) {
            throw new RuntimeException("Failed to load expirationTime configuration", e);
        }
    }

}
