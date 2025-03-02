package com.videodanmaku.video.infra.basic.service;

import com.videodanmaku.video.infra.basic.entity.VideoTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (VideoTag)表服务接口
 *
 * @author makejava
 * @since 2025-03-02 16:58:18
 */
public interface VideoTagService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoTag queryById(Integer id);

    /**
     * 分页查询
     *
     * @param videoTag 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<VideoTag> queryByPage(VideoTag videoTag, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param videoTag 实例对象
     * @return 实例对象
     */
    VideoTag insert(VideoTag videoTag);

    /**
     * 修改数据
     *
     * @param videoTag 实例对象
     * @return 实例对象
     */
    VideoTag update(VideoTag videoTag);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
