package com.videodanmaku.video.application.controller;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.videodanmaku.common.entity.Result;
import com.videodanmaku.video.application.convert.VideoInfoDTOConverter;
import com.videodanmaku.video.application.dto.VideoInfoDTO;
import com.videodanmaku.video.domain.entity.VideoInfoBO;
import com.videodanmaku.video.domain.service.VideoInfoDomainService;
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
            return Result.ok(VideoInfoDTOConverter.INSTANCE.convertBoToDTO(videoInfoBo));
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
            VideoInfoBO videoInfoBO = VideoInfoDTOConverter.INSTANCE.convertDtoToBO(videoInfoDTO);

            return Result.ok(videoInfoDomainService.uploadVideo(videoInfoBO));
        }catch (Exception e){
            log.error("VideoInfoController.uploadVideo.error:{}", e.getMessage(), e);
            return Result.fail("上传视频失败");
        }
    }


}
