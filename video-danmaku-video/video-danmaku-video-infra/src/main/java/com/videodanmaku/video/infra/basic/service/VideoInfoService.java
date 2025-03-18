package com.videodanmaku.video.infra.basic.service;

import com.videodanmaku.video.infra.basic.entity.VideoInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 视频信息表(VideoInfo)表服务接口
 *
 * @author makejava
 * @since 2025-02-22 21:08:27
 */
public interface VideoInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoInfo queryById(Integer id);

    /**
     * 分页查询
     *
     * @param videoInfo 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<VideoInfo> queryByPage(VideoInfo videoInfo, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param videoInfo 实例对象
     * @return 实例对象
     */
    VideoInfo insert(VideoInfo videoInfo);

    /**
     * 修改数据
     *
     * @param videoInfo 实例对象
     * @return 实例对象
     */
    Integer update(VideoInfo videoInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<VideoInfo> queryByUpId(Integer upId);

    List<VideoInfo> getRandom();
    
    /**
     * 根据视频标题模糊查询
     *
     * @param title 视频标题
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<VideoInfo> queryByTitleLike(String title, PageRequest pageRequest);
}
