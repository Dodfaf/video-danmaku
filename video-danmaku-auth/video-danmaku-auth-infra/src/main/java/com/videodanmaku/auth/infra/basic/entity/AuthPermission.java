package com.videodanmaku.auth.infra.basic.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 权限表(AuthPermission)实体类
 *
 * @author makejava
 * @since 2025-02-20 21:47:36
 */
public class AuthPermission implements Serializable {
    private static final long serialVersionUID = -48241072978209921L;
/**
     * 主键
     */
    private Long id;
/**
     * 权限名称
     */
    private String name;
/**
     * 父id
     */
    private Long parentId;
/**
     * 权限类型
     */
    private Integer type;
/**
     * 菜单路由
     */
    private String menuUrl;
/**
     * 菜单状态 0启用 1禁用
     */
    private Integer status;
/**
     * 图标
     */
    private String icon;
/**
     * 权限唯一标识
     */
    private String permissionKey;
/**
     * 展示状态 0启用 1禁用
     */
    private Integer show;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey;
    }

    public Integer getShow() {
        return show;
    }

    public void setShow(Integer show) {
        this.show = show;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}

