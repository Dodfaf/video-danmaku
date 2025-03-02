package com.videodanmaku.video.infra.basic.entity;

import java.io.Serializable;

/**
 * (VideoTagMapping)实体类
 *
 * @author makejava
 * @since 2025-03-02 16:58:52
 */
public class VideoTagMapping implements Serializable {
    private static final long serialVersionUID = 410372944622698400L;

    private Integer id;
/**
     * 视频id
     */
    private Integer videoId;
/**
     * 标签id
     */
    private Integer tagId;
/**
     * 逻辑删除标志
     */
    private Integer isDeleted;


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

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

}

