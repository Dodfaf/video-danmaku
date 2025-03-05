package com.videodanmaku.video.infra.basic.mapper;

import com.videodanmaku.video.infra.basic.entity.VideoTagMapping;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (VideoTagMapping)表数据库访问层
 *
 * @author makejava
 * @since 2025-03-02 16:58:52
 */
public interface VideoTagMappingDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoTagMapping queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param videoTagMapping 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<VideoTagMapping> queryAllByLimit(VideoTagMapping videoTagMapping, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param videoTagMapping 查询条件
     * @return 总行数
     */
    long count(VideoTagMapping videoTagMapping);

    /**
     * 新增数据
     *
     * @param videoTagMapping 实例对象
     * @return 影响行数
     */
    int insert(VideoTagMapping videoTagMapping);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VideoTagMapping> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VideoTagMapping> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<VideoTagMapping> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<VideoTagMapping> entities);

    /**
     * 修改数据
     *
     * @param videoTagMapping 实例对象
     * @return 影响行数
     */
    int update(VideoTagMapping videoTagMapping);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

