package com.kurumi.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileUtils {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd/");

    public static String uploadSimpleFile(MultipartFile file, HttpServletRequest request, String storageFolder) throws IOException {
        // 获取文件在服务器的存储位置 当前：../webapp/
        String realPath = request.getSession().getServletContext().getRealPath("/");
        // realPath = realPath.replace("target\\video_website_spring-1.0-SNAPSHOT", "src" + File.separator + "main" + File.separator + "webapp");
        System.out.println("webapp====>" + realPath);
        String originalName = file.getOriginalFilename();
        String format = simpleDateFormat.format(new Date());
        String storagePath = realPath + storageFolder + File.separator + format;

        File folder = new File(storagePath);
        if(!folder.exists()) {
            folder.mkdirs();
        }
        System.out.println(storagePath);

        // 获取文件后缀名
        String suffix = originalName.substring(originalName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + suffix;
        System.out.println(newFileName);

        file.transferTo(new File(folder, newFileName));
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + storageFolder + format + newFileName;
        return url;
    }
}
