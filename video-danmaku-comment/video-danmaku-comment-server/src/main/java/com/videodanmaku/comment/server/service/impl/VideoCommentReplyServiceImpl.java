package com.videodanmaku.comment.server.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.videodanmaku.comment.api.enums.IsDeletedFlagEnum;
import com.videodanmaku.comment.api.req.GetShareCommentReq;
import com.videodanmaku.comment.api.req.GetVideoCommentReq;
import com.videodanmaku.comment.api.req.RemoveShareCommentReq;
import com.videodanmaku.comment.api.req.SaveVideoCommentReplyReq;
import com.videodanmaku.comment.api.vo.ShareCommentReplyVO;
import com.videodanmaku.comment.server.dao.ShareCommentReplyMapper;
import com.videodanmaku.comment.server.dao.ShareMomentMapper;
import com.videodanmaku.comment.server.dao.VideoInfoMapper;
import com.videodanmaku.comment.server.entity.dto.UserInfo;
import com.videodanmaku.comment.server.entity.po.ShareCommentReply;
import com.videodanmaku.comment.server.entity.po.VideoInfo;
import com.videodanmaku.comment.server.rpc.UserRpc;
import com.videodanmaku.comment.server.service.VideoCommentReplyService;
import com.videodanmaku.comment.server.util.LoginUtil;
import com.videodanmaku.comment.server.util.TreeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 评论及回复信息 服务实现类
 * </p>
 */
@Service
public class VideoCommentReplyServiceImpl extends ServiceImpl<ShareCommentReplyMapper, ShareCommentReply> implements VideoCommentReplyService {
//
    @Resource
    private ShareMomentMapper shareMomentMapper;
    @Resource
    private VideoInfoMapper videoInfoMapper;
    @Resource
    private UserRpc userRpc;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveComment(SaveVideoCommentReplyReq req) {
//        ShareMoment moment = shareMomentMapper.selectById(req.getMomentId());
        VideoInfo video = videoInfoMapper.selectById(req.getVideoId());
        ShareCommentReply comment = new ShareCommentReply();
        comment.setVideoId(req.getVideoId());
//        comment.setMomentId(req.getMomentId());
        comment.setReplyType(req.getReplyType());
        String loginId = LoginUtil.getLoginId();
        // 1评论 2回复
        if (req.getReplyType() == 1) {
            comment.setParentId(-1L);
            comment.setToId(req.getTargetId());
            comment.setToUser(loginId);
            comment.setToUserAuthor(Objects.nonNull(video.getCreateBy()) && loginId.equals(video.getCreateBy()) ? 1 : 0);
        } else {
            comment.setParentId(req.getTargetId());
            comment.setReplyId(req.getTargetId());
            comment.setReplyUser(loginId);
            comment.setReplayAuthor(Objects.nonNull(video.getCreateBy()) && loginId.equals(video.getCreateBy()) ? 1 : 0);
        }
        comment.setContent(req.getContent());
        if (!CollectionUtils.isEmpty(req.getPicUrlList())) {
            comment.setPicUrls(JSON.toJSONString(req.getPicUrlList()));
        }
        comment.setCreatedBy(LoginUtil.getLoginId());
        comment.setCreatedTime(new Date());
        comment.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        videoInfoMapper.incrReplyCount(video.getId(), 1);
        Boolean flag = super.save(comment);
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeComment(RemoveShareCommentReq req) {
        ShareCommentReply comment = getById(req.getId());
        LambdaQueryWrapper<ShareCommentReply> query = Wrappers.<ShareCommentReply>lambdaQuery()
                .eq(ShareCommentReply::getMomentId, comment.getMomentId())
                .eq(ShareCommentReply::getIsDeleted, IsDeletedFlagEnum.UN_DELETED.getCode()).select(ShareCommentReply::getId,
                        ShareCommentReply::getMomentId,
                        ShareCommentReply::getReplyType,
                        ShareCommentReply::getContent,
                        ShareCommentReply::getPicUrls,
                        ShareCommentReply::getCreatedBy,
                        ShareCommentReply::getToUser,
                        ShareCommentReply::getParentId);
        List<ShareCommentReply> list = list(query);
        List<ShareCommentReply> replyList = new ArrayList<>();
        List<ShareCommentReply> tree = TreeUtils.buildTree(list);
        for (ShareCommentReply reply : tree) {
            TreeUtils.findAll(replyList, reply, req.getId());
        }
        // 关联子级对象及 moment 的回复数量
        Set<Long> ids = replyList.stream().map(ShareCommentReply::getId).collect(Collectors.toSet());
        LambdaUpdateWrapper<ShareCommentReply> update = Wrappers.<ShareCommentReply>lambdaUpdate()
                .eq(ShareCommentReply::getMomentId, comment.getMomentId())
                .in(ShareCommentReply::getId, ids);
        ShareCommentReply updateEntity = new ShareCommentReply();
        updateEntity.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int count = getBaseMapper().update(updateEntity, update);
        shareMomentMapper.incrReplyCount(comment.getMomentId(), -count);
        return true;
    }

    @Override
    public List<ShareCommentReplyVO> listComment(GetVideoCommentReq req) {

        // 构建查询条件：查找未被删除、指定视频ID的评论回复
        LambdaQueryWrapper<ShareCommentReply> query = Wrappers.<ShareCommentReply>lambdaQuery()
                .eq(ShareCommentReply::getVideoId, req.getId())  // 视频ID匹配
                .eq(ShareCommentReply::getIsDeleted, IsDeletedFlagEnum.UN_DELETED.getCode()) // 未删除
                .select( // 选择需要的字段（提高查询效率）
                        ShareCommentReply::getId,
                        ShareCommentReply::getVideoId,
                        ShareCommentReply::getReplyType,
                        ShareCommentReply::getContent,
                        ShareCommentReply::getPicUrls,
                        ShareCommentReply::getCreatedBy,
                        ShareCommentReply::getToUser,
                        ShareCommentReply::getCreatedTime,
                        ShareCommentReply::getParentId
                );

        // 执行查询，获取原始评论回复列表
        List<ShareCommentReply> list = list(query);

        // 提取所有创建者用户名，去重，用于后续批量查询用户信息
        List<String> userNameList = list.stream()
                .map(ShareCommentReply::getCreatedBy)
                .distinct()
                .collect(Collectors.toList());

        // 通过RPC批量获取用户信息，返回Map<用户名, 用户信息>
        Map<String, UserInfo> userInfoMap = userRpc.batchGetUserInfo(userNameList);

        // 设置一个默认用户，防止后面找不到用户信息时报错
        UserInfo defaultUser = new UserInfo();

        // 将原始评论回复实体映射为视图对象（VO）
        List<ShareCommentReplyVO> voList = list.stream().map(item -> {
            ShareCommentReplyVO vo = new ShareCommentReplyVO();

            // 基本字段赋值
            vo.setId(item.getId());
            vo.setVideoId(item.getVideoId());
            vo.setReplyType(item.getReplyType());
            vo.setContent(item.getContent());

            // 图片处理：JSON字符串转为列表
            if (Objects.nonNull(item.getPicUrls())) {
                vo.setPicUrlList(JSONArray.parseArray(item.getPicUrls(), String.class));
            }

            // 如果是用户对用户的回复（类型为2），则设置 fromId 和 toId
            if (item.getReplyType() == 2) {
                vo.setFromId(item.getCreatedBy());
                vo.setToId(item.getToUser());
            }

            // 设置父评论ID（用于构建树形结构）
            vo.setParentId(item.getParentId());

            // 设置用户昵称和头像（从批量用户信息中获取，找不到用默认值）
            UserInfo user = userInfoMap.getOrDefault(item.getCreatedBy(), defaultUser);
            vo.setUserName(user.getNickName());
            vo.setAvatar(user.getAvatar());

            // 设置评论时间（转为时间戳）
            vo.setCreatedTime(item.getCreatedTime().getTime());

            return vo;
        }).collect(Collectors.toList());

        // 构建树形结构的评论列表（根据 ParentId 形成父子关系）
        return TreeUtils.buildTree(voList);
    }


}
