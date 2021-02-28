package com.atguigu.staservice.client;


import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Dec
 */
@Component
@FeignClient(name = "service-ucenter", fallback = UcenterClientImpl.class)

public interface UcenterClient {
    /**
     * 查询某一天注册人数
     *
     * @param day
     * @return
     */
    @GetMapping("/educenter/member/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day);
}
