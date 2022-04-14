package com.kurumi.service;

import com.kurumi.domain.Video;

import java.util.List;

public interface VideoService {

    List<Video> selectAll();
    boolean saveVideo(Video video);
    Video selectById(Integer id);
}
