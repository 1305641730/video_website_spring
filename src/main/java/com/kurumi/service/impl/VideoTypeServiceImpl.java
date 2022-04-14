package com.kurumi.service.impl;

import com.kurumi.domain.VideoType;
import com.kurumi.mapper.VideoTypeMapper;
import com.kurumi.service.VideoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoTypeServiceImpl implements VideoTypeService {

    @Autowired
    private VideoTypeMapper videoTypeMapper;

    @Override
    public List<VideoType> selectAll() {
        return videoTypeMapper.selectAll();
    }
}
