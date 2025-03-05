package com.videodanmaku.video.infra.basic.entity;

import java.io.Serializable;

/**
 * (VideoCategoryMapping)实体类
 *
 * @author makejava
 * @since 2025-03-03 13:42:07
 */
public class VideoCategoryMapping implements Serializable {
    private static final long serialVersionUID = -92141269549567108L;

    private Integer id;
/**
     * 视频id
     */
    private Integer videoId;
/**
     * 分类id
     */
    private Integer categoryId;

    private Integer idDeleted;


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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getIdDeleted() {
        return idDeleted;
    }

    public void setIdDeleted(Integer idDeleted) {
        this.idDeleted = idDeleted;
    }

}

