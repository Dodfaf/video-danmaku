package com.videodanmaku.video.infra.basic.mapper;

import com.videodanmaku.video.infra.basic.entity.VideoInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 视频信息表(VideoInfo)表数据库访问层
 *
 * @author makejava
 * @since 2025-02-22 21:08:26
 */
public interface VideoInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoInfo queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param videoInfo 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<VideoInfo> queryAllByLimit(@Param("videoInfo")VideoInfo videoInfo, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param videoInfo 查询条件
     * @return 总行数
     */
    long count(VideoInfo videoInfo);

    /**
     * 新增数据
     *
     * @param videoInfo 实例对象
     * @return 影响行数
     */
    int insert(VideoInfo videoInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VideoInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VideoInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<VideoInfo> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<VideoInfo> entities);

    /**
     * 修改数据
     *
     * @param videoInfo 实例对象
     * @return 影响行数
     */
    int update(VideoInfo videoInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<VideoInfo> getRandom();
    
    /**
     * 根据视频标题模糊查询
     *
     * @param title 视频标题
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<VideoInfo> queryByTitleLike(@Param("title") String title, @Param("pageable") Pageable pageable);
    
    /**
     * 统计模糊查询总行数
     *
     * @param title 视频标题
     * @return 总行数
     */
    long countByTitleLike(@Param("title") String title);
}
