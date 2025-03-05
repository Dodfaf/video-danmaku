package com.videodanmaku.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.google.common.collect.ImmutableMap;
import com.videodanmaku.common.entity.Result;
import com.videodanmaku.oss.service.FileService;

import io.minio.*;
import io.minio.http.Method;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class FileController {

    @Resource
    private FileService fileService;
    @Resource
    private MinioClient minioClient;
    @NacosValue(value = "${storage.service.type}", autoRefreshed = true)
    private String storageType;


    @PostMapping("/uploadVideo")
    public Result handleFileUpload(
            @RequestParam("file")
            MultipartFile file,
            @RequestParam(value = "bucket", defaultValue = "video-bucket") String bucketName) {

        try {
            // 验证存储桶
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            // 生成唯一对象名称
            String originalFilename = file.getOriginalFilename();
            String objectName = UUID.randomUUID() +
                    (originalFilename != null ?
                            originalFilename.substring(originalFilename.lastIndexOf(".")) :
                            "");

            // 流式上传到 MinIO
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(file.getInputStream(), file.getSize(), 50 * 1024 * 1024)
                            .contentType(file.getContentType())
                            .build());

            return Result.ok(ImmutableMap.of(
                    "objectName", objectName,
                    "url", generatePresignedUrl(bucketName, objectName)
            ));
        } catch (Exception e) {
            return Result.fail("上传失败！"+ e.getMessage());
        }
    }

    private String generatePresignedUrl(String bucketName, String objectName)
            throws Exception {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(objectName)
                        .expiry(90, TimeUnit.DAYS)
                        .build());
    }








    @RequestMapping("test")
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
