package com.smartreal.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.smartreal.api.mapper")
public class ApiResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiResourceApplication.class, args);
    }

}
