package com.happy_hao.pdsds.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "config.mail")
public class MailConfig {

    private Integer overtime;

}
