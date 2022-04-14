package com.kurumi.controller;

import com.kurumi.domain.Video;
import com.kurumi.service.VideoService;
import com.kurumi.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/api/videos")
//@CrossOrigin("http://localhost:8081")
public class VideoController {

    @Autowired
    private VideoService videoService;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd/");

    @GetMapping
    public ResObj getVideos() {
        List<Video> videos = videoService.selectAll();
        return new ResObj(true, "获取视频列表成功", videos);
    }

    // 图片上传，并保存后返回图片路径
    @PostMapping("/img")
    public ResObj uploadImg(MultipartFile file, HttpServletRequest request) {
        ResObj result =  new ResObj();
        try {
            String url = FileUtils.uploadSimpleFile(file, request, "imgCovers");
            result.setFlag(true);
            result.setMsg("上传图片成功！");
            result.setData(url);
        } catch (IOException e) {
            result.setFlag(false);
            result.setData("error");
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @PostMapping
    public ResObj uploadVideo(MultipartFile file, HttpServletRequest request) {
        ResObj result = new ResObj();
        try {
            String url = FileUtils.uploadSimpleFile(file, request, "videos");
            result.setFlag(true);
            result.setMsg("上传视频成功!");
            result.setData(url);
        } catch(IOException e) {
            result.setFlag(false);
            result.setMsg("error");
            result.setData(e.getMessage());
        }
        return result;
    }

    @PostMapping("/info")
    public ResObj uploadVideoInfo(@RequestBody Video video) {
        System.out.println(video);
        boolean result = videoService.saveVideo(video);
        return new ResObj(true, result? "保存视频信息成功！": "上传视频失败，请重新上传！", result);
    }

    @GetMapping("/{id}")
    public ResObj selectById(@PathVariable Integer id) {
        Video video = videoService.selectById(id);
        return new ResObj(true, video == null ? "视频加载失败!": "视频加载成功!", video);
    }
}
