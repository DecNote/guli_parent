package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.EduSubject;
import com.atguigu.educenter.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Dec
 * @since 2021-01-08
 */
public interface EduSubjectService extends IService<EduSubject> {

    public void saveSubject(MultipartFile file, EduSubjectService subjectService) ;

    List<OneSubject> getAllOneTwoSubject();
}
