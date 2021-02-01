package com.atguigu.oos.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oos.service.IOssService;
import com.atguigu.oos.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements IOssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            //获取上传文件的输入流
            InputStream inputStream = file.getInputStream();

            //构建日期路径：avatar/2019/02/26/文件名
            String filePath = new DateTime().toString("yyyy/MM/dd");

            String original = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            String fileType = original.substring(original.lastIndexOf("."));
            String newName = fileName + fileType;

            String fileUrl =  filePath + "/" + newName;

            //调用oss方法实现上传
            ossClient.putObject(bucketName, fileUrl, inputStream);

            //关闭OSSClient
            ossClient.shutdown();

            //把上传后文件路径返回
            //需要把上传到阿里云oss路径手动凭借出来
            // https://edu-jf.oss-cn-beijing.aliyuncs.com/%E7%BD%97%E6%96%AF.jpg
            String url = "http://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        } catch (Exception e) {
            return null;
        }
    }
}
