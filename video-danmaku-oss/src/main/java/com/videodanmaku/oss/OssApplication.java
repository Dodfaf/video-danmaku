package com.videodanmaku.oss;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * oss服务启动器
 *
 * @author:  x
 * @date:  2025/2/13 20:59
 */

@SpringBootApplication
@ComponentScan("com.videodanmaku")
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class);
    }
}
