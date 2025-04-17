package com.videodanmaku.video.infra.basic.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频收藏记录(VideoCollectionRecord)实体类
 *
 * @author videodanmaku
 * @since 2025-05-20
 */
@Data
public class VideoCollectionRecord implements Serializable {
    private static final long serialVersionUID = 1L;

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

    /**
     * 是否删除：0-未删除，1-已删除
     */
    private Integer isDeleted;
}