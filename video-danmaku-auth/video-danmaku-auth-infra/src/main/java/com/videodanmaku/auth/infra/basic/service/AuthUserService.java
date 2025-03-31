package com.videodanmaku.auth.infra.basic.service;

import com.videodanmaku.auth.infra.basic.entity.AuthUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 用户信息表(AuthUser)表服务接口
 *
 * @author makejava
 * @since 2025-02-18 11:38:06
 */
public interface AuthUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthUser queryById(Integer id);


    AuthUser queryByUsername(String username);

    /**
     * 分页查询
     *
     * @param authUser 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<AuthUser> queryByPage(AuthUser authUser, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    Integer insert(AuthUser authUser);

    /**
     * 修改数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    Integer update(AuthUser authUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<AuthUser> listUserInfoByIds(List<String> userNameList);
}
