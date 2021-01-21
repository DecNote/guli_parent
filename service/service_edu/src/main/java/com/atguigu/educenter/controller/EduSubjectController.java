package com.atguigu.educenter.controller;


import com.atguigu.commonutils.R;
import com.atguigu.educenter.entity.subject.OneSubject;
import com.atguigu.educenter.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Dec
 * @since 2021-01-08
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {


    @Autowired
    EduSubjectService subjectService;

    /**
     * 添加课程分类信息：根据上传过来的文件，读取文件内容添加进数据库
     */
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        // file表示上传过来excel文件
        subjectService.saveSubject(file, subjectService);
        return R.ok();
    }


    /**
     * 获取课程分类列表（树形）
     */
    @GetMapping("getAllSubject")
    public R getAllSubject() {
        //list集合泛型是一级分类
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return R.ok().data("list", list);
    }
}

