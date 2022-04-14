package com.kurumi.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Comment {

    private Integer id;
    // 回复用户是谁
    private Integer comUserId;
    // 哪个视频下的评论
    private Integer videoId;
    // 哪条评论下的评论
    private Integer commentId;
    private String content;
    private Date comDate;
    private Integer stars;
    // 哪一个用户的评论
    private User comUser;
}
