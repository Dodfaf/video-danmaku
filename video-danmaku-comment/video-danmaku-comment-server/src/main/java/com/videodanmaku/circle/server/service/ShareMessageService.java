package com.videodanmaku.circle.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.videodanmaku.circle.api.common.PageResult;
import com.videodanmaku.circle.api.req.GetShareMessageReq;
import com.videodanmaku.circle.api.vo.ShareMessageVO;
import com.videodanmaku.circle.server.entity.po.ShareMessage;

/**
 * <p>
 * 消息表 服务类
 * </p>
 *
 * @author ChickenWing
 * @since 2024/05/18
 */
public interface ShareMessageService extends IService<ShareMessage> {

    PageResult<ShareMessageVO> getMessages(GetShareMessageReq req);

    void comment(String fromId, String toId, Long targetId);

    void reply(String fromId, String toId, Long targetId);

    Boolean unRead();

}
