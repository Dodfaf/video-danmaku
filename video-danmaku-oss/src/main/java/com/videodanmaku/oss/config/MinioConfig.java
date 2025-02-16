package com.videodanmaku.oss.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio配置
 *
 * @author:  x
 * @date:  2025/2/15 10:45
 */


@Configuration
public class MinioConfig {

    /**
     * minioUrl
     */
    @Value("${minio.url}")
    private String url;

    /**
     * minio账户
     */
    @Value("${minio.accessKey}")
    private String accessKey;

    /**
     * minio密码
     */
    @Value("${minio.secretKey}")
    private String secretKey;

    /**
     * 构造minioClient
     */
    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder().endpoint(url).credentials(accessKey, secretKey).build();
    }

}
