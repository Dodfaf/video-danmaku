package com.videodanmaku.video.infra.basic.service;

import com.videodanmaku.video.infra.basic.entity.VideoStorage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 视频存储表(VideoStorage)表服务接口
 *
 * @author makejava
 * @since 2025-02-26 10:32:03
 */
public interface VideoStorageService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoStorage queryById(Integer id);

    /**
     * 分页查询
     *
     * @param videoStorage 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<VideoStorage> queryByPage(VideoStorage videoStorage, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param videoStorage 实例对象
     * @return 实例对象
     */
    VideoStorage insert(VideoStorage videoStorage);

    /**
     * 修改数据
     *
     * @param videoStorage 实例对象
     * @return 实例对象
     */
    VideoStorage update(VideoStorage videoStorage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
