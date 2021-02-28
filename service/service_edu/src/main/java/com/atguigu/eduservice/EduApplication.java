package com.atguigu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Dec
 * @since 2020-12-31
 */



@SpringBootApplication

// nacos注册
@EnableDiscoveryClient

// 在消费者端（相对于生产者）开启feign功能
@EnableFeignClients

// 配置包扫描规则，这样让common工程(已添加依赖)的swagger也可以被扫描
@ComponentScan(basePackages = "com.atguigu")

public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
