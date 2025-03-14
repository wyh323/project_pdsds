package com.happy_hao.pdsds.config;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {

    public Integer getOvertime() {
        try (InputStream input = MailConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            return Integer.valueOf(properties.getProperty("mail.overtime"));

        } catch (Exception e) {
            throw new RuntimeException("Failed to load overtime configuration", e);
        }
    }

}
