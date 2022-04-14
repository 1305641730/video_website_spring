package com.kurumi.service.impl;

import com.kurumi.domain.Video;
import com.kurumi.mapper.VideoMapper;
import com.kurumi.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> selectAll() {
        return videoMapper.selectAll();
    }

    @Override
    public boolean saveVideo(Video video) {
        return videoMapper.saveVideo(video) > 0;
    }

    @Override
    public Video selectById(Integer id) {
        return videoMapper.selectById(id);
    }
}
