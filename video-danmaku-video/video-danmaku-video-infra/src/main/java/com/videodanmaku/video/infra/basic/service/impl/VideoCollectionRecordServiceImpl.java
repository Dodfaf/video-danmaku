package com.videodanmaku.video.infra.basic.service.impl;

import com.videodanmaku.video.infra.basic.entity.VideoCollectionRecord;
import com.videodanmaku.video.infra.basic.mapper.VideoCollectionRecordDao;
import com.videodanmaku.video.infra.basic.service.VideoCollectionRecordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频收藏记录(VideoCollectionRecord)表服务实现类
 *
 * @author videodanmaku
 * @since 2025-05-20
 */
@Service("videoCollectionRecordService")
public class VideoCollectionRecordServiceImpl implements VideoCollectionRecordService {
    @Resource
    private VideoCollectionRecordDao videoCollectionRecordDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public VideoCollectionRecord queryById(Long id) {
        return this.videoCollectionRecordDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param videoCollectionRecord 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<VideoCollectionRecord> queryByPage(VideoCollectionRecord videoCollectionRecord, PageRequest pageRequest) {
        long total = this.videoCollectionRecordDao.count(videoCollectionRecord);
        return new PageImpl<>(this.videoCollectionRecordDao.queryAllByLimit(videoCollectionRecord, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param videoCollectionRecord 实例对象
     * @return 实例对象
     */
    @Override
    public VideoCollectionRecord insert(VideoCollectionRecord videoCollectionRecord) {
        this.videoCollectionRecordDao.insert(videoCollectionRecord);
        return videoCollectionRecord;
    }

    /**
     * 修改数据
     *
     * @param videoCollectionRecord 实例对象
     * @return 实例对象
     */
    @Override
    public VideoCollectionRecord update(VideoCollectionRecord videoCollectionRecord) {
        this.videoCollectionRecordDao.update(videoCollectionRecord);
        return this.queryById(videoCollectionRecord.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.videoCollectionRecordDao.deleteById(id) > 0;
    }

    /**
     * 查询收藏夹中的视频ID列表
     *
     * @param folderId 收藏夹ID
     * @return 视频ID列表
     */
    @Override
    public List<Long> queryVideoIdsByFolderId(Long folderId) {
        return this.videoCollectionRecordDao.queryVideoIdsByFolderId(folderId, 0);
    }

    /**
     * 查询用户是否已收藏视频
     *
     * @param userId 用户ID
     * @param videoId 视频ID
     * @return 收藏记录
     */
    @Override
    public VideoCollectionRecord queryByUserIdAndVideoId(Long userId, Long videoId) {
        return this.videoCollectionRecordDao.queryByUserIdAndVideoId(userId, videoId, 0);
    }

    /**
     * 查询收藏夹中的视频记录
     *
     * @param folderId 收藏夹ID
     * @return 收藏记录列表
     */
    @Override
    public List<VideoCollectionRecord> queryByFolderId(Long folderId) {
        return this.videoCollectionRecordDao.queryByFolderId(folderId, 0);
    }
}