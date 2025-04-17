package com.videodanmaku.video.infra.basic.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频收藏夹(VideoCollectionFolder)实体类
 *
 * @author videodanmaku
 * @since 2025-05-20
 */
@Data
public class VideoCollectionFolder implements Serializable {
    private static final long serialVersionUID = 1L;

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

    /**
     * 是否删除：0-未删除，1-已删除
     */
    private Integer isDeleted;
}