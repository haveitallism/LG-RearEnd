package com.group8.controller;

import com.group8.dto.DownFile;
import com.group8.entity.LgScenicspot;
import com.group8.service.ScenicService;
import com.group8.dto.UploadImg;
import com.group8.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author acoffee
 * @create 2022-02-17 17:29
 */
@RequestMapping("/scenic")
@RestController
@CrossOrigin
public class ScenicController {

    @Autowired
    ScenicService scenicService;

    //上传图片
    @PostMapping("/uploadImg")
    public ResponseEntity<String> uploadImg(UploadImg uploadImg){
        String imgUrl = scenicService.uploadImg(uploadImg);
        return new ResponseEntity(200,"上传成功！",imgUrl);
    }

    //上传攻略
    @PostMapping("/uploadStrategy")
    public ResponseEntity<String> uploadStrategy(UploadImg uploadImg){
        String strategy = scenicService.uploadStrategy(uploadImg);
        return new ResponseEntity(200,"上传成功！",strategy);
    }

    //下载攻略
    @PostMapping("/downloadStrategy")
    public ResponseEntity<String> downloadStrategy(DownFile file){
        String strategy = scenicService.downloadStrategy(file);
        return new ResponseEntity<>(200,"下载成功！",strategy);
    }

}
