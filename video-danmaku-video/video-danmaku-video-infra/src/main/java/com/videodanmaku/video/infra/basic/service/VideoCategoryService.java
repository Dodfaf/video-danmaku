package com.videodanmaku.video.infra.basic.service;

import com.videodanmaku.video.infra.basic.entity.VideoCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (VideoCategory)表服务接口
 *
 * @author makejava
 * @since 2025-03-03 13:41:41
 */
public interface VideoCategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoCategory queryById(Integer id);

    /**
     * 分页查询
     *
     * @param videoCategory 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<VideoCategory> queryByPage(VideoCategory videoCategory, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param videoCategory 实例对象
     * @return 实例对象
     */
    VideoCategory insert(VideoCategory videoCategory);

    /**
     * 修改数据
     *
     * @param videoCategory 实例对象
     * @return 实例对象
     */
    VideoCategory update(VideoCategory videoCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<VideoCategory> getCategorys();
}
