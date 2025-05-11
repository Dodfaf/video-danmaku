package com.videodanmaku.video.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.videodanmaku.common.entity.Result;
import com.videodanmaku.video.application.convert.VideoCollectionConverter;
import com.videodanmaku.video.application.dto.VideoCollectionFolderDTO;
import com.videodanmaku.video.application.dto.VideoCollectionRecordDTO;
import com.videodanmaku.video.domain.entity.VideoCollectionFolderBO;
import com.videodanmaku.video.domain.entity.VideoCollectionRecordBO;
import com.videodanmaku.video.domain.service.VideoCollectionDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 视频收藏控制器
 */
@RestController
@RequestMapping("videoCollection")
@Slf4j
public class VideoCollectionController {

    @Resource
    private VideoCollectionDomainService videoCollectionDomainService;

    /**
     * 创建收藏夹
     */
    @PostMapping("/createFolder")
    public Result<VideoCollectionFolderDTO> createFolder(@RequestBody VideoCollectionFolderDTO folderDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("创建收藏夹入参: {}", JSON.toJSONString(folderDTO));
            }
            Preconditions.checkArgument(!Objects.isNull(folderDTO), "参数不能为空");
            Preconditions.checkArgument(!Objects.isNull(folderDTO.getUserId()), "用户ID不能为空");
            Preconditions.checkArgument(!Objects.isNull(folderDTO.getFolderName()), "收藏夹名称不能为空");
            
            VideoCollectionFolderBO folderBO = VideoCollectionConverter.toBO(folderDTO);
            folderBO = videoCollectionDomainService.createFolder(folderBO);
            
            VideoCollectionFolderDTO result = VideoCollectionConverter.toDTO(folderBO);
            if (log.isInfoEnabled()) {
                log.info("创建收藏夹出参: {}", JSON.toJSONString(result));
            }
            return Result.ok(result);
        } catch (IllegalArgumentException e) {
            log.error("参数异常: {}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("创建收藏夹异常", e);
            return Result.fail("创建收藏夹失败");
        }
    }

    /**
     * 更新收藏夹
     */
    @PostMapping("/updateFolder")
    public Result<VideoCollectionFolderDTO> updateFolder(@RequestBody VideoCollectionFolderDTO folderDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("更新收藏夹入参: {}", JSON.toJSONString(folderDTO));
            }
            Preconditions.checkArgument(!Objects.isNull(folderDTO), "参数不能为空");
            Preconditions.checkArgument(!Objects.isNull(folderDTO.getId()), "收藏夹ID不能为空");
            Preconditions.checkArgument(!Objects.isNull(folderDTO.getUserId()), "用户ID不能为空");
            
            VideoCollectionFolderBO folderBO = VideoCollectionConverter.toBO(folderDTO);
            folderBO = videoCollectionDomainService.updateFolder(folderBO);
            
            VideoCollectionFolderDTO result = VideoCollectionConverter.toDTO(folderBO);
            if (log.isInfoEnabled()) {
                log.info("更新收藏夹出参: {}", JSON.toJSONString(result));
            }
            return Result.ok(result);
        } catch (IllegalArgumentException e) {
            log.error("参数异常: {}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("更新收藏夹异常", e);
            return Result.fail("更新收藏夹失败");
        }
    }

    /**
     * 删除收藏夹
     */
    @PostMapping("/deleteFolder")
    public Result<Boolean> deleteFolder(@RequestParam("folderId") Long folderId, @RequestParam("userId") Long userId) {
        try {
            if (log.isInfoEnabled()) {
                log.info("删除收藏夹入参: folderId={}, userId={}", folderId, userId);
            }
            Preconditions.checkArgument(!Objects.isNull(folderId), "收藏夹ID不能为空");
            Preconditions.checkArgument(!Objects.isNull(userId), "用户ID不能为空");
            
            boolean result = videoCollectionDomainService.deleteFolder(folderId, userId);
            if (log.isInfoEnabled()) {
                log.info("删除收藏夹出参: {}", result);
            }
            return Result.ok(result);
        } catch (IllegalArgumentException e) {
            log.error("参数异常: {}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("删除收藏夹异常", e);
            return Result.fail("删除收藏夹失败");
        }
    }

    /**
     * 获取收藏夹详情
     */
    @GetMapping("/getFolderDetail")
    public Result<VideoCollectionFolderDTO> getFolderDetail(@RequestParam("folderId") Long folderId) {
        try {
            if (log.isInfoEnabled()) {
                log.info("获取收藏夹详情入参: folderId={}", folderId);
            }
            Preconditions.checkArgument(!Objects.isNull(folderId), "收藏夹ID不能为空");
            
            VideoCollectionFolderBO folderBO = videoCollectionDomainService.getFolderDetail(folderId);
            if (folderBO == null) {
                return Result.fail("收藏夹不存在");
            }
            
            VideoCollectionFolderDTO result = VideoCollectionConverter.toDTO(folderBO);
            if (log.isInfoEnabled()) {
                log.info("获取收藏夹详情出参: {}", JSON.toJSONString(result));
            }
            return Result.ok(result);
        } catch (IllegalArgumentException e) {
            log.error("参数异常: {}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("获取收藏夹详情异常", e);
            return Result.fail("获取收藏夹详情失败");
        }
    }

    /**
     * 获取用户的收藏夹列表
     */
    @GetMapping("/getUserFolders")
    public Result<List<VideoCollectionFolderDTO>> getUserFolders(@RequestParam("userId") Long userId) {
        try {
            if (log.isInfoEnabled()) {
                log.info("获取用户收藏夹列表入参: userId={}", userId);
            }
            Preconditions.checkArgument(!Objects.isNull(userId), "用户ID不能为空");
            
            List<VideoCollectionFolderBO> folderBOList = videoCollectionDomainService.getUserFolders(userId);
            List<VideoCollectionFolderDTO> result = VideoCollectionConverter.toDTOList(folderBOList);
            
            if (log.isInfoEnabled()) {
                log.info("获取用户收藏夹列表出参: {}", JSON.toJSONString(result));
            }
            return Result.ok(result);
        } catch (IllegalArgumentException e) {
            log.error("参数异常: {}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("获取用户收藏夹列表异常", e);
            return Result.fail("获取用户收藏夹列表失败");
        }
    }

    /**
     * 收藏视频
     */
    @PostMapping("/collectVideo")
    public Result<VideoCollectionRecordDTO> collectVideo(@RequestBody VideoCollectionRecordDTO recordDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("收藏视频入参: {}", JSON.toJSONString(recordDTO));
            }
            Preconditions.checkArgument(!Objects.isNull(recordDTO), "参数不能为空");
            Preconditions.checkArgument(!Objects.isNull(recordDTO.getUserId()), "用户ID不能为空");
            Preconditions.checkArgument(!Objects.isNull(recordDTO.getFolderId()), "收藏夹ID不能为空");
            Preconditions.checkArgument(!Objects.isNull(recordDTO.getVideoId()), "视频ID不能为空");
            
            VideoCollectionRecordBO recordBO = VideoCollectionConverter.toBO(recordDTO);
            recordBO = videoCollectionDomainService.collectVideo(recordBO);
            
            VideoCollectionRecordDTO result = VideoCollectionConverter.toDTO(recordBO);
            if (log.isInfoEnabled()) {
                log.info("收藏视频出参: {}", JSON.toJSONString(result));
            }
            return Result.ok(result);
        } catch (IllegalArgumentException e) {
            log.error("参数异常: {}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("收藏视频异常", e);
            return Result.fail("收藏视频失败");
        }
    }

    /**
     * 取消收藏
     */
    @RequestMapping("/cancelCollection")
    public Result<Boolean> cancelCollection(@RequestParam("recordId") Long recordId, @RequestParam("userId") Long userId) {
        try {
            if (log.isInfoEnabled()) {
                log.info("取消收藏入参: recordId={}, userId={}", recordId, userId);
            }
            Preconditions.checkArgument(!Objects.isNull(recordId), "收藏记录ID不能为空");
            Preconditions.checkArgument(!Objects.isNull(userId), "用户ID不能为空");
            
            boolean result = videoCollectionDomainService.cancelCollection(recordId, userId);
            if (log.isInfoEnabled()) {
                log.info("取消收藏出参: {}", result);
            }
            return Result.ok(result);
        } catch (IllegalArgumentException e) {
            log.error("参数异常: {}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("取消收藏异常", e);
            return Result.fail("取消收藏失败");
        }
    }

    /**
     * 获取收藏夹中的视频ID列表
     */
    @GetMapping("/getFolderVideos")
    public Result<List<VideoCollectionRecordDTO>> getFolderVideos(@RequestParam("folderId") Long folderId) {
        try {
            if (log.isInfoEnabled()) {
                log.info("获取收藏夹视频列表入参: folderId={}", folderId);
            }
            Preconditions.checkArgument(!Objects.isNull(folderId), "收藏夹ID不能为空");
            
            List<VideoCollectionRecordBO> recordBOList = videoCollectionDomainService.getFolderVideos(folderId);
            List<VideoCollectionRecordDTO> result = VideoCollectionConverter.toRecordDTOList(recordBOList);
            
            if (log.isInfoEnabled()) {
                log.info("获取收藏夹视频列表出参: {}", JSON.toJSONString(result));
            }
            return Result.ok(result);
        } catch (IllegalArgumentException e) {
            log.error("参数异常: {}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("获取收藏夹视频列表异常", e);
            return Result.fail("获取收藏夹视频列表失败");
        }
    }

    /**
     * 检查视频是否已被用户收藏
     */
    @GetMapping("/isVideoCollected")
    public Result<Boolean> isVideoCollected(@RequestParam("videoId") Long videoId, @RequestParam("userId") Long userId) {
        try {
            if (log.isInfoEnabled()) {
                log.info("检查视频是否已收藏入参: videoId={}, userId={}", videoId, userId);
            }
            Preconditions.checkArgument(!Objects.isNull(videoId), "视频ID不能为空");
            Preconditions.checkArgument(!Objects.isNull(userId), "用户ID不能为空");
            
            boolean result = videoCollectionDomainService.isVideoCollected(videoId, userId);
            if (log.isInfoEnabled()) {
                log.info("检查视频是否已收藏出参: {}", result);
            }
            return Result.ok(result);
        } catch (IllegalArgumentException e) {
            log.error("参数异常: {}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("检查视频是否已收藏异常", e);
            return Result.fail("检查视频是否已收藏失败");
        }
    }

    /**
     * 获取视频收藏记录
     */
    @GetMapping("/getCollectionRecord")
    public Result<VideoCollectionRecordDTO> getCollectionRecord(@RequestParam("videoId") Long videoId, @RequestParam("userId") Long userId) {
        try {
            if (log.isInfoEnabled()) {
                log.info("获取视频收藏记录入参: videoId={}, userId={}", videoId, userId);
            }
            Preconditions.checkArgument(!Objects.isNull(videoId), "视频ID不能为空");
            Preconditions.checkArgument(!Objects.isNull(userId), "用户ID不能为空");
            
            // 获取收藏记录详情
            VideoCollectionRecordBO recordBO = videoCollectionDomainService.getCollectionRecord(videoId, userId);
            
            if (recordBO == null) {
                return Result.ok(null);
            }
            
            VideoCollectionRecordDTO result = VideoCollectionConverter.toDTO(recordBO);
            if (log.isInfoEnabled()) {
                log.info("获取视频收藏记录出参: {}", JSON.toJSONString(result));
            }
            return Result.ok(result);
        } catch (IllegalArgumentException e) {
            log.error("参数异常: {}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        } catch (Exception e) {
            log.error("获取视频收藏记录异常", e);
            return Result.fail("获取视频收藏记录失败");
        }
    }
}