package com.videodanmaku.video.domain.service.impl;

import com.videodanmaku.video.domain.convert.VideoInfoConverter;
import com.videodanmaku.video.domain.entity.VideoInfoBO;
import com.videodanmaku.video.domain.service.VideoInfoDomainService;
import com.videodanmaku.video.infra.basic.entity.VideoInfo;
import com.videodanmaku.video.infra.basic.service.VideoInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VideoInfoDomainServiceImpl implements VideoInfoDomainService {
    @Resource
    private VideoInfoService videoInfoService;
    @Override
    public VideoInfoBO getVideoInfoById(VideoInfoBO videoInfoBO) {
        VideoInfo videoInfo = videoInfoService.queryById(videoInfoBO.getId());

        VideoInfoBO bo = VideoInfoConverter.INSTANCE.convertEntityToBO(videoInfo);
        return bo;
    }

    @Override
    public boolean update(VideoInfoBO videoInfoBO) {
        VideoInfo videoInfo = VideoInfoConverter.INSTANCE.convertBoToEntity(videoInfoBO);
        int count = videoInfoService.update(videoInfo);
        return  count > 0;
    }

    @Override
    public Page<VideoInfoBO> getUserVideoList(VideoInfoBO videoInfoBO, int page, int size) {
        VideoInfo videoInfo = VideoInfoConverter.INSTANCE.convertBoToEntity(videoInfoBO);
        // 假设前端传入页码和每页大小
        PageRequest  pageRequest = PageRequest.of(page-1,size, Sort.by(Sort.Direction.DESC, "createdTime"));
        Page<VideoInfo> videoInfoPage = videoInfoService.queryByPage(videoInfo, pageRequest);

        return videoInfoPage.map(VideoInfoConverter.INSTANCE::convertEntityToBO);
    }

    @Override
    public boolean uploadVideo(VideoInfoBO videoInfoBO, MultipartFile file) {

        VideoInfo videoInfo = VideoInfoConverter.INSTANCE.convertBoToEntity(videoInfoBO);
        videoInfoService.insert(videoInfo);
        System.out.println("插入数据返回id: "+videoInfo.getId());
        return true;
    }

    @Override
    public List<VideoInfo> getHomePageVideoList() {
        return videoInfoService.getRandom();
    }
}
