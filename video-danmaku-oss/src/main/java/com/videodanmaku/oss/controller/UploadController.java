package com.videodanmaku.oss.controller;

import com.google.common.collect.ImmutableMap;
import com.videodanmaku.common.entity.Result;
import io.minio.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class UploadController {

    @Autowired
    private MinioClient minioClient;

    @PostMapping("/api/upload")
    public Result handleFileUpload(
            @RequestParam("file") MultipartFile file,
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
                    .stream(file.getInputStream(), file.getSize(), -1)
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
                .expiry(1, TimeUnit.MINUTES)
                .build());
    }
}