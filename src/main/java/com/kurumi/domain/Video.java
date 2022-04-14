package com.kurumi.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Video {

    private Integer id;
    private String title;
    private String desc;
    private Date uploadDate;
    private int stars;
    private int collections;
    private String videoUrl;
    private String videoCoverUrl;

    // 那个用户上传
    private User user;
    // 视频类型
    private VideoType type;
}
