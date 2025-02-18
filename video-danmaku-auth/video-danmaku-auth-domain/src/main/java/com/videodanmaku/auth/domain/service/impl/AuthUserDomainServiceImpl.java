package com.videodanmaku.auth.domain.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.alibaba.druid.sql.visitor.functions.Now;
import com.videodanmaku.auth.common.enums.AuthUserStatusEnum;
import com.videodanmaku.auth.common.enums.IsDeletedFlagEnum;
import com.videodanmaku.auth.domain.convert.AuthUserBOConverter;
import com.videodanmaku.auth.domain.entity.AuthUserBO;
import com.videodanmaku.auth.domain.service.AuthUserDomainService;
import com.videodanmaku.auth.infra.basic.entity.AuthUser;
import com.videodanmaku.auth.infra.basic.mapper.AuthUserDao;
import com.videodanmaku.auth.infra.basic.service.AuthUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import sun.util.calendar.BaseCalendar;

import javax.annotation.Resource;
import javax.rmi.CORBA.Util;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private AuthUserService authUserService;

    @Override
    public Boolean register(AuthUserBO authUserBO) {

        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        authUser.setCreateTime(new Date());
        System.out.println(new Date());
        authUser.setUpdateTime(new Date());
        return !StringUtils.isBlank(authUserService.insert(authUser).getUserName());
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        return null;
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        return null;
    }

    @Override
    public SaTokenInfo doLogin(String validCode) {
        return null;
    }

    @Override
    public AuthUserBO getUserInfo(AuthUserBO authUserBO) {
        return null;
    }

    @Override
    public List<AuthUserBO> listUserInfoByIds(List<String> ids) {
        return null;
    }
}
