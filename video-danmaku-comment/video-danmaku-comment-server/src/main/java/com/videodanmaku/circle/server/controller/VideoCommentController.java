package com.videodanmaku.circle.server.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.base.Preconditions;
import com.videodanmaku.circle.api.common.Result;
import com.videodanmaku.circle.api.enums.IsDeletedFlagEnum;
import com.videodanmaku.circle.api.req.GetShareCommentReq;
import com.videodanmaku.circle.api.req.RemoveShareCommentReq;
import com.videodanmaku.circle.api.req.SaveShareCommentReplyReq;
import com.videodanmaku.circle.api.vo.ShareCommentReplyVO;
import com.videodanmaku.circle.server.entity.po.ShareCommentReply;
import com.videodanmaku.circle.server.entity.po.ShareMoment;
import com.videodanmaku.circle.server.sensitive.WordFilter;
import com.videodanmaku.circle.server.service.ShareCommentReplyService;
import com.videodanmaku.circle.server.service.ShareMessageService;
import com.videodanmaku.circle.server.service.ShareMomentService;
import com.videodanmaku.circle.server.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 视频评论及回复 前端控制器
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/video/comment")
public class VideoCommentController {

    @Resource
    private ShareMomentService shareMomentService; // 这里实际上是视频服务
    @Resource
    private ShareCommentReplyService shareCommentReplyService;
    @Resource
    private WordFilter wordFilter;
    @Resource
    private ShareMessageService shareMessageService;

    /**
     * 发布视频评论
     */
    @PostMapping(value = "/save")
    public Result<Boolean> save(@RequestBody SaveShareCommentReplyReq req) {
        try {
            if (log.isInfoEnabled()) {
                log.info("发布视频评论入参{}", JSON.toJSONString(req));
            }
            Preconditions.checkArgument(Objects.nonNull(req), "参数不能为空！");
            Preconditions.checkArgument(Objects.nonNull(req.getReplyType()), "类型不能为空！");
            Preconditions.checkArgument(Objects.nonNull(req.getMomentId()), "视频ID不能为空！");

            // 这里需要修改为检查视频是否存在
            ShareMoment video = shareMomentService.getById(req.getMomentId());
            Preconditions.checkArgument((Objects.nonNull(video) && video.getIsDeleted() != IsDeletedFlagEnum.DELETED.getCode()), "视频不存在！");
            Preconditions.checkArgument((Objects.nonNull(req.getContent()) || Objects.nonNull(req.getPicUrlList())), "评论内容不能为空！");

            wordFilter.check(req.getContent()); //敏感词校验
            Boolean result = shareCommentReplyService.saveComment(req);
            if (result) {
                if (req.getReplyType() == 1) {
                    // 评论视频
                    shareMessageService.comment(LoginUtil.getLoginId(), video.getCreatedBy(), video.getId());
                } else {
                    // 回复评论
                    LambdaQueryWrapper<ShareCommentReply> query = Wrappers.<ShareCommentReply>lambdaQuery()
                            .eq(ShareCommentReply::getId, req.getTargetId())
                            .select(ShareCommentReply::getCreatedBy);
                    ShareCommentReply reply = shareCommentReplyService.getOne(query);
                    shareMessageService.reply(LoginUtil.getLoginId(), reply.getCreatedBy(), video.getId());
                }
            }
            if (log.isInfoEnabled()) {
                log.info("发布视频评论结果{}", JSON.toJSONString(result));
            }
            return Result.ok(result);
        } catch (IllegalArgumentException e) {
            log.error("参数异常！错误原因{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("发布视频评论异常！错误原因{}", e.getMessage(), e);
            return Result.fail("发布视频评论失败！");
        }
    }

    /**
     * 删除视频评论
     */
    @PostMapping(value = "/remove")
    public Result<Boolean> remove(@RequestBody RemoveShareCommentReq req) {
        try {
            if (log.isInfoEnabled()) {
                log.info("删除视频评论入参{}", JSON.toJSONString(req));
            }
            Preconditions.checkArgument(Objects.nonNull(req), "参数不能为空！");
            Preconditions.checkArgument(Objects.nonNull(req.getReplyType()), "类型不能为空！");
            Preconditions.checkArgument(Objects.nonNull(req.getId()), "评论ID不能为空！");
            Boolean result = shareCommentReplyService.removeComment(req);
            if (log.isInfoEnabled()) {
                log.info("删除视频评论结果{}", JSON.toJSONString(result));
            }
            return Result.ok(result);
        } catch (IllegalArgumentException e) {
            log.error("参数异常！错误原因{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("删除视频评论异常！错误原因{}", e.getMessage(), e);
            return Result.fail("删除视频评论失败！");
        }
    }

    /**
     * 查询视频下的评论列表
     */
    @PostMapping(value = "/list")
    public Result<List<ShareCommentReplyVO>> list(@RequestBody GetShareCommentReq req) {
        try {
            if (log.isInfoEnabled()) {
                log.info("获取视频评论列表入参{}", JSON.toJSONString(req));
            }
            Preconditions.checkArgument(Objects.nonNull(req), "参数不能为空！");
            Preconditions.checkArgument(Objects.nonNull(req.getId()), "视频ID不能为空！");
            List<ShareCommentReplyVO> result = shareCommentReplyService.listComment(req);
            if (log.isInfoEnabled()) {
                log.info("获取视频评论列表结果{}", JSON.toJSONString(result));
            }
            return Result.ok(result);
        } catch (IllegalArgumentException e) {
            log.error("参数异常！错误原因{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("获取视频评论列表异常！错误原因{}", e.getMessage(), e);
            return Result.fail("获取视频评论列表失败！");
        }
    }
}