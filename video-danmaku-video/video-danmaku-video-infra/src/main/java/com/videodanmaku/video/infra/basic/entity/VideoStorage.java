package com.videodanmaku.video.infra.basic.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 视频存储表(VideoStorage)实体类
 *
 * @author makejava
 * @since 2025-02-26 10:32:03
 */
public class VideoStorage implements Serializable {
    private static final long serialVersionUID = -91676036777326407L;
/**
     * 主键ID
     */
    private Integer id;
/**
     * 关联视频ID
     */
    private Integer videoId;
/**
     * 清晰度
     */
    private String resolution;
/**
     * 视频存储地址
     */
    private String videoUrl;
/**
     * 视频格式
     */
    private String format;
/**
     * 创建时间
     */
    private Date createTime;
/**
     * 更新时间
     */
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}

