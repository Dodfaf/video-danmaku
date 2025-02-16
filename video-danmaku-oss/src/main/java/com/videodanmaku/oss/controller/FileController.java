package com.videodanmaku.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.videodanmaku.oss.service.FileService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FileController {

    @Resource
    private FileService fileService;

    @NacosValue(value = "${storage.service.type}", autoRefreshed = true)
    private String storageType;
    @RequestMapping("testGetAll")
    public String testGetAll() throws Exception {
        List<String> allBucket = fileService.getAllBucket();
        return allBucket.get(0);
    }

    @RequestMapping("testtype")
    public String testType(){
        return storageType;
    }



}
