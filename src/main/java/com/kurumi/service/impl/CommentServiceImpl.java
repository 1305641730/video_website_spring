package com.kurumi.service.impl;

import com.kurumi.domain.Comment;
import com.kurumi.mapper.CommentMapper;
import com.kurumi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> selectAll() {
        List<Comment> comments = commentMapper.selectAll();
        return comments;
    }

    @Override
    public boolean saveComment(Comment comment) {
        return commentMapper.saveComment(comment) > 0;
    }
}
