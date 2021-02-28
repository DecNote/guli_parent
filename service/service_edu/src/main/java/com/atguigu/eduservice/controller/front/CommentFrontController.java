package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduComment;
import com.atguigu.eduservice.service.EduCommentService;
import com.atguigu.eduservice.client.UcenterClient;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自编写评论功能代码:
 * - CommentFrontController, UcenterClient, UcenterClientImpl,
 * - UcenterMemberController.getCommentMemberInfoById
 *
 * @author Dec
 */
@RestController
@RequestMapping("/eduservice/comment")
@CrossOrigin
public class CommentFrontController {

    @Autowired
    private EduCommentService commentService;
    @Autowired
    private UcenterClient ucenterClient;


    /**
     * 待完善方法名
     * 根据课程id查询评论列表
     *
     * @param page
     * @param limit
     * @param courseId
     * @return
     */
    @ApiOperation(value = "评论分页列表")
    @PostMapping("{page}/{limit}")
    public R getCommentPageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseId", value = "课程id值", required = true)
            @RequestBody String courseId) {
        Page<EduComment> pageParam = new Page<>(page, limit);


        System.out.println(courseId);


        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);

        commentService.page(pageParam, wrapper);
        List<EduComment> commentList = pageParam.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", pageParam.getCurrent());
        map.put("pages", pageParam.getPages());
        map.put("size", pageParam.getSize());
        map.put("total", pageParam.getTotal());
        map.put("hasNext", pageParam.hasNext());
        map.put("hasPrevious", pageParam.hasPrevious());
        return R.ok().data(map);
    }

    /**
     * 添加评论
     *
     * @param comment
     * @param request
     * @return
     */
    @ApiOperation(value = "添加评论")
    @PostMapping("auth/save")
    public R saveComment(@RequestBody EduComment comment, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)) {
            return R.error().code(28004).message("请登录");
        }

        R commentMemberInfo = ucenterClient.getCommentMemberInfoById(memberId);
        Map<String, Object> data = commentMemberInfo.getData();
        String nickName = (String) data.get("nickName");
        String avatar = (String) data.get("avatar");

        comment.setMemberId(memberId);
        comment.setNickname(nickName);
        comment.setAvatar(avatar);

        commentService.save(comment);
        return R.ok();
    }
}