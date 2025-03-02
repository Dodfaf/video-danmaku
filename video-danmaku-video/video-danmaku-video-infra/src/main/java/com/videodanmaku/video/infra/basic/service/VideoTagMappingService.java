package com.videodanmaku.video.infra.basic.service;

import com.videodanmaku.video.infra.basic.entity.VideoTagMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (VideoTagMapping)表服务接口
 *
 * @author makejava
 * @since 2025-03-02 16:58:52
 */
public interface VideoTagMappingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoTagMapping queryById(Integer id);

    /**
     * 分页查询
     *
     * @param videoTagMapping 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<VideoTagMapping> queryByPage(VideoTagMapping videoTagMapping, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param videoTagMapping 实例对象
     * @return 实例对象
     */
    VideoTagMapping insert(VideoTagMapping videoTagMapping);

    /**
     * 修改数据
     *
     * @param videoTagMapping 实例对象
     * @return 实例对象
     */
    VideoTagMapping update(VideoTagMapping videoTagMapping);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
