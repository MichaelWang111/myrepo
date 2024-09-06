package com.jackson0714.passjava.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wukong
 */
@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.jackson0714.passjava.member.api.feign")
@SpringBootApplication(scanBasePackages = {"com.jackson0714.passjava"})
public class PassjavaAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassjavaAuthApplication.class, args);
    }

}
