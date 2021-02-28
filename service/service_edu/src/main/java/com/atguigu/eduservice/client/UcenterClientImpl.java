package com.atguigu.eduservice.client;


import com.atguigu.commonutils.R;
import org.springframework.stereotype.Component;

/**
 *
 *  * 自编写评论功能代码:
 *  * - CommentFrontController, UcenterClient, UcenterClientImpl,
 *  * - UcenterMemberController.getCommentMemberInfoById
 *
 * UcenterClient接口的实现类，用来实现熔断机制。当接口的远程方法调用失败，会执行实现类方法
 *
 * @author Dec
 */

@Component
public class UcenterClientImpl implements UcenterClient{

    @Override
    public R getCommentMemberInfoById(String memberId) {
        return null;
    }
}
