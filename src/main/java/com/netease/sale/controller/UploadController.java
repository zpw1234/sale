package com.netease.sale.controller;

import com.netease.sale.utils.QiniuUtils;
import com.netease.sale.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author zpw
 * @Package com.zpw.blogapi.controller
 * @date 2022/2/25 17:03
 */
@RestController
@RequestMapping("upload")
public class UploadController {
    @Autowired
    private QiniuUtils qiniuUtils;
    private static final long MAX_SIZE = 1*1024*1024;
    @PostMapping
    public Result upload(@RequestParam("file")MultipartFile file){
        if (file.getSize() > MAX_SIZE){
            return Result.fail(999,"图片过大(不超过1M)");
        }
        //原始文件名称 比如说aa.png
        String originalFilename = file.getOriginalFilename();
        //唯一的文件名称
        String fileName =  UUID.randomUUID().toString()+"."+StringUtils.substringAfterLast(originalFilename, ".");
        //上传文件上传到那里呢？　七牛云　云服务器
        //降低我们自身应用服务器的带宽消耗
        boolean upload = qiniuUtils.upload(file, fileName);
        if (upload) {
            return Result.success(QiniuUtils.url+fileName);
        }
        return Result.fail(20001,"上传失败");
    }
}
