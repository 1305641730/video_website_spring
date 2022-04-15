package com.kurumi.service;

import com.kurumi.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> selectAll();
    List<Comment> selectAllByVideoId(Integer id);
    boolean saveComment(Comment comment);
}
