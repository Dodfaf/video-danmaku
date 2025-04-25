package com.videodanmaku.video.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * 视频收藏记录业务对象
 */
@Data
public class VideoCollectionRecordBO {
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
    
    // 新增视频信息字段
    private String videoTitle;
    private String coverUrl;
    private Integer duration;
}