package com.atguigu.msmservice.service;

import java.util.Map;

/**
 * @author Dec
 */
public interface MsmService {
    boolean send(Map<String, Object> param, String phone);
}
