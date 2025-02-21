package com.videodanmaku.auth.infra.basic.service.impl;

import com.videodanmaku.auth.infra.basic.entity.AuthRole;
import com.videodanmaku.auth.infra.basic.mapper.AuthRoleDao;
import com.videodanmaku.auth.infra.basic.service.AuthRoleService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 角色表(AuthRole)表服务实现类
 *
 * @author makejava
 * @since 2025-02-20 21:46:48
 */
@Service("authRoleService")
public class AuthRoleServiceImpl implements AuthRoleService {
    @Resource
    private AuthRoleDao authRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthRole queryById(Long id) {
        return this.authRoleDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param authRole 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<AuthRole> queryByPage(AuthRole authRole, PageRequest pageRequest) {
        long total = this.authRoleDao.count(authRole);
        return new PageImpl<>(this.authRoleDao.queryAllByLimit(authRole, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    @Override
    public AuthRole insert(AuthRole authRole) {
        this.authRoleDao.insert(authRole);
        return authRole;
    }

    /**
     * 修改数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    @Override
    public AuthRole update(AuthRole authRole) {
        this.authRoleDao.update(authRole);
        return this.queryById(authRole.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authRoleDao.deleteById(id) > 0;
    }
}
