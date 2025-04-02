package com.videodanmaku.circle.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 评论微服务启动类
 *
 */
@SpringBootApplication
@ComponentScan("com.videodanmaku")
@MapperScan("com.videodanmaku.**.dao")
@EnableFeignClients(basePackages = "com.videodanmaku")
public class CommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentApplication.class);
    }

}
