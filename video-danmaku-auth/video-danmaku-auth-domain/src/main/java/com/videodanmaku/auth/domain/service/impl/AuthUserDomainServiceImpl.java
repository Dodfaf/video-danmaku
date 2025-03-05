package com.videodanmaku.auth.domain.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.google.common.base.Preconditions;
import com.videodanmaku.auth.common.enums.AuthUserStatusEnum;
import com.videodanmaku.auth.common.enums.IsDeletedFlagEnum;
import com.videodanmaku.auth.domain.convert.AuthUserBOConverter;
import com.videodanmaku.auth.domain.entity.AuthUserBO;
import com.videodanmaku.auth.domain.redis.RedisUtil;
import com.videodanmaku.auth.domain.service.AuthUserDomainService;
import com.videodanmaku.auth.infra.basic.entity.AuthUser;
import com.videodanmaku.auth.infra.basic.service.AuthUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AuthUserDomainServiceImpl implements AuthUserDomainService {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private AuthUserService authUserService;
    @Resource
    private RedisUtil redisUtil;
    private static final String LOGIN_PREFIX = "loginCode";

    private static final String LOGIN_FAIL_PREFIX = "login:fail:";
    private final int MAX_FAIL_TIMES = 5; //账号限制连续登陆失败次数
    private final int LOCK_TIME = 1; // 账号锁定时间30分钟

    /**
     * 注册账号
     */

    @Override
    public Boolean register(AuthUserBO authUserBO) {
        if (authUserService.queryByUsername(authUserBO.getUserName()) != null){
            return false;
        }

        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        String hashpwd = BCrypt.hashpw(authUser.getPassword(), BCrypt.gensalt(12));
        if (StringUtils.isBlank(authUser.getAvatar())){ //设置默认头像地址
            authUser.setAvatar("http://1234");
        }
        if (StringUtils.isBlank(authUser.getNickName())){ //设置默认昵称
            authUser.setNickName("热心市民");
        }
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        authUser.setPassword(hashpwd);
        authUser.setCreateTime(new Date());
        authUser.setUpdateTime(new Date());
        return authUserService.insert(authUser) > 0;
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);

        return authUserService.update(authUser) > 0;
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        AuthUser user = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        user.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        return authUserService.update(user) > 0;
    }

    @Override
    public SaTokenInfo doLogin(AuthUserBO authUserBO) {

        String username = authUserBO.getUserName();
        String password = authUserBO.getPassword();

        // 1. 限制失败次数（防止暴力破解）
        if (!checkLoginAttempts(username)) {
            throw new RuntimeException("该账号已被锁定，请30分钟后再试");
        }

        // 2. 查询用户（可使用 Redis 缓存）
        AuthUser user = getUserFromCache(username);

        if (user == null) {
//            recordFailedLogin(username); // 记录失败
            throw new RuntimeException("用户名或密码错误");
        }

        // 3. 密码校验
        if (!BCrypt.checkpw(password, user.getPassword())) {
            recordFailedLogin(username); // 记录失败
            throw new RuntimeException("用户名或密码错误");
        }

        // 4. 登录成功，清除失败记录
        clearLoginFailCount(username);

        // 5. 记录登录状态，并返回 Token
        StpUtil.login(user.getId(), 60 * 60 * 24 ); //token一天有效
        return StpUtil.getTokenInfo();
    }
    public boolean checkLoginAttempts(String username) {
        String redisKey = redisUtil.buildKey(LOGIN_FAIL_PREFIX, username) ;
        Integer failCount = (Integer) redisTemplate.opsForValue().get(redisKey);
        if (failCount != null){
            System.out.println(failCount);
        }
        return failCount == null || failCount < MAX_FAIL_TIMES;
    }

    //记录登录失败次数
    public void recordFailedLogin(String username) {
        String redisKey = redisUtil.buildKey(LOGIN_FAIL_PREFIX, username);
        Long count = redisUtil.increment(redisKey);
        if (count == 1) {
            redisUtil.expire(redisKey, LOCK_TIME, TimeUnit.MINUTES);
        }
    }

    public void clearLoginFailCount(String username) {
        redisUtil.del(LOGIN_FAIL_PREFIX + username);
    }

    @Override
    public AuthUserBO getUserInfo(AuthUserBO authUserBO) {
//        AuthUser user = authUserService.queryByUsername(authUserBO.getUserName());
//        user.setPassword("");
        AuthUser user1 = authUserService.queryById(authUserBO.getId());
        user1.setPassword("");

        return AuthUserBOConverter.INSTANCE.convertEntityToBO(user1);
    }



    @Override
    public List<AuthUserBO> listUserInfoByIds(List<String> ids) {
        return null;
    }

    private AuthUser getUserFromCache(String username) {
        String cacheKey = "user:info:" + username;
        AuthUser user = (AuthUser) redisTemplate.opsForValue().get(cacheKey);
        if (user == null) {
            user = authUserService.queryByUsername(username);
            if (user != null) {
                redisTemplate.opsForValue().set(cacheKey, user, 30, TimeUnit.MINUTES);
            }
        }
        return user;
    }


}
