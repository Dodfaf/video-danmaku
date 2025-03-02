package com.videodanmaku.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.videodanmaku.common.entity.Result;
import com.videodanmaku.oss.service.FileService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FileController {

    @Resource
    private FileService fileService;

    @NacosValue(value = "${storage.service.type}", autoRefreshed = true)
    private String storageType;


    @RequestMapping("testtype")
    public String testType(){
        fileService.getUrl("music", "test");
        return storageType;
    }
    @RequestMapping("getUrl")
    public String getUrl(String bucket, String objectName){

        return fileService.getUrl(bucket, objectName);
    }

    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile uploadFile, String bucket, String objectName) throws Exception {
        String url = fileService.uploadFile(uploadFile, bucket, objectName);
        return Result.ok(url);
    }


}
