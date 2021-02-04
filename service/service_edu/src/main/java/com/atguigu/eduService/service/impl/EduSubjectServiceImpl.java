package com.atguigu.eduService.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduService.entity.EduSubject;
import com.atguigu.eduService.entity.excel.SubjectData;
import com.atguigu.eduService.listener.SubjectExcelListener;
import com.atguigu.eduService.mapper.EduSubjectMapper;
import com.atguigu.eduService.service.IEduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author jf
 * @since 2021-02-04
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements IEduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, IEduSubjectService eduSubjectService) {

        try {
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }catch (Exception e) {

        }

    }
}
