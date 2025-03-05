package com.videodanmaku.video.infra.basic.entity;

import java.io.Serializable;

/**
 * (VideoCategory)实体类
 *
 * @author makejava
 * @since 2025-03-03 13:41:40
 */
public class VideoCategory implements Serializable {
    private static final long serialVersionUID = -45649753381607527L;
/**
     * 主键
     */
    private Integer id;
/**
     * 分类名称
     */
    private String categoryName;
/**
     * 删除标记
     */
    private Integer isDeteled;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getIsDeteled() {
        return isDeteled;
    }

    public void setIsDeteled(Integer isDeteled) {
        this.isDeteled = isDeteled;
    }

}

