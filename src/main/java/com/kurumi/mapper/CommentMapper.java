package com.kurumi.mapper;

import com.kurumi.domain.Comment;

import java.util.List;

public interface CommentMapper {

    int saveComment(Comment comment);
    List<Comment> selectAll();
    List<Comment> selectAllByVideoId(Integer id);
}
