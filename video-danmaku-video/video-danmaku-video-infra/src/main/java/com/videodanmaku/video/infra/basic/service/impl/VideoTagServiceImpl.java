package com.videodanmaku.video.infra.basic.service.impl;

import com.videodanmaku.video.infra.basic.entity.VideoTag;
import com.videodanmaku.video.infra.basic.mapper.VideoTagDao;
import com.videodanmaku.video.infra.basic.service.VideoTagService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (VideoTag)表服务实现类
 *
 * @author makejava
 * @since 2025-03-02 16:58:18
 */
@Service("videoTagService")
public class VideoTagServiceImpl implements VideoTagService {
    @Resource
    private VideoTagDao videoTagDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public VideoTag queryById(Integer id) {
        return this.videoTagDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param videoTag 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<VideoTag> queryByPage(VideoTag videoTag, PageRequest pageRequest) {
        long total = this.videoTagDao.count(videoTag);
        return new PageImpl<>(this.videoTagDao.queryAllByLimit(videoTag, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param videoTag 实例对象
     * @return 实例对象
     */
    @Override
    public VideoTag insert(VideoTag videoTag) {
        this.videoTagDao.insert(videoTag);
        return videoTag;
    }

    /**
     * 修改数据
     *
     * @param videoTag 实例对象
     * @return 实例对象
     */
    @Override
    public VideoTag update(VideoTag videoTag) {
        this.videoTagDao.update(videoTag);
        return this.queryById(videoTag.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.videoTagDao.deleteById(id) > 0;
    }
}
