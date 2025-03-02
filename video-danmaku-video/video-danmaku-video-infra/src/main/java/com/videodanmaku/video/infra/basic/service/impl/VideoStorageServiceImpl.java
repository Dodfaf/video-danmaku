package com.videodanmaku.video.infra.basic.service.impl;

import com.videodanmaku.video.infra.basic.entity.VideoStorage;
import com.videodanmaku.video.infra.basic.mapper.VideoStorageDao;
import com.videodanmaku.video.infra.basic.service.VideoStorageService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 视频存储表(VideoStorage)表服务实现类
 *
 * @author makejava
 * @since 2025-02-26 10:32:03
 */
@Service("videoStorageService")
public class VideoStorageServiceImpl implements VideoStorageService {
    @Resource
    private VideoStorageDao videoStorageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public VideoStorage queryById(Integer id) {
        return this.videoStorageDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param videoStorage 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<VideoStorage> queryByPage(VideoStorage videoStorage, PageRequest pageRequest) {
        long total = this.videoStorageDao.count(videoStorage);
        return new PageImpl<>(this.videoStorageDao.queryAllByLimit(videoStorage, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param videoStorage 实例对象
     * @return 实例对象
     */
    @Override
    public VideoStorage insert(VideoStorage videoStorage) {
        this.videoStorageDao.insert(videoStorage);
        return videoStorage;
    }

    /**
     * 修改数据
     *
     * @param videoStorage 实例对象
     * @return 实例对象
     */
    @Override
    public VideoStorage update(VideoStorage videoStorage) {
        this.videoStorageDao.update(videoStorage);
        return this.queryById(videoStorage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.videoStorageDao.deleteById(id) > 0;
    }
}
