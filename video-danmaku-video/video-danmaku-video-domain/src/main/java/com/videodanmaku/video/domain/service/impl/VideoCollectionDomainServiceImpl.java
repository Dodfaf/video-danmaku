package com.videodanmaku.video.domain.service.impl;

import com.videodanmaku.video.domain.entity.VideoCollectionFolderBO;
import com.videodanmaku.video.domain.entity.VideoCollectionRecordBO;
import com.videodanmaku.video.domain.service.VideoCollectionDomainService;
import com.videodanmaku.video.infra.basic.entity.VideoCollectionFolder;
import com.videodanmaku.video.infra.basic.entity.VideoCollectionRecord;
import com.videodanmaku.video.infra.basic.service.VideoCollectionFolderService;
import com.videodanmaku.video.infra.basic.service.VideoCollectionRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 视频收藏服务实现类
 */
@Service
public class VideoCollectionDomainServiceImpl implements VideoCollectionDomainService {

    @Resource
    private VideoCollectionFolderService videoCollectionFolderService;

    @Resource
    private VideoCollectionRecordService videoCollectionRecordService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VideoCollectionFolderBO createFolder(VideoCollectionFolderBO folderBO) {
        VideoCollectionFolder folder = new VideoCollectionFolder();
        BeanUtils.copyProperties(folderBO, folder);
        
        // 设置初始值
        folder.setVideoCount(0);
        folder.setCreateTime(new Date());
        folder.setUpdateTime(new Date());
        folder.setIsDeleted(0);
        
        // 保存收藏夹
        folder = videoCollectionFolderService.insert(folder);
        
        // 转换为业务对象返回
        VideoCollectionFolderBO result = new VideoCollectionFolderBO();
        BeanUtils.copyProperties(folder, result);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VideoCollectionFolderBO updateFolder(VideoCollectionFolderBO folderBO) {
        // 查询原收藏夹
        VideoCollectionFolder folder = videoCollectionFolderService.queryById(folderBO.getId());
        if (folder == null || !folder.getUserId().equals(folderBO.getUserId()) || folder.getIsDeleted() == 1) {
            throw new IllegalArgumentException("收藏夹不存在或无权限修改");
        }
        
        // 更新收藏夹信息
        BeanUtils.copyProperties(folderBO, folder, "videoCount", "createTime", "isDeleted");
        folder.setUpdateTime(new Date());
        
        // 保存更新
        folder = videoCollectionFolderService.update(folder);
        
        // 转换为业务对象返回
        VideoCollectionFolderBO result = new VideoCollectionFolderBO();
        BeanUtils.copyProperties(folder, result);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteFolder(Long folderId, Long userId) {
        // 查询收藏夹
        VideoCollectionFolder folder = videoCollectionFolderService.queryById(folderId);
        if (folder == null || !folder.getUserId().equals(userId) || folder.getIsDeleted() == 1) {
            throw new IllegalArgumentException("收藏夹不存在或无权限删除");
        }
        
        // 删除收藏夹
        return videoCollectionFolderService.deleteById(folderId);
    }

    @Override
    public VideoCollectionFolderBO getFolderDetail(Long folderId) {
        VideoCollectionFolder folder = videoCollectionFolderService.queryById(folderId);
        if (folder == null || folder.getIsDeleted() == 1) {
            return null;
        }
        
        VideoCollectionFolderBO result = new VideoCollectionFolderBO();
        BeanUtils.copyProperties(folder, result);
        return result;
    }

    @Override
    public List<VideoCollectionFolderBO> getUserFolders(Long userId) {
        List<VideoCollectionFolder> folders = videoCollectionFolderService.queryByUserId(userId);
        List<VideoCollectionFolderBO> result = new ArrayList<>(folders.size());
        
        for (VideoCollectionFolder folder : folders) {
            VideoCollectionFolderBO bo = new VideoCollectionFolderBO();
            BeanUtils.copyProperties(folder, bo);
            result.add(bo);
        }
        
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VideoCollectionRecordBO collectVideo(VideoCollectionRecordBO recordBO) {
        // 检查收藏夹是否存在
        VideoCollectionFolder folder = videoCollectionFolderService.queryById(recordBO.getFolderId());
        if (folder == null || !folder.getUserId().equals(recordBO.getUserId()) || folder.getIsDeleted() == 1) {
            throw new IllegalArgumentException("收藏夹不存在或无权限操作");
        }
        
        // 检查是否已收藏到该收藏夹
        VideoCollectionRecord existRecord = videoCollectionRecordService.queryByUserIdAndVideoId(recordBO.getUserId(), recordBO.getVideoId());
        if (existRecord != null && existRecord.getFolderId().equals(recordBO.getFolderId()) && existRecord.getIsDeleted() == 0) {
            throw new IllegalArgumentException("该视频已收藏到此收藏夹");
        }
        
        // 创建收藏记录
        VideoCollectionRecord record = new VideoCollectionRecord();
        BeanUtils.copyProperties(recordBO, record);
        record.setCreateTime(new Date());
        record.setIsDeleted(0);
        
        // 保存收藏记录
        record = videoCollectionRecordService.insert(record);
        
        // 更新收藏夹视频数量
        folder.setVideoCount(folder.getVideoCount() + 1);
        folder.setUpdateTime(new Date());
        videoCollectionFolderService.update(folder);
        
        // 转换为业务对象返回
        VideoCollectionRecordBO result = new VideoCollectionRecordBO();
        BeanUtils.copyProperties(record, result);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelCollection(Long recordId, Long userId) {
        // 查询收藏记录
        VideoCollectionRecord record = videoCollectionRecordService.queryById(recordId);
        if (record == null || !record.getUserId().equals(userId) || record.getIsDeleted() == 1) {
            throw new IllegalArgumentException("收藏记录不存在或无权限操作");
        }
        
        // 删除收藏记录
        boolean result = videoCollectionRecordService.deleteById(recordId);
        
        if (result) {
            // 更新收藏夹视频数量
            VideoCollectionFolder folder = videoCollectionFolderService.queryById(record.getFolderId());
            if (folder != null && folder.getVideoCount() > 0) {
                folder.setVideoCount(folder.getVideoCount() - 1);
                folder.setUpdateTime(new Date());
                videoCollectionFolderService.update(folder);
            }
        }
        
        return result;
    }

    @Override
    public List<Long> getFolderVideos(Long folderId) {
        return videoCollectionRecordService.queryVideoIdsByFolderId(folderId);
    }

    @Override
    public boolean isVideoCollected(Long videoId, Long userId) {
        VideoCollectionRecord record = videoCollectionRecordService.queryByUserIdAndVideoId(userId, videoId);
        return record != null;
    }
}