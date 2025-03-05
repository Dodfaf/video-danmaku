package com.videodanmaku.video.infra.basic.mapper;

import com.videodanmaku.video.infra.basic.entity.VideoCategoryMapping;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (VideoCategoryMapping)表数据库访问层
 *
 * @author makejava
 * @since 2025-03-03 13:42:07
 */
public interface VideoCategoryMappingDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoCategoryMapping queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param videoCategoryMapping 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<VideoCategoryMapping> queryAllByLimit(VideoCategoryMapping videoCategoryMapping, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param videoCategoryMapping 查询条件
     * @return 总行数
     */
    long count(VideoCategoryMapping videoCategoryMapping);

    /**
     * 新增数据
     *
     * @param videoCategoryMapping 实例对象
     * @return 影响行数
     */
    int insert(VideoCategoryMapping videoCategoryMapping);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VideoCategoryMapping> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VideoCategoryMapping> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<VideoCategoryMapping> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<VideoCategoryMapping> entities);

    /**
     * 修改数据
     *
     * @param videoCategoryMapping 实例对象
     * @return 影响行数
     */
    int update(VideoCategoryMapping videoCategoryMapping);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

