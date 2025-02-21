package com.videodanmaku.auth.infra.basic.service.impl;

import com.videodanmaku.auth.infra.basic.entity.AuthUser;
import com.videodanmaku.auth.infra.basic.mapper.AuthUserDao;
import com.videodanmaku.auth.infra.basic.service.AuthUserService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 用户信息表(AuthUser)表服务实现类
 *
 * @author makejava
 * @since 2025-02-18 11:38:06
 */
@Service("authUserService")
public class AuthUserServiceImpl implements AuthUserService {
    @Resource
    private AuthUserDao authUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthUser queryById(Integer id) {
        return this.authUserDao.queryById(id);
    }

    @Override
    public AuthUser queryByUsername(String username) {
        return this.authUserDao.queryByUsername(username);
    }

    /**
     * 分页查询
     *
     * @param authUser 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<AuthUser> queryByPage(AuthUser authUser, PageRequest pageRequest) {
        long total = this.authUserDao.count(authUser);
        return new PageImpl<>(this.authUserDao.queryAllByLimit(authUser, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insert(AuthUser authUser) {
        ;
        return this.authUserDao.insert(authUser);
    }

    /**
     * 修改数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    @Override
    public Integer update(AuthUser authUser) {
        return this.authUserDao.update(authUser);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.authUserDao.deleteById(id) > 0;
    }
}
