package com.atguigu.eduService.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduService.entity.EduTeacher;
import com.atguigu.eduService.entity.TeacherQuery;
import com.atguigu.eduService.service.IEduTeacherService;
import com.atguigu.servicebase.exceptionHandler.guliException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author jf
 * @since 2021-01-05
 */
@CrossOrigin
@RestController
@RequestMapping("/eduService/teacher")
@Api(tags = {"讲师模块管理"})
public class EduTeacherController {

    @Autowired
    private IEduTeacherService eduTeacherService;

    @GetMapping("findAll")
    @ApiOperation("查询所有讲师信息")
    public R findAllTeacher(){

        List<EduTeacher> eduTeacherList = eduTeacherService.list(null);
        return R.ok().data("items", eduTeacherList);
    }

    @CrossOrigin
    @DeleteMapping("delete/{id}")
    @ApiOperation("单个删除讲师信息")
    public R removeById(@PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("page/{current}/{limit}")
    @ApiOperation("分页查询")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){
        Page<EduTeacher> eduTeacherPage = new Page<>(current, limit);
        eduTeacherService.page(eduTeacherPage, null);

        long total = eduTeacherPage.getTotal();
        List<EduTeacher> records = eduTeacherPage.getRecords();
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);
    }

    @CrossOrigin
    @ApiOperation(value = "分页讲师列表(支持模糊查询)")
    @RequestMapping(method = RequestMethod.POST, value = "/pageTeacherCondition/{current}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long current,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @RequestBody(required = false)
            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                    TeacherQuery teacherQuery){

        Page<EduTeacher> pageParam = new Page<>(current, limit);

        eduTeacherService.pageQuery(pageParam, teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return  R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping(value = "addTeacher")
    public R save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){

        eduTeacherService.save(teacher);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("getById/{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("item", teacher);
    }

    @CrossOrigin
    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("updateById/{id}")
    public R updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,

            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){

        teacher.setId(id);
        eduTeacherService.updateById(teacher);
        return R.ok();
    }


}

