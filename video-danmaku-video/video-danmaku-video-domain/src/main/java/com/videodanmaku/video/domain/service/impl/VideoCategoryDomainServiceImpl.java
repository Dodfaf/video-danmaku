package com.videodanmaku.video.domain.service.impl;

import com.videodanmaku.video.domain.service.VideoCategoryDomainService;
import com.videodanmaku.video.infra.basic.entity.VideoCategory;
import com.videodanmaku.video.infra.basic.service.VideoCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VideoCategoryDomainServiceImpl implements VideoCategoryDomainService {

    @Resource
    private VideoCategoryService videoCategoryService;

    @Override
    public List<VideoCategory> getCategorys() {
        return videoCategoryService.getCategorys();
    }
}
