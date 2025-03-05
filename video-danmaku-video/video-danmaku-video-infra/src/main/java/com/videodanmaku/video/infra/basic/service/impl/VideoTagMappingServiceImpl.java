package com.videodanmaku.video.infra.basic.service.impl;

import com.videodanmaku.video.infra.basic.entity.VideoTagMapping;
import com.videodanmaku.video.infra.basic.mapper.VideoTagMappingDao;
import com.videodanmaku.video.infra.basic.service.VideoTagMappingService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (VideoTagMapping)表服务实现类
 *
 * @author makejava
 * @since 2025-03-02 16:58:52
 */
@Service("videoTagMappingService")
public class VideoTagMappingServiceImpl implements VideoTagMappingService {
    @Resource
    private VideoTagMappingDao videoTagMappingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public VideoTagMapping queryById(Integer id) {
        return this.videoTagMappingDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param videoTagMapping 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<VideoTagMapping> queryByPage(VideoTagMapping videoTagMapping, PageRequest pageRequest) {
        long total = this.videoTagMappingDao.count(videoTagMapping);
        return new PageImpl<>(this.videoTagMappingDao.queryAllByLimit(videoTagMapping, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param videoTagMapping 实例对象
     * @return 实例对象
     */
    @Override
    public VideoTagMapping insert(VideoTagMapping videoTagMapping) {
        this.videoTagMappingDao.insert(videoTagMapping);
        return videoTagMapping;
    }

    /**
     * 修改数据
     *
     * @param videoTagMapping 实例对象
     * @return 实例对象
     */
    @Override
    public VideoTagMapping update(VideoTagMapping videoTagMapping) {
        this.videoTagMappingDao.update(videoTagMapping);
        return this.queryById(videoTagMapping.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.videoTagMappingDao.deleteById(id) > 0;
    }
}
