package com.atguigu.eduservice.client;


import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 *  * 自编写评论功能代码:
 *  * - CommentFrontController, UcenterClient, UcenterClientImpl,
 *  * - UcenterMemberController.getCommentMemberInfoById
 *
 * @author Dec
 * <p>
 * name标记要调用的 生产者微服务 的name(spring.application.name)
 * fallback指定当调用发生错误之后要调用的实现类（熔断）
 */

@Component
@FeignClient(name = "service-ucenter", fallback = UcenterClientImpl.class)
public interface UcenterClient {


    /**
     * 根据用户id获取用户信息
     * @param memberId
     * @return
     */
    @GetMapping("getCommentMemberInfoById/{memberId}")
    public R getCommentMemberInfoById(@PathVariable("memberId") String memberId);
}