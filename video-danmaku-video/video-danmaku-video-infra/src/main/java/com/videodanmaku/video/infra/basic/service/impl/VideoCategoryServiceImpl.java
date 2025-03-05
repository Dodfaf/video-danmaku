package com.videodanmaku.video.infra.basic.service.impl;

import com.videodanmaku.video.infra.basic.entity.VideoCategory;
import com.videodanmaku.video.infra.basic.mapper.VideoCategoryDao;
import com.videodanmaku.video.infra.basic.service.VideoCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (VideoCategory)表服务实现类
 *
 * @author makejava
 * @since 2025-03-03 13:41:41
 */
@Service("videoCategoryService")
public class VideoCategoryServiceImpl implements VideoCategoryService {
    @Resource
    private VideoCategoryDao videoCategoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public VideoCategory queryById(Integer id) {
        return this.videoCategoryDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param videoCategory 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<VideoCategory> queryByPage(VideoCategory videoCategory, PageRequest pageRequest) {
        long total = this.videoCategoryDao.count(videoCategory);
        return new PageImpl<>(this.videoCategoryDao.queryAllByLimit(videoCategory, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param videoCategory 实例对象
     * @return 实例对象
     */
    @Override
    public VideoCategory insert(VideoCategory videoCategory) {
        this.videoCategoryDao.insert(videoCategory);
        return videoCategory;
    }

    /**
     * 修改数据
     *
     * @param videoCategory 实例对象
     * @return 实例对象
     */
    @Override
    public VideoCategory update(VideoCategory videoCategory) {
        this.videoCategoryDao.update(videoCategory);
        return this.queryById(videoCategory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.videoCategoryDao.deleteById(id) > 0;
    }

    @Override
    public List<VideoCategory> getCategorys() {
        return videoCategoryDao.getAll();
    }
}
