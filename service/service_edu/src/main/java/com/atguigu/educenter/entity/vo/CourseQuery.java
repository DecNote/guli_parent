package com.atguigu.educenter.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Dec
 */
@Data
@ApiModel(value = "Course查询对象", description = "课程查询对象封装")
public class CourseQuery {

    @ApiModelProperty(value = "课程名称")
    String title;

    @ApiModelProperty(value = "课程状态")
    String status;
}
