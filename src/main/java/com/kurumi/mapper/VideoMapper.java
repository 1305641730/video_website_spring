package com.kurumi.mapper;

import com.kurumi.domain.Video;

import java.util.List;

public interface VideoMapper {

    List<Video> selectAll();
    int saveVideo(Video video);
    Video selectById(Integer id);
}
