package com.videodanmaku.gateway.exception;

import cn.dev33.satoken.exception.SaTokenException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.videodanmaku.gateway.eneity.Result;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        ServerHttpRequest request = serverWebExchange.getRequest();

        ServerHttpResponse response =serverWebExchange.getResponse();


        Integer code = 200;
        String message = "";
        if (throwable instanceof SaTokenException) {
            code = 401;
            message = "用户无权限";
            throwable.printStackTrace();
        } else {
            code = 500;
            message = "系统繁忙";
            throwable.printStackTrace();
        }
        Result result = Result.fail(code, message);

        // 设置响应头
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.getHeaders().add("Access-Control-Allow-Origin", "http://localhost:5173");
        response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.getHeaders().add("Access-Control-Allow-Headers", "*");
        response.getHeaders().add("Access-Control-Expose-Headers", "satoken");
        response.getHeaders().add("Access-Control-Allow-Credentials", "true");



        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            byte[] bytes = null;
            try {
                bytes = objectMapper.writeValueAsBytes(result);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return dataBufferFactory.wrap(bytes);
        }));
    }
}
