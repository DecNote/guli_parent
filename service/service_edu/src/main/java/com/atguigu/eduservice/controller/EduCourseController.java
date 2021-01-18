package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.entity.vo.CourseQuery;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Dec
 * @since 2021-01-09
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {


    @Autowired
    private EduCourseService courseService;


    /**
     * 添加课程信息，需要用到两张表，课程表(edu_course)和课程描述表(edu_course_description)
     *
     * @param courseInfoVo 表单提交信息
     */
    @PostMapping("addCourseInfo")
    public R addCourse(@RequestBody CourseInfoVo courseInfoVo) {
        // 返回添加之后课程id，为了后面添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }


    /**
     * 根据课程id查询课程基本信息
     *
     * @param courseId
     * @return
     */
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }


    /**
     * 修改课程信息
     *
     * @param courseInfoVo
     * @return
     */
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }


    /**
     * 根据课程id查询课程确认信息
     *
     * @param id
     * @return
     */
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return R.ok().data("publishCourse", coursePublishVo);
    }


    /**
     * 课程最终发布，修改课程状态
     *
     * @param id
     * @return
     */
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        // 设置课程发布状态
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return R.ok();
    }




    @GetMapping
    public R getCourseList() {
        List<EduCourse> list = courseService.list(null);
        return R.ok().data("list", list);
    }


    /**
     * 条件查询带分页
     * @param current
     * @param limit
     * @param courseQuery
     * @return
     */
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R pageCourseCondition(@PathVariable long current, @PathVariable long limit,
                                 @RequestBody(required = false) CourseQuery courseQuery) {

        // page对象
        Page<EduCourse> coursePage = new Page<>(current, limit);

        // 构建条件
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();

        // 多条件组合查询
        String status = courseQuery.getStatus();
        String title = courseQuery.getTitle();

        // 判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(status)) {
            queryWrapper.eq("status", status);
        }

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }


        // 排序
        queryWrapper.orderByDesc("gmt_create");

        // 调用方法实现条件查询分页
        courseService.page(coursePage, queryWrapper);

        // 总记录数
        long total = coursePage.getTotal();
        // 结果集
        List<EduCourse> records = coursePage.getRecords();

        return R.ok().data("total", total).data("rows", records);
    }


    /**
     * 删除课程，涉及多张表的操作
     *
     * @param courseId
     * @return
     */
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return R.ok();
    }


}

