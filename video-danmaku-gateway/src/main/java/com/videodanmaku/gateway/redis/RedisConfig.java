package com.videodanmaku.gateway.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * redis的config处理，自定义redisTemplate
 *
 * @author:  Dodfaf
 * @date:  2025/2/18 10:13
 */

@Configuration
public class RedisConfig {

    /**
     * 配置 RedisTemplate，设置序列化方式，解决存储对象时的序列化问题。
     *
     * @param redisConnectionFactory Redis 连接工厂，Spring Boot 自动注入
     * @return 配置好的 RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        // 创建字符串序列化器，主要用于 key 和 hash 的 key
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();

        // 设置 Redis 连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 设置 key 和 hash key 的序列化方式为字符串序列化
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);

        // 设置 value 和 hash value 的序列化方式为 JSON 序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer());

        return redisTemplate;
    }

    /**
     * 配置 Jackson2JsonRedisSerializer，用于 Redis 值（value）的序列化
     *
     * @return Jackson2JsonRedisSerializer 序列化器
     */
    private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
        // 创建 JSON 序列化器
        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        // 配置 Jackson ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // 设置所有字段可见性
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 允许反序列化未知属性，不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 启用默认类型，存储类信息，确保反序列化时不丢失类型
        objectMapper.activateDefaultTyping(
                objectMapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );

        // 设置 ObjectMapper 到序列化器
        jsonRedisSerializer.setObjectMapper(objectMapper);

        return jsonRedisSerializer;
    }
}
