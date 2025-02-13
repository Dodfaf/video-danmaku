package com.videodanmaku.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * auth服务启动器
 *
 * @author:  x
 * @date:  2025/2/12 22:07
 */

@SpringBootApplication
@ComponentScan("com.videodanmaku")
@MapperScan("com.videodanmaku.**.mapper")

public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class);
    }
}
