package com.videodanmaku.comment.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.videodanmaku.comment.api.common.PageInfo;
import com.videodanmaku.comment.api.common.PageResult;
import com.videodanmaku.comment.api.enums.IsDeletedFlagEnum;
import com.videodanmaku.comment.api.req.GetShareMomentReq;
import com.videodanmaku.comment.api.req.RemoveShareMomentReq;
import com.videodanmaku.comment.api.req.SaveMomentCircleReq;
import com.videodanmaku.comment.api.vo.ShareMomentVO;
import com.videodanmaku.comment.server.dao.ShareCommentReplyMapper;
import com.videodanmaku.comment.server.dao.ShareMomentMapper;
import com.videodanmaku.comment.server.dao.VideoInfoMapper;
import com.videodanmaku.comment.server.entity.dto.UserInfo;
import com.videodanmaku.comment.server.entity.po.ShareCommentReply;
import com.videodanmaku.comment.server.entity.po.ShareMoment;
import com.videodanmaku.comment.server.entity.po.VideoInfo;
import com.videodanmaku.comment.server.rpc.UserRpc;
import com.videodanmaku.comment.server.service.ShareMomentService;
import com.videodanmaku.comment.server.service.VideoInfoService;
import com.videodanmaku.comment.server.util.LoginUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 动态信息 服务实现类
 * </p>
 *

 */
@Service
public class VideoInfoServiceImpl extends ServiceImpl<VideoInfoMapper, VideoInfo> implements VideoInfoService {

    @Resource
    private ShareCommentReplyMapper shareCommentReplyMapper;
    @Resource
    private UserRpc userRpc;

    @Override
    public Boolean saveMoment(SaveMomentCircleReq req) {

        return true;

    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeMoment(RemoveShareMomentReq req) {
        return true;
    }

    @Override
    public void incrReplyCount(Integer id, int count) {
        getBaseMapper().incrReplyCount(id, count);
    }

}
