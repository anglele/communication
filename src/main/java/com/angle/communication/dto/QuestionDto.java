package com.angle.communication.dto;

import com.angle.communication.Model.User;
import lombok.Data;

/**
 *fa贴列表和user得组合两个表相互关联得中和表
 */
@Data
public class QuestionDto {
    private Integer id;
    private  String title;
    private String description;
    private String tag;
    private long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
    private Integer TotalCount;
}
