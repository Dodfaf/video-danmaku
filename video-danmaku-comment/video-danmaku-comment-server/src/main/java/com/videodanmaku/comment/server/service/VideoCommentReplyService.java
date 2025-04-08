package com.videodanmaku.comment.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.videodanmaku.comment.api.req.*;
import com.videodanmaku.comment.api.vo.ShareCommentReplyVO;
import com.videodanmaku.comment.server.entity.po.ShareCommentReply;

import java.util.List;

/**
 * <p>
 * 评论及回复信息 服务类
 * </p>
 *
 * @author ChickenWing
 * @since 2024/05/16
 */
public interface VideoCommentReplyService extends IService<ShareCommentReply> {

    Boolean saveComment(SaveVideoCommentReplyReq req);

    Boolean removeComment(RemoveShareCommentReq req);

    List<ShareCommentReplyVO> listComment(GetVideoCommentReq req);

}
