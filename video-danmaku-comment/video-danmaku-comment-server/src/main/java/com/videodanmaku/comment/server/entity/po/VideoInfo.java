package com.videodanmaku.comment.server.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 动态信息
 * </p>

 */
@Getter
@Setter
@TableName("video_info")
public class VideoInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 动态ID
     */
//    @TableId(value = "id", type = IdType.AUTO)
//    private Long id;


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否删除
     */
    private Integer isDeleted;

    /**
     * 视频时长
     */
    private Integer duration;

    /**
     * 视频简介
     */
    private String description;
    /**
     * 播放量
     *
     */
    private Integer views;

    private Integer replyCount;
}
