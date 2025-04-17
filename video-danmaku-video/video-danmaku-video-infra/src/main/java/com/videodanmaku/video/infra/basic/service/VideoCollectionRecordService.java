package com.videodanmaku.video.infra.basic.service;

import com.videodanmaku.video.infra.basic.entity.VideoCollectionRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 视频收藏记录(VideoCollectionRecord)表服务接口
 *
 * @author videodanmaku
 * @since 2025-05-20
 */
public interface VideoCollectionRecordService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoCollectionRecord queryById(Long id);

    /**
     * 分页查询
     *
     * @param videoCollectionRecord 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<VideoCollectionRecord> queryByPage(VideoCollectionRecord videoCollectionRecord, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param videoCollectionRecord 实例对象
     * @return 实例对象
     */
    VideoCollectionRecord insert(VideoCollectionRecord videoCollectionRecord);

    /**
     * 修改数据
     *
     * @param videoCollectionRecord 实例对象
     * @return 实例对象
     */
    VideoCollectionRecord update(VideoCollectionRecord videoCollectionRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 查询收藏夹中的视频ID列表
     *
     * @param folderId 收藏夹ID
     * @return 视频ID列表
     */
    List<Long> queryVideoIdsByFolderId(Long folderId);

    /**
     * 查询用户是否已收藏视频
     *
     * @param userId 用户ID
     * @param videoId 视频ID
     * @return 收藏记录
     */
    VideoCollectionRecord queryByUserIdAndVideoId(Long userId, Long videoId);

    /**
     * 查询收藏夹中的视频记录
     *
     * @param folderId 收藏夹ID
     * @return 收藏记录列表
     */
    List<VideoCollectionRecord> queryByFolderId(Long folderId);
}