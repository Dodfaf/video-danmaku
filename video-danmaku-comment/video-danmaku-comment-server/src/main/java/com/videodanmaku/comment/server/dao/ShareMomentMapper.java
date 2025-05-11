package com.videodanmaku.comment.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.videodanmaku.comment.server.entity.po.ShareMoment;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 动态信息 Mapper 接口
 * </p>
 */
public interface ShareMomentMapper extends BaseMapper<ShareMoment> {

    void incrReplyCount(@Param("id") Long id, @Param("count") int count);

}
