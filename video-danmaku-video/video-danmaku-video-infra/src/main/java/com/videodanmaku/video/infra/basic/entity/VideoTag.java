package com.videodanmaku.video.infra.basic.entity;

import java.io.Serializable;

/**
 * (VideoTag)实体类
 *
 * @author makejava
 * @since 2025-03-02 16:58:17
 */
public class VideoTag implements Serializable {
    private static final long serialVersionUID = 255112490108961033L;

    private Integer id;
/**
     * 标签名称
     */
    private String name;
/**
     * 是否预设 1是 0否
     */
    private Integer isPreset;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsPreset() {
        return isPreset;
    }

    public void setIsPreset(Integer isPreset) {
        this.isPreset = isPreset;
    }

}

