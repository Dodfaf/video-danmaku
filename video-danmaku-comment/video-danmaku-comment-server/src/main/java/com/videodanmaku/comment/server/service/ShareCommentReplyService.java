package com.videodanmaku.comment.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.videodanmaku.comment.api.req.GetShareCommentReq;
import com.videodanmaku.comment.api.req.RemoveShareCommentReq;
import com.videodanmaku.comment.api.req.SaveShareCommentReplyReq;
import com.videodanmaku.comment.api.vo.ShareCommentReplyVO;
import com.videodanmaku.comment.server.entity.po.ShareCommentReply;

import java.util.List;

/**
 * <p>
 * 评论及回复信息 服务类
 * </p>
 */
public interface ShareCommentReplyService extends IService<ShareCommentReply> {

    Boolean saveComment(SaveShareCommentReplyReq req);

    Boolean removeComment(RemoveShareCommentReq req);

    List<ShareCommentReplyVO> listComment(GetShareCommentReq req);

}
