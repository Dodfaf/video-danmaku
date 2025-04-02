package com.videodanmaku.circle.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.videodanmaku.circle.api.common.PageResult;
import com.videodanmaku.circle.api.req.GetShareMomentReq;
import com.videodanmaku.circle.api.req.RemoveShareMomentReq;
import com.videodanmaku.circle.api.req.SaveMomentCircleReq;
import com.videodanmaku.circle.api.vo.ShareMomentVO;
import com.videodanmaku.circle.server.entity.po.ShareMoment;

/**
 * <p>
 * 动态信息 服务类
 * </p>
 *
 * @author ChickenWing
 * @since 2024/05/16
 */
public interface ShareMomentService extends IService<ShareMoment> {

    Boolean saveMoment(SaveMomentCircleReq req);

    PageResult<ShareMomentVO> getMoments(GetShareMomentReq req);

    Boolean removeMoment(RemoveShareMomentReq req);

    void incrReplyCount(Long id, int count);

}
