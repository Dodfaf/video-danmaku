package com.videodanmaku.video.domain.service;

import com.videodanmaku.video.domain.entity.VideoLikedBO;

/**
 * 视频点赞服务接口
 */
public interface VideoLikedDomainService {
    
    /**
     * 点赞或取消点赞
     * @param videoId 视频ID
     * @param userId 用户ID
     * @return 点赞状态 true-已点赞 false-已取消
     */
    Boolean likeVideo(Long videoId, Long userId);
    
    /**
     * 获取用户对视频的点赞状态
     * @param videoId 视频ID
     * @param userId 用户ID
     * @return 是否已点赞
     */
    Boolean getLikeStatus(Long videoId, Long userId);
    
    /**
     * 获取视频点赞数
     * @param videoId 视频ID
     * @return 点赞数
     */
    Long getLikeCount(Long videoId);
    
    /**
     * 同步Redis点赞数据到数据库
     * 定时任务调用
     */
    void syncLikeDataToDb();
}