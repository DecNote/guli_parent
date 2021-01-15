package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Dec
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
