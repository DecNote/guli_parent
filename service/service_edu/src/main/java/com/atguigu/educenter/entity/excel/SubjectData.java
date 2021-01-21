package com.atguigu.educenter.entity.excel;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Dec
 * <p>
 * 实体类和excel数据对应
 * <p>
 * excel表格示例：
 * 一级分类    二级分类
 * 后端开发    java
 * 后端开发    C++
 * 前端开发    vue
 * ...        ...
 */
@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
