package com.atguigu.eduService.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduService.service.IEduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author jf
 * @since 2021-02-04
 */
@RestController
@RequestMapping("/eduService/edu-subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private IEduSubjectService eduSubjectService;

    @PostMapping("addSubJect")
    public R addSubJect(MultipartFile file){
        eduSubjectService.saveSubject(file, eduSubjectService);
        return null;
    }

}

