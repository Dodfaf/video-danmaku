package com.videodanmaku.oss.adapter;

import com.videodanmaku.oss.entity.FileInfo;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public interface StorageAdapter {

     void createBucket(String bucket);

    /**
     * 上传文件
     */
    void uploadFile(MultipartFile uploadFile, String bucket, String objectName);

    /**
     * 列出所有桶
     */
    List<String> getAllBucket();

    /**
     * 列出当前桶及文件
     */
    List<FileInfo> getAllFile(String bucket);

    /**
     * 下载文件
     */
     InputStream downLoad(String bucket, String objectName);

    /**
     * 删除桶
     */
    void deleteBucket(String bucket);

    /**
     * 删除文件
     */
    void deleteObject(String bucket, String objectName);

    /**
     * 获取文件url
     */
     String getUrl(String bucket, String objectName);

}
