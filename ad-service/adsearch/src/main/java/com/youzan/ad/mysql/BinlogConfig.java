package com.youzan.ad.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author TCP
 * @create 2019/4/17 8:54
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "ad.mysql")
@Configuration
public class BinlogConfig {
    @Value("${host}")
    private String host;

    @Value("${port}")
    private Integer port;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;
}



