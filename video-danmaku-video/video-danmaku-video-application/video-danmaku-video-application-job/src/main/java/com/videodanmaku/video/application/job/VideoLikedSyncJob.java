package com.videodanmaku.video.application.job;

import com.videodanmaku.video.domain.service.VideoLikedDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 视频点赞数据同步定时任务
 */
@Component
@Slf4j
public class VideoLikedSyncJob {

    @Resource
    private VideoLikedDomainService videoLikedDomainService;

    /**
     * 每天凌晨2点执行同步
     */
    @Scheduled(cron = "*/10 * * * * ?")
    public void syncLikeDataToDb() {
        log.info("开始执行视频点赞数据同步任务");
        try {
            videoLikedDomainService.syncLikeDataToDb();
            log.info("视频点赞数据同步任务执行完成");
        } catch (Exception e) {
            log.error("视频点赞数据同步任务执行异常: {}", e.getMessage(), e);
        }
    }
}