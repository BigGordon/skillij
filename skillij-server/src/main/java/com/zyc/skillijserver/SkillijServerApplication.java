package com.zyc.skillijserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EntityScan(basePackages={"com.zyc.skillijcommon"})//扫描common模块中的实体类
@EnableWebSocket
public class SkillijServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkillijServerApplication.class, args);
    }
}
