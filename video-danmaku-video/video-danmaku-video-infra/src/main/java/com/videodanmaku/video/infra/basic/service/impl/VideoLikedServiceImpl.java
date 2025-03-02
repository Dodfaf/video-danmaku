package com.videodanmaku.video.infra.basic.service.impl;

import com.videodanmaku.video.infra.basic.entity.VideoLiked;
import com.videodanmaku.video.infra.basic.mapper.VideoLikedDao;
import com.videodanmaku.video.infra.basic.service.VideoLikedService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 点赞表(VideoLiked)表服务实现类
 *
 * @author makejava
 * @since 2025-02-23 15:37:49
 */
@Service("videoLikedService")
public class VideoLikedServiceImpl implements VideoLikedService {
    @Resource
    private VideoLikedDao videoLikedDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public VideoLiked queryById(Integer id) {
        return this.videoLikedDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param videoLiked 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<VideoLiked> queryByPage(VideoLiked videoLiked, PageRequest pageRequest) {
        long total = this.videoLikedDao.count(videoLiked);
        return new PageImpl<>(this.videoLikedDao.queryAllByLimit(videoLiked, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param videoLiked 实例对象
     * @return 实例对象
     */
    @Override
    public VideoLiked insert(VideoLiked videoLiked) {
        this.videoLikedDao.insert(videoLiked);
        return videoLiked;
    }

    /**
     * 修改数据
     *
     * @param videoLiked 实例对象
     * @return 实例对象
     */
    @Override
    public VideoLiked update(VideoLiked videoLiked) {
        this.videoLikedDao.update(videoLiked);
        return this.queryById(videoLiked.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.videoLikedDao.deleteById(id) > 0;
    }
}
