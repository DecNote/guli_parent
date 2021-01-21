package com.atguigu.educenter.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.educenter.entity.EduSubject;
import com.atguigu.educenter.entity.excel.SubjectData;
import com.atguigu.educenter.service.EduSubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;

/**
 * @author Dec
 * <p>
 * EasyExcel监听器
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    /**
     * 因为SubjectExcelListener不能交给spring进行管理，需要自己new，
     * 不能注入EduSubjectService进行数据库操作，
     * 所以加入成员变量EduSubjectService，并生成对应构造函数
     */
    public EduSubjectService subjectService;

    public SubjectExcelListener() {
    }


    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


    /**
     * 读取表头数据
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
    }

    /**
     * 读取excel内容，一行一行进行读取
     */
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new GuliException(20001, "文件数据为空");
        }

        // 1. 添加一级分类
        //  1.1 判断数据库中是否已存在当前要添加的一级分类名
        EduSubject oneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());

        // 1.2 如果未重复，则添加该一级分类
        if (oneSubject == null) {
            oneSubject = new EduSubject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(oneSubject);
        }

        //  1.2 获取一级分类id值
        String pid = oneSubject.getId();

        // 2. 添加二级分类
        //  2.1 判断二级分类是否重复
        EduSubject twoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);

        // 1.2 如果未重复，则添加该二级分类
        if (twoSubject == null) {
            twoSubject = new EduSubject();
            twoSubject.setParentId(pid);
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(twoSubject);
        }
    }


    /**
     * 判断当前要添加的一级分类名是否已经在数据库中存在，存在则获取该条记录，不存在则返回null
     */
    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }


    /**
     * 判断当前要添加的二级分类名是否已经在数据库中存在，存在则获取该条记录，不存在则返回null
     */
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }
}
