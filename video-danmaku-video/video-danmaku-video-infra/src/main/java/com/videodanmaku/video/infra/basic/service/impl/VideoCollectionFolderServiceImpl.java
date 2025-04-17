package com.videodanmaku.video.infra.basic.service.impl;

import com.videodanmaku.video.infra.basic.entity.VideoCollectionFolder;
import com.videodanmaku.video.infra.basic.mapper.VideoCollectionFolderDao;
import com.videodanmaku.video.infra.basic.service.VideoCollectionFolderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频收藏夹(VideoCollectionFolder)表服务实现类
 *
 * @author videodanmaku
 * @since 2025-05-20
 */
@Service("videoCollectionFolderService")
public class VideoCollectionFolderServiceImpl implements VideoCollectionFolderService {
    @Resource
    private VideoCollectionFolderDao videoCollectionFolderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public VideoCollectionFolder queryById(Long id) {
        return this.videoCollectionFolderDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param videoCollectionFolder 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<VideoCollectionFolder> queryByPage(VideoCollectionFolder videoCollectionFolder, PageRequest pageRequest) {
        long total = this.videoCollectionFolderDao.count(videoCollectionFolder);
        return new PageImpl<>(this.videoCollectionFolderDao.queryAllByLimit(videoCollectionFolder, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param videoCollectionFolder 实例对象
     * @return 实例对象
     */
    @Override
    public VideoCollectionFolder insert(VideoCollectionFolder videoCollectionFolder) {
        this.videoCollectionFolderDao.insert(videoCollectionFolder);
        return videoCollectionFolder;
    }

    /**
     * 修改数据
     *
     * @param videoCollectionFolder 实例对象
     * @return 实例对象
     */
    @Override
    public VideoCollectionFolder update(VideoCollectionFolder videoCollectionFolder) {
        this.videoCollectionFolderDao.update(videoCollectionFolder);
        return this.queryById(videoCollectionFolder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.videoCollectionFolderDao.deleteById(id) > 0;
    }

    /**
     * 查询用户的收藏夹列表
     *
     * @param userId 用户ID
     * @return 收藏夹列表
     */
    @Override
    public List<VideoCollectionFolder> queryByUserId(Long userId) {
        return this.videoCollectionFolderDao.queryByUserId(userId, 0);
    }
}