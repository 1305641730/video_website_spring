package com.kurumi.controller;

import com.kurumi.domain.Comment;
import com.kurumi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResObj saveComment() {
        List<Comment> comments = commentService.selectAll();
        return new ResObj(true, "请求评论数据成功！", comments);
    }

    @PostMapping
    public ResObj saveComment(@RequestBody Comment comment) {
        boolean result = commentService.saveComment(comment);
        return new ResObj(true, result? "评论成功！": "评论失败！请重新再试！", result);
    }
}
