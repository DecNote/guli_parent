package com.atguigu.msmservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonResponse;
import com.atguigu.msmservice.service.MsmService;
import com.atguigu.msmservice.utils.HttpUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dec
 */


@Service
public class MsmServiceImpl implements MsmService {


    /**
     * 发送短信的方法
     *
     * @param param
     * @param phone
     * @return
     */
    @Override
    public boolean send(Map<String, Object> param, String phone) {
        if (StringUtils.isEmpty(phone)) {
            return false;
        }

        String code = (String) param.get("code");

        String host = "https://smssend.shumaidata.com";
        String path = "/sms/send";
        String method = "POST";
        String appcode = "4785a45c90d844efbcd93bd86ada3926";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<>();
        querys.put("receive", phone);
        querys.put("tag", code);
        querys.put("templateId", "M09DD535F4");
        Map<String, String> bodys = new HashMap<>();


        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            Map map = (Map) JSON.parse(EntityUtils.toString(response.getEntity()));
            Boolean flag = (Boolean) map.get("success");
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
