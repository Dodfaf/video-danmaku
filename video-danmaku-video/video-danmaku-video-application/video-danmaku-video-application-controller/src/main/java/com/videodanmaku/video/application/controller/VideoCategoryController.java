package com.videodanmaku.video.application.controller;

import com.videodanmaku.common.entity.Result;
import com.videodanmaku.video.domain.service.VideoCategoryDomainService;
import com.videodanmaku.video.infra.basic.entity.VideoCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("videoCategory")
@Slf4j
public class VideoCategoryController {

//    @Resource
//    private VideoCategoryDomainService videoCategoryDomainService;

    @Resource
    private VideoCategoryDomainService videoCategoryDomainService;

    @RequestMapping("/getAll")
    public Result<List<VideoCategory>> getAll(){

        return Result.ok(videoCategoryDomainService.getCategorys());
    }

}
