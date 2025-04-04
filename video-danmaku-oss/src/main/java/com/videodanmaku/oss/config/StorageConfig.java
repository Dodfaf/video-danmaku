package com.videodanmaku.oss.config;


import com.videodanmaku.oss.adapter.AliStorageAdapter;
import com.videodanmaku.oss.adapter.MinioStorageAdapter;
import com.videodanmaku.oss.adapter.StorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class StorageConfig {

    @Value("${storage.service.type}")
    private String storageType;
    @Bean
    @RefreshScope
    public StorageAdapter storageService(){
        if ("minio".equals(storageType)){
            return new MinioStorageAdapter();
        } else if ("aliyun".equals(storageType)) {
            return new AliStorageAdapter();
        } else {
            throw new IllegalArgumentException("未找到对应的对象存储服务");
        }
    }
}
