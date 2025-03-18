package com.videodanmaku.video.application.controller;


import com.videodanmaku.video.domain.entity.DanmakuRepository;
import com.videodanmaku.video.infra.basic.entity.Danmaku;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/danmaku")
public class DanmakuController {
    private final DanmakuRepository danmakuRepository;

    public DanmakuController(DanmakuRepository danmakuRepository) {
        this.danmakuRepository = danmakuRepository;
    }

    @GetMapping("/history")
    public Map<String, Object> getDanmakuHistory(@RequestParam Integer videoId) { // 修改为 Integer
        System.out.println(videoId);
        List<Danmaku> danmakuList = danmakuRepository.findByVideoIdAndIsDeleted(videoId, 0);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("code", 200);
        response.put("message", "成功");
        response.put("data", danmakuList);
        return response;
    }
}