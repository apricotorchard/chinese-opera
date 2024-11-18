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
//        1.需要根据文件类型做判断

        //如果是单个文件
//        String picture = "chineseopera/picture/";
//        String audio = "chineseopera/audio/";
//        String video = "chineseopera/video/";
        //2.如果是文件夹
        //需要对里面的每个文件都进行导入





//        try {
//            ObjectMetadata metadata = new ObjectMetadata();
//            metadata.setContentType("audio/mpeg");
//            metadata.setContentDisposition("inline");
//
//
//            // 关闭OSSClient
//
//
//            return new ResponseResult<>(HttpStatus.SUCCESS,fileUrl);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseResult<>(HttpStatus.ERROR,"失败");
//        }

        //1.先判断上传过来的文件格式
        //            1)是不是一个文件夹
        //                文件夹的话：合集的id
        //
        //            2)单个文件，判断上传的是mp3，还是图片，还是单个的视频
//                        如果是视频，需要看视频的类型，不同的类型分配到不同的文件夹下
        //2.生成文件名
        //3.上传文件
        //4.获取文件url

}
