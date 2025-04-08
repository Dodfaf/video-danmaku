package com.videodanmaku.comment.server.config;

import com.videodanmaku.comment.server.sensitive.WordContext;
import com.videodanmaku.comment.server.sensitive.WordFilter;
import com.videodanmaku.comment.server.service.SensitiveWordsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SensitiveConfig {

    @Bean
    public WordContext wordContext(SensitiveWordsService service) {
        return new WordContext(true, service);
    }

    @Bean
    public WordFilter wordFilter(WordContext wordContext) {
        return new WordFilter(wordContext);
    }

}
