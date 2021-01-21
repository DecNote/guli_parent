package com.atguigu.educenter.mapper;

import com.atguigu.educenter.entity.EduCourse;
import com.atguigu.educenter.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Dec
 * @since 2021-01-09
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public CoursePublishVo getPublishCourseInfo(String courseId);
}
