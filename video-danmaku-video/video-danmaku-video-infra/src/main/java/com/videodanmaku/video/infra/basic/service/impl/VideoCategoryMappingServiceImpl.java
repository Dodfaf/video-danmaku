package com.videodanmaku.video.infra.basic.service.impl;

import com.videodanmaku.video.infra.basic.entity.VideoCategoryMapping;
import com.videodanmaku.video.infra.basic.mapper.VideoCategoryMappingDao;
import com.videodanmaku.video.infra.basic.service.VideoCategoryMappingService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (VideoCategoryMapping)表服务实现类
 *
 * @author makejava
 * @since 2025-03-03 13:42:07
 */
@Service("videoCategoryMappingService")
public class VideoCategoryMappingServiceImpl implements VideoCategoryMappingService {
    @Resource
    private VideoCategoryMappingDao videoCategoryMappingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public VideoCategoryMapping queryById(Integer id) {
        return this.videoCategoryMappingDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param videoCategoryMapping 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<VideoCategoryMapping> queryByPage(VideoCategoryMapping videoCategoryMapping, PageRequest pageRequest) {
        long total = this.videoCategoryMappingDao.count(videoCategoryMapping);
        return new PageImpl<>(this.videoCategoryMappingDao.queryAllByLimit(videoCategoryMapping, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param videoCategoryMapping 实例对象
     * @return 实例对象
     */
    @Override
    public VideoCategoryMapping insert(VideoCategoryMapping videoCategoryMapping) {
        this.videoCategoryMappingDao.insert(videoCategoryMapping);
        return videoCategoryMapping;
    }

    /**
     * 修改数据
     *
     * @param videoCategoryMapping 实例对象
     * @return 实例对象
     */
    @Override
    public VideoCategoryMapping update(VideoCategoryMapping videoCategoryMapping) {
        this.videoCategoryMappingDao.update(videoCategoryMapping);
        return this.queryById(videoCategoryMapping.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.videoCategoryMappingDao.deleteById(id) > 0;
    }
}
