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
}

