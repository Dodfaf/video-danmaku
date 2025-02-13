package com.videodanmaku.auth.infra.basic.service;

import com.videodanmaku.auth.infra.basic.entity.AuthUser;


/**
 * (AuthUser)表服务接口
 *
 * @author makejava
 * @since 2025-02-12 23:11:44
 */
public interface AuthUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthUser queryById(Long id);


    /**
     * 新增数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    AuthUser insert(AuthUser authUser);

    /**
     * 修改数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    AuthUser update(AuthUser authUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
