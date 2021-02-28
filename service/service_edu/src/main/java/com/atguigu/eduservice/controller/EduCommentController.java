package com.atguigu.eduservice.controller;


import com.atguigu.eduservice.service.EduCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author Dec
 * @since 2021-02-19
 */
@RestController
@RequestMapping("/eduservice/comment")
public class EduCommentController {

    @Autowired
    EduCommentService commentService;

}

