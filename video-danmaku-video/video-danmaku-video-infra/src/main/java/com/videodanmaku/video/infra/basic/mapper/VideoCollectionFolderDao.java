package com.videodanmaku.video.infra.basic.mapper;

import com.videodanmaku.video.infra.basic.entity.VideoCollectionFolder;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 视频收藏夹(VideoCollectionFolder)表数据库访问层
 *
 * @author videodanmaku
 * @since 2025-05-20
 */
public interface VideoCollectionFolderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoCollectionFolder queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param videoCollectionFolder 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<VideoCollectionFolder> queryAllByLimit(VideoCollectionFolder videoCollectionFolder, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param videoCollectionFolder 查询条件
     * @return 总行数
     */
    long count(VideoCollectionFolder videoCollectionFolder);

    /**
     * 新增数据
     *
     * @param videoCollectionFolder 实例对象
     * @return 影响行数
     */
    int insert(VideoCollectionFolder videoCollectionFolder);

    /**
     * 修改数据
     *
     * @param videoCollectionFolder 实例对象
     * @return 影响行数
     */
    int update(VideoCollectionFolder videoCollectionFolder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 查询用户的收藏夹列表
     *
     * @param userId 用户ID
     * @param isDeleted 是否删除
     * @return 收藏夹列表
     */
    List<VideoCollectionFolder> queryByUserId(@Param("userId") Long userId, @Param("isDeleted") Integer isDeleted);
}