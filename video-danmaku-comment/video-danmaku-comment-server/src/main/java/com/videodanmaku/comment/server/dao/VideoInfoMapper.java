package com.videodanmaku.comment.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.videodanmaku.comment.server.entity.po.ShareMoment;
import com.videodanmaku.comment.server.entity.po.VideoInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 动态信息 Mapper 接口
 * </p>
 *
 * @author ChickenWing
 * @since 2024/05/16
 */
public interface VideoInfoMapper extends BaseMapper<VideoInfo> {

    void incrReplyCount(@Param("id") Integer id, @Param("count") int count);

}
