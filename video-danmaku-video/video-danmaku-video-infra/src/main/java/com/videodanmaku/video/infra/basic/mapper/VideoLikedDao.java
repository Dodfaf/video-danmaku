package com.videodanmaku.video.infra.basic.mapper;

import com.videodanmaku.video.infra.basic.entity.VideoLiked;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 点赞表(VideoLiked)表数据库访问层
 *
 * @author makejava
 * @since 2025-02-23 15:37:48
 */
public interface VideoLikedDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoLiked queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param videoLiked 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<VideoLiked> queryAllByLimit(VideoLiked videoLiked, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param videoLiked 查询条件
     * @return 总行数
     */
    long count(VideoLiked videoLiked);

    /**
     * 新增数据
     *
     * @param videoLiked 实例对象
     * @return 影响行数
     */
    int insert(VideoLiked videoLiked);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VideoLiked> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VideoLiked> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<VideoLiked> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<VideoLiked> entities);

    /**
     * 修改数据
     *
     * @param videoLiked 实例对象
     * @return 影响行数
     */
    int update(VideoLiked videoLiked);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

