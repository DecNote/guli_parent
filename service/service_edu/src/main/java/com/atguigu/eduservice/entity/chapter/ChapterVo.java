package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 章节内容
 *
 * @author Dec
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    /**
     * 小节
     */
    private List<VideoVo> children = new ArrayList<>();
}
