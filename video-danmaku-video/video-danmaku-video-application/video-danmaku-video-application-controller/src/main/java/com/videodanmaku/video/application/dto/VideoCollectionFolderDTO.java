package com.videodanmaku.video.application.dto;

import lombok.Data;

import java.util.Date;

/**
 * 视频收藏夹DTO
 */
@Data
public class VideoCollectionFolderDTO {
    /**
     * 收藏夹ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 收藏夹名称
     */
    private String folderName;

    /**
     * 收藏夹描述
     */
    private String description;

    /**
     * 收藏夹封面URL
     */
    private String coverUrl;

    /**
     * 是否公开：0-私密，1-公开
     */
    private Integer isPublic;

    /**
     * 收藏视频数量
     */
    private Integer videoCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}