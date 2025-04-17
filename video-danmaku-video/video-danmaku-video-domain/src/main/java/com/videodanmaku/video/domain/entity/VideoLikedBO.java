package com.videodanmaku.video.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * 视频点赞业务对象
 */
@Data
public class VideoLikedBO {
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 点赞用户ID
     */
    private Long likeUserId;
    
    /**
     * 点赞状态 1-已点赞 0-已取消
     */
    private Integer status;
    
    /**
     * 视频ID
     */
    private Long videoId;
    
    /**
     * 创建人
     */
    private String createBy;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新人
     */
    private String updateBy;
    
    /**
     * 更新时间
     */
    private Date updateTime;
    
    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Integer isDeleted;
}