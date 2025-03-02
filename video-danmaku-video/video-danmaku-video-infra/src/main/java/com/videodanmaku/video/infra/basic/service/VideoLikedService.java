package com.videodanmaku.video.infra.basic.service;

import com.videodanmaku.video.infra.basic.entity.VideoLiked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 点赞表(VideoLiked)表服务接口
 *
 * @author makejava
 * @since 2025-02-23 15:37:49
 */
public interface VideoLikedService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoLiked queryById(Integer id);

    /**
     * 分页查询
     *
     * @param videoLiked 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<VideoLiked> queryByPage(VideoLiked videoLiked, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param videoLiked 实例对象
     * @return 实例对象
     */
    VideoLiked insert(VideoLiked videoLiked);

    /**
     * 修改数据
     *
     * @param videoLiked 实例对象
     * @return 实例对象
     */
    VideoLiked update(VideoLiked videoLiked);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
