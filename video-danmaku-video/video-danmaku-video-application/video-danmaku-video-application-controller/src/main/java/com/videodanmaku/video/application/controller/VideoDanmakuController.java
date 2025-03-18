package com.videodanmaku.video.application.controller;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.videodanmaku.common.entity.Result;
import com.videodanmaku.video.application.convert.VideoInfoDTOConverter;
import com.videodanmaku.video.application.dto.VideoInfoDTO;
import com.videodanmaku.video.domain.entity.VideoInfoBO;
import com.videodanmaku.video.domain.service.VideoDanmakuDomainService;
import com.videodanmaku.video.domain.service.VideoInfoDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("danmaku")
@Slf4j
public class VideoDanmakuController {

    @Resource
    private VideoDanmakuDomainService videoDanmakuDomainService;



}
