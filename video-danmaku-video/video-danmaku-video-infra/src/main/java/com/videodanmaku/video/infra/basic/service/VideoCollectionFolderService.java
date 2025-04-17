package com.videodanmaku.video.infra.basic.service;

import com.videodanmaku.video.infra.basic.entity.VideoCollectionFolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 视频收藏夹(VideoCollectionFolder)表服务接口
 *
 * @author videodanmaku
 * @since 2025-05-20
 */
public interface VideoCollectionFolderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoCollectionFolder queryById(Long id);

    /**
     * 分页查询
     *
     * @param videoCollectionFolder 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<VideoCollectionFolder> queryByPage(VideoCollectionFolder videoCollectionFolder, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param videoCollectionFolder 实例对象
     * @return 实例对象
     */
    VideoCollectionFolder insert(VideoCollectionFolder videoCollectionFolder);

    /**
     * 修改数据
     *
     * @param videoCollectionFolder 实例对象
     * @return 实例对象
     */
    VideoCollectionFolder update(VideoCollectionFolder videoCollectionFolder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 查询用户的收藏夹列表
     *
     * @param userId 用户ID
     * @return 收藏夹列表
     */
    List<VideoCollectionFolder> queryByUserId(Long userId);
}