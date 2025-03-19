package com.videodanmaku.auth.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息表(AuthUser)实体类
 *
 * @author makejava
 * @since 2025-02-18 11:38:06
 */
@Data
public class AuthUserDTO implements Serializable {
    private static final long serialVersionUID = -39206367286101534L;
/**
     * 主键
     */
    private Integer id;
/**
     * 账户名
     */
    private String userName;
/**
     * 状态0启用 1禁用
     */
    private Integer status;
/**
     * 头像
     */
    private String avatar;
/**
     * 昵称
     */
    private String nickName;
/**
     * 邮箱
     */
    private String email;
/**
     * 手机号
     */
    private String phone;
/**
     * 密码
     */
    private String password;
/**
     * 性别 0男 1女
     */
    private Integer sex;
    /**
     * 简介
     */
    private String introduce;




}

