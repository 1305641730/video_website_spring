package com.kurumi.service;

import com.kurumi.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> selectAll();
    boolean saveComment(Comment comment);
}
