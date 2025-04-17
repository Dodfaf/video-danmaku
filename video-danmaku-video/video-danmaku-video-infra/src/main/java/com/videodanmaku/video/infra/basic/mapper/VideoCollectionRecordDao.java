package com.videodanmaku.video.infra.basic.mapper;

import com.videodanmaku.video.infra.basic.entity.VideoCollectionRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 视频收藏记录(VideoCollectionRecord)表数据库访问层
 *
 * @author videodanmaku
 * @since 2025-05-20
 */
public interface VideoCollectionRecordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoCollectionRecord queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param videoCollectionRecord 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<VideoCollectionRecord> queryAllByLimit(VideoCollectionRecord videoCollectionRecord, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param videoCollectionRecord 查询条件
     * @return 总行数
     */
    long count(VideoCollectionRecord videoCollectionRecord);

    /**
     * 新增数据
     *
     * @param videoCollectionRecord 实例对象
     * @return 影响行数
     */
    int insert(VideoCollectionRecord videoCollectionRecord);

    /**
     * 修改数据
     *
     * @param videoCollectionRecord 实例对象
     * @return 影响行数
     */
    int update(VideoCollectionRecord videoCollectionRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 查询收藏夹中的视频ID列表
     *
     * @param folderId 收藏夹ID
     * @param isDeleted 是否删除
     * @return 视频ID列表
     */
    List<Long> queryVideoIdsByFolderId(@Param("folderId") Long folderId, @Param("isDeleted") Integer isDeleted);

    /**
     * 查询用户是否已收藏视频
     *
     * @param userId 用户ID
     * @param videoId 视频ID
     * @param isDeleted 是否删除
     * @return 收藏记录
     */
    VideoCollectionRecord queryByUserIdAndVideoId(@Param("userId") Long userId, @Param("videoId") Long videoId, @Param("isDeleted") Integer isDeleted);

    /**
     * 查询收藏夹中的视频记录
     *
     * @param folderId 收藏夹ID
     * @param isDeleted 是否删除
     * @return 收藏记录列表
     */
    List<VideoCollectionRecord> queryByFolderId(@Param("folderId") Long folderId, @Param("isDeleted") Integer isDeleted);
}