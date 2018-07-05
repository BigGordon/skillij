package com.zyc.skillijserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages={"com.zyc.skillijcommon"})//扫描common模块中的实体类
public class SkillijServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkillijServerApplication.class, args);
    }
}
