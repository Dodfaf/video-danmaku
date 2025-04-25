package com.videodanmaku.auth.domain.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.videodanmaku.auth.domain.entity.AuthUserBO;

import java.util.List;

public interface AuthUserDomainService {

    /**
     * 注册
     */
    Boolean register(AuthUserBO authUserBO);

    /**
     * 更新用户信息
     */
    Boolean update(AuthUserBO authUserBO);

    /**
     * 更新用户信息
     */
    Boolean delete(AuthUserBO authUserBO);

    SaTokenInfo doLogin(AuthUserBO authUserBO);

    AuthUserBO getUserInfo(AuthUserBO authUserBO);

    List<AuthUserBO> listUserInfoByIds(List<String> ids);

    /**
     * 获取用户列表
     * 
     * @param authUserBO 查询条件
     * @return 用户信息列表
     */
    List<AuthUserBO> getUserList(AuthUserBO authUserBO);


    boolean isAdmin(Integer id);
    /**
     * 获取用户列表
     * 
     * @param authUserBO 查询条件
     * @param pageNo 页码
     * @param pageSize 每页大小
     * @return 分页的用户信息
     */
//    Page<AuthUserBO> getUserList(AuthUserBO authUserBO, Integer pageNo, Integer pageSize);
}

