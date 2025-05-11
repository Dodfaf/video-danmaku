package com.videodanmaku.comment.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.videodanmaku.comment.api.common.PageResult;
import com.videodanmaku.comment.api.req.GetShareMomentReq;
import com.videodanmaku.comment.api.req.RemoveShareMomentReq;
import com.videodanmaku.comment.api.req.SaveMomentCircleReq;
import com.videodanmaku.comment.api.vo.ShareMomentVO;
import com.videodanmaku.comment.server.entity.po.ShareMoment;
import com.videodanmaku.comment.server.entity.po.VideoInfo;

/**
 * <p>
 * 动态信息 服务类
 * </p>
 */
public interface VideoInfoService extends IService<VideoInfo> {

    Boolean saveMoment(SaveMomentCircleReq req);

//    PageResult<ShareMomentVO> getMoments(GetShareMomentReq req);

    Boolean removeMoment(RemoveShareMomentReq req);

    void incrReplyCount(Integer id, int count);

}
