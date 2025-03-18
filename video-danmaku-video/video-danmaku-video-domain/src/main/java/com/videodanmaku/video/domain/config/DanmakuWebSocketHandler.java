package com.videodanmaku.video.domain.config;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.videodanmaku.video.domain.entity.DanmakuRepository;
import com.videodanmaku.video.infra.basic.entity.Danmaku;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DanmakuWebSocketHandler extends TextWebSocketHandler {
    private final Map<Integer, Map<String, WebSocketSession>> sessions = new ConcurrentHashMap<>(); // ConcurrentHashMap保证线程安全
    private final DanmakuRepository danmakuRepository;
    private final ObjectMapper objectMapper;

    public DanmakuWebSocketHandler(DanmakuRepository danmakuRepository, ObjectMapper objectMapper) {
        this.danmakuRepository = danmakuRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Integer videoId = getVideoId(session);
        sessions.computeIfAbsent(videoId, k -> new ConcurrentHashMap<>()).put(session.getId(), session);
        System.out.println("WebSocket 连接建立: " + videoId + " - " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Integer videoId = getVideoId(session);
        Danmaku danmaku = objectMapper.readValue(message.getPayload(), Danmaku.class);
        danmaku.setVideoId(videoId);
        danmaku.setCreateTime(LocalDateTime.now());
        danmaku.setIsDeleted(0);

        danmakuRepository.save(danmaku);

        String json = objectMapper.writeValueAsString(danmaku);
        Map<String, WebSocketSession> videoSessions = sessions.getOrDefault(videoId, new ConcurrentHashMap<>());
        for (WebSocketSession s : videoSessions.values()) {
            if (s.isOpen()) {
                s.sendMessage(new TextMessage(json));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Integer videoId = getVideoId(session);
        Map<String, WebSocketSession> videoSessions = sessions.get(videoId);
        if (videoSessions != null) {
            videoSessions.remove(session.getId());
            if (videoSessions.isEmpty()) {
                sessions.remove(videoId);
            }
        }
        System.out.println("WebSocket 连接关闭: " + videoId + " - " + session.getId());
    }

    private Integer getVideoId(WebSocketSession session) {
        String path = session.getUri().getPath();
        String videoIdStr = path.substring(path.lastIndexOf('/') + 1);
        return Integer.valueOf(videoIdStr); // 转换为 Integer
    }
}