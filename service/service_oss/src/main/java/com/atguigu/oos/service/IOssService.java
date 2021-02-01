package com.atguigu.oos.service;

import org.springframework.web.multipart.MultipartFile;

public interface IOssService {

    /**
     * 上传文件到oss
     * @param file
     * @return
     */
    String uploadFileAvatar(MultipartFile file);
}
