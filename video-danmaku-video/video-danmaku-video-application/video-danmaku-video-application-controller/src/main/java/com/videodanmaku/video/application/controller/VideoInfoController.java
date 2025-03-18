package com.videodanmaku.video.application.controller;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.videodanmaku.common.entity.Result;
import com.videodanmaku.video.application.convert.VideoInfoDTOConverter;
import com.videodanmaku.video.application.dto.VideoInfoDTO;
import com.videodanmaku.video.domain.entity.VideoInfoBO;
import com.videodanmaku.video.domain.service.VideoInfoDomainService;
import com.videodanmaku.video.infra.basic.entity.VideoInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("video")
@Slf4j
public class VideoInfoController {

    @Resource
    private VideoInfoDomainService videoInfoDomainService;

    @RequestMapping("/getVideoInfo")
    public Result<VideoInfoDTO> getVideoInfoById(@RequestBody VideoInfoDTO videoInfoDTO){

        try {
            if (log.isInfoEnabled()){
                log.info("VideoInfoController.getVideoInfoById.dto:{}", JSON.toJSONString(videoInfoDTO));
            }
            Preconditions.checkNotNull(videoInfoDTO.getId(), "videoId不能为空！");
            VideoInfoBO videoInfoBO = VideoInfoDTOConverter.INSTANCE.convertDtoToBO(videoInfoDTO);
            VideoInfoBO videoInfoBo = videoInfoDomainService.getVideoInfoById(videoInfoBO);
            VideoInfoDTO info = VideoInfoDTOConverter.INSTANCE.convertBoToDTO(videoInfoBo);
            System.out.println(info.getDuration());
            return Result.ok(info);
        }catch (Exception e){
            log.error("VideoInfoController.getVideoInfoById.error:{}", e.getMessage(), e);
            return Result.fail("查询视频信息失败");
        }
    }
    @RequestMapping("/getUserVideoList")
    public Result<Page<VideoInfoDTO>> getUserVideoList(@RequestBody VideoInfoDTO videoInfoDTO){

        try {
            if (log.isInfoEnabled()){
                log.info("VideoInfoController.getUserVideoList.dto:{}", JSON.toJSONString(videoInfoDTO));
            }
            Preconditions.checkNotNull(videoInfoDTO.getUpId(), "upId不能为空！");
            VideoInfoBO videoInfoBO = VideoInfoDTOConverter.INSTANCE.convertDtoToBO(videoInfoDTO);
            Page<VideoInfoBO> videoInfoBoList = videoInfoDomainService.getUserVideoList(videoInfoBO, videoInfoDTO.getPageNo(), videoInfoDTO.getPageSize());
            return Result.ok(videoInfoBoList.map(VideoInfoDTOConverter.INSTANCE::convertBoToDTO));
        }catch (Exception e){
            log.error("VideoInfoController.getUserVideoList.error:{}", e.getMessage(), e);
            return Result.fail("查询用户视频列表失败");
        }
    }
    @RequestMapping("/getHomePageVideoList")
    public Result<List<VideoInfoDTO>> getHomePageVideoList(){

        try {
            return Result.ok(videoInfoDomainService.getHomePageVideoList());
        }catch (Exception e){
            log.error("VideoInfoController.getHomePageVideoList.error:{}", e.getMessage(), e);
            return Result.fail("获取首页视频列表失败");
        }
    }




    @RequestMapping("/update")
    public Result<Boolean> update(@RequestBody VideoInfoDTO videoInfoDTO){

        try {
            if (log.isInfoEnabled()){
                log.info("VideoInfoController.getVideoInfoById.dto:{}", JSON.toJSONString(videoInfoDTO));
            }
            Preconditions.checkNotNull(videoInfoDTO.getId(), "videoId不能为空！");
            VideoInfoBO videoInfoBO = VideoInfoDTOConverter.INSTANCE.convertDtoToBO(videoInfoDTO);

            return Result.ok(videoInfoDomainService.update(videoInfoBO));
        }catch (Exception e){
            log.error("VideoInfoController.getVideoInfoById.error:{}", e.getMessage(), e);
            return Result.fail("查询视频信息失败");
        }
    }

    @RequestMapping("/uploadVideo")
    public Result<Boolean> uploadVideo(@RequestBody VideoInfoDTO videoInfoDTO, MultipartFile file){

        try {
            if (log.isInfoEnabled()){
                log.info("VideoInfoController.uploadVideo.dto:{}", JSON.toJSONString(videoInfoDTO));
            }
//            Preconditions.checkNotNull(file, "上传视频不能为空！");
            videoInfoDTO.setStatus(0);
            videoInfoDTO.setIsDeleted(0);
            VideoInfoBO videoInfoBO = VideoInfoDTOConverter.INSTANCE.convertDtoToBO(videoInfoDTO);

            return Result.ok(videoInfoDomainService.uploadVideo(videoInfoBO));
        }catch (Exception e){
            log.error("VideoInfoController.uploadVideo.error:{}", e.getMessage(), e);
            return Result.fail("上传视频失败");
        }
    }

    @RequestMapping("/updateStatus")
    public Result<Boolean> updateStatus(@RequestBody VideoInfoDTO videoInfoDTO){

        try {
            if (log.isInfoEnabled()){
                log.info("VideoInfoController.updateStatus.dto:{}", JSON.toJSONString(videoInfoDTO));
            }
            Preconditions.checkNotNull(videoInfoDTO.getId(), "videoId不能为空！");
            Preconditions.checkNotNull(videoInfoDTO.getStatus(), "status不能为空！");
            VideoInfoBO videoInfoBO = VideoInfoDTOConverter.INSTANCE.convertDtoToBO(videoInfoDTO);

            return Result.ok(videoInfoDomainService.update(videoInfoBO));
        }catch (Exception e){
            log.error("VideoInfoController.getVideoInfoById.error:{}", e.getMessage(), e);
            return Result.fail("更新视频状态失败");
        }
    }

    @RequestMapping("/getStatusVideoList")
    public Result<Page<VideoInfoDTO>> getStatusVideoList(@RequestBody VideoInfoDTO videoInfoDTO){

        try {
            if (log.isInfoEnabled()){
                log.info("VideoInfoController.getNoStatusVideoList.dto:{}", JSON.toJSONString(videoInfoDTO));
            }
            Preconditions.checkNotNull(videoInfoDTO.getStatus(), "status不能为空！");
            VideoInfoBO videoInfoBO = VideoInfoDTOConverter.INSTANCE.convertDtoToBO(videoInfoDTO);
            Page<VideoInfoBO> videoInfoBoList = videoInfoDomainService.getNoStatusVideoList(videoInfoBO, videoInfoDTO.getPageNo(), videoInfoDTO.getPageSize());
            return Result.ok(videoInfoBoList.map(VideoInfoDTOConverter.INSTANCE::convertBoToDTO));
        }catch (Exception e){
            log.error("VideoInfoController.getUserVideoList.error:{}", e.getMessage(), e);
            return Result.fail("查询用户视频列表失败");
        }
    }

    /**
     * 根据视频标题模糊查询
     *
     * @param videoInfoDTO 包含查询标题的DTO对象
     * @return 查询结果
     */
    @RequestMapping("/searchByTitle")
    public Result<Page<VideoInfoDTO>> searchByTitle(@RequestBody VideoInfoDTO videoInfoDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("VideoInfoController.searchByTitle.dto:{}", JSON.toJSONString(videoInfoDTO));
            }
            Preconditions.checkNotNull(videoInfoDTO.getVideoTitle(), "视频标题不能为空！");
            Preconditions.checkNotNull(videoInfoDTO.getPageNo(), "页码不能为空！");
            Preconditions.checkNotNull(videoInfoDTO.getPageSize(), "每页大小不能为空！");
            
            Page<VideoInfoBO> videoInfoBoList = videoInfoDomainService.searchVideoByTitle(
                videoInfoDTO.getVideoTitle(), 
                videoInfoDTO.getPageNo(), 
                videoInfoDTO.getPageSize()
            );
            Page<VideoInfoDTO> result = videoInfoBoList.map(VideoInfoDTOConverter.INSTANCE::convertBoToDTO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("VideoInfoController.searchByTitle.error:{}", e.getMessage(), e);
            return Result.fail("视频标题模糊查询失败");
        }
    }

}
