package com.videodanmaku.video.application.dto;

import lombok.Data;

import java.util.Date;

/**
 * 视频收藏记录DTO
 */
@Data
public class VideoCollectionRecordDTO {
    /**
     * 收藏记录ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 收藏夹ID
     */
    private Long folderId;

    /**
     * 视频ID
     */
    private Long videoId;

    /**
     * 收藏时间
     */
    private Date createTime;
}