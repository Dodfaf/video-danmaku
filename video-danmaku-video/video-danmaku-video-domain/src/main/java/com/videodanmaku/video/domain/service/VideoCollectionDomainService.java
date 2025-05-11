package com.videodanmaku.video.domain.service;

import com.videodanmaku.video.domain.entity.VideoCollectionFolderBO;
import com.videodanmaku.video.domain.entity.VideoCollectionRecordBO;

import java.util.List;

/**
 * 视频收藏服务接口
 */
public interface VideoCollectionDomainService {
    
    /**
     * 创建收藏夹
     * 
     * @param folderBO 收藏夹信息
     * @return 创建后的收藏夹
     */
    VideoCollectionFolderBO createFolder(VideoCollectionFolderBO folderBO);
    
    /**
     * 更新收藏夹
     * 
     * @param folderBO 收藏夹信息
     * @return 更新后的收藏夹
     */
    VideoCollectionFolderBO updateFolder(VideoCollectionFolderBO folderBO);
    
    /**
     * 删除收藏夹
     * 
     * @param folderId 收藏夹ID
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean deleteFolder(Long folderId, Long userId);
    
    /**
     * 获取收藏夹详情
     * 
     * @param folderId 收藏夹ID
     * @return 收藏夹信息
     */
    VideoCollectionFolderBO getFolderDetail(Long folderId);
    
    /**
     * 获取用户的收藏夹列表
     * 
     * @param userId 用户ID
     * @return 收藏夹列表
     */
    List<VideoCollectionFolderBO> getUserFolders(Long userId);
    
    /**
     * 收藏视频
     * 
     * @param recordBO 收藏记录
     * @return 收藏记录
     */
    VideoCollectionRecordBO collectVideo(VideoCollectionRecordBO recordBO);
    
    /**
     * 取消收藏
     * 
     * @param recordId 收藏记录ID
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean cancelCollection(Long recordId, Long userId);
    
    /**
     * 获取收藏夹中的视频列表
     * 
     * @param folderId 收藏夹ID
     * @return 视频ID列表
     */
    List<VideoCollectionRecordBO> getFolderVideos(Long folderId);
    
    /**
     * 检查视频是否已被用户收藏
     * 
     * @param videoId 视频ID
     * @param userId 用户ID
     * @return 是否已收藏
     */
    boolean isVideoCollected(Long videoId, Long userId);
    
    /**
     * 获取用户对视频的收藏记录
     * 
     * @param videoId 视频ID
     * @param userId 用户ID
     * @return 收藏记录
     */
    VideoCollectionRecordBO getCollectionRecord(Long videoId, Long userId);
}