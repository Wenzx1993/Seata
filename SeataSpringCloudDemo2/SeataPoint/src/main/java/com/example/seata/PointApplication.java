package com.example.seata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = {"com.example.seata.mapper"})
public class PointApplication {

    public static void main(String[] args) {
        SpringApplication.run(PointApplication.class);
    }
}
