package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Dec
 */

// name标记要调用的 生产者微服务 的name(spring.application.name)
// fallback指定当调用发生错误之后要调用的实现类（熔断）
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {


    /**
     * 根据视频id删除阿里云视频
     * @PathVariable注解一定要指定参数名称，否则出错
     * @param id
     * @return
     */
    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable("id") String id);


    /**
     * 根据视频id删除多个阿里云视频
     * @param videoIdList
     * @return
     */
    @DeleteMapping("/eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
