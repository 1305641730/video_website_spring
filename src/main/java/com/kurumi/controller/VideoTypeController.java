package com.kurumi.controller;

import com.kurumi.domain.VideoType;
import com.kurumi.mapper.VideoTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/videotypes")
public class VideoTypeController {

    @Autowired
    private VideoTypeMapper videoTypeMapper;

    @GetMapping
    public ResObj getVideoTypes() {
        List<VideoType> videoTypes = videoTypeMapper.selectAll();
        return new ResObj(true, "请求视频类型列表数据成功！", videoTypes);
    }
}
