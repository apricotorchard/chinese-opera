package com.example.springboot.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.example.springboot.common.constant.HttpStatus;
import com.example.springboot.utils.ResponseResult;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    //阿里云的一些相关配置
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.oss.access-key-secret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;



    //上传的是音频或者是视频
    @PostMapping("/upload")
    public ResponseResult uploadFile(@RequestParam("file")MultipartFile file) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();

        try {
            ossClient.putObject(bucketName, fileName, file.getInputStream());
            String fileUrl = "https://" + bucketName + "." + endpoint + "/" + fileName;
            ossClient.shutdown();
            return new ResponseResult(HttpStatus.SUCCESS,"成功",fileUrl);
        } catch (IOException e) {
            return new ResponseResult(HttpStatus.ERROR,"失败");
        }
    }
}
