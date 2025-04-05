package com.videodanmaku.gateway.filter;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoginFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String url = request.getURI().getPath();
        log.info("LoginFilter.filter.url:{}", url);

        if (url.equals("/user/doLogin")) {
            return chain.filter(exchange);
        }

        // 从请求头中手动取出 token
        String token = request.getHeaders().getFirst("satoken");

        if (token == null || token.isEmpty()) {
            log.warn("未提供 token");
            return chain.filter(exchange);
        }

        // WebFlux 环境下要用手动方式设置 token（否则上下文 ThreadLocal 无法生效）
        return Mono.defer(() -> {
            // 手动设置上下文（关键！WebFlux 下必须）
            StpUtil.getTokenSessionByToken(token); // 初始化上下文，否则 getLoginId 会失败
            Object loginId;
            try {
                loginId = StpUtil.getLoginIdByToken(token);
            } catch (Exception e) {
                log.error("非法 token: {}", token);
                return Mono.error(e);
            }

            log.info("LoginFilter: token={}, loginId={}", token, loginId);

            // 构造新请求并透传 loginId
            ServerHttpRequest newRequest = request.mutate()
                    .header("loginId", loginId.toString())
                    .build();

            return chain.filter(exchange.mutate().request(newRequest).build());
        });
    }

}
