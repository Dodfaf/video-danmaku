package com.videodanmaku.video.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.videodanmaku.common.entity.Result;
import com.videodanmaku.video.application.dto.VideoLikedDTO;
import com.videodanmaku.video.application.util.LoginUtil;
import com.videodanmaku.video.domain.service.VideoLikedDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 视频点赞控制器
 */
@RestController
@RequestMapping("video/like")
@Slf4j
public class VideoLikedController {

    @Resource
    private VideoLikedDomainService videoLikedDomainService;

    /**
     * 点赞或取消点赞
     */
    @PostMapping("/toggle")
    public Result<Boolean> toggleLike(@RequestBody VideoLikedDTO videoLikedDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("VideoLikedController.toggleLike.dto:{}", JSON.toJSONString(videoLikedDTO));
            }
            Preconditions.checkNotNull(videoLikedDTO.getVideoId(), "视频ID不能为空！");
            
            // 从登录信息获取当前用户ID
            Integer currentUserId = Integer.valueOf(LoginUtil.getLoginId());
            Preconditions.checkNotNull(currentUserId, "用户未登录！");
            
            Boolean likeStatus = videoLikedDomainService.likeVideo(
                    videoLikedDTO.getVideoId(), 
                    currentUserId.longValue()
            );
            
            return Result.ok(likeStatus);
        } catch (Exception e) {
            log.error("VideoLikedController.toggleLike.error:{}", e.getMessage(), e);
            return Result.fail("操作点赞失败");
        }
    }

    /**
     * 获取点赞状态
     */
    @PostMapping("/status")
    public Result<Boolean> getLikeStatus(@RequestBody VideoLikedDTO videoLikedDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("VideoLikedController.getLikeStatus.dto:{}", JSON.toJSONString(videoLikedDTO));
            }
            Preconditions.checkNotNull(videoLikedDTO.getVideoId(), "视频ID不能为空！");
            Preconditions.checkNotNull(videoLikedDTO.getLikeUserId(), "用户ID不能为空！");
            
            Boolean likeStatus = videoLikedDomainService.getLikeStatus(
                    videoLikedDTO.getVideoId(), 
                    videoLikedDTO.getLikeUserId()
            );
            
            return Result.ok(likeStatus);
        } catch (Exception e) {
            log.error("VideoLikedController.getLikeStatus.error:{}", e.getMessage(), e);
            return Result.fail("获取点赞状态失败");
        }
    }

    /**
     * 获取视频点赞数
     */
    @PostMapping("/count")
    public Result<Long> getLikeCount(@RequestBody VideoLikedDTO videoLikedDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("VideoLikedController.getLikeCount.dto:{}", JSON.toJSONString(videoLikedDTO));
            }
            Preconditions.checkNotNull(videoLikedDTO.getVideoId(), "视频ID不能为空！");
            
            Long likeCount = videoLikedDomainService.getLikeCount(videoLikedDTO.getVideoId());
            
            return Result.ok(likeCount);
        } catch (Exception e) {
            log.error("VideoLikedController.getLikeCount.error:{}", e.getMessage(), e);
            return Result.fail("获取点赞数失败");
        }
    }
}