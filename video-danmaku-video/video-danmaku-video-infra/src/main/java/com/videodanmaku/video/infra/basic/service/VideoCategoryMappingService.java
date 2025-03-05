package com.videodanmaku.video.infra.basic.service;

import com.videodanmaku.video.infra.basic.entity.VideoCategoryMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (VideoCategoryMapping)表服务接口
 *
 * @author makejava
 * @since 2025-03-03 13:42:07
 */
public interface VideoCategoryMappingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoCategoryMapping queryById(Integer id);

    /**
     * 分页查询
     *
     * @param videoCategoryMapping 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<VideoCategoryMapping> queryByPage(VideoCategoryMapping videoCategoryMapping, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param videoCategoryMapping 实例对象
     * @return 实例对象
     */
    VideoCategoryMapping insert(VideoCategoryMapping videoCategoryMapping);

    /**
     * 修改数据
     *
     * @param videoCategoryMapping 实例对象
     * @return 实例对象
     */
    VideoCategoryMapping update(VideoCategoryMapping videoCategoryMapping);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
