package com.videodanmaku.video.infra.basic.dao;

import com.videodanmaku.video.infra.basic.entity.VideoTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (VideoTag)表数据库访问层
 *
 * @author makejava
 * @since 2025-03-02 16:58:17
 */
public interface VideoTagDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoTag queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param videoTag 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<VideoTag> queryAllByLimit(VideoTag videoTag, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param videoTag 查询条件
     * @return 总行数
     */
    long count(VideoTag videoTag);

    /**
     * 新增数据
     *
     * @param videoTag 实例对象
     * @return 影响行数
     */
    int insert(VideoTag videoTag);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VideoTag> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VideoTag> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<VideoTag> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<VideoTag> entities);

    /**
     * 修改数据
     *
     * @param videoTag 实例对象
     * @return 影响行数
     */
    int update(VideoTag videoTag);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

