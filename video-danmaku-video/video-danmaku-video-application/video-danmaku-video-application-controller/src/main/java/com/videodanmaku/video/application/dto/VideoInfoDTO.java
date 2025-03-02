package com.videodanmaku.video.application.dto;

import com.videodanmaku.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频信息表(VideoInfo)实体类
 *
 * @author makejava
 * @since 2025-02-22 21:08:26
 */
@Data
public class VideoInfoDTO extends PageInfo implements Serializable {
    private static final long serialVersionUID = -66648278681006828L;
/**
     * 主键
     */
    private Integer id;
/**
     * 标题
     */
    private String videoTitle;
/**
     * up主id
     */
    private Integer upId;
/**
     * 视频地址
     */
    private String videoUrl;
/**
     * 视频状态 0待审核1审核通过2违规
     */
    private Integer status;
/**
     * 封面地址
     */
    private String coverUrl;
/**
     * 点赞数
     */
    private Integer likes;
/**
     * 收藏数
     */
    private Integer favorites;
/**
     * 创建人
     */
    private String createBy;
/**
     * 创建时间
     */
    private Date createTime;

/**
     * 更新时间
     */
    private Date updateTime;
/**
     * 是否删除
     */
    private Integer isDeleted;




}

