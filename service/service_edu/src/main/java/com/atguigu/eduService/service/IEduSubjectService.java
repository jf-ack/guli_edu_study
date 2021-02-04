package com.atguigu.eduService.service;

import com.atguigu.eduService.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author jf
 * @since 2021-02-04
 */
public interface IEduSubjectService extends IService<EduSubject> {

    //添加课程分类
    void saveSubject(MultipartFile file, IEduSubjectService eduSubjectService);
}
