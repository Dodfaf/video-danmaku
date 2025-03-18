package com.videodanmaku.video.domain.service;

import com.videodanmaku.video.domain.entity.VideoInfoBO;
import com.videodanmaku.video.infra.basic.entity.VideoInfo;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoInfoDomainService {


    VideoInfoBO getVideoInfoById(VideoInfoBO videoInfoBO);

    boolean update(VideoInfoBO videoInfoBO);

    Page<VideoInfoBO> getUserVideoList(VideoInfoBO videoInfoBO,int page, int size);

    boolean uploadVideo(VideoInfoBO videoInfoBO);

    List<VideoInfo> getHomePageVideoList();

    Page<VideoInfoBO> getNoStatusVideoList(VideoInfoBO videoInfoBO, Integer pageNo, Integer pageSize);
    
    /**
     * 根据视频标题模糊查询
     *
     * @param title 视频标题
     * @param pageNo 页码
     * @param pageSize 每页大小
     * @return 查询结果
     */
    Page<VideoInfoBO> searchVideoByTitle(String title, Integer pageNo, Integer pageSize);
}
