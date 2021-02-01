package com.atguigu.eduService.service;

import com.atguigu.eduService.entity.EduTeacher;
import com.atguigu.eduService.entity.TeacherQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author jf
 * @since 2021-01-05
 */
public interface IEduTeacherService extends IService<EduTeacher> {
    void pageQuery(IPage<EduTeacher> pageParam, TeacherQuery teacherQuery);
}
