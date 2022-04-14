package com.kurumi.mapper;

import com.kurumi.domain.Video;
import com.kurumi.domain.VideoType;

import java.util.List;

public interface VideoTypeMapper {

    List<VideoType> selectAll();
}
