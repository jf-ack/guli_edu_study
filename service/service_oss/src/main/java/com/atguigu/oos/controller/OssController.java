package com.atguigu.oos.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oos.service.IOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = {"OSS上传下载"})
@RestController()
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private IOssService ossService;

    @ApiOperation("上传头像")
    @PostMapping("/uploadOssFile")
    @CrossOrigin
    @ResponseBody
    public R uploadOssFile(@RequestParam MultipartFile file) throws IOException {
        //获取上传文件 MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);

    }
}
