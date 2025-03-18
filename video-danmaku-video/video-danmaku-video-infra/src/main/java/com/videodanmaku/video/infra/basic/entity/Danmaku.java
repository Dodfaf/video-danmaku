package com.videodanmaku.video.infra.basic.entity;


/**
 * 弹幕表(Danmaku)实体类
 *
 * @author makejava
 * @since 2025-03-07 19:39:29
 */

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "danmaku")
public class Danmaku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "video_id")
    private Integer videoId;

    private String content;

    @Column(name = "send_time")
    private BigDecimal sendTime;

    private String color;

    private String position;

    private Byte type;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "is_deleted")
    private Integer isDeleted;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getVideoId() { return videoId; }
    public void setVideoId(Integer videoId) { this.videoId = videoId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public BigDecimal getSendTime() { return sendTime; }
    public void setSendTime(BigDecimal sendTime) { this.sendTime = sendTime; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public Byte getType() { return type; }
    public void setType(Byte type) { this.type = type; }
    public String getCreateBy() { return createBy; }
    public void setCreateBy(String createBy) { this.createBy = createBy; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public Integer getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Integer isDeleted) { this.isDeleted = isDeleted; }
}
