package com.atguigu.educenter.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * * 自编写评论功能代码:
 * * - CommentFrontController, UcenterClient, UcenterClientImpl,
 * * - UcenterMemberController.getCommentMemberInfoById
 *
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Dec
 * @since 2021-01-18
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {


    @Autowired
    private UcenterMemberService memberService;


    /**
     * 登录方法
     * <p>
     * member对象封装手机号和密码
     * 返回token值，使用jwt生成
     *
     * @param member
     * @return
     */
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member) {
        String token = memberService.login(member);
        return R.ok().data("token", token);
    }


    /**
     * 用户注册
     *
     * @param registerVo
     * @return
     */
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }


    /**
     * 根据token获取用户信息
     *
     * @param request
     * @return
     */
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        // 调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        // 查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("userInfo", member);
    }


    /**
     * 根据id获取评论用户信息
     *
     * @param memberId
     * @return
     */
    @GetMapping("getCommentMemberInfoById/{memberId}")
    public R getCommentMemberInfoById(@PathVariable("memberId") String memberId) {
        UcenterMember member = memberService.getById(memberId);
        String nickname = member.getNickname();
        String avatar = member.getAvatar();
        return R.ok().data("nickName", nickname).data("avatar", avatar);
    }

    /**
     * 查询某一天注册人数
     *
     * @param day
     * @return
     */
    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable("day") String day) {
        Integer count = memberService.countRegisterDay(day);
        System.out.println(count);
        return R.ok().data("countRegister", count);
    }
}

